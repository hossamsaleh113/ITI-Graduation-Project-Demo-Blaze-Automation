
package com.demoblaz.components;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.Objects;

public class SignUpComponent<T> {

    private final GUIDriver driver;
    private final Class<T> currentPage;
    private final String signupSuccessMessage = "Sign up successful.";
    private final String signupEmptyFieldsMessage = "Please fill out Username and Password.";
    private final String signupalreadyRegisteredUsereMessage = "This user already exist.";
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

    @Step("Fill in the signup form with username '{username}' and password '{password}'")
    public SignUpComponent<T> fillSignUpForm(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        return this;
    }

    @Step("click on 'Sign up' button in the signup form")
    public SignUpComponent<T> clickSignUpButton() {
        driver.element().click(signupButton);
        return this;
    }

    @Step("click on 'Close' button in the signup form")
    public T clickCloseButton() {
        driver.element().click(closeButton);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;

        }
    }

    @Step("click on 'X' button in the signup form")
    public T clickXButton() {
        driver.element().click(xButton);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }

    private String getSignUpAlertMessage() {
        return driver.alert().getAlertText();
    }

    @Step("accept the signup alert message on Failure")
    public SignUpComponent<T>  acceptSignUpAlertOnFailure() {
            driver.alert().acceptAlert();
            return this;
        }


    @Step("accept the signup alert message on success")
    public T acceptSignUpAlertOnSuccess() {
        driver.alert().acceptAlert();
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }
    // validation
    @Step("Verify signup label is displayed in the signup form")
    public SignUpComponent<T> isSignUpLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(signupLabel);
        String errorMessage = "Signup label is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify username label is displayed in the signup form")
    public SignUpComponent<T> isUserNameLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(userNameLabel);
        String errorMessage = "The username label is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify username field is displayed in the signup form")
    public SignUpComponent<T> isUserNameFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(userNameField);
        String errorMessage = "The username field is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify password label is displayed in the signup form")
    public SignUpComponent<T> isPasswordLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(passwordLabel);
        String errorMessage = "The password label is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify password field is displayed in the signup form")
    public SignUpComponent<T> isPasswordFieldDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(passwordField);
        String errorMessage = "The password field is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify signup button is displayed in the signup form")
    public SignUpComponent<T> isSignUpButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(signupButton);
        String errorMessage = "The signup button is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify close button is displayed in the signup form")
    public SignUpComponent<T> isCloseButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(closeButton);
        String errorMessage = "The close button is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify X button is displayed in the signup form")
    public SignUpComponent<T> isXButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(xButton);
        String errorMessage = "The X button is not displayed in the signup form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify signup success alert message is displayed as expected")
    public SignUpComponent<T> isSignUpSuccessAlertMessageDisplayedAsExpected() {
        String actualResult = getSignUpAlertMessage();
       String errorMessage = "Expected signup success alert: '" + signupSuccessMessage + "' but was: '" + actualResult + "'";
       driver.verification().assertEquals(actualResult, signupSuccessMessage, errorMessage);
        return this;
    }

    @Step("Verify signup empty fields alert message is displayed as expected")
    public SignUpComponent<T> isSignUpEmptyFieldsAlertMessageDisplayedAsExpected() {
        String actualResult = getSignUpAlertMessage();
        String errorMessage = "Expected signup empty-fields alert: '" + signupEmptyFieldsMessage + "' but was: '" + actualResult + "'";
        driver.verification().assertEquals(actualResult,signupEmptyFieldsMessage, errorMessage);
        return this;
    }

    @Step("Verify signup failure alert message is displayed as expected")
    public SignUpComponent<T> isSignUpRegisteredUserAlertMessageDisplayedAsExpected() {
        String actualResult = getSignUpAlertMessage();
        String errorMessage = "Expected signup registered-user alert: '" + signupalreadyRegisteredUsereMessage + "' but was: '" + actualResult + "'";
        driver.verification().assertEquals(actualResult, signupalreadyRegisteredUsereMessage, errorMessage);
        return this;
    }

}
