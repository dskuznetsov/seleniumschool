package dskuznetsov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;


public interface ProfilePage extends WebPage {
    @FindBy("//button[contains(@class,'profile_btn_cut_left')]")
    HtmlElement quickSendMessageButton();

    @FindBy("//*[@class='page_name']")
    HtmlElement userName();
}
