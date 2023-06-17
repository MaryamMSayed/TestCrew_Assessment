package pages_helper;


import pages_helper.HelperPages.ActionType;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ElementsActions {
    //static WebDriver driver;

    @Step("Click on element: [{by}]")
    public static void click(WebDriver driver, By by) {
        locatingElementStrategy(driver, by);



        try {
            // Log element text if not empty. Else, log clicking
            if (!driver.findElement(by).getText().isBlank()) {
                Logger.logMessage("Clicking on: " + driver.findElement(by).getText());
            } else {
                Logger.logMessage("Clicking on element:" + by);
            }
            driver.findElement(by).click();
        } catch (Exception exception) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
                        driver.findElement(by));
            } catch (Exception rootCauseException) {
                rootCauseException.initCause(exception);
                Logger.logMessage(exception.getMessage());
                Logger.logMessage(rootCauseException.getMessage());

            }
        }

    }
    @Step ("Find list of Elements and display each element text")
    public static List<WebElement> findElements(WebDriver driver , By by)
    {
        try {
        // Mouse hover on the element before clicking
        HelperPages.actions(driver, by, ActionType.MOUSE_HOVER);
        // wait for the element to be clickable
        HelperPages.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(by));
    } catch (Exception e) {
        Logger.logMessage(e.getMessage());
    }
       List<WebElement> elements= driver.findElements(by);
        for(WebElement element : elements)
        {
            System.out.println(element.getText());
        }
        return elements;


    }


    private static void locatingElementStrategy(WebDriver driver, By by) {
        try {
            // Wait for the element to be visible
            HelperPages.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
            // Scroll the element into view to handle some browsers cases
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
            // Check if the element is displayed
            driver.findElement(by).isDisplayed();
        } catch (TimeoutException toe) {
            Logger.logMessage("The element is not Visible...." + toe.getMessage());
        } catch (Exception e) {
            Logger.logMessage(e.getMessage());
        }
    }

}
