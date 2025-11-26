package coreLibraries.utils.actions;

import coreLibraries.utils.WaitManager;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AlertActions {
    private final WebDriver driver;
    private final WaitManager waitManager;

    public AlertActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    /**
     * Accepts the alert
     */
    public void acceptAlert() {
        waitManager.waitForAlert().until(ExpectedConditions.alertIsPresent());
        LogsManager.info("Accepts the alert");
        driver.switchTo().alert().accept();
    }

    /**
     * Dismisses the alert
     */
    public void dismissAlert() {
        waitManager.waitForAlert().until(ExpectedConditions.alertIsPresent());
        LogsManager.info("Dismiss the alert");
        driver.switchTo().alert().dismiss();

    }

    /**
     * Gets the text from the alert
     *
     * @return the text of the alert
     */
    public String getAlertText() {
        waitManager.waitForAlert().until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        LogsManager.info("Getting the alert text");
        String text = alert.getText();
        alert.accept();
        if (text != null) {
            LogsManager.info("Alert text: " + text);
            return text;
        } else {
            LogsManager.warn("Alert text is null");
            return null;
        }
    }

    /**
     * Sets the text in the alert
     *
     * @param text the text to set in the alert
     */
    public void sendTextToAlert(String text) {
        waitManager.waitForAlert().until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        LogsManager.info("Send text " + text + " to alert");
        alert.sendKeys(text);
        alert.accept();
    }
}
