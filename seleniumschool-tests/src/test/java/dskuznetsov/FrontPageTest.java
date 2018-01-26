package dskuznetsov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static dskuznetsov.Utils.UtilsWebDriver.waitUntilExpectedTitleDisplayed;
import static org.assertj.core.api.Assertions.assertThat;

public class FrontPageTest {

    private WebDriver driver;
    private String searchText = "как написать selenium тест";

    @Test
    public void TestTitle() {
        driver.get("http://www.yandex.ru");
        System.out.println("Заголовок до поиска: " + driver.getTitle());

        WebElement searchString = driver.findElement(By.id("text"));
        searchString.sendKeys(searchText);

        WebElement searchButton = driver.findElement(By.className("button_theme_websearch"));
        searchButton.submit();

        waitUntilExpectedTitleDisplayed(driver, searchText);
        assertThat(driver.getTitle()).startsWith(searchText);
        System.out.println("Заголовок после поиска: " + driver.getTitle());
    }

    @Before
    public void createDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }


}
