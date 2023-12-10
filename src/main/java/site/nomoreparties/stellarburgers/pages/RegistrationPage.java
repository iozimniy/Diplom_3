package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.user.User;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.REGISTRATION_URL;


public class RegistrationPage {

    //Поле "Имя"
    private static final By nameField = By.xpath(".//fieldset[1]/div/div/input");
    private static final By emailField = By.xpath(".//fieldset[2]/div/div/input");
    private static final By passwordField = By.xpath(".//fieldset[3]/div/div/input");
    private static final By regButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By enterLink = By.xpath(".//a[text()='Войти']");
    private static final By regError = By.xpath(".//p[text()='Некорректный пароль']");

    WebDriver driver;
    User user;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.user = user;
    }

    public void open() {
        driver.get(REGISTRATION_URL);
    }

    //ждём загрузки страницы
    public void waitRegistrationPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    //заполняем поле Имя
    public void fillInName(User user) {
        driver.findElement(nameField).sendKeys(user.getName());
    }

    //заполним почту
    public void fillInEmail(User user) {
        driver.findElement(emailField).sendKeys(user.getEmail());
    }

    //заполним пароль
    public void fillInPassword(User user) {
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    //заполним все поля сразу
    public void fillInData(User user) {
        fillInName(user);
        fillInEmail(user);
        fillInPassword(user);
    }

    //кликнем на кнопку "Зарегистрироваться", когда все поля заполнены верно
    public LoginPage clickRegButton() {
        driver.findElement(regButton).click();
        return new LoginPage(driver);
    }

    //кликнем на кнопку "Зарегистрироваться", когда пароль менее 6 символов
    public void clickRegButtonError() {
        driver.findElement(regButton).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(regError));
        } catch (TimeoutException e) {
            throw new AssertionError("Не появляется сообщение об ошибочном пароле");
        }

    }

    //клик на кнопку "Войти"
    public LoginPage clickOnEnterLink() {
        driver.findElement(enterLink).click();
        return new LoginPage(driver);
    }
}
