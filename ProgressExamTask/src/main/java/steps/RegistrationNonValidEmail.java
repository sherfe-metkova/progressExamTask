package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import pages.Common;
import pages.Registration;

import java.util.List;
import java.util.Map;


public class RegistrationNonValidEmail {

    private Context context;
    private Registration registerPage;
    WebDriver driver = Common.getDriver();

    public RegistrationNonValidEmail(Context context) {
        this.context = context;
        registerPage = new Registration(driver, context);
    }

    @And("enters invalid Email address in Create account section")
    public void entersInvalidEmailAddressInCreateAccountSection(DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps();
        registerPage.enterNonValidEmail(userData.get(0));
    }

    @Then("the user clicks Submit button")
    public void theUserClicksSubmitButton() {
        registerPage.submitEmail();
    }

    @And("error message is shown")
    public void errorMessageIsShown() {
        registerPage.verifyInvalidEMailMessage();
    }
}
