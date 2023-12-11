package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.user.User;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.REGISTRATION_URL;


public class RegistrationPage {

    private final By nameField = By.xpath(".//fieldset[1]/div/div/input");
    private final By emailField = By.xpath(".//fieldset[2]/div/div/input");
    private final By passwordField = By.xpath(".//fieldset[3]/div/div/input");
    private final By regButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By enterLink = By.xpath(".//a[text()='Войти']");
    private final By regError = By.xpath(".//p[text()='Некорректный пароль']");

    WebDriver driver;
    User user;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.user = user;
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(REGISTRATION_URL);
    }

    @Step("Дождаться загрузки страницы регистрации и проверить, что она загрузилась")
    public void waitRegistrationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    public void fillInName(User user) {
        driver.findElement(nameField).sendKeys(user.getName());
    }

    public void fillInEmail(User user) {
        driver.findElement(emailField).sendKeys(user.getEmail());
    }

    public void fillInPassword(User user) {
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Заполнить поля данными")
    public void fillInData(User user) {
        fillInName(user);
        fillInEmail(user);
        fillInPassword(user);
    }

    @Step("Нажать на кнопку Зарегистрироваться")
    public LoginPage clickRegButton() {
        driver.findElement(regButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Зарегистрироваться и проверить, что появилось сообщение неправильном пароле")
    public void clickRegButtonError() {
        driver.findElement(regButton).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(regError));
        } catch (TimeoutException e) {
            throw new AssertionError("Не появляется сообщение о неправильном пароле");
        }
    }

    //клик на кнопку "Войти"
    @Step("Нажать на кнопку Войти")
    public LoginPage clickOnEnterLink() {
        driver.findElement(enterLink).click();
        return new LoginPage(driver);
    }
}
