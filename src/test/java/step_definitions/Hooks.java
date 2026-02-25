package step_definitions;

import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import utils.*;

import java.time.Duration;
import java.util.Map;
import java.util.logging.Level;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().cachePath("wdm-cache").setup();

        ChromeOptions options = new ChromeOptions();
        options.setCapability("goog:loggingPrefs",
                Map.of(LogType.BROWSER, Level.ALL));
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        String url = Utils.readProperty("url");
        driver.get(url);
        try {
            driver.findElement(By.cssSelector("#details-button")).click();
            driver.findElement(By.cssSelector("#proceed-link")).click();
        } catch (Exception ignored) {
        }
    }
//@Before
//public void setup() {
//
//    ChromeOptions options = new ChromeOptions();
//    options.addArguments("--remote-allow-origins=*");
//    options.addArguments("--disable-extensions");
//    options.addArguments("--disable-infobars");
//    options.addArguments("--disable-dev-shm-usage");
//    options.addArguments("--no-sandbox");
//
//    driver = new ChromeDriver(options);
//
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    driver.manage().window().maximize();
//
//    String url = Utils.readProperty("url");
//    driver.get(url);
//
//    try {
//        driver.findElement(By.cssSelector("#details-button")).click();
//        driver.findElement(By.cssSelector("#proceed-link")).click();
//    } catch (Exception ignored) {}
//}

    //    @After
//    public void tearDown(Scenario scenario) {
//
//        if (scenario.isFailed() && driver != null) {
//
//            byte[] screenshot =
//                    ((TakesScreenshot) driver)
//                            .getScreenshotAs(OutputType.BYTES);
//
//            Allure.addAttachment(
//                    "Screenshot - " + scenario.getName(),
//                    "image/png",
//                    new ByteArrayInputStream(screenshot),
//                    ".png"
//            );
//        }
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {

            AllureUtils.attachScreenshot(driver,
                    "Screenshot - " + scenario.getName());

            AllureUtils.attachBrowserLogs(driver);

            AllureUtils.attachPageSource(driver);

            AllureUtils.attachCurrentUrl(driver);
        }
        ScenarioContext.clear();
        if (driver != null) {
            driver.quit();
        }
    }
}
