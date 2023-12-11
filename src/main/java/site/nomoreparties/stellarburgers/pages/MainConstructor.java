package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.BASE_URL;

public class MainConstructor {

    private final By placeAnOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By authButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By accountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private final By header = By.xpath(".//h1[text() = 'Соберите бургер']");

    WebDriver driver;

    public MainConstructor(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Дождаться загрузки главной страницы и проверить, что она загрузилась")
    public void waitMainConstructor() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(header));
        } catch (TimeoutException e) {
            throw new AssertionError("Не открывается главная страница");
        }

    }

    @Step("Проверить, что кнопка имеет нужное название")
    public void checkOrderButtonName() {
        try {
            driver.findElement(placeAnOrderButton);
        } catch (NoSuchElementException e) {
            throw new AssertionError("Кнопка не поменяла название!");
        }
    }

    @Step("Нажать на кнопку Войти в аккаунт")
    public LoginPage clickOnAuthButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(authButton)).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Личный Кабинет")
    public LoginPage clickOnAccountButton() {
        driver.findElement(accountButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать на кнопку Личный Кабинет")
    public ProfilePage clickOnProfileButton() {
        driver.findElement(accountButton).click();
        return new ProfilePage(driver);
    }
}
