package PageObjectModels;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CreateAccountPageObjectModel extends LoadableComponent<CreateAccountPageObjectModel>{
    private WebDriver driver;
    private WebDriverWait wait;
    private String urlAuthentication = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private String urlAccountCreation = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";

    //AUTHENTICATION PAGE

    @FindBy(how = How.ID, using = "email_create")
    private WebElement inputEmail;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement submitButton;

    //CREATE ACCOUNT PAGE
    //personal information

//    @FindBy(how = How.ID, using = "uniform-id_gender1")
//    private WebElement registrationCustomerMr;
//    @FindBy(how = How.ID, using = "uniform-id_gender2")
//    private WebElement registrationCustomerMrs;

    @FindBy(how = How.ID, using = "customer_firstname")
    private WebElement personalFirstName;

    @FindBy(how = How.ID, using = "customer_lastname")
    private WebElement personalLastName;

    @FindBy(how = How.ID, using = "email")
    private WebElement email;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement password;

    //address information

    @FindBy(how = How.ID, using = "firstname")
    private WebElement addressFirstName;

    @FindBy(how = How.ID, using = "lastname")
    private WebElement addressLastName;

    @FindBy(how = How.ID, using = "address1")
    private WebElement address;

    @FindBy(how = How.CSS, using = "#id_state > option:nth-child(2)")
    private WebElement city;

    @FindBy(how = How.ID, using = "uniform-id_state")
    private WebElement state;

    @FindBy(how = How.ID, using = "postcode")
    private WebElement postcode;

    @FindBy(how = How.CSS, using = "#id_country > option:nth-child(2)")
    private WebElement country;

    @FindBy(how = How.ID, using = "phone_mobile")
    private WebElement mobilePhone;

    @FindBy(how = How.ID, using = "alias")
    private WebElement alias;

    @FindBy(how = How.ID, using = "submitAccount")
    private WebElement registerButton;

    @Override
    public void load() {
        driver.get(urlAuthentication);
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertEquals("Page is not loaded", urlAuthentication, driver.getCurrentUrl());
    }

    public CreateAccountPageObjectModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public String generateEmail() {
        String result = "";
        Random random = new Random();
        result = "newuser" + random.nextInt() + "@yopmail.com";
        return result;
    }

    public void enterValidEmail(String email) {
        inputEmail.sendKeys(generateEmail());
    }

    public void submitValidEmail() {
        submitButton.click();
    }

    public void enterValidFirstName(){
        personalFirstName.sendKeys("Sherfe");
    }

    public void enterValidLastName(){
        personalLastName.sendKeys("Metkova");
    }
}
