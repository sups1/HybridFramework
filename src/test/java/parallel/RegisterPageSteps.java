package parallel;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;
import io.cucumber.java.en.*;

import org.junit.Assert;

public class RegisterPageSteps {

    private Hooks hooks;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    private boolean regSuccess;

    public RegisterPageSteps(Hooks hooks) {
        this.hooks = hooks;
        loginPage = hooks.getLoginPage();
    }

    @Given("the user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page() {
        registerPage = loginPage.navigateToRegisterPage();
    }

    @When("the user enters {string}, {string}, {string}, {string} and subscribes {string}")
    public void the_user_enters_and_subscribes(String firstName, String lastName, String telephone, String password, String subscribe) {
        regSuccess = registerPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), telephone, password, subscribe);

    }

    @Then("the user registration should be successful")
    public void the_user_registration_should_be_successful() {
        Assert.assertTrue(regSuccess);
    }

    @When("the user enters data from excel file")
    public void theUserEntersDataFromExcelFile() {
       Object data[][] = ExcelUtil.getTestData("register");

    }
}
