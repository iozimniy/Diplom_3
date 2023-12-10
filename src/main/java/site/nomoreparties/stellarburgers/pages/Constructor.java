package site.nomoreparties.stellarburgers.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

import static site.nomoreparties.stellarburgers.Config.BASE_URL;

public class Constructor {

    //Соусы
    private static final By saucesTab = By.xpath(".//span[text() = 'Соусы']");

    //Начинки
    private static final By fillingsTab = By.xpath(".//span[text() = 'Начинки']");

    //Булки
    private static final By bansTab = By.xpath(".//span[text() = 'Булки']");

    private static final By activeTab = By.xpath(".//div[contains(@class, 'current')]");

    String saucesHeader = "Соусы";
    String fillingsHeader = "Начинки";
    String bunsHeader = "Булки";


    WebDriver driver;

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    //кликаем на соусы
    public void isSauceCurrent() {
        driver.findElement(saucesTab).click();
        assertEquals("Раздел соусов не текущий!",saucesHeader, driver.findElement(activeTab).getText());
    }

    public void isFillingsCurrent() {
        driver.findElement(fillingsTab).click();
        assertEquals("Раздел начинок не текущий", fillingsHeader, driver.findElement(activeTab).getText());
    }

    public void isBunsCurrent() {
        driver.findElement(bansTab).click();
        assertEquals("Раздел булок не текущий!", bunsHeader, driver.findElement(activeTab).getText());
    }

    public void clickOnFillings() {
        driver.findElement(fillingsTab).click();
    }
}
