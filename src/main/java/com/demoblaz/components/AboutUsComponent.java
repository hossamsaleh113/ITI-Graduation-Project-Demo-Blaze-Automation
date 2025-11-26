package com.demoblaz.components;

import coreLibraries.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AboutUsComponent<T> {
    private final GUIDriver driver;
    private final Class<T> currentPage;

    //locators
    private final By aboutUsLabel = new By.ByCssSelector("#videoModalLabel");
    private final By videoFrame = new By.ByCssSelector("#example-video");
    private final By playButton = new By.ByCssSelector("#videoModal .modal-body .vjs-big-play-button");
    private final By closeButton = new By.ByXPath("(//button[contains(text(),'Close')])[4]");
    private final By xButton = new By.ByXPath("(//button/span[.='×'])[4]");
    private final By aboutusform = new By.ByCssSelector("#videoModal .modal-content");

    public AboutUsComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(aboutusform)) {
            coreLibraries.utils.logs.LogsManager.info("About Us form is displayed.");
        } else {
            coreLibraries.utils.logs.LogsManager.error("About Us form is not displayed.");
        }
    }

    /// actions
    @Step("click on 'Play' button in the About Us form")
    public AboutUsComponent<T> clickPlayButton() {
        driver.element().click(playButton);
        return this;
    }

    @Step("click on 'Close' button in the About Us form")
    public T clickCloseButton() {
        driver.element().click(closeButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            coreLibraries.utils.logs.LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'X' button in the About Us form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            coreLibraries.utils.logs.LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

}
