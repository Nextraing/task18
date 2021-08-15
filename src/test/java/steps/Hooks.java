package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static utils.Log.LOG;

public class Hooks {

    @Before
    public void beforeTest(Scenario scenario) {

        LOG.info("Старт сценария '" + scenario.getName() +"'");
    }

    @After
    public void afterTest(Scenario scenario) {

        if (scenario.isFailed()) {

            LOG.warn("Сценарий '" + scenario.getName() + "' провалился.");

        } else {

            LOG.info("Сценарий '" + scenario.getName() + "' выполнен успешно.");
        }
    }
}
