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
    private final By contactlabel = new By.ByCssSelector("#exampleModalLabel");
    private final By contactemailLabel = new By.ByXPath("//label[.='Contact Email:']");
    private final By contactemailField = new By.ByCssSelector("#recipient-email");
    private final By contactnameLabel = new By.ByXPath("//label[.='Contact Name:']");
    private final By contactnameField = new By.ByCssSelector("#recipient-name");
    private final By messageLabel = new By.ByCssSelector("label[for='message-text']");
    private final By messageField = new By.ByCssSelector("#message-text");
    private final By sendmessageButton = new By.ByXPath("//button[contains(text(),'Send message')]");
    private final By closebutton = new By.ByXPath("(//button[contains(text(),'Close')])[1]");
    private final By xButton = new By.ByXPath("(//button/span[.='×'])[1]");
    private final By contactform = new By.ByCssSelector("#exampleModal .modal-content");

    public ContactComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(contactform)) {
            LogsManager.info("Contact form is displayed.");
        } else {
            LogsManager.error("Contact form is not displayed.");
        }


    }

    /// actions
    @Step("enter contact email '{email}' in the contact form")
    public ContactComponent<T> enterContactEmail(String email) {
        driver.element().type(contactemailField, email);
        return this;
    }

    @Step("enter contact name '{name}' in the contact form")
    public ContactComponent<T> enterContactName(String name) {
        driver.element().type(contactnameField, name);
        return this;
    }

    @Step("enter message '{message}' in the contact form")
    public ContactComponent<T> enterMessage(String message) {
        driver.element().type(messageField, message);
        return this;
    }

    @Step("click on 'Send message' button in the contact form")
    public T clickSendMessageButton() {
        driver.element().click(sendmessageButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'Close' button in the contact form")
    public T clickCloseButton() {
        driver.element().click(closebutton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'X' button in the contact form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("accept the contact alert and verify the alert message")
    public T acceptContactAlert() {
        driver.alert().getAlertText();
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    //validation
    private String getContactUsAlertMessage() {
        return driver.alert().getAlertText();
    }
}