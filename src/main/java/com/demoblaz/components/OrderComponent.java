package com.demoblaz.components;

import com.demoblaz.pages.CartPage;
import com.demoblaz.pages.HomePage;
import coreLibraries.drivers.GUIDriver;
import coreLibraries.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class OrderComponent {
    // variables
    private final GUIDriver driver;
    private final String alertOrderFailureMessage = "Please fill out Name and Creditcard.";
    private final String alertOrderSuccessMessage = "Thank you for your purchase!";


    //locators
    private final By orderLabel = By.id("orderModalLabel");
    private final By totalPrice = By.id("totalm");
    private final By orderNameLabel = By.xpath("(//label[@for='name'])[2]");
    private final By orderNameInput = By.id("name");
    private final By orderCountryLabel = By.xpath("(//label[@for='country'])");
    private final By orderCountryInput = By.id("country");
    private final By orderCityLabel = By.xpath("(//label[@for='city'])");
    private final By orderCityInput = By.id("city");
    private final By orderCreditCardLabel = By.xpath("(//label[@for='card'])");
    private final By orderCreditCardInput = By.id("card");
    private final By orderMonthLabel = By.xpath("(//label[@for='month'])");
    private final By orderMonthInput = By.id("month");
    private final By orderYearLabel = By.xpath("(//label[@for='year'])");
    private final By orderYearInput = By.id("year");
    private final By purchaseButton = By.xpath("//button[text()='Purchase']");
    private final By SuccessMessageSign = By.className("sa-placeholder");
    private final By orderConfirmationTitle = By.xpath("//h2[text()='Thank you for your purchase!']");
    private final By okButton = By.xpath("//button[text()='OK']");
    private final By closeButton = By.xpath("//button[text()='Close']");
    private final By xButton = RelativeLocator.with(By.xpath("//span[text()='×']")).toRightOf(By.id("orderModalLabel"));
    private final By orderForm = By.cssSelector("#orderModal .modal-content");

    //constructor
    public OrderComponent(GUIDriver driver) {
        this.driver = driver;
        if (driver.element().isElementDisplayed(orderForm)) {
            System.out.println("Order form is displayed.");
        } else {
            System.out.println("Order form is not displayed.");
        }
    }

    //actions
    @Step("enter order name '{name}' in the order form")
    public OrderComponent enterOrderName(String name) {
        driver.element().type(orderNameInput, name);
        return this;
    }

    @Step("enter order country '{country}' in the order form")
    public OrderComponent enterOrderCountry(String country) {
        driver.element().type(orderCountryInput, country);
        return this;
    }

    @Step("enter order city '{city}' in the order form")
    public OrderComponent enterOrderCity(String city) {
        driver.element().type(orderCityInput, city);
        return this;
    }

    @Step("enter order credit card '{creditCard}' in the order form")
    public OrderComponent enterOrderCreditCard(String creditCard) {
        driver.element().type(orderCreditCardInput, creditCard);
        return this;
    }

    @Step("enter order month '{month}' in the order form")
    public OrderComponent enterOrderMonth(String month) {
        driver.element().type(orderMonthInput, month);
        return this;
    }

    @Step("enter order year '{year}' in the order form")
    public OrderComponent enterOrderYear(String year) {
        driver.element().type(orderYearInput, year);
        return this;
    }

    //wait for form to be ready
    @Step("wait for order form to be ready")
    public OrderComponent waitForOrderFormToBeReady() {
        if (driver.element().isElementEnabled(purchaseButton)) {
            LogsManager.info("Order form is displayed.");
            return this;
        } else {
            LogsManager.error("Order form is not displayed.");
            return null;
        }
    }

    @Step("click on 'Purchase' button in the order form")
    public OrderComponent clickPurchaseButton() {
        driver.element().click(purchaseButton);
        return this;
    }

    @Step("click on 'Close' button in the order form")
    public CartPage clickCloseButton() {
        driver.element().click(closeButton);
        return new CartPage(driver);
    }

    @Step("click on 'X' button in the order form")
    public CartPage clickXButton() {
        driver.element().click(xButton);
        return new CartPage(driver);
    }

    @Step("accept alert after purchase")
    public OrderComponent acceptAlert() {
        driver.alert().acceptAlert();
        return this;
    }

    @Step("click 'OK' button after purchase")
    public HomePage clickOKAfterPurchase() {
        driver.element().click(okButton);
        return new HomePage(driver);
    }

    @Step("fill the order form with name: '{name}', country: '{country}', city: '{city}', credit card: '{creditCard}', month: '{month}', year: '{year}'")
    public OrderComponent fillOrderForm(String name, String country, String city, String creditCard, String month, String year) {
        enterOrderName(name);
        enterOrderCountry(country);
        enterOrderCity(city);
        enterOrderCreditCard(creditCard);
        enterOrderMonth(month);
        enterOrderYear(year);
        return this;
    }

    private String getOrderAlertMessage() {
        return driver.alert().getAlertText();
    }

    // validations
    @Step("Verify order label is displayed in the order form")
    public OrderComponent isOrderLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderLabel);
        String errorMessage = "Order label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify total price is displayed in the order form")
    public OrderComponent isTotalPriceDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(totalPrice);
        String errorMessage = "Total price is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order name label is displayed in the order form")
    public OrderComponent isOrderNameLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderNameLabel);
        String errorMessage = "Order name label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order name input is displayed in the order form")
    public OrderComponent isOrderNameInputDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderNameInput);
        String errorMessage = "Order name input is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order country label is displayed in the order form")
    public OrderComponent isOrderCountryLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderCountryLabel);
        String errorMessage = "Order country label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order country input is displayed in the order form")
    public OrderComponent isOrderCountryInputDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderCountryInput);
        String errorMessage = "Order country input is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order city label is displayed in the order form")
    public OrderComponent isOrderCityLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderCityLabel);
        String errorMessage = "Order city label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order city input is displayed in the order form")
    public OrderComponent isOrderCityInputDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderCityInput);
        String errorMessage = "Order city input is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order credit card label is displayed in the order form")
    public OrderComponent isOrderCreditCardLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderCreditCardLabel);
        String errorMessage = "Order credit card label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order credit card input is displayed in the order form")
    public OrderComponent isOrderCreditCardInputDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderCreditCardInput);
        String errorMessage = "Order credit card input is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order month label is displayed in the order form")
    public OrderComponent isOrderMonthLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderMonthLabel);
        String errorMessage = "Order month label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order month input is displayed in the order form")
    public OrderComponent isOrderMonthInputDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderMonthInput);
        String errorMessage = "Order month input is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order year label is displayed in the order form")
    public OrderComponent isOrderYearLabelDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderYearLabel);
        String errorMessage = "Order year label is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify order year input is displayed in the order form")
    public OrderComponent isOrderYearInputDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(orderYearInput);
        String errorMessage = "Order year input is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify purchase button is displayed in the order form")
    public OrderComponent isPurchaseButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(purchaseButton);
        String errorMessage = "Purchase button is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify close button is displayed in the order form")
    public OrderComponent isCloseButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(closeButton);
        String errorMessage = "Close button is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }
    @Step("Verify X button is displayed in the order form")
    public OrderComponent isXButtonDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(xButton);
        String errorMessage = "X button is not displayed in the order form.";
        driver.softValidation().assertTrue(actualResult, errorMessage);
        return this;
    }

    @Step("Verify order failure alert message is displayed correctly")
    public OrderComponent verifyOrderFailureAlertMessage() {
        String actualAlertMessage = getOrderAlertMessage();
        String errorMessage = "Order failure alert message is incorrect. Expected: '" + alertOrderFailureMessage + "' but found: '" + actualAlertMessage + "'";
        driver.verification().assertEquals(actualAlertMessage, alertOrderFailureMessage, errorMessage);
        return this;
    }

    @Step("Verify order success alert message is displayed correctly")
    public OrderComponent verifyOrderSuccessMessage() {
        String actualAlertMessage = driver.element().getText(orderConfirmationTitle);
        String errorMessage = "Order success alert message is incorrect. Expected: '" + alertOrderSuccessMessage + "' but found: '" + actualAlertMessage + "'";
        driver.verification().assertEquals(actualAlertMessage, alertOrderSuccessMessage, errorMessage);
        return this;
    }

    @Step("Verify success message sign is displayed after purchase")
    public OrderComponent isSuccessMessageSignDisplayed() {
        boolean actualResult = driver.element().isElementDisplayed(SuccessMessageSign);
        String errorMessage = "The success message sign is not displayed after purchase.";
        driver.verification().assertTrue(actualResult, errorMessage);
        return this;
    }
}
