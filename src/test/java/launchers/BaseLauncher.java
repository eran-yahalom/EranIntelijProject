package launchers;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;

import java.io.File;

public class BaseLauncher extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Start running the test suite...");

        File screenshotDir = new File("target/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }

    /**
     * דריסת מתודת ההרצה של Cucumber כדי להזריק לה את ה-RetryAnalyzer.
     * זה מבטיח שכל Launcher שיורש מ-BaseLauncher יקבל את מנגנון ה-Retry באופן אוטומטי.
     */
    @Override
    @Test(
            groups = "cucumber",
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios",
            retryAnalyzer = RetryAnalyzer.class
    )
    public void runScenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        super.runScenario(pickle, cucumberFeature);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}