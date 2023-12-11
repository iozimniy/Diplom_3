package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static site.nomoreparties.stellarburgers.Config.BASE_URL;

public class Constructor {

    private final By saucesTab = By.xpath(".//span[text() = 'Соусы']");

    private final By fillingsTab = By.xpath(".//span[text() = 'Начинки']");

    private final By bansTab = By.xpath(".//span[text() = 'Булки']");

    private final By activeTab = By.xpath(".//div[contains(@class, 'current')]");
    private final String saucesTabName = "Соусы";
    private final String fillingsTabName = "Начинки";
    private final String bunsTabName = "Булки";
    WebDriver driver;

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть страницу с конструктором")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Нажать на вкладку Соусы и проверить, что этот раздел стал текущим")
    public void isSauceCurrent() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
        assertEquals("Раздел соусов не текущий!", saucesTabName, driver.findElement(activeTab).getText());
    }

    @Step("Нажать на вкладку Начинки и проверить, что этот раздел стал текущим")
    public void isFillingsCurrent() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
        assertEquals("Раздел начинок не текущий", fillingsTabName, driver.findElement(activeTab).getText());
    }

    @Step("Нажать на вкладку Булки и проверить, что этот раздел стал текущим")
    public void isBunsCurrent() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(bansTab)).click();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.textToBePresentInElementLocated(activeTab, bunsTabName));

        } catch (TimeoutException e) {
            throw new AssertionError("Раздел булок не текущий!");
        }
    }
}
