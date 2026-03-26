package hooks;

import com.google.inject.Inject;
import configurations.EnvManager;
import configurations.db.DBSetupService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;
import utils.DriverManager;
import utils.ScenarioContext;
import utils.Utils;

import java.time.Duration;

public class Hooks {

    private final WebDriver driver;

    @Inject
    public Hooks(WebDriver driver) {
        this.driver = driver;
    }

    @Before
    public void setup() {
        DBSetupService.init(EnvManager.get().getSchema(), EnvManager.get().getDbName(), null, null);
        DriverManager.setDriver(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get(EnvManager.get().getUrl());

        try {
            driver.findElement(By.cssSelector("#details-button")).click();
            driver.findElement(By.cssSelector("#proceed-link")).click();
        } catch (Exception ignored) {
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            DBSetupService.close();

            if (scenario.isFailed() && driver != null) {
                AllureUtils.attachScreenshot(driver, "Screenshot - " + scenario.getName());
                AllureUtils.attachBrowserLogs(driver);
                AllureUtils.attachPageSource(driver);
                AllureUtils.attachCurrentUrl(driver);
            }
        } finally {
            ScenarioContext.clear();
            if (driver != null) {
                driver.quit();
                DriverManager.unload();
            }
        }
    }
}