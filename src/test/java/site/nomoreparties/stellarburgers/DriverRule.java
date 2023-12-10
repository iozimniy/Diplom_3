package site.nomoreparties.stellarburgers;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {
    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else
            setUpChrome();
    }

    private void setUpChrome() {

        System.setProperty("webriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/WebDriver/bin/chromedriver"))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary("E:/Prog/chrome for testing/chrome-win64/chrome.exe")
                .addArguments("start-maximized");

        driver = new ChromeDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setUpYandex() {

        System.setProperty("webriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:/WebDriver/bin/chromedriver"))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary("C:/Users/Китрисс/AppData/Local/Yandex/YandexBrowser/Application/browser.exe")
                .addArguments("start-maximized");

        driver = new ChromeDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void after() {
        driver.quit();
    }
}
