package dskuznetsov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.aeonbits.owner.ConfigFactory;


import static dskuznetsov.Utils.UtilsWebDriver.waitUntilExpectedElementDisplayed;
import static dskuznetsov.Utils.UtilsWebDriver.waitUntilExpectedTitleDisplayed;
import static org.assertj.core.api.Assertions.assertThat;

public class VkMessage {
    private WebDriver driver;
    private Credentials cfg = ConfigFactory.create(Credentials.class);
    private String vkUserUrl = "https://vk.com/avgribanov";
    private String vkUserName = "Артем Грибанов";
    private String expectedTitleOfDialogPage = "Диалоги";



    @Before
    public void createDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void sendMessageTest() {

        driver.get(vkUserUrl);
        Long currentTime = System.currentTimeMillis() / 1000L;

        //Открываем страницу получателя сообщения и логинимся
        waitUntilExpectedTitleDisplayed(driver, vkUserName);
        WebElement loginTextField = driver.findElement(By.xpath("//input[@type='text' and @name='email']"));
        loginTextField.sendKeys(cfg.LOGIN());
        WebElement passwordTextField = driver.findElement(By.xpath("//input[@type='password' and @name='pass']"));
        passwordTextField.sendKeys(cfg.PASSWORD());
        passwordTextField.submit();

        //Тап "Написать сообщение"
        waitUntilExpectedElementDisplayed(driver, "//button[contains(@class,'profile_btn_cut_left')]");
        WebElement quickSendMessageButton = driver.findElement(By.xpath("//button[contains(@class,'profile_btn_cut_left')]"));
        quickSendMessageButton.click();

        //Тап "Перейти к диалогу"
        waitUntilExpectedElementDisplayed(driver, "//*[@class='mail_box_header_link']");
        WebElement openDialogPageButton = driver.findElement(By.xpath("//*[@class='mail_box_header_link']"));
        openDialogPageButton.click();

        //Находим поля для ввода текста, вводим сообщение и отправляем по кнопке "Отправить"
        waitUntilExpectedTitleDisplayed(driver, expectedTitleOfDialogPage);
        WebElement messageTextField = driver.findElement(By.xpath("//div[contains(@class,'im-chat-input--text')][@role='textbox']"));
        messageTextField.sendKeys(String.valueOf(currentTime));
        WebElement sendMessageButton = driver.findElement(By.xpath("//button[contains(@class,'im-send-btn_send')]"));
        sendMessageButton.click();

        assertThat(driver.findElement(By.xpath("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='" + currentTime + "']")).isDisplayed()).isTrue();
    }
}
