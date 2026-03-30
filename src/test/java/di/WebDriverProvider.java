package di;

import com.google.inject.Provider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.DriverManager;
import java.io.File;

public class WebDriverProvider implements Provider<WebDriver> {
    @Override
    public WebDriver get() {
        if (DriverManager.getDriver() != null) {
            return DriverManager.getDriver();
        }

        ChromeOptions options = new ChromeOptions();

        // בדיקה: האם אנחנו רצים בתוך ה-Docker של ג'נקינס?
        // אנחנו בודקים אם הנתיב של Chromium (שהתקנת קודם) קיים במערכת
        boolean isDocker = new File("/usr/bin/chromium").exists();

        if (isDocker) {
            // הגדרות ספציפיות ל-Docker (Jenkins)
            options.setBinary("/usr/bin/chromium");
            options.addArguments("--headless=new");      // חובה: הרצה ללא ממשק גרפי
            options.addArguments("--no-sandbox");         // חובה: מאפשר הרצה כ-Root
            options.addArguments("--disable-dev-shm-usage"); // מונע קריסות זיכרון בלינוקס
            WebDriverManager.chromedriver().browserInDocker().setup();
        } else {
            // הגדרות להרצה ללא-Docker (ה-MacBook שלך)
            // כאן לא צריך setBinary, סלניום ימצא את ה-Chrome הרגיל שלך
            WebDriverManager.chromedriver().setup();
        }

        // הגדרות כלליות שעוזרות בשתי הסביבות
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);

        return driver;
    }
}