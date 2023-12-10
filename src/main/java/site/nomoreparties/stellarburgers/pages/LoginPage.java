package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.user.User;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.LOGIN_URL;

public class LoginPage {

    private static final By emailField = By.xpath(".//input[@name = 'name']");
    private static final By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private static final By enterButton = By.xpath(".//button[text() = 'Войти']");

    WebDriver driver;
    User user;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.user = user;
    }

    public void open() {
        driver.get(LOGIN_URL);
    }

    //дожидаемся загрузки страницы
    public void waitLoginPage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        } catch (TimeoutException e) {
            throw new AssertionError("Не загружается страница входа");
        }
    }

    //заполняем поле почты
    public void fillInEmail(User user) {
        driver.findElement(emailField).sendKeys(user.getEmail());
    }

    public void fillinPassword(User user) {
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    //заполним все поля сразу
    public void fillInData(User user) {
        fillInEmail(user);
        fillinPassword(user);
    }

    //кликаем на кнопку "Войти"
    public MainConstructor clickEnterButton() {
        driver.findElement(enterButton).click();
        return new MainConstructor(driver);
    }

    //логиним созданного пользователя одним шагом
    public MainConstructor loginUser(User user) {
        open();
        waitLoginPage();
        fillInData(user);
        clickEnterButton();
        return new MainConstructor(driver);
    }
}
