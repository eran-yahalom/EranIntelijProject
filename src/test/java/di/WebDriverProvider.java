package di;

import com.google.inject.Provider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import utils.DriverManager;

import java.util.Map;
import java.util.logging.Level;

public class WebDriverProvider implements Provider<WebDriver> {
    @Override
    public WebDriver get() {
        // אם כבר קיים דרייבר ל-Thread הזה, החזר אותו במקום ליצור חדש
        if (DriverManager.getDriver() != null) {
            return DriverManager.getDriver();
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        // ... שאר ההגדרות שלך ...

        WebDriver driver = new ChromeDriver(options);
        // שמירה ב-ThreadLocal מיד עם היצירה
        DriverManager.setDriver(driver);
        return driver;
    }
}
