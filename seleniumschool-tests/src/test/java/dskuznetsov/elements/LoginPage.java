package dskuznetsov.elements;

import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.HtmlElement;


public interface LoginPage extends WebPage {
    @FindBy("//input[@type='text' and @name='email']")
    HtmlElement loginTextField();

    @FindBy("//input[@type='password' and @name='pass']")
    HtmlElement passwordTextField();
}
