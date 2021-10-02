package guru.qa.tests;

import guru.qa.domain.AuthorizationPage;
import guru.qa.page.BasePage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class parametrizedTest extends BasePage {

    BasePage basePage = new BasePage();

    @ParameterizedTest(name = "Test open authorization form from different pages")
    @EnumSource(value = AuthorizationPage.class, names = {"MAIN", "CLUB", "CHECKOUT"})
    void testOpenAuthorizationPopupEnumSource(AuthorizationPage authorizationPage) {
        basePage
                .openAuthorizationPopup(authorizationPage)
                .assertAuthorizationPopupShouldBeVisible();
    }

    static Stream<Arguments> authorizationData() {
        return Stream.of(
                Arguments.of(
                        "+79000000000", "000000"
                ),
                Arguments.of(
                        "+79999999999", "123456"
                )
        );
    }

    @MethodSource("authorizationData")
    @ParameterizedTest(name = "Login by phone with incorrect code")
    void fillAuthorizationForm(String phone, String code) {
        open(URL);

        basePage
                .clickImageUser()
                .setInputPhone(phone)
                .clickButtonSubmit()
                .setInputCode(code)
                .clickButtonSubmit()
                .assertTextErrorShouldHaveText("Введён неверный код подтверждения");
    }

    @CsvSource({"email1@mail.ru", "email2@yandex.ru"})
    @ParameterizedTest(name = "Login with new email")
    void checkFideBackFormTest(String email) {
        open(URL);

        basePage
                .clickImageUser()
                .clickButtonEmail()
                .setInputEmail(email)
                .clickButtonSubmit()
                .assertTextErrorShouldHaveText("Введённый Email не существует в системе. Попробуйте авторизоваться по номеру телефона");
    }
}
