package site.nomoreparties.stellarburgers;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.ForgotPasswordPage;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.MainConstructor;
import site.nomoreparties.stellarburgers.pages.RegistrationPage;
import site.nomoreparties.stellarburgers.user.User;
import site.nomoreparties.stellarburgers.user.UserChecks;
import site.nomoreparties.stellarburgers.user.UserClient;
import site.nomoreparties.stellarburgers.user.UserGenerator;

public class AuthorizationTests {

    @Rule
    public DriverRule rule = new DriverRule();

    String accessToken;
    User user;

    @Before
    public void setUser() {
        user = UserGenerator.generateRandomUser();
        ValidatableResponse createUser = UserClient.createUser(user);
        accessToken = UserChecks.assertUserCreateSuccessfully(createUser, user.getEmail(), user.getName());
    }

    @Test
    public void authFromAuthButtonMainPage() {
        MainConstructor mainConstructor = new MainConstructor(rule.driver);

        mainConstructor.open();
        mainConstructor.waitMainConstructor();
        LoginPage loginPage = mainConstructor.clickOnAuthButton();

        loginPage.waitLoginPage();
        loginPage.fillInData(user);
        MainConstructor mainConstructorAuth = loginPage.clickEnterButton();

        mainConstructorAuth.waitMainConstructor();
        mainConstructorAuth.checkOrderButtonName();
    }

    @Test
    public void authFromAccountButtonMainPage() {
        MainConstructor mainConstructor = new MainConstructor(rule.driver);

        mainConstructor.open();
        mainConstructor.waitMainConstructor();
        LoginPage loginPage = mainConstructor.clickOnAccountButton();

        loginPage.waitLoginPage();
        loginPage.fillInData(user);
        MainConstructor mainConstructorAuth = loginPage.clickEnterButton();

        mainConstructorAuth.waitMainConstructor();
        mainConstructorAuth.checkOrderButtonName();
    }

    @Test
    public void authFromEnterLinkRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(rule.driver);

        registrationPage.open();
        registrationPage.waitRegistrationPage();

        LoginPage loginPage = registrationPage.clickOnEnterLink();
        loginPage.waitLoginPage();
        loginPage.fillInData(user);
        MainConstructor mainConstructor = loginPage.clickEnterButton();

        mainConstructor.waitMainConstructor();
        mainConstructor.checkOrderButtonName();
    }

    @Test
    public void authFromEnterLinkForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(rule.driver);

        forgotPasswordPage.open();
        forgotPasswordPage.waitForgotPasswordPage();
        LoginPage loginPage = forgotPasswordPage.clickOnEnterLink();

        loginPage.waitLoginPage();
        loginPage.fillInData(user);
        MainConstructor mainConstructor = loginPage.clickEnterButton();

        mainConstructor.waitMainConstructor();
        mainConstructor.checkOrderButtonName();
    }

    @After
    public void deleteUser() {
        UserClient.delete(accessToken);
    }
}
