package dskuznetsov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;


public interface QuickMessagePopup extends WebPage {
    @FindBy("//*[@class='mail_box_header_link']")
    HtmlElement openDialogButton();
}
