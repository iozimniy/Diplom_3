package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pages.LoginPage;
import site.nomoreparties.stellarburgers.pages.MainConstructor;
import site.nomoreparties.stellarburgers.pages.ProfilePage;
import site.nomoreparties.stellarburgers.user.User;
import site.nomoreparties.stellarburgers.user.UserChecks;
import site.nomoreparties.stellarburgers.user.UserClient;
import site.nomoreparties.stellarburgers.user.UserGenerator;

public class TransitionTests {
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
    @DisplayName("Переход в Личный Кабинет")
    public void transitionToAccountTest() {
        LoginPage loginPage = new LoginPage(rule.driver);
        MainConstructor mainConstructor = loginPage.loginUser(user);

        mainConstructor.open();
        mainConstructor.waitMainConstructor();
        mainConstructor.checkOrderButtonName();

        ProfilePage profilePage = mainConstructor.clickOnProfileButton();
        profilePage.waitProfilePage();
    }

    @Test
    @DisplayName("Переход из Личного Кабинета по кнопке Конструктор в хедере")
    public void transitionFromProfilePageToConstructor() {
        LoginPage loginPage = new LoginPage(rule.driver);
        MainConstructor mainConstructor = loginPage.loginUser(user);

        ProfilePage profilePage = mainConstructor.clickOnProfileButton();
        profilePage.waitProfilePage();

        MainConstructor mainConstructorFromProfile = profilePage.clickOnConstructorButton();
        mainConstructorFromProfile.waitMainConstructor();
    }

    @Test
    @DisplayName("Переход из Личного кабинета по клику на логотип в хедере")
    public void transitionFromProfileToMainPage() {
        LoginPage loginPage = new LoginPage(rule.driver);
        MainConstructor mainConstructor = loginPage.loginUser(user);

        ProfilePage profilePage = mainConstructor.clickOnProfileButton();
        profilePage.waitProfilePage();

        MainConstructor mainConstructorFromProfile = profilePage.clickOnLogo();
        mainConstructorFromProfile.waitMainConstructor();
    }

    @After
    public void deleteUser() {
        UserClient.delete(accessToken);
    }
}
