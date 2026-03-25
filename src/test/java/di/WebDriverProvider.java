package di;

import com.google.inject.Provider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;

import java.util.Map;
import java.util.logging.Level;

public class WebDriverProvider implements Provider<WebDriver> {

    @Override
    public WebDriver get() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.setCapability("goog:loggingPrefs", Map.of(LogType.BROWSER, Level.ALL));
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }
}