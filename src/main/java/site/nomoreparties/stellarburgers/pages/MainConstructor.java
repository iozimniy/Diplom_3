package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.BASE_URL;

public class MainConstructor {

    private static final By placeAnOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private static final By authButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private static final By accountButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    private static final By header = By.xpath(".//h1[text() = 'Соберите бургер']");

    WebDriver driver;

    public MainConstructor(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    //дожидаемся загрузки страницы
    public void waitMainConstructor() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(header));
        } catch (TimeoutException e) {
            throw new AssertionError("Не открывается главная страница");
        }

    }

    //проверяем, что кнопка имеет нужное название
    public void checkOrderButtonName() {
        try {
            driver.findElement(placeAnOrderButton);
        } catch (NoSuchElementException e) {
            throw new AssertionError("Кнопка не поменяла название!");
        }
    }

    //клик на кнопку "Войти в аккаунт"
    public LoginPage clickOnAuthButton() {
        driver.findElement(authButton).click();
        return new LoginPage(driver);
    }

    //клик на кнопку "Личный Кабинет"
    public LoginPage clickOnAccountButton() {
        driver.findElement(authButton).click();
        return new LoginPage(driver);
    }

    public ProfilePage clickOnProfileButton() {
        driver.findElement(accountButton).click();
        return new ProfilePage(driver);
    }
}
