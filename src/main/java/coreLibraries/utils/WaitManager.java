package coreLibraries.utils;

import coreLibraries.utils.dataReader.PropertyReader;
import coreLibraries.utils.logs.LogsManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class WaitManager {
    private WebDriver driver;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("DEFAULT_WAIT"))))
                .pollingEvery(Duration.ofMillis(Long.parseLong(PropertyReader.getProperty("DEFAULT_POLLING_IN_MILLISECONDS"))))
                .ignoreAll(getExceptions());

    }


    public ArrayList<Class<? extends Exception>> getExceptions() {
        ArrayList<Class<? extends Exception>> exceptions = new ArrayList<>();
        exceptions.add(NoSuchElementException.class);
        exceptions.add(StaleElementReferenceException.class);
        exceptions.add(ElementNotInteractableException.class);
        exceptions.add(ElementClickInterceptedException.class);
        exceptions.add(NullPointerException.class);
        exceptions.add(NoAlertPresentException.class);
        return exceptions;
    }


    public FluentWait<WebDriver> waitForAlert(){
        LogsManager.info("Waiting for alert to be present");
          return new FluentWait<>(driver)
                 .withTimeout(Duration.ofSeconds(Long.parseLong(PropertyReader.getProperty("DEFAULT_WAIT"))))
                 .pollingEvery(Duration.ofMillis(Long.parseLong(PropertyReader.getProperty("DEFAULT_POLLING_IN_MILLISECONDS"))))
                  .ignoreAll(getExceptions());

    }



}
