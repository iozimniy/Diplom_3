package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.FORGOT_PASSWORD_URL;

public class ForgotPasswordPage {

    private final By enterLink = By.xpath(".//a[@href = '/login']");

    WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get(FORGOT_PASSWORD_URL);
    }

    @Step("Дождаться загрузки страницы восстановления пароля и проверить, что она загрузилась")
    public void waitForgotPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterLink));
    }

    @Step("Нажать на кнопку Войти")
    public LoginPage clickOnEnterLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(enterLink)).click();
        return new LoginPage(driver);
    }
}
