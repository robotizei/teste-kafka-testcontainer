package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static br.com.utils.WebDriverHelper.closeDriverWeb;

@CucumberOptions(
        glue = "gherkin",
        tags = {"@TESTE"},
        plugin = {"json:target/json-cucumber-reports/cukejson.json",
                "testng:target/testng-cucumber-reports/cuketestng.xml",
                "rerun:target/rerun.txt"
        },
        features = "src/main/feature")
public class Runner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public void setUp() {
    }

    @AfterClass
    public void tearDown() {
        closeDriverWeb();
    }
}
