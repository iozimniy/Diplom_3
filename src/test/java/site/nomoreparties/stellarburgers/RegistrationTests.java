package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.RegistrationPage;
import site.nomoreparties.stellarburgers.user.User;
import site.nomoreparties.stellarburgers.user.UserGenerator;

public class RegistrationTests {

    @Rule
    public DriverRule rule = new DriverRule();
    User user;

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registrationSuccessfully() {
        user = UserGenerator.generateRandomUser();

        RegistrationPage registrationPage = new RegistrationPage(rule.getDriver());

        registrationPage.open();
        registrationPage.waitRegistrationPage();
        registrationPage.fillInData(user);
        LoginPage loginPage = registrationPage.clickRegButton();
        loginPage.waitLoginPage();
    }

    @Test
    @DisplayName("Регистрация с невалидным паролём")
    public void errorPasswordLessSixSimbols() {
        user = UserGenerator.generateRandomIncorrectPassword();

        RegistrationPage registrationPage = new RegistrationPage(rule.getDriver());

        registrationPage.open();
        registrationPage.waitRegistrationPage();
        registrationPage.fillInData(user);
        registrationPage.clickRegButtonError();
    }

}
