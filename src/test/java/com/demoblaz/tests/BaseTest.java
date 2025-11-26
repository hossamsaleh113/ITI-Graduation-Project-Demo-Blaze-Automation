package com.demoblaz.tests;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.drivers.WebDriverProvider;
import coreLibraries.utils.dataReader.JsonReader;
import coreLibraries.utils.dataReader.PropertyReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;
    protected JsonReader orderData;


    @Override
    public GUIDriver getWebDriver() {
        return driver;
    }


    @BeforeClass
    public void startUp(){
        testData = new JsonReader("test-data");
        orderData = new JsonReader("orders");
    }

    @BeforeMethod
    public void setUp() {
        driver = new GUIDriver();
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb"));
    }


    @AfterMethod
    public void tearDown() {
        driver.get().quit();
    }



}



