package steps;

import com.saucedemo.LoginPage;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Тогда;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Steps {

    private final LoginPage loginPage = new LoginPage();

    @Допустим("^открыта страница \"([^\"]*)\"$")
    public void openPage(String expectedUrl) {

        open(expectedUrl);
        String actualUrl = getWebDriver().getCurrentUrl();

        assertEquals(expectedUrl, actualUrl, "Ошибка открытия страницы.");
    }

    @Допустим("^в поле Username введено значение \"([^\"]*)\"$")
    public void fillUserNameField(String userName) {

        loginPage.setValueToLoginField(userName);
    }

    @Допустим("^в поле Password введено значение \"([^\"]*)\"$")
    public void fillPasswordField(String password) {

        loginPage.setValueToPasswordField(password);
    }

    @Допустим("нажата кнопка LOGIN")
    public void clickOnButton() {

        loginPage.clickOnLoginButton();
    }

    @Тогда("^открыта следующая страница \"([^\"]*)\"$")
    public void openNextPage(String expectedUrl) {

        String actualUrl = getWebDriver().getCurrentUrl();

        assertEquals(expectedUrl, actualUrl, "Ошибка входа на сайт.");
    }

    @Тогда("^открыто сообщение ошибки \"([^\"]*)\"$")
    public void openErrorMessage(String expectedMessage) {

        String actualMessage = loginPage.getErrorMessageText();

        assertEquals(expectedMessage, actualMessage, "Ошибка в сообщении о требовании пароля.");
    }
}
