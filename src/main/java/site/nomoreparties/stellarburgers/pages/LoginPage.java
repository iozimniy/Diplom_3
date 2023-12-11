package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.user.User;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.LOGIN_URL;

public class LoginPage {

    private final By emailField = By.xpath(".//input[@name = 'name']");
    private final By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private final By enterButton = By.xpath(".//button[text() = 'Войти']");

    WebDriver driver;
    User user;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.user = user;
    }

    @Step("Открыть страницу Вход")
    public void open() {
        driver.get(LOGIN_URL);
    }

    @Step("Дождаться загрузки страницы входа и проверить, что она загрузилась")
    public void waitLoginPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        } catch (TimeoutException e) {
            throw new AssertionError("Не загружается страница входа");
        }
    }

    public void fillInEmail(User user) {
        driver.findElement(emailField).sendKeys(user.getEmail());
    }

    public void fillinPassword(User user) {
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Заполнить поля Email и Пароль")
    public void fillInData(User user) {
        fillInEmail(user);
        fillinPassword(user);
    }

    @Step("Нажать на кнопку Войти")
    public MainConstructor clickEnterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(enterButton)).click();
        return new MainConstructor(driver);
    }

    @Step("Авторизовать пользователя")
    public MainConstructor loginUser(User user) {
        open();
        waitLoginPage();
        fillInData(user);
        clickEnterButton();
        return new MainConstructor(driver);
    }
}
