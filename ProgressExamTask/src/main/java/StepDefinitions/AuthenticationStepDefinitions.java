package StepDefinitions;

import PageObjectModels.AuthenticationPageObjectModel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

import javax.naming.Context;
public class AuthenticationStepDefinitions {

    private StepDefinitions.Context context;
    private AuthenticationPageObjectModel authenticatePage;
    private WebDriver driver = Common.getDriver();
    private Common common;

    public AuthenticationStepDefinitions(Context context) {
        this.context = (StepDefinitions.Context) context;
        authenticatePage = new AuthenticationPageObjectModel(driver);
    }

    @Given("user is on authentication page")
    public void userIsOnAuthenticationPage() {
        authenticatePage.load();
        authenticatePage.isLoaded();
    }

    @And("submits a valid Email address")
    public void submitsAValidEmailAddress() {
        context.username=common.generateEmail();
        authenticatePage.enterValidEmail(context.username);
        authenticatePage.submitValidEmail();

    }

}
