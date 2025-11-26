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


    // validations
    private String getOrderAlertMessage() {
        return driver.alert().getAlertText();
    }

}
