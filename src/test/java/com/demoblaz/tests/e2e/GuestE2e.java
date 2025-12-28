package com.demoblaz.tests.e2e;

import com.demoblaz.pages.HomePage;
import com.demoblaz.tests.base.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(coreLibraries.listeners.TestNGListeners.class)
public class GuestE2e extends BaseTest {


    @Test
    public void guestFlow() {
        new HomePage(driver).
                clickOnItem(testData.getJsonData("Products.item1.name")).
                addItemToCart().
                acceptAlert().
                clickProductStoreLinkInNavBar().
                clickOnItem(testData.getJsonData("Products.item2.name")).
                addItemToCart().
                acceptAlert().
                clickCartLinkInNavBar().
                deleteItem(testData.getJsonData("Products.item1.name")).
                validateTotalPrice().
                clickPlaceOrderButton().
                waitForOrderFormToBeReady().
                enterOrderName("Hossam").
                enterOrderCountry("Egypt").
                enterOrderCity("Cairo").
                enterOrderCreditCard("1234567890123456").
                enterOrderMonth("12").
                enterOrderYear("2025").
                clickPurchaseButton().
                clickOKAfterPurchase();
    }








}
