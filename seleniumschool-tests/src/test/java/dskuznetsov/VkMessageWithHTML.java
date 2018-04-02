package dskuznetsov;

import dskuznetsov.elements.DialogPage;
import dskuznetsov.elements.LoginPage;
import dskuznetsov.elements.ProfilePage;
import dskuznetsov.elements.QuickMessagePopup;
import io.qameta.htmlelements.WebPageFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.aeonbits.owner.ConfigFactory;

import static io.qameta.htmlelements.matcher.DisplayedMatcher.displayed;
import static org.assertj.core.api.Assertions.assertThat;

public class VkMessageWithHTML {
    private WebDriver driver;
    private Credentials cfg = ConfigFactory.create(Credentials.class);
    private String vkUserUrl = "https://vk.com/avgribanov";
    LoginPage loginPage;
    ProfilePage profilePage;
    QuickMessagePopup quickMessagePopup;
    DialogPage dialogPage;
    WebPageFactory factory = new WebPageFactory();

    @Before
    public void createDriver() {
        driver = new ChromeDriver();
        loginPage = factory.get(driver, LoginPage.class);
        profilePage = factory.get(driver, ProfilePage.class);
        quickMessagePopup = factory.get(driver, QuickMessagePopup.class);
        dialogPage = factory.get(driver, DialogPage.class);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void sendMessageTest() {
        driver.get(vkUserUrl);
        String currentTime = String.valueOf(System.currentTimeMillis() / 1000L);

        loginPage.loginTextField().sendKeys(cfg.LOGIN());
        loginPage.passwordTextField().sendKeys(cfg.PASSWORD());
        loginPage.passwordTextField().submit();

        String userName = profilePage.userName().getText();
        profilePage.quickSendMessageButton().click();

        quickMessagePopup.openDialogButton().click();

        assertThat(dialogPage.userNameDialog().getText()).isEqualTo(userName);
        dialogPage.messageTextField().sendKeys(currentTime);
        dialogPage.sendMessageButton().click();

        dialogPage.messageList(currentTime).should(displayed());
    }
}
