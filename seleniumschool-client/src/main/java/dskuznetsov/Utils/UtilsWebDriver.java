package dskuznetsov.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilsWebDriver {

    public static void waitUntilExpectedTitleDisplayed(WebDriver driver, final String expectedTitle) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith(expectedTitle);
            }
        });
    }

    public static void waitUntilExpectedElementDisplayed(WebDriver driver, final String xpathOfExpectedElement) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath(xpathOfExpectedElement)).isDisplayed();
            }
        });
    }
}
