package com.demoblaz.components;

import com.demoblaz.pages.HomePage;
import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginComponent<T> {
    private final GUIDriver driver;
    private final Class<T> currentPage;
    private final String emptyFieldsMessage = "Please fill out Username and Password.";
    private final String wrongPasswordMessage = "Wrong password.";
    private final String unregisteredUserMessage = "User does not exist.";
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
    private final By logOutButton = new By.ByXPath("//a[contains(text(),'Log out')]");


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

    @Step("Fill in the login form with username '{username}' and password '{password}'")
    public LoginComponent<T> fillLoginForm(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return this;
    }

    @Step("click on 'Log in' button in the login form")
    public T clickLoginButtonOnSuccess()  {
        try {
            driver.element().click(loginButton);
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
        }

    public LoginComponent<T> clickLoginButtonOnFailure() {
        driver.element().click(loginButton);
        return this;
    }



    @Step("click on 'Close' button in the login form")
    public T clickCloseButton() {
        driver.element().click(closeButton);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("click on 'X' button in the login form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    @Step("accept the login alert ")
    public LoginComponent<T> acceptLogInAlert() {
        driver.alert().acceptAlert();
        return this;
    }

    private String getLogInAlertMessage() {
        return driver.alert().getAlertText();
    }



    /// validations
    @Step("Verify login label is displayed in the login form")
    public LoginComponent<T> isLoginLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(loginLabel);
        String errorMessage = "The login label is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify username label is displayed in the login form")
    public LoginComponent<T> isUsernameLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(usernameLabel);
        String errorMessage = "The username label is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult,errorMessage);
        return this;
    }

    @Step("Verify username field is displayed in the login form")
    public LoginComponent<T> isUserNameFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(userNameField);
        String errorMessage = "The username field is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify password label is displayed in the login form")
    public LoginComponent<T> isPasswordLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(passwordLabel);
        String errorMessage = "The password label is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify password field is displayed in the login form")
    public LoginComponent<T> isPasswordFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(passwordField);
        String errorMessage = "The password field is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify login button is displayed in the login form")
    public LoginComponent<T> isLoginButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(loginButton);
        String errorMessage = "The login button is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify close button is displayed in the login form")
    public LoginComponent<T> isCloseButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(closeButton);
        String errorMessage = "The close button is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify X button is displayed in the login form")
    public LoginComponent<T> isXButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(xButton);
        String errorMessage = "The X button is not displayed in the login form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }



    @Step("Verify login failure alert message is correct for empty fields")
    public LoginComponent<T> validateMessage_ForEmptyFields() {
        String actualAlertMessage = getLogInAlertMessage();
        String errorMessage = "Expected alert for empty fields: '" + emptyFieldsMessage + "' but was: '" + actualAlertMessage + "'";
        driver.verification().assertEquals(actualAlertMessage, emptyFieldsMessage, errorMessage);
        return this;
    }

    @Step("Verify login failure alert message is correct for wrong password")
    public LoginComponent<T> validateMessage_ForWrongPassword() {
        String actualAlertMessage = getLogInAlertMessage();
        String errorMessage = "Expected alert for wrong password: '" + wrongPasswordMessage + "' but was: '" + actualAlertMessage + "'";
        driver.verification().assertEquals(actualAlertMessage, wrongPasswordMessage, errorMessage);
        return this;
    }

    @Step("Verify login failure alert message is correct for non-existing user")
    public LoginComponent<T> validateMessage_ForUnregisteredUser() {
        String actualAlertMessage = getLogInAlertMessage();
        String errorMessage = "Expected alert for unregistered user: '" + unregisteredUserMessage + "' but was: '" + actualAlertMessage + "'";
        driver.verification().assertEquals(actualAlertMessage, unregisteredUserMessage, errorMessage);
        return this;
    }


}

