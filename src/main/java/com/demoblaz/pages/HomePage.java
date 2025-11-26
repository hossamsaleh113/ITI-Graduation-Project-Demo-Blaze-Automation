package com.demoblaz.pages;

import com.demoblaz.components.NavigationBarComponent;
import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {
    private final GUIDriver driver;
    private final NavigationBarComponent<HomePage> navBar;

    /*
    Locators
     */
    private final By productStoreLink = By.xpath("//a[@class='navbar-brand' and contains(text(),'PRODUCT STORE')]");
    private final By laptopsCategoryLink = By.linkText("Laptops");
    private final By phonesCategoryLink = By.linkText("Phones");
    private final By monitorsCategoryLink = By.linkText("Monitors");
    private final By nextButton = By.id("next2");
    private final By previousButton = By.id("prev2");
    private final By nextIconArrow = By.className("carousel-control-next-icon");
    private final By previousIconArrow = By.className("carousel-control-prev-icon");

    /*
    Constructor
     */
    public HomePage(GUIDriver driver) {
        this.driver = driver;
        navBar = new NavigationBarComponent<>(driver, HomePage.class);

    }

    /*
    Page Actions
     */
    @Step("Navigate to the home page of the web application")
    public HomePage navigateToHomePage() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
        return this;
    }

    @Step("Click on item:{itemName} ")
    public ProductPage clickOnItem(String itemName) {
        By itemLink = By.linkText(itemName);
        driver.element().click(itemLink);
        return new ProductPage(driver, itemName);
    }

    //click laptops category
    @Step("Click on 'Laptops' category link")
    public HomePage clickLaptopsCategory() {
        driver.element().click(laptopsCategoryLink);
        return this;
    }

    //click phones category
    @Step("Click on 'Phones' category link")
    public HomePage clickPhonesCategory() {
        driver.element().click(phonesCategoryLink);
        return this;
    }

    //click monitors category
    @Step("Click on 'Monitors' category link")
    public HomePage clickMonitorsCategory() {
        driver.element().click(monitorsCategoryLink);
        return this;
    }

    //click next button
    @Step("Click on 'Next' button to navigate to the next page of products")
    public HomePage clickNextButton() {
        driver.element().click(nextButton);
        return this;
    }

    //click previous button
    @Step("Click on 'Previous' button to navigate to the previous page of products")
    public HomePage clickPreviousButton() {
        driver.element().click(previousButton);
        return this;
    }

    //getFirstItemName
    @Step("Get the name of the first item in the product list")
    public String getFirstItemName() {
        By firstItemLink = By.xpath("(//div[@class='card h-100']//a[@class='hrefch'])[1]");
        return driver.element().getText(firstItemLink);
    }

    //click next icon arrow
    @Step("Click on 'Next' icon arrow to navigate to the next page of products")
    public HomePage clickNextIconArrow() {
        driver.element().click(nextIconArrow);
        return this;
    }

    //click previous icon arrow
    @Step("Click on 'Previous' icon arrow to navigate to the previous page of products")
    public HomePage clickPreviousIconArrow() {
        driver.element().click(previousIconArrow);
        return this;
    }

    //get product description by item name
    @Step("Get the product description for item: {itemName}")
    public String getProductDescriptionByItemName(String itemName) {
        By itemDescription = By.xpath("//a[text()='"+ itemName +"']/../..//p");
        return driver.element().getText(itemDescription);
    }

    @Step("Go to navigation bar from cart page")
    public NavigationBarComponent<HomePage> NavBar() {
        return navBar;
    }


    /*
    Validations
     */
    @Step("validate home page is opened by checking the presence of 'Product Store' link")
    public HomePage isHomePageOpened() {
        driver.verification().isElementVisible(productStoreLink);
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

    //validate first item in the list
    @Step("Validate first item in the product list is {ItemName}")
    public HomePage validateFirstItemInList(String ItemName) {
        driver.validation().assertEquals(getFirstItemName(), ItemName, "Incorrect first item in the product list.");
        return this;
    }

    //validate product description by item name
    @Step("Validate product description for item: {itemName} contains '{expectedDescription}'")
    public HomePage validateProductDescriptionByItemName(String itemName, String expectedDescription) {
        String actualDescription = getProductDescriptionByItemName(itemName);
        driver.validation().assertEquals(actualDescription , expectedDescription, "Product description does not match.");
        return this;
    }
}
