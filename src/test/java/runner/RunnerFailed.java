package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static br.com.utils.WebDriverHelper.closeDriverWeb;

@CucumberOptions(
        glue = "gherkin",
        plugin = {"json:target/json-cucumber-reports/cukejson.json",
                "testng:target/testng-cucumber-reports/cuketestng.xml",
        },
        strict = true,
        monochrome = true,
        features = "@target/rerun.txt")
public class RunnerFailed extends AbstractTestNGCucumberTests {


    @BeforeClass
    public void setUp() {

    }

    @AfterClass
    public void tearDown() {
        closeDriverWeb();
    }
}
