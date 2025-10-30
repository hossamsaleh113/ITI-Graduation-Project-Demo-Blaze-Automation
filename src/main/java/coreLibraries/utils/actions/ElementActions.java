package coreLibraries.utils.actions;

import coreLibraries.utils.WaitManager;
import coreLibraries.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class ElementActions {
    private final WebDriver driver;
    private WaitManager waitManager;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    /// Find Element
    public WebElement findElement(By locator) {
        return waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = driver_wait.findElement(locator);
                LogsManager.info("Find element : " + locator);
                return element;
            } catch (Exception e) {
                LogsManager.info("Failed to find element : " + locator);
                return null;
            }
        });
    }

    /// Click on Element
    public void click(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                element.click();
                LogsManager.info("Clicked on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to click on element: " + locator);
                return false;
            }
        });
    }

    /// Type on Element
    public void type(By locator, String text) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                element.clear();
                element.sendKeys(text);
                LogsManager.info("Typed text '" + text + "' into element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to type text '" + text + "' into element: " + locator);
                return false;
            }
        });
    }

    /// clear text from input field

    /// Get Text from Element
    public String getText(By locator) {
        return waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                String mess = element.getText();

                if (!mess.isEmpty()) {
                    LogsManager.info("Retrieved text from element: " + locator + " - Text: " + mess);
                    return mess;
                } else {
                    LogsManager.info("No text found in element: " + locator);
                    return null;
                }
            } catch (Exception e) {

                LogsManager.info("Failed to get text from element: " + locator);
                return null;

            }
        });
    }

    /// Double-Click on Element
    public void doubleClick(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).doubleClick(element).perform();
                LogsManager.info("Double clicked on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to double click on element: " + locator);
                return false;
            }
        });
    }

    /// right-Click on Element
    public void rightClick(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).contextClick(element).perform();
                LogsManager.info("Right clicked on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to right click on element: " + locator);
                return false;
            }
        });
    }

    /// hover on Element
    public void hover(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).moveToElement(element).perform();
                LogsManager.info("Hovered over element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to hover over element: " + locator);
                return false;
            }
        });
    }

    /// clickandhold on Element
    public void clickAndHold(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).clickAndHold(element).perform();
                LogsManager.info("Clicked and held on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to click and hold on element: " + locator);
                return false;
            }
        });
    }

    /// release on Element
    public void release(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).release(element).perform();
                LogsManager.info("Released on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to release on element: " + locator);
                return false;
            }
        });
    }

    /// holding key on Element(try it first)
    public void holdKey(By locator, CharSequence key) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).keyDown(element, key).perform();
                LogsManager.info("Held key '" + key + "' on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to hold key '" + key + "' on element: " + locator);
                return false;
            }
        });
    }

    /// release key with element(try it first)
    public void releaseKey(By locator, CharSequence key) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Actions(driver_wait).keyUp(element, key).perform();
                LogsManager.info("Released key '" + key + "' on element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to release key '" + key + "' on element: " + locator);
                return false;
            }
        });
    }

    /// hold key without element
    public void holdKey(CharSequence key) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                new Actions(driver_wait).keyDown(key).perform();
                LogsManager.info("Held key '" + key + "'");
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to hold key '" + key + "'");
                return false;
            }
        });
    }

    /// release key without element
    public void releaseKey(CharSequence key) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                new Actions(driver_wait).keyUp(key).perform();
                LogsManager.info("Released key '" + key + "'");
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to release key '" + key + "'");
                return false;
            }
        });
    }

    /// upload file to element
    public void uploadFile(By locator, String filePath) {
        String fileAbsolute = System.getProperty("user.dir") + File.separator + filePath;
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                element.sendKeys(fileAbsolute);
                LogsManager.info("Uploaded file '" + filePath + "' to element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to upload file '" + filePath + "' to element: " + locator);
                return false;
            }
        });
    }

    /// select from dropdown by visible text
    public void selectFromDropdownByVisibleText(By locator, String visibleText) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Select(element).selectByVisibleText(visibleText);
                LogsManager.info("Selected '" + visibleText + "' from dropdown: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to select '" + visibleText + "' from dropdown: " + locator);
                return false;
            }
        });
    }

    /// select from dropdown by value
    public void selectFromDropdownByValue(By locator, String value) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Select(element).selectByValue(value);
                LogsManager.info("Selected '" + value + "' from dropdown: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to select '" + value + "' from dropdown: " + locator);
                return false;
            }
        });
    }

    /// select from dropdown by index
    public void selectFromDropdownByIndex(By locator, int index) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                new Select(element).selectByIndex(index);
                LogsManager.info("Selected index '" + index + "' from dropdown: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to select index '" + index + "' from dropdown: " + locator);
                return false;
            }
        });
    }

    /// select from checkbox
    public void selectCheckbox(By locator, boolean shouldBeSelected) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                if (element.isSelected() != shouldBeSelected) {
                    element.click();
                }
                LogsManager.info("Checkbox " + locator + " set to " + shouldBeSelected);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to set checkbox " + locator + " to " + shouldBeSelected);
                return false;
            }
        });
    }

    /// select from radio button
    public void selectRadioButton(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                WebElement element = findElement(locator);
                scrollToElement(locator);
                if (!element.isSelected()) {
                    element.click();
                }
                LogsManager.info("Radio button " + locator + " selected.");
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to select radio button " + locator);
                return false;
            }
        });
    }

    /// scrolling up  using js
    private void scrollUp(int C) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0,-" + C + ");");
    }

    /// scrolling up

    public void ScrollUp(int C) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                scrollUp(C);
                LogsManager.info("Scrolled up by " + C + " pixels.");
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to scroll up by " + C + " pixels.");
                return false;
            }
        });
    }

    /// scrolling down  using js
    public void scrollDown(int C) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0," + C + ");");
    }

    /// scroll down
    public void ScrollDown(int C) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                scrollDown(C);
                LogsManager.info("Scrolled down by " + C + " pixels.");
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to scroll down by " + C + " pixels.");
                return false;
            }
        });
    }

    /// function to scroll to an element using js
    private void scrollToElementJS(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(""" 
                        arguments[0].scrollIntoView({behaviour:"auto",block:"center",inline:"center"});""", findElement(locator));
    }


    /// Scroll to Element
    public void scrollToElement(By locator) {
        waitManager.fluentWait().until(driver_wait -> {
            try {
                scrollToElementJS(locator);
                LogsManager.info("Scrolled to element: " + locator);
                return true;
            } catch (Exception e) {
                LogsManager.info("Failed to scroll to element: " + locator);
                return false;
            }
        });
    }
}
