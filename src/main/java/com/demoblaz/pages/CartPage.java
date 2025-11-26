package com.demoblaz.pages;

import com.demoblaz.components.NavigationBarComponent;
import com.demoblaz.components.OrderComponent;
import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

import static org.testng.Assert.fail;

public class CartPage {

    /*
    variables
     */
    private final GUIDriver driver;
    private final NavigationBarComponent<CartPage> navBar;


    /*
    locators
     */
    private final By totalPrice = By.id("totalp");
    private final By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private final By deleteLink = By.xpath("//a[text()='Delete']");
    private final By cartItems = By.cssSelector(".success");
    private final By itemPrice = By.xpath(".//td[3]");

    /*
     Constructor
     */
    public CartPage(GUIDriver driver) {
        this.driver = driver;
        navBar = new NavigationBarComponent<>(driver, CartPage.class);
        if (driver.element().isElementDisplayed(totalPrice)) {
            LogsManager.info("Items in cartPage are displayed.");
        } else {
            LogsManager.error("Items in cartPage are not displayed.");
        }
    }

    /*
    actions
     */
    public String getTotalPrice() {
        return driver.element().getText(totalPrice);
    }

    public String getSumOfItemPrices() {
        int sum = 0;
        List<String> prices = driver.element().findElements(itemPrice).stream()
                .map(WebElement::getText)
                .toList();
        for (String price : prices) {
            sum += Integer.parseInt(price);
        }
        return String.valueOf(sum);
    }

    public int getItemCount() {
        try {
            return driver.element().findElements(cartItems).size();
        } catch (Exception e) {
            LogsManager.info("No items found in the cart.");
            return 0;
        }
    }

    @Step("Wait for cart page to be ready after deletion")
    private void waitForCartPageToBeReady() {
        int initialCount = getItemCount();
        driver.element().waitFor().until(driver1 -> {
            try {
                return getItemCount() == initialCount - 1;
            } catch (Exception e) {
                return false;
            }
        });
    }

    //go to navigation bar
    @Step("Go to navigation bar from cart page")
    public NavigationBarComponent<CartPage> NavBar() {
        return navBar;
    }

    @Step("Delete item: {itemName} from the cart")
    public CartPage deleteItem(String itemName) {
        By deleteButton = RelativeLocator.with(deleteLink)
                .toRightOf(By.xpath("//td[text()='" + itemName + "']"));
        driver.element().click(deleteButton);
        waitForCartPageToBeReady();
        if (driver.element().isElementEnabled((deleteLink))) {
            LogsManager.info("Item '" + itemName + "' deleted from the cart.");
            System.out.println("Actual price after deletion: " + getTotalPrice());
            System.out.println("Expected price after deletion: " + getSumOfItemPrices());
            return this;
        } else {
            LogsManager.error("Item '" + itemName + "' was not deleted from the cart.");
            fail("Item '" + itemName + "' was not deleted from the cart.");
            return null;
        }
    }

    //click place order button
    @Step("Click on 'Place Order' button")
    public OrderComponent clickPlaceOrderButton() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if(driver.element().isElementEnabled(placeOrderButton)){
            driver.element().click(placeOrderButton);
        }
        return new OrderComponent(driver);
    }


    /*
    validations
     */
    @Step("Validate total price in the cart")
    public CartPage validateTotalPrice() {
        String expectedTotal = getSumOfItemPrices();
        String actualTotal = getTotalPrice();
        driver.validation().assertEquals(actualTotal, expectedTotal, "Incorrect total price.");
        return this;
    }

    //validate item count
    @Step("Validate item count in the cart is '{expectedCount}'")
    public CartPage validateItemCount(String expectedCount) {
        String actualCount = String.valueOf(getItemCount());
        driver.validation().assertEquals(actualCount, expectedCount, "Incorrect item count.");
        return this;
    }

    //validate item is deleted  //Incorrect Logic because there could be multiple items with same name
    @Step("Validate item: {itemName} is deleted from the cart")
    public CartPage validateItemIsDeleted(String itemName) {
        By itemLocator = By.xpath("//td[text()='" + itemName + "']");
        driver.validation().assertElementNotVisible(itemLocator, "Item '" + itemName + "' was not deleted from the cart.");
        return this;
    }





}





