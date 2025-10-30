package demoblaz.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Try {
    private final By homelink = new By.ByXPath("//a[contains(text(),'Home ')]");
    private final By contactlink = new By.ByXPath("//a[contains(text(),'Contact')]");
    private final By aboutuslink = new By.ByXPath("//a[contains(text(),'About us')]");
    private final By cartlink = new By.ByXPath("//a[contains(text(),'Cart')]");
    private final By loginlink = new By.ByXPath("//a[contains(text(),'Log in')]");
    private final By signuplink = new By.ByXPath("//a[contains(text(),'Sign up')]");
    private final By productstorelink = new By.ByXPath("//a[@class='navbar-brand' and contains(text(),'PRODUCT STORE')]");
    private final By userlabel = new By.ByCssSelector("#nameofuser");

    private final By logoutlink = new By.ByXPath("//a[contains(text(),'Log out')]");

    @Test
    public void tryMethod() {

        WebDriver webDriver;
        webDriver = new ChromeDriver();
        webDriver.get("https://www.demoblaze.com/index.html");
        webDriver.findElement(logoutlink).click();
    }
}
