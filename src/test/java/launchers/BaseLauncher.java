package launchers;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeSuite;
import java.io.File;

public class BaseLauncher extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("--- התחלת הרצת Suite אוטומציה ---");

        // יצירת תיקיית Screenshots אם היא לא קיימת
        File screenshotDir = new File("target/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }
}