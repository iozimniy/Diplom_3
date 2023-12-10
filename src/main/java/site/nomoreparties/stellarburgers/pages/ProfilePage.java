package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static site.nomoreparties.stellarburgers.Config.PROFILE_URL;

public class ProfilePage {

    //кнопка выход
    private static final By exitButton = By.xpath(".//button[text() = 'Выход']");
    private static final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");

    WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(PROFILE_URL);
    }

    //ждём загрузки профиля
    public void waitProfilePage() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        } catch (TimeoutException e) {
            throw new AssertionError("Не открылся профиль!");
        }
    }

    //переходим на главную через клик по конструктору в хедере
    public MainConstructor clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        return new MainConstructor(driver);
    }

    //кликаем на кнопку выход
    public LoginPage clickOnExitButton() {
        driver.findElement(exitButton).click();
        return new LoginPage(driver);
    }
}


