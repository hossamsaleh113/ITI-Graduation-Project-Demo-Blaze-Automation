package com.demoblaz.components;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpComponent<T> {

    private final GUIDriver driver;
    private final Class<T> currentPage;
    private final String alertSignupSuccessMessage = "Sign up successful.";
    private final String alertSignupFailureMessage = "This user already exist.";
    /// locators

    private final By signupLabel = new By.ByCssSelector("#signInModalLabel");
    private final By userNameLabel = new By.ByCssSelector("label[for='sign-username']");
    private final By userNameField = new By.ByCssSelector("#sign-username");
    private final By passwordLabel = new By.ByCssSelector("label[for='sign-password']");
    private final By passwordField = new By.ByCssSelector("#sign-password");
    private final By signupButton = new By.ByXPath("//button[contains(text(),'Sign up')]");
    private final By closeButton = new By.ByXPath("(//button[contains(text(),'Close')])[2]");
    private final By xButton = new By.ByXPath("(//button/span[.='×'])[2]");
    private final By signupForm = new By.ByCssSelector("#signInModal .modal-content");

    public SignUpComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(signupForm)) {
            LogsManager.info("Sign up form is displayed.");
        } else {
            LogsManager.error("Sign up form is not displayed.");
        }

    }

    /// actions

    @Step("enter username '{username}' in the signup form")
    public SignUpComponent<T> enterUserName(String username) {
        driver.element().type(userNameField, username);
        return this;
    }

    @Step("enter password '{password}' in the signup form")
    public SignUpComponent<T> enterPassword(String password) {
        driver.element().type(passwordField, password);
        return this;
    }

    @Step("click on 'Sign up' button in the signup form")
    public T clickSignUpButton() {
        driver.element().click(signupButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;

        }
    }

    @Step("click on 'Close' button in the signup form")
    public T clickCloseButton() {
        driver.element().click(closeButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;

        }
    }

    @Step("click on 'X' button in the signup form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("accept the signup alert")
    public T acceptSignUpAlert() {
        driver.alert().acceptAlert();
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    /// validations



    private String getSignUpAlertMessage() {
        return driver.alert().getAlertText();
    }
}
