package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.Context;

import java.util.Map;

public class Registration extends LoadableComponent<Login> {

    private WebDriver driver;
    private WebDriverWait wait;
    Context context;

    private static final String URLAUTHENTICATION = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private static final String URLACCOUNTCREATION = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
    private static final String URLMYACCOUNT = "http://automationpractice.com/index.php?controller=my-account";

    //AUTHENTICATION PAGE

    @FindBy(how = How.CSS, using = "#email_create")
    private WebElement emailCreate;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement submitCreate;

    @FindBy(how = How.ID, using = "create_account_error")
    private WebElement invalidEmailError;

    //CREATE ACCOUNT PAGE
    //personal information

    @FindBy(how = How.ID, using = "uniform-id_gender1")
    private WebElement maleGender;
    @FindBy(how = How.ID, using = "uniform-id_gender2")
    private WebElement femaleGender;

    @FindBy(how = How.ID, using = "customer_firstname")
    private WebElement personalFirstName;

    @FindBy(how = How.ID, using = "customer_lastname")
    private WebElement personalLastName;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement password;

    @FindBy(how = How.ID, using = "days")
    private WebElement dayOfBirth;

    @FindBy(how = How.ID, using = "months")
    private WebElement monthOfBirth;

    @FindBy(how = How.ID, using = "years")
    private WebElement yearOfBirth;

    //address information

    @FindBy(how = How.ID, using = "address1")
    private WebElement address;

    @FindBy(how = How.ID, using = "city")
    private WebElement city;

    @FindBy(how = How.ID, using = "id_state")
    private WebElement state;

    @FindBy(how = How.ID, using = "postcode")
    private WebElement postcode;

    @FindBy(how = How.ID, using = "phone_mobile")
    private WebElement phone;

    @FindBy(how = How.ID, using = "submitAccount")
    private WebElement registerButton;

    @FindBy(how = How.ID, using = "#header > div.nav > div > div > nav > div:nth-child(2) > a")
    private WebElement signOutButton;

    //ERRORS
    @FindBy(how = How.CSS, using = "#center_column > div")
    private WebElement invalidPasswordError;

    @Override
    public void load() {
        driver.get(URLAUTHENTICATION);
    }

    @Override
    public void isLoaded() throws Error {
        Assert.assertEquals("Page is not loaded",
                URLAUTHENTICATION,
                driver.getCurrentUrl());
    }

    public Registration(WebDriver driver, Context context) {
        this.context = context;
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void enterValidRegistrationEmail(Map<String, String> userData) {
        context.username = userData.get("email");
        emailCreate.sendKeys(context.username);
        wait.until(ExpectedConditions.elementToBeClickable(submitCreate));
        System.out.println(context.username);
    }

    public void submitEmail() {
        submitCreate.click();
    }

    public void verifyRedirectedToCreationPage() {
        wait.until(ExpectedConditions.urlContains(URLACCOUNTCREATION));
        Assert.assertEquals("Account creation page is not loaded.",
                URLACCOUNTCREATION, driver.getCurrentUrl());
    }

    public void enterData(Map<String, String> userData) {

        if (userData.get("Gender").equalsIgnoreCase("male")) {
            maleGender.click();
        } else if (userData.get("Gender").equalsIgnoreCase("female")) {
            femaleGender.click();
        } else {
            Assert.fail("Please specify correct gender.");
        }

        personalFirstName.sendKeys(userData.get("FirstName"));
        personalLastName.sendKeys(userData.get("LastName"));

        context.password = userData.get("Password");
        password.sendKeys(context.password);

        dayOfBirth.sendKeys(userData.get("Day"));
        monthOfBirth.sendKeys(userData.get("Month"));
        yearOfBirth.sendKeys(userData.get("Year"));

        address.sendKeys(userData.get("Address"));
        wait.until(ExpectedConditions.elementToBeClickable(city));
        city.sendKeys(userData.get("City"));

        Select selectState = new Select(state);
        selectState.selectByValue(userData.get("State"));

        postcode.sendKeys(userData.get("Code"));
        phone.sendKeys(userData.get("Phone"));
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void verifyIsRedirectedToMyAccount() {
        wait.until(ExpectedConditions.urlContains(URLMYACCOUNT));
        Assert.assertEquals("My account page is not loaded.",
                URLMYACCOUNT, driver.getCurrentUrl());
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public void quit() {
        driver.quit();
    }

    public void enterNonValidEmail(Map<String, String> userData) {
        emailCreate.sendKeys(userData.get("Non Valid Email"));
    }

    public void verifyInvalidEMailMessage() {
        wait.until(ExpectedConditions.textToBePresentInElement(invalidEmailError, "Invalid email address."));
        Assert.assertTrue("Message for invalid email is not shown", invalidEmailError.getText().contains("Invalid email address."));
    }

    public void verifyInvalidPasswordMessage() {
        Assert.assertTrue("Message for invalid password is not displayed", invalidPasswordError.getText().contains("passwd is invalid"));
    }
}
