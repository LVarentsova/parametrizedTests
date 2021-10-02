package guru.qa.page;

import com.codeborne.selenide.Configuration;
import guru.qa.domain.AuthorizationPage;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {
    public static final String URL = "https://fundayshop.com";
    public String locatorAuthorizationPopup = ".modal-content";
    public String locatorImgUser = ".control-user .icon";
    public String locatorTextError = "//p[@class='error']";
    public String locatorInputPhone = "//input[@type='tel'][not(contains(@name,'code'))]";
    public String locatorButtonSubmit = "//div[@class='modal-content']//button[@type='submit']";
    public String locatorInputCode = "//input[@type='tel'][contains(@name,'code')]";
    public String locatorButtonEmail = "//div[contains(text(),'Войти по Email')]";
    public String locatorInputEmail = "//input[@type='email'][not(contains(@name,'code'))]";

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    public BasePage openAuthorizationPopup(AuthorizationPage authorizationPage) {
        open(authorizationPage.getUrl());
        $(authorizationPage.getSelector()).click();
        return this;
    }

    public BasePage assertAuthorizationPopupShouldBeVisible() {
        $(locatorAuthorizationPopup).shouldBe(visible);
        return this;
    }

    public BasePage clickImageUser() {
        $(locatorImgUser).click();
        return this;
    }

    public BasePage setInputPhone(String phone) {
        $(By.xpath(locatorInputPhone)).setValue(phone);
        return this;
    }

    public BasePage clickButtonSubmit () {
        $(By.xpath(locatorButtonSubmit)).click();
        return this;
    }

    public BasePage setInputCode(String code) {
        $(By.xpath(locatorInputCode)).setValue(code);
        return this;
    }

    public BasePage assertTextErrorShouldHaveText(String textError) {
        $(By.xpath(locatorTextError)).shouldHave(text(textError));
        return this;
    }

    public BasePage clickButtonEmail() {
        $(By.xpath(locatorButtonEmail)).click();
        return this;
    }

    public BasePage setInputEmail(String email) {
        $(By.xpath(locatorInputEmail)).setValue(email);
        return this;
    }
}
