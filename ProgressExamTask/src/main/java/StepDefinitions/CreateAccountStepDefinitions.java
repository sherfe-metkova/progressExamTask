package StepDefinitions;

import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import PageObjectModels.CreateAccountPageObjectModel;

import javax.naming.Context;

public class CreateAccountStepDefinitions {

    private StepDefinitions.Context context;
    private CreateAccountPageObjectModel createAccountPage;
    WebDriver driver = Common.getDriver();

    public CreateAccountStepDefinitions(Context context) {
        this.context = (StepDefinitions.Context) context;
        createAccountPage = new CreateAccountPageObjectModel(driver);
    }

    @And("the user inputs valid data in all required fields")
    public void theUserInputsValidDataInAllRequiredFields() {
        createAccountPage.enterValidFirstName();
        createAccountPage.enterValidLastName();
    }
}
