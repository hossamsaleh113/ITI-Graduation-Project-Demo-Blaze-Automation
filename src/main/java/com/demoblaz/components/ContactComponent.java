package com.demoblaz.components;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactComponent<T> {
    private final GUIDriver driver;
    private final Class<T> currentPage;
    private final String alertContactSuccessMessage = "Thanks for the message!!";

    /// locators
    private final By contactLabel = new By.ByCssSelector("#exampleModalLabel");
    private final By contactEmailLabel = new By.ByXPath("//label[.='Contact Email:']");
    private final By contactEmailField = new By.ByCssSelector("#recipient-email");
    private final By contactNameLabel = new By.ByXPath("//label[.='Contact Name:']");
    private final By contactNameField = new By.ByCssSelector("#recipient-name");
    private final By messageLabel = new By.ByCssSelector("label[for='message-text']");
    private final By messageField = new By.ByCssSelector("#message-text");
    private final By sendMessageButton = new By.ByXPath("//button[contains(text(),'Send message')]");
    private final By closeButton = new By.ByXPath("(//button[contains(text(),'Close')])[1]");
    private final By xButton = new By.ByXPath("(//button/span[.='×'])[1]");
    private final By contactForm = new By.ByCssSelector("#exampleModal .modal-content");

    public ContactComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(contactForm)) {
            LogsManager.info("Contact form is displayed.");
        } else {
            LogsManager.error("Contact form is not displayed.");
        }


    }

    /// actions
    @Step("enter contact email '{email}' in the contact form")
    public ContactComponent<T> enterContactEmail(String email) {
        driver.element().type(contactEmailField, email);
        return this;
    }

    @Step("enter contact name '{name}' in the contact form")
    public ContactComponent<T> enterContactName(String name) {
        driver.element().type(contactNameField, name);
        return this;
    }

    @Step("enter message '{message}' in the contact form")
    public ContactComponent<T> enterMessage(String message) {
        driver.element().type(messageField, message);
        return this;
    }

    @Step("Fill in the contact form with email '{email}', name '{name}', and message '{message}'")
    public ContactComponent<T> fillContactForm(String email, String name, String message) {
        enterContactEmail(email);
        enterContactName(name);
        enterMessage(message);
        return this;
    }

    @Step("click on 'Send message' button in the contact form")
    public T clickSendMessageButton() {
        driver.element().click(sendMessageButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'Close' button in the contact form")
    public T clickCloseButton() {
        driver.element().click(closeButton);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'X' button in the contact form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("accept the contact alert and verify the alert message")
    public T acceptContactAlert() {
        driver.alert().getAlertText();
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    private String getContactUsAlertMessage() {
        return driver.alert().getAlertText();
    }

    //validation
    @Step("Verify contact form label is displayed")
    public ContactComponent<T> isContactLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(contactLabel);
        String errorMessage = "the contact form label is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify contact email label is displayed")
    public ContactComponent<T> isContactEmailLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(contactEmailLabel);
        String errorMessage = "the contact email label is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify contact email field is displayed")
    public ContactComponent<T> isContactEmailFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(contactEmailField);
        String errorMessage = "the contact email field is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify contact name label is displayed")
    public ContactComponent<T> isContactNameLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(contactNameLabel);
        String errorMessage = "the contact name label is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;

    }

    @Step("Verify contact name field is displayed")
    public ContactComponent<T> isContactNameFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(contactNameField);
        String errorMessage = "the contact name field is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify message label is displayed")
    public ContactComponent<T> isMessageLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(messageLabel);
        String errorMessage = "the message label is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify message field is displayed")
    public ContactComponent<T> isMessageFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(messageField);
        String errorMessage = "the message field is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify send message button is displayed")
    public ContactComponent<T> isSendMessageButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(sendMessageButton);
        String errorMessage = "the send message button is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify close button is displayed")
    public ContactComponent<T> isCloseButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(closeButton);
        String errorMessage = "the close button is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify 'X' button is displayed")
    public ContactComponent<T> isXButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(xButton);
        String errorMessage = "the X button is not displayed in contact form";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify contact alert message is as expected")
    public ContactComponent<T> isContactAlertMessageAsExpected() {
        String actualResult = getContactUsAlertMessage();
        String errorMessage = "the contact alert message is not as expected";
        driver.verification().assertEquals(actualResult, alertContactSuccessMessage, errorMessage);
        return this;
    }

}