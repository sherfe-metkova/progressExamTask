package pages;

import org.junit.Assert;
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

public class Login extends LoadableComponent<Login> {

    private WebDriver driver;
    private WebDriverWait wait;
    Context context;

    private static final String URLAUTHENTICATION = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private static final String URLACCOUNTCREATION = "http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
    private static final String URLMYACCOUNT = "http://automationpractice.com/index.php?controller=my-account";

    //AUTHENTICATION PAGE
    //create an account

    @FindBy(how = How.ID, using = "email_create")
    private WebElement inputEmail;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement submitButton;

    //already registered

    @FindBy(how = How.ID, using = "email")
    private WebElement emailLogin;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement passLogin;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement signInButton;

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

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void enterValidEmail(Map<String, String> userData) {
        inputEmail.sendKeys(userData.get("Email"));
        //wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    }

    public void submitValidEmail() {
        submitButton.click();
    }

    public void verifyRedirectedToCreationPage() {
        wait.until(ExpectedConditions.urlContains(URLACCOUNTCREATION));
        Assert.assertEquals("Account creation page is not loaded.",
                URLACCOUNTCREATION, driver.getCurrentUrl());
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

//    public String generateEmail() {
//        String result = "";
//        Random random = new Random();
//        result = "user" + random.nextInt() + "@yopmail.com";
//        return result;
//    }

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

        password.sendKeys(userData.get("Password"));

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

    public void enterPassword() {
        passLogin.sendKeys(context.password);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void enterRegisteredEmail() {
        emailLogin.sendKeys(context.username);
    }
}
