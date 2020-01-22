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


    public RegistrationSteps(Context context) {
        WebDriver driver = Common.getDriver();
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
        registerPage.enterValidRegistrationEmail(userData.get(0));
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

    @Then("user is presented with error message")
    public void userIsPresentedWithErrorMessage() {
        registerPage.verifyInvalidPasswordMessage();
        registerPage.quit();
    }

    @Then("the user is successfully registered")
    public void theUserIsSuccessfullyRegistered() {
        registerPage.verifyIsRedirectedToMyAccount();
        registerPage.deleteCookies();
        registerPage.quit();
    }

    @And("enters invalid Email address in Create account section")
    public void entersInvalidEmailAddressInCreateAccountSection(DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps();
        registerPage.enterNonValidEmail(userData.get(0));
    }

    @Then("the user clicks Submit button")
    public void theUserClicksSubmitButton() throws InterruptedException {
        registerPage.submitEmail();
    }

    @And("error message is shown")
    public void errorMessageIsShown() {
        registerPage.verifyInvalidEMailMessage();
        registerPage.quit();
    }
}
