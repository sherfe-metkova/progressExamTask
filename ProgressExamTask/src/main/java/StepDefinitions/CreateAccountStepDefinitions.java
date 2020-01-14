package StepDefinitions;

import Browser.Common;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import PageObjectModels.CreateAccountPageObjectModel;

import javax.naming.Context;

public class CreateAccountStepDefinitions {

    private Context context;
    private CreateAccountPageObjectModel createAccountPage;
    WebDriver driver = Common.getDriver();

    public CreateAccountStepDefinitions(Context context) {
        this.context = context;
        createAccountPage = new CreateAccountPageObjectModel(driver);
    }

    @Given("user is on authentication page and submits a valid email address")
    public void userIsOnPageWithURL() {
        createAccountPage.load();
        createAccountPage.isLoaded();
        context.username = createAccountPage.generateEmail();
        createAccountPage.enterValidEmail(context.username);
        createAccountPage.submitValidEmail();
    }

    @And("the user inputs valid data in all required fields")
    public void theUserInputsValidDataInAllRequiredFields() {
        createAccountPage.enterValidFirstName();
        createAccountPage.enterValidLastName();
    }
}
