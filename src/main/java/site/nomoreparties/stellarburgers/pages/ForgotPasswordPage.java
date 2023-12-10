package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.user.User;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.FORGOT_PASSWORD_URL;

public class ForgotPasswordPage {

    //кнопка "Войти"
    private static final By enterLink = By.xpath(".//a[@href = '/login']");

    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(FORGOT_PASSWORD_URL);
    }

    //ждём загрузки страницы
    public void waitForgotPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterLink));
    }

    //кликаем по ссылке "Войти"
    public LoginPage clickOnEnterLink() {
        driver.findElement(enterLink).click();
        return new LoginPage(driver);
    }
}
