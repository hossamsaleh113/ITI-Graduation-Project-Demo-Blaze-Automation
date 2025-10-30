package demoblaz.pages;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    private final GUIDriver driver;

    /// locators
    private final By productstorelink = new By.ByXPath("//a[@class='navbar-brand' and contains(text(),'PRODUCT STORE')]");
    private final By userlabel = new By.ByCssSelector("#nameofuser");
    private final By loginlink = new By.ByXPath("//a[contains(text(),'Log in')]");
    private final By logoutlink = new By.ByXPath("//a[contains(text(),'Log out')]");

    /// constructor
    public HomePage(GUIDriver driver) {
        this.driver = driver;

    }

    /// actions
    @Step("Navigate to the home page of the web application")
    public HomePage navigateToHomePage() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on 'Log out' link")
    public HomePage clickLogoutLink() {
        driver.element().click(logoutlink);
        return this;
    }

    /// validations
    @Step("validate home page is opened by checking the presence of 'Product Store' link")
    public HomePage isHomePageOpened() {
        driver.verification().isElementVisible(productstorelink);
        return this;
    }

    @Step("validate the  home page title  is '{expectedTitle}'")
    public HomePage validateHomePageTitle(String expectedTitle) {
        driver.verification().assertPageTitle(expectedTitle);
        return this;
    }

    @Step("validate the home page URL is '{expectedURL}'")
    public HomePage validateHomePageURL(String expectedURL) {
        driver.verification().assertPageUrl(expectedURL);
        return this;
    }

    @Step("validate the logged in username is '{expectedUsername}'")
    public HomePage validateLoggedInUsername(String expectedUsername) {
        String actualUsername = driver.element().getText(userlabel);
        expectedUsername = expectedUsername.startsWith("Welcome ") ? expectedUsername : "Welcome " + expectedUsername;
        driver.verification().assertEquals(actualUsername, expectedUsername, "Logged in username does not match.");
        return this;
    }

    @Step("validate logout link is visible")
    public HomePage validateLogoutLinkIsVisible() {
        driver.verification().isElementVisible(logoutlink);
        return this;
    }

    @Step("validate login link is  visible")
    public HomePage validateLoginLinkIsVisible() {
        driver.verification().isElementVisible(loginlink);
        return this;
    }
}
