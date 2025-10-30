package demoblaz.pages.components;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.dataReader.PropertyReader;
import demoblaz.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationBarComponent {
    private final GUIDriver driver;
    /// Locators
    private final By homelink = new By.ByXPath("//a[contains(text(),'Home ')]");
    private final By contactlink = new By.ByXPath("//a[contains(text(),'Contact')]");
    private final By aboutuslink = new By.ByXPath("//a[contains(text(),'About us')]");
    private final By cartlink = new By.ByXPath("//a[contains(text(),'Cart')]");
    private final By loginlink = new By.ByXPath("//a[contains(text(),'Log in')]");
    private final By signuplink = new By.ByXPath("//a[contains(text(),'Sign up')]");
    private final By productstorelink = new By.ByXPath("//a[@class='navbar-brand' and contains(text(),'PRODUCT STORE')]");


    /// Constructor
    public NavigationBarComponent(GUIDriver driver) {
        this.driver = driver;
    }

    /// Actions


    @Step("Navigate to the base URL of the web application")
    public NavigationBarComponent navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on 'Home' link in the navigation bar")
    public HomePage clickHomeLink() {
        driver.element().click(homelink);
        return new HomePage(driver);
    }

    @Step("Click on 'Contact' link in the navigation bar")
    public ContactPage clickContactLink() {
        driver.element().click(contactlink);
        return new ContactPage(driver);
    }

    @Step("Click on 'About us' link in the navigation bar")
    public AboutUsPage clickAboutUsLink() {
        driver.element().click(aboutuslink);
        return new AboutUsPage(driver);
    }

    @Step("Click on 'Cart' link in the navigation bar")
    public CartPage clickCartLink() {
        driver.element().click(cartlink);
        return new CartPage(driver);
    }

    @Step("Click on 'Log in' link in the navigation bar")
    public LoginPage clickLoginLink() {
        driver.element().click(loginlink);
        return new LoginPage(driver);
    }

    @Step("Click on 'Sign up' link in the navigation bar")
    public SignUpPage clickSignUpLink() {
        driver.element().click(signuplink);
        return new SignUpPage(driver);
    }

    @Step("Click on 'PRODUCT STORE' link in the navigation bar")
    public ProductPage clickProductStoreLink() {
        driver.element().click(productstorelink);
        return new ProductPage(driver);
    }
    ///Validations
}
