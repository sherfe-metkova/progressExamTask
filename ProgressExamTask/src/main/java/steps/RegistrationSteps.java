package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import pages.Common;
import pages.Registration;

import java.util.List;
import java.util.Map;

public class RegistrationSteps {

    private Context context;
    private Registration registerPage;
    WebDriver driver = Common.getDriver();

    public RegistrationSteps(Context context) {
        this.context = context;
        registerPage = new Registration(driver, context);
    }

    @Given("user is on authentication page")
    public void userIsOnAuthenticationPage() {
        registerPage.load();
        registerPage.isLoaded();
    }

    @And("enters a valid Email address in Create account section")
    public void entersAValidEmailAddressInCreateAccountSection(DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps();
        registerPage.enterValidEmail(userData.get(0));
    }

    @Then("the user is on create account page after submitting the email")
    public void theUserIsOnCreateAccountPage() {
        registerPage.submitEmail();
        registerPage.verifyRedirectedToCreationPage();
    }

    @And("the user inputs below data in the account creation fields")
    public void theUserInputsValidDataInAllRequiredFields(DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps();
        registerPage.enterData(userData.get(0));
    }

    @When("the user presses Register button")
    public void theUserPressesRegisterButton() {
        registerPage.clickRegisterButton();
    }

    @Then("the user is successfully registered")
    public void theUserIsSuccessfullyRegistered() {
        registerPage.verifyIsRedirectedToMyAccount();
        registerPage.deleteCookies();
        registerPage.quit();
    }
}
