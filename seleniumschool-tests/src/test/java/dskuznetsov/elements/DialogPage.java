package dskuznetsov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.HtmlElement;


public interface DialogPage extends WebPage {
    @FindBy("//div[contains(@class,'im-chat-input--text')][@role='textbox']")
    HtmlElement messageTextField();

    @FindBy("//button[contains(@class,'im-send-btn_send')]")
    HtmlElement sendMessageButton();

    @FindBy("//li[last()]//div[@class='im-mess--text wall_module _im_log_body' and text()='{{ currentTime }}']")
    HtmlElement messageList(@Param("currentTime") String currentTime);

    @FindBy("//a[contains(@class,'im_page_peer_name')]")
    HtmlElement userNameDialog();
}
