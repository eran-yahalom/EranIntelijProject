package launchers;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import utils.RetryListener;

@Listeners(RetryListener.class)
@CucumberOptions(
        features = "src/test/resources/features/PopularTags.feature",
        glue = {"step_definitions", "hooks", "di"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber.json"
        },
        tags = "@popularTags"
)
public class PopularTagsLauncher extends BaseLauncher {

    // Only override if you explicitly want to toggle parallel execution per launcher
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}