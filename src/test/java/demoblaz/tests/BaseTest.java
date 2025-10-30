package demoblaz.tests;

import coreLibraries.drivers.GUIDriver;
import coreLibraries.drivers.WebDriverProvider;
import coreLibraries.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;

    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
