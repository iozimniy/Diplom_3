package site.nomoreparties.stellarburgers;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class DriverRule extends ExternalResource {

    private static final String CHROME_WEBDRIVER = "C:/WebDriver/bin/chromedriver";
    private static final String CHROME_BROWSER_PATH = "E:/Prog/chrome for testing/chrome-win64/chrome.exe";
    private static final String YANDEX_WEBDRIVER = "C:/WebDriver/bin/yandexdriver.exe";
    private static final String YANDEX_BROWSER_PATH = "C:/Users/Китрисс/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";
    WebDriver driver;

    @Override
    protected void before() throws Throwable {
        if ("chrome".equals(System.getProperty("browser")))
            setUpChrome();
        else
            setUpYandex();
    }

    private void setUpChrome() {

        System.setProperty("webriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(CHROME_WEBDRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(CHROME_BROWSER_PATH)
                .addArguments("start-maximized");

        driver = new ChromeDriver(service, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void setUpYandex() {

        System.setProperty("webriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(YANDEX_WEBDRIVER))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary(YANDEX_BROWSER_PATH)
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
