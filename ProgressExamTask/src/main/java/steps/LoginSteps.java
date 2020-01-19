package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import pages.Common;
import pages.Login;

import java.util.List;
import java.util.Map;

public class LoginSteps {

    private Context context;
    private Login loginPage;
    WebDriver driver = Common.getDriver();

    public LoginSteps(Context context) {
        this.context = context;
        loginPage = new Login(driver);
    }

    @And("enters already registered Email address and password in Login section")
    public void entersAlreadyRegisteredEmailAddressAndPasswordInLoginSection() {
        loginPage.enterRegisteredEmail();
        loginPage.enterPassword();
    }

    @And("the user presses Login button")
    public void theUserPressesLoginButton() {
        loginPage.clickSignInButton();
    }

    @Then("the user is successfully logged in")
    public void theUserIsSuccessfullyLoggedIn() {
        loginPage.verifyIsRedirectedToMyAccount();
    }
}


