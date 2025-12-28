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
    private final By muteButton = new By.ByCssSelector("button[title='Mute']");
    private final By unmuteButton = new By.ByCssSelector("button[title='Unmute']");
    private final By playButtonInsideVideo = new By.ByCssSelector("button[title='Play']");
    private final By pauseButtonInsideVideo = new By.ByCssSelector("button[title='Pause']");
    private final By closeButton = new By.ByXPath("(//button[contains(text(),'Close')])[4]");
    private final By xButton = new By.ByXPath("(//button/span[.='×'])[4]");
    private final By aboutUsForm = new By.ByCssSelector("#videoModal .modal-content");

    public AboutUsComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(aboutUsForm)) {
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
@Step("click on 'Mute' button in the About Us form")
    public AboutUsComponent<T> clickMuteButton() {
        driver.element().click(muteButton);
        return this;
    }
    @Step("click on 'Unmute' button in the About Us form")
    public AboutUsComponent<T> clickUnmuteButton() {
        driver.element().click(unmuteButton);
        return this;
    }
    @Step("click on 'Pause' button inside the video in the About Us form")
    public AboutUsComponent<T> clickPauseButtonInsideVideo() {
        driver.element().click(pauseButtonInsideVideo);
        return this;
    }
    @Step("click on 'Play' button inside the video in the About Us form")
    public AboutUsComponent<T> clickPlayButtonInsideVideo() {
        driver.element().click(playButtonInsideVideo);
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

    //validation
    @Step("Verify if About Us label is displayed in the About Us form")
    public AboutUsComponent<T> isAboutUsLabelDisplayed() {
        boolean actualResult= driver.element().isElementDisplayed(aboutUsLabel);
        String errorMessage="The about us label is not displayed in about us form";
        driver.softValidation().assertTrue(actualResult,errorMessage);
        return this;
    }

    @Step("Verify if video frame is displayed in the About Us form")
    public AboutUsComponent<T> isVideoFrameDisplayed() {
        boolean actualResult=driver.element().isElementDisplayed(videoFrame);
        String errorMessage="The video frame is not displayed in about us form";
        driver.softValidation().assertTrue(actualResult,errorMessage);
        return this;
    }

    @Step("Verify if close button is displayed in the About Us form")
    public AboutUsComponent<T> isCloseButtonDisplayed() {
        boolean actualResult=driver.element().isElementDisplayed(closeButton);
        String errorMessage="The close button is not displayed in about us form";
        driver.softValidation().assertTrue(actualResult,errorMessage);
        return this;
    }

    @Step("Verify if X button is displayed in the About Us form")
    public AboutUsComponent<T> isXButtonDisplayed() {
        boolean actualResult=driver.element().isElementDisplayed(xButton);
        String errorMessage="The X button is not displayed in about us form";
        driver.softValidation().assertTrue(actualResult,errorMessage);
        return this;
    }

    @Step("Verify if play button is displayed in the About Us form")
    public AboutUsComponent<T> isPlayButtonDisplayed() {
        boolean actualResult=driver.element().isElementDisplayed(playButton);
        String errorMessage="The play button is not displayed in about us form";
        driver.softValidation().assertTrue(actualResult,errorMessage);
        return this;
    }
}
