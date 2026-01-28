package parallel;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Hooks {

    private  DriverFactory df;
    private  WebDriver driver;
    private  Properties prop;
    private  LoginPage loginPage;

    @Before
    public void setUp() {
        df = new DriverFactory();
        prop = df.initProp();

        // Map thread to browser
        long threadNum = Thread.currentThread().getId() % 3;

        switch ((int)threadNum) {
            case 0:
                prop.setProperty("browser", "chrome");
                break;
            case 1:
                prop.setProperty("browser", "firefox");
                break;
            case 2:
                prop.setProperty("browser", "edge");
                break;
        }

        System.out.println("Thread " + Thread.currentThread().getId() +
                " running on " + prop.getProperty("browser"));

        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
    }

    @After
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
            BrowserContext.removeBrowser();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public Properties getProperties() {
        return prop;
    }

    private Map<String, Object> scenarioData = new HashMap<>();

    public void setScenarioData(String key, Object value) {
        scenarioData.put(key, value);
    }

    public Object getScenarioData(String key) {
        return scenarioData.get(key);
    }
}
