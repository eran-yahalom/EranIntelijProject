package hooks;

import configurations.db.DBSetupService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import utils.AllureUtils;
import utils.DriverManager;
import utils.ScenarioContext;
import utils.Utils;

import java.time.Duration;
import java.util.Map;
import java.util.logging.Level;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        DBSetupService.init();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");

        options.setCapability("goog:loggingPrefs", Map.of(LogType.BROWSER, Level.ALL));
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);

        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        DriverManager.getDriver().manage().window().maximize();

        String url = Utils.readProperty("url");
        DriverManager.getDriver().get(url);

        try {
            DriverManager.getDriver().findElement(By.cssSelector("#details-button")).click();
            DriverManager.getDriver().findElement(By.cssSelector("#proceed-link")).click();
        } catch (Exception ignored) {
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver currentDriver = DriverManager.getDriver();
        DBSetupService.close();

        if (scenario.isFailed() && currentDriver != null) {
            AllureUtils.attachScreenshot(currentDriver,
                    "Screenshot - " + scenario.getName());

            AllureUtils.attachBrowserLogs(currentDriver);

            AllureUtils.attachPageSource(currentDriver);

            AllureUtils.attachCurrentUrl(currentDriver);
        }

        ScenarioContext.clear();

        if (currentDriver != null) {
            currentDriver.quit();
            DriverManager.unload();
        }
    }
}
