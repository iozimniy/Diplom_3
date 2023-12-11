package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    private final By exitButton = By.xpath(".//button[text() = 'Выход']");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By logo = By.xpath(".//div[contains(@class, 'logo')]/a[@href = '/']");

    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Дождаться загрузки профиля и проверить, что он загрузился")
    public void waitProfilePage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        } catch (TimeoutException e) {
            throw new AssertionError("Не открылся профиль!");
        }
    }

    @Step("Нажать на кнопку Конструктор в хедере")
    public MainConstructor clickOnConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        return new MainConstructor(driver);
    }

    @Step("Нажать на логотип в хедере")
    public MainConstructor clickOnLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logo))
                .click();
        return new MainConstructor(driver);
    }

    @Step("Нажать на кнопку Выход")
    public LoginPage clickOnExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(exitButton)).click();
        return new LoginPage(driver);
    }
}


