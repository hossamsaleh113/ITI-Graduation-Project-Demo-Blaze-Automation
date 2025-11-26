package com.demoblaz.tests;

import com.demoblaz.pages.HomePage;
import io.qameta.allure.Severity;
import net.bytebuddy.build.Plugin;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(coreLibraries.listeners.TestNGListeners.class)
public class Try extends BaseTest {


    @Test
    public void test() {
        new HomePage(driver).clickOnItem(testData.getJsonData("Products.item1.name")).addItemToCart().acceptAlert()
                .clickProductStoreLinkInNavBar().clickOnItem(testData.getJsonData("Products.item2.name")).
                addItemToCart().acceptAlert().clickCartLinkInNavBar().deleteItem(testData.getJsonData("Products.item1.name")).validateTotalPrice()
                .clickPlaceOrderButton().waitForOrderFormToBeReady().enterOrderName("Hossam").enterOrderCountry("Egypt").enterOrderCity("Cairo").enterOrderCreditCard("1234567890123456")
                .enterOrderMonth("12").enterOrderYear("2025").clickPurchaseButton().clickOKAfterPurchase();
    }








}
