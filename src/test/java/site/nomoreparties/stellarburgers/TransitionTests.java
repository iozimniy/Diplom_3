package site.nomoreparties.stellarburgers;

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
    public void transitionFromProfilePageToConstructor() {
        LoginPage loginPage = new LoginPage(rule.driver);
        MainConstructor mainConstructor = loginPage.loginUser(user);

        ProfilePage profilePage = mainConstructor.clickOnProfileButton();
        profilePage.waitProfilePage();

        MainConstructor mainConstructorFromProfile = profilePage.clickOnConstructorButton();
        mainConstructorFromProfile.waitMainConstructor();
    }

    @After
    public void deleteUser() {
        UserClient.delete(accessToken);
    }
}
