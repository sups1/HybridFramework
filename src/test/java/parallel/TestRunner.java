package parallel;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.*;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/parallel",
        glue = {"parallel"},
        plugin = {
                "com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
                //"pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports/Cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"

        },
        dryRun = false,
        monochrome = false
)

//below line connects cucumber with testNG
public class TestRunner extends AbstractTestNGCucumberTests {

        @BeforeMethod(alwaysRun = true)
        @Parameters("browser")
        public void captureBrowser(String browser) {
                BrowserContext.setBrowser(browser);
        }

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

}

