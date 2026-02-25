package launchers;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features", // נתיב לקבצי ה-feature
        glue = {"step_definitions"},                // שם החבילה של ה-Hooks והצעדים (Case Sensitive!)
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber.json"
        },
        tags = "@logIn or @ui"                        // מריץ טסטים עם התגיות הללו
)
public class APILauncher extends BaseLauncher {

    @Override
    @DataProvider(parallel = true) // ניתן לשנות ל-true להרצה מקבילית
    public Object[][] scenarios() {
        return super.scenarios();
    }
}