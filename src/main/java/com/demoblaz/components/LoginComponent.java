package com.demoblaz.components;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginComponent<T> {
    private final GUIDriver driver;
    private final Class<T> currentPage;
    private final String alertloginSuccessMessage = "Sign up successful.";
    private final String alertLoginFailureMessage = "This user already exist.";

    /// locators
    private final By loginLabel = new By.ByCssSelector("#logInModalLabel");
    private final By usernameLabel = new By.ByCssSelector("label[for='log-name']");
    private final By userNameField = new By.ByCssSelector("#loginusername");
    private final By passwordLabel = new By.ByXPath("//div/label[.='Password:' and @for='log-pass']");
    private final By passwordField = new By.ByCssSelector("#loginpassword");
    private final By loginButton = new By.ByXPath("//button[contains(text(),'Log in')]");
    private final By closeButton = new By.ByXPath("(//button[contains(text(),'Close')])[3]");
    private final By xButton = new By.ByXPath("(//button/span[.='×'])[3]");
    private final By loginForm = new By.ByCssSelector("#logInModal .modal-content");

    public LoginComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(loginForm)) {
            LogsManager.info("Sign up form is displayed.");
        } else {
            LogsManager.error("Sign up form is not displayed.");
        }
    }

    /// actions
    @Step("enter username '{username}' in the login form")
    public LoginComponent<T> enterUserName(String username) {
        driver.element().type(userNameField, username);
        return this;
    }

    @Step("enter password '{password}' in the login form")
    public LoginComponent<T> enterPassword(String password) {
        driver.element().type(passwordField, password);
        return this;
    }

    @Step("click on 'Log in' button in the login form")
    public T clickLoginButton() {
        driver.element().click(loginButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'Close' button in the login form")
    public T clickCloseButton() {
        driver.element().click(closeButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'X' button in the login form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("accept the login alert ")
    public T acceptLogInAlert() {
        driver.alert().acceptAlert();
        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    /// validations
    private String getLogInAlertMessage() {
        return alertloginSuccessMessage;
    }
}
