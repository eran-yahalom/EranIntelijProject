package di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;

public class TestInjectorSource implements InjectorSource {

    @Override
    public Injector getInjector() {

        return Guice.createInjector(
                CucumberModules.createScenarioModule(), // 🔥 per scenario scope
                new TestModule()
        );
    }
}