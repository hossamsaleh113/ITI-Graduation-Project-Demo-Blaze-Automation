package com.demoblaz.tests.negative;

import com.demoblaz.pages.HomePage;
import com.demoblaz.tests.base.BaseTest;
import coreLibraries.utils.TimeManager;
import org.testng.annotations.Test;

public class NegativeTests extends BaseTest {
    //test user already exists during signup
    @Test
    public void testUserAlreadyExistsDuringSignUp() {
        String username = "user" + TimeManager.getTimestamp();
        String password = "pass" + TimeManager.getTimestamp();
        new HomePage(driver).
                NavBar().
                clickSignUpLink().
                fillSignUpForm(username, password).
                clickSignUpButton().
                isSignUpSuccessAlertMessageDisplayedAsExpected().
                acceptSignUpAlertOnSuccess().
                NavBar().
                clickSignUpLink().
                fillSignUpForm(username, password).
                clickSignUpButton().
                isSignUpRegisteredUserAlertMessageDisplayedAsExpected().
                acceptSignUpAlertOnFailure().
                clickCloseButton();

    }

    //test login with unregistered user
    @Test
    public void testLoginWithUnregisteredUser() {
        String username = "user" + TimeManager.getTimestamp();
        String password = "pass" + TimeManager.getTimestamp();
        new HomePage(driver).
                NavBar().
                clickLoginLink().
                fillLoginForm(username, password).
                clickLoginButtonOnFailure().
                validateMessage_ForUnregisteredUser().
                acceptLogInAlert().
                clickCloseButton();
    }

    //test login with wrong password
    @Test
    public void testLoginWithWrongPassword() {
        String username = "user" + TimeManager.getTimestamp();
        String password = "pass" + TimeManager.getTimestamp();
        String wrongPassword = "wrongPass" + TimeManager.getTimestamp();
        new HomePage(driver).
                NavBar().
                clickSignUpLink().
                fillSignUpForm(username, password).
                clickSignUpButton().
                isSignUpSuccessAlertMessageDisplayedAsExpected().
                acceptSignUpAlertOnSuccess().
                NavBar().
                clickLoginLink().
                fillLoginForm(username, wrongPassword).
                clickLoginButtonOnFailure().
                validateMessage_ForWrongPassword().
                acceptLogInAlert().
                clickCloseButton();
    }

    //test login with empty fields
    @Test
    public void testLoginWithEmptyFields() {
        new HomePage(driver).
                NavBar().
                clickLoginLink().
                clickLoginButtonOnFailure().
                validateMessage_ForEmptyFields().
                acceptLogInAlert().
                clickCloseButton();
    }

    //test signup with empty fields
    @Test
    public void testSignUpWithEmptyFields() {
        new HomePage(driver).
                NavBar().
                clickSignUpLink().
                clickSignUpButton().
                isSignUpEmptyFieldsAlertMessageDisplayedAsExpected().
                acceptSignUpAlertOnFailure().
                clickCloseButton();
    }

    //fill out order form with empty fields
    @Test
    public void testPlaceOrderWithEmptyFields() {
        new HomePage(driver).
                clickOnItem(testData.getJsonData("Products.item1.name")).
                addItemToCart().
                acceptAlert().
                NavBar().
                clickCartLink().
                clickPlaceOrderButton().
                waitForOrderFormToBeReady().
                clickPurchaseButton().
                verifyOrderFailureAlertMessage().
                acceptAlert().
                clickXButton();
    }
}
