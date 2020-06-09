package gherkin.hook;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static br.com.model.GeradorDeMassa.featureName;
import static br.com.model.GeradorDeMassa.updateValuesMassas;
import static br.com.utils.PropertiesHelper.initializeProps;
import static br.com.utils.ReporterUtils.logReport;
import static br.com.utils.WebDriverHelper.testScenario;

public class Hook {

    @Before()
    public void init(Scenario scenario) {
        initializeProps();
        if (featureName == null || !featureName.equals(scenario.getUri().getPath())) {
            updateValuesMassas();
            featureName = scenario.getUri().getPath();
        }
        testScenario.set(scenario);
    }
//
//    @Before("@KAFKA")
//    public void init(Scenario scenario) {
//
//    }

    @After()
    public void cleanUp() {
        logReport();
    }

}
