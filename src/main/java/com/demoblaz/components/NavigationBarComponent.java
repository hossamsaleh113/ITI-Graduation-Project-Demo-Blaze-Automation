package com.demoblaz.components;

import com.demoblaz.pages.CartPage;
import com.demoblaz.pages.HomePage;
import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.dataReader.PropertyReader;

import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent<T> {
    private final GUIDriver driver;
    private final Class<T> currentPage;
    /// Locators
    private final By homeLink = new By.ByXPath("//a[contains(text(),'Home ')]");
    private final By contactLink = new By.ByXPath("//a[contains(text(),'Contact')]");
    private final By aboutUsLink = new By.ByXPath("//a[contains(text(),'About us')]");
    private final By cartLink = new By.ByXPath("//a[contains(text(),'Cart')]");
    private final By loginLink = new By.ByXPath("//a[contains(text(),'Log in')]");
    private final By signUpLink = new By.ByXPath("//a[contains(text(),'Sign up')]");
    private final By logOutLink = new By.ByXPath("//a[contains(text(),'Log out')]");
    private final By productStoreLink = new By.ByXPath("//a[@class='navbar-brand' and contains(text(),'PRODUCT STORE')]");
    private final By navBar = new By.ByCssSelector(".navbar-collapse#navbarExample");
    private final By welcomeUserLabel = new By.ByCssSelector("#nameofuser");

    /// Constructor
    public NavigationBarComponent(GUIDriver driver, Class<T> currentPage) {
        this.driver = driver;
        this.currentPage = currentPage;
        if (driver.element().isElementDisplayed(navBar)) {
            LogsManager.info("Navigation bar is displayed.");
        } else {
            LogsManager.error("Navigation bar is not displayed.");
        }
    }

    /// Actions


    @Step("Navigate to the base URL of the web application")
    public NavigationBarComponent<T> navigateToBaseURL() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on 'Home' link in the navigation bar")
    public HomePage clickHomeLink() {
        driver.element().click(homeLink);
        return new HomePage(driver);
    }

    @Step("Click on 'Contact' link in the navigation bar")
    public ContactComponent<T> clickContactLink() {
        driver.element().click(contactLink);
        return new ContactComponent<>(driver, currentPage);
    }

    @Step("Click on 'About us' link in the navigation bar")
    public AboutUsComponent<T> clickAboutUsLink() {
        driver.element().click(aboutUsLink);
        return new AboutUsComponent<>(driver, currentPage);
    }

    @Step("Click on 'Cart' link in the navigation bar")
    public CartPage clickCartLink() {
        driver.element().click(cartLink);
        return new CartPage(driver);
    }

    @Step("Click on 'Log in' link in the navigation bar")
    public LoginComponent<T> clickLoginLink() {
        driver.element().click(loginLink);
        return new LoginComponent<>(driver, currentPage);
    }

    @Step("Click on 'Sign up' link in the navigation bar")
    public SignUpComponent<T> clickSignUpLink() {
        driver.element().click(signUpLink);
        return new SignUpComponent<>(driver, currentPage);
    }

    @Step("Click on 'Log out' link in the navigation bar")
    public HomePage clickLogOutLink() {
        driver.element().click(logOutLink);
        return new HomePage(driver);
    }

    @Step("Click on 'PRODUCT STORE' link in the navigation bar")
    public HomePage clickProductStoreLink() {
        driver.element().click(productStoreLink);
        return new HomePage(driver);
    }

    private String getWelcomeUsernameText() {
        if (driver.element().isElementDisplayed(welcomeUserLabel)) {
            return driver.element().getText(welcomeUserLabel);
        } else {
            LogsManager.error("Welcome user label is not displayed.");
            return null;
        }
    }
//validations


    @Step("Verify the welcome contains the right user: '{expectedUsername}'")
    public T validateLoggedInUsername(String expectedUsername) {
        String actualUsernameText = getWelcomeUsernameText();
        boolean contains = actualUsernameText != null && actualUsernameText.contains(expectedUsername);
        String errorMessage = "Expected welcome username message to be : " + "Welcome " + expectedUsername + " but was: " + actualUsernameText;
        driver.verification().assertTrue(contains, errorMessage);
        try {
            return currentPage.getDeclaredConstructor(GUIDriver.class).newInstance(driver);
        } catch (Exception e) {
            LogsManager.error("Couldn't create new instance of the current page class. Error: ", e.getMessage());
            return null;
        }
    }
}
