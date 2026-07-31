package launchers;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import utils.RetryListener;

@Listeners(RetryListener.class)
@CucumberOptions(
        features = "src/test/resources/features/CheckOut.feature",
        glue = {"step_definitions", "hooks", "di"},
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber.json"
        },
        tags = "@CheckOut"
)
public class CheckOutLauncher extends BaseLauncher {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
