package dskuznetsov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.aeonbits.owner.ConfigFactory;

import java.util.List;

import static dskuznetsov.Utils.UtilsWebDriver.waitUntilExpectedElementDisplayed;
import static dskuznetsov.Utils.UtilsWebDriver.waitUntilExpectedTitleDisplayed;
import static org.assertj.core.api.Assertions.assertThat;

public class VkMessage {
    private WebDriver driver;
    private Credentials cfg = ConfigFactory.create(Credentials.class);
    private String vkUserUrl = "https://vk.com/avgribanov";
    private String vkUserName = "Артем Грибанов";


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


        //Открываем страницу получателя сообщения и логинимся
        waitUntilExpectedTitleDisplayed(driver, vkUserName);
        WebElement loginTextField = driver.findElement(By.xpath("//input[@type='text' and @name='email']"));
        loginTextField.sendKeys(cfg.LOGIN());
        WebElement passwordTextField = driver.findElement(By.xpath("//input[@type='password' and @id='quick_pass']"));
        passwordTextField.sendKeys(cfg.PASSWORD());
        passwordTextField.submit();

        //Тап "Написать сообщение"
        waitUntilExpectedElementDisplayed(driver, "//*[@class='flat_button profile_btn_cut_left']");
        WebElement quickSendMessageButton = driver.findElement(By.xpath("//*[@class='flat_button profile_btn_cut_left']"));
        quickSendMessageButton.click();

        //Тап "Перейти к диалогу"
        waitUntilExpectedElementDisplayed(driver, "//*[@class='mail_box_header_link']");
        WebElement openDialogPageButton = driver.findElement(By.xpath("//*[@class='mail_box_header_link']"));
        openDialogPageButton.click();

        //Подсчет количества сообщений на странице до отправки сообщения
        waitUntilExpectedTitleDisplayed(driver, "Диалоги");
        List<WebElement> msgOnPageBeforeSend = driver.findElements(By.xpath("//div[contains(@class,'im-mess--text wall_module _im_log_body')]"));
        System.out.println(msgOnPageBeforeSend.size());

        //Находим поля для ввода текста, вводим сообщение и отправляем по кнопке "Отправить"
        WebElement messageTextField = driver.findElement(By.xpath("//*[@id='im_editable16435589']"));
        messageTextField.sendKeys("Привет");
        WebElement sendMessageButton = driver.findElement(By.xpath("//button[@class='im-send-btn im-chat-input--send _im_send im-send-btn_send']"));
        sendMessageButton.click();

        //Подсчет количества сообщений на странице после отправки сообщения
        List<WebElement> msgOnPageAfterSend = driver.findElements(By.xpath("//div[contains(@class,'im-mess--text wall_module _im_log_body')]"));
        System.out.println(msgOnPageAfterSend.size());

        //Количество сообщений на странице увеличилось на 1
        assertThat(msgOnPageAfterSend.size() - msgOnPageBeforeSend.size()).isEqualTo(1);

    }
}

