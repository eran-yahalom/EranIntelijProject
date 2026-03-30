package launchers;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.RetryAnalyzer;

import java.io.File;

@Log4j2
public class BaseLauncher extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        log.info("--- Starting Test Suite Execution ---");

        File screenshotDir = new File("target/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
    }

    @Override
    @Test(
            groups = "cucumber",
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios",
            retryAnalyzer = RetryAnalyzer.class
    )
    public void runScenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
        log.info("Starting Scenario: {}", pickle.getPickle().getName());

        try {
            super.runScenario(pickle, cucumberFeature);
            log.info("Finished Scenario: {} - SUCCESS", pickle.getPickle().getName());
        } catch (Throwable t) {
            log.error("Scenario FAILED: {} | Error: {}", pickle.getPickle().getName(), t.getMessage());
            throw t;
        }
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}