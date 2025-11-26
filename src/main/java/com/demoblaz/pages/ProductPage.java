package com.demoblaz.pages;

import com.demoblaz.components.NavigationBarComponent;
import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class ProductPage {
    /*
    variables
     */
    private final GUIDriver driver;
    private final String itemName;
    private final NavigationBarComponent<ProductPage> navBar;

    /*
    constructor
     */
    public ProductPage(GUIDriver driver , String itemName) {
        this.itemName = itemName;
        this.driver = driver;
        navBar = new NavigationBarComponent<>(driver, ProductPage.class);
        LogsManager.info("Navigated to Product Page for item: " + itemName);
    }

    /*
    locators
     */
    private final By itemTitle = By.cssSelector(".name");
    private final By itemPrice = By.cssSelector(".price-container");
    private final By productDescription = RelativeLocator.with(By.tagName("p")).above(By.cssSelector("[class*= 'btn-success']"));
    private final By addToCartButton = By.cssSelector("[class*= 'btn-success']");

    /*
    actions
     */

    //add item to cart
    @Step("Add item to cart")
    public ProductPage addItemToCart() {
        LogsManager.info("Adding item '" + itemName + "' to cart.");
        driver.element().click(addToCartButton);
        return this;
    }

    //accept the alert
    @Step("Accept alert")
    public ProductPage acceptAlert() {
        LogsManager.info("Accepting alert.");
        driver.alert().acceptAlert();
        return this;
    }


    //click product store link in nav bar
    @Step("Click 'Home' link in navigation bar")
    public HomePage clickProductStoreLinkInNavBar() {
        LogsManager.info("Clicking 'Home' link in navigation bar to go to Product Store page.");
        return navBar.clickHomeLink();
    }

    //click cart link in nav bar
    @Step("Click 'Cart' link in navigation bar")
    public CartPage clickCartLinkInNavBar() {
        LogsManager.info("Clicking 'Cart' link in navigation bar to go to Cart page.");
        return navBar.clickCartLink();
    }

    //get product description
    @Step("Click 'Log in' link in navigation bar")
    public String getProductDescription() {
        LogsManager.info("Getting product description for item '" + itemName + "'.");
        return driver.element().getText(productDescription);
    }

    //get product price
    @Step("Get price of the product")
    public String  getProductPrice() {
        LogsManager.info("Getting product price for item '" + itemName + "'.");
        String priceText = driver.element().getText(itemPrice);
        // Extract numeric value from the price text (assuming format is like "$123.45")
        return priceText.replaceAll("[^0-9]", "");
    }

    @Step("Go to navigation bar from cart page")
    public NavigationBarComponent<ProductPage> NavBar() {
        return navBar;
    }

/*
validations
 */
    //validate item name
    @Step("Validate item name")
    public ProductPage validateItemName() {
        LogsManager.info("Validating item name is '" + itemName + "'.");
        String actualItemName = driver.element().getText(itemTitle);
        driver.verification().assertEquals(actualItemName, itemName, "Item name does not match.");
        return this;
    }

    //validate item price is displayed
    @Step("Validate item price is displayed")
    public ProductPage validateItemPriceIsDisplayed() {
        LogsManager.info("Validating item price is displayed for item '" + itemName + "'.");
        driver.verification().isElementVisible(itemPrice);
        return this;
    }

    //validate item description is displayed
    @Step("Validate item description is displayed")
    public ProductPage validateItemDescriptionIsDisplayed() {
        LogsManager.info("Validating item description is displayed for item '" + itemName + "'.");
        driver.verification().isElementVisible(productDescription);
        return this;
    }

    //verify item description contains text
    @Step("Validate item description contains text: {expectedText}")
    public ProductPage validateItemDescription(String expectedText) {
        LogsManager.info("Validating item description contains text '" + expectedText + "' for item '" + itemName + "'.");
        String actualDescription = driver.element().getText(productDescription);
        driver.verification().assertEquals(actualDescription ,expectedText , "Item description does not contain expected text.");
        return this;
    }

    //validate add to cart button is displayed
    @Step("Validate 'Add to Cart' button is displayed")
    public ProductPage validateAddToCartButtonIsDisplayed() {
        LogsManager.info("Validating 'Add to Cart' button is displayed");
        driver.verification().isElementVisible(addToCartButton);
        return this;
    }


}



