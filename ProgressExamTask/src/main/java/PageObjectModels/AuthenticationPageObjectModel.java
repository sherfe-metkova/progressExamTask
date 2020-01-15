package PageObjectModels;

import StepDefinitions.Common;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPageObjectModel extends LoadableComponent<AuthenticationPageObjectModel>{

    private WebDriver driver;
    private WebDriverWait wait;
    private String urlAuthentication = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private Common common;
    //AUTHENTICATION PAGE

    @FindBy(how = How.ID, using = "email_create")
    private WebElement inputEmail;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement submitButton;

    @Override
    public void load() {
        driver.get(urlAuthentication);
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertEquals("Page is not loaded", urlAuthentication, driver.getCurrentUrl());
    }

    public AuthenticationPageObjectModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void enterValidEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void submitValidEmail() {
        submitButton.click();
    }

}
