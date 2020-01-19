//package pages;
//
//import org.junit.Assert;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.logging.LogEntries;
//import org.openqa.selenium.logging.LogEntry;
//import org.openqa.selenium.logging.LogType;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class WebDriver implements org.openqa.selenium.WebDriver {
//
//    private static final String SELECTED = "selected";
//    private static final String EXPECTED = "Expected: ";
//    private static final String TO_CONTAIN = " to contain ";
//    private static final String TO_NOT_CONTAIN = " to not contain ";
//    private static final String BUT_FOUND = " but found ";
//    private static final String IS_MOBILE_PROPERTY = "mobile";
//    private static final String IS_LOCAL_MOBILE_PROPERTY = "localMobile";
//    private static final String NOT_FOUND_OPTION = "Could not find / click an option with value: ";
//    private static final String JS_ARGUMENT_CLICK = "arguments[0].click();";
//    protected org.openqa.selenium.WebDriver webDriver;
//    protected JavascriptExecutor jsExecutor;
//    protected Actions actions;
//    public static final long TIMEOUT = 30;
//    public static final long ONE_SECOND_TIMEOUT = 1000;
//    protected Boolean isAuthenticated = false;
//    private static final String ERROR_LOGGED = "Error logged: ";
//    private String chrome = "chrome";
//    private String internetExplorer = "internet explorer";
//    private String firefox = "firefox";
//    private String safari = "safari";
//    private String edge = "MicrosoftEdge";
//    private static final List<String> BROWSERS_USING_JS = Arrays.asList("internet explorer");
//    private static final String INTEGER_REGEX = "\\d+([,. ]\\d{3})*([,.]\\d{2})?";
//    private Map<String, Integer> browsersWindowViewportConstants;
//    private int currentPackageSize = 0;
//    private Field reflectedField;
//    private Class reflectedClass;
//    private By feedBackLink = By.cssSelector("[id*=slider-control]");
//    private static final String SIGNUP_COOKIE = "UserSignUpAndSave";
//    private static final String TRACKING_COOKIE = "notice_preferences";
//
//    public WebDriver() {
//        this.webDriver = webDriver;
////        this.jsExecutor = (JavascriptExecutor) webDriver;
////        this.actions = new Actions(this.webDriver);
////        this.browsersWindowViewportConstants = new HashMap<>();
////        browsersWindowViewportConstants.put(chrome, 17);
////        browsersWindowViewportConstants.put(internetExplorer, 17);
////        browsersWindowViewportConstants.put(firefox, 17);
////        browsersWindowViewportConstants.put(edge, 16);
//    }
//
//    public void setIsAuthenticated(boolean value) {
//        this.isAuthenticated = value;
//    }
//
//    public RemoteWebDriver getRemoteWebDriver() {
//        return (RemoteWebDriver) webDriver;
//    }
//
//    public boolean isChrome() {
//        return chrome.equals(getRemoteWebDriver().getCapabilities().getBrowserName());
//    }
//
//    public boolean isInternetExplorer() {
//        return internetExplorer.equals(getRemoteWebDriver().getCapabilities().getBrowserName());
//    }
//
//    public boolean isEdge() {
//        return edge.equals(getRemoteWebDriver().getCapabilities().getBrowserName());
//    }
//
//    public boolean isFirefox() {
//        return firefox.equals(getRemoteWebDriver().getCapabilities().getBrowserName());
//    }
//
//    public boolean isSafari() {
//        return safari.equals(getRemoteWebDriver().getCapabilities().getBrowserName());
//    }
//
//    public static boolean isMobile() {
//        return Boolean.parseBoolean(System.getProperty(IS_MOBILE_PROPERTY, "false"));
//    }
//
//    public boolean isLocalMobile() {
//        return Boolean.parseBoolean(System.getProperty(IS_LOCAL_MOBILE_PROPERTY));
//    }
//
//    public boolean isNuPDPProperty() {
//        return "true".equals(System.getProperty("nuPDP"));
//    }
//
//    public void moveToElement(WebElement element, int xOffset, int yOffset) {
//        actions.moveToElement(element, xOffset, yOffset);
//        actions.perform();
//    }
//
//    /**
//     * This method selects an option from a drop down via option text
//     *
//     * @param selectElement - the select element which will be used
//     * @param selectOption  - the options which will be used for checking
//     * @param value         - the value which will be selected
//     */
//    public void selectOptionFromDropdown(By selectElement, By selectOption, String value) {
//        WebElement firstVisibleSelect = null;
//        for (WebElement element : findElements(selectElement)) {
//            if (element.isDisplayed()) {
//                firstVisibleSelect = element;
//                break;
//            }
//        }
//        Assert.assertTrue("Element with locator: " + selectElement + " is not found", firstVisibleSelect != null);
//        if (value != null) {
//            firstVisibleSelect.click();
//            sleep(200);
//            boolean isSuccessful = isFirefox() ? iterateOverAllOptionsAndClickByJS(selectOption, value)
//                    : iterateOverAllOptions(selectOption, value);
//            Assert.assertTrue(NOT_FOUND_OPTION + value, isSuccessful);
//        }
//    }
//
//    /**
//     * This method selects an option from a drop down via option text
//     *
//     * @param selectElement - the select element which will be used
//     * @param selectOption  - the options which will be used for checking
//     * @param value         - the value which will be selected
//     */
//    public void iterateSelectOptionsAndClickByJS(By selectElement, By selectOption, String value) {
//        if (value != null) {
//            click(selectElement);
//            sleep(200);
//            boolean isSuccessful = iterateOverAllOptionsAndClickByJS(selectOption, value);
//            Assert.assertTrue(NOT_FOUND_OPTION + value, isSuccessful);
//        }
//    }
//
//    /**
//     * Selects an option from a dropdown by visible text, when first scrolls to the option.
//     *
//     * @param selectElement - the locator of the select element
//     * @param selectOptions - the locator of all select options
//     * @param visibleText   - the text of the option that will be selected
//     */
//    public void scrollAndSelect(By selectElement, By selectOptions, String visibleText) {
//        click(selectElement);
//        selectSize(selectOptions, visibleText);
//    }
//
//    public void selectSize(By selectOptions, String visibleText) {
//        boolean isOptionFound = false;
//        sleep(200);
//
//        List<WebElement> allOptions = findElements(selectOptions);
//        iterateOverAllOptions(allOptions, visibleText, isOptionFound);
//    }
//
//    public void selectSize(List<WebElement> allOptions, String visibleText) {
//        boolean isOptionFound = false;
//        sleep(200);
//        iterateOverAllOptions(allOptions, visibleText, isOptionFound);
//    }
//
//    private void iterateOverAllOptions(List<WebElement> allOptions, String visibleText, boolean isOptionFound) {
//        for (WebElement currentOption : allOptions) {
//            Actions scrollAndSelectAction = new Actions(webDriver);
//            scrollAndSelectAction.moveToElement(currentOption);
//            //action.Peform() is not supported in ChromeDriver 76
//            //scrollAndSelectAction.perform();
//            String currentOptionText = currentOption.getText().trim();
//            String currentOptionClass = currentOption.getClass().toString();
//            if (visibleText.trim().equals(currentOptionText)) {
//                if (!currentOptionClass.contains(SELECTED)) {
//                    scrollAndSelectAction.click();
//                    scrollAndSelectAction.perform();
//                }
//                isOptionFound = true;
//                break;
//            }
//        }
//        Assert.assertTrue("Option " + visibleText + " was not found.", isOptionFound);
//    }
//
//    /**
//     * This method can be used for Date Of Birth drop downs. They are
//     *
//     * @param selectElement - the select element which will be used
//     * @param selectOption  - the options which will be used for checking
//     * @param value         - the value which will be selected
//     * @param index         - the drop down index (0 / 1 / 2)
//     */
//    public void selectDOB(By selectElement, By selectOption, String value, int index) {
//        if (value != null) {
//            findElements(selectElement).get(index).click();
//            sleep(200);
//            boolean isSuccessful = iterateOverAllOptions(selectOption, value);
//            Assert.assertTrue(NOT_FOUND_OPTION + value, isSuccessful);
//        }
//    }
//
//    /**
//     * Method for clicking on option identified via text value. If there are several options with such text it will
//     * execute the action on the first found element.
//     *
//     * @param selectOption - the options which will be used for checking
//     * @param value        - the value which will be selected
//     * @return - (true / false) - depending on the result whether the element was found or not found
//     */
//    public boolean iterateOverAllOptions(By selectOption, String value) {
//        for (WebElement element : findElements(selectOption)) {
//            if (value.equalsIgnoreCase(element.getText().trim())) {
//                clickElement(element);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Method for clicking on option identified via text value. If there are several options with such text it will
//     * execute the action on the first found element.
//     *
//     * @param selectOption - the options which will be used for checking
//     * @param value        - the value which will be selected
//     * @return - (true / false) - depending on the result whether the element was found or not found
//     */
//    public boolean iterateOverAllOptionsAndClickByJS(By selectOption, String value) {
//        for (WebElement element : findElements(selectOption)) {
//            if (value.equalsIgnoreCase(element.getText())) {
//                clickByJavascript(element);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    protected String getDevelopmentPass() {
//        return "deVec0m2018";
//    }
//
//    private String getDevelopmentPassDevec0m() {
//        return "devec0m";
//    }
//
//    private String getStagingPass() {
//        return "stGec0m2018";
//    }
//
//    /**
//     * Open link relative on store front path in configuration.
//     *
//     * @param url - provide URL/pipeline
//     */
//    public void openRootBasedUrl(String host, String path, String url, String protocol) {
//        String storefront = "storefront";
//        String password = getDevelopmentPass();
//        if (host != null && host.toLowerCase().contains("staging")) {
//            password = getStagingPass();
//        }
//        loadBasedUrl(url);
//    }
//
//    public void passSafariWarningWindows(String url) {
//        By warningDetailsButton = By.cssSelector("#detailsButton");
//        By warningVisitWebSite = By.cssSelector("#detailsText a[onclick*='visitInsecureWebsite']");
//        if (!findElements(warningDetailsButton).isEmpty()) {
//            click(warningDetailsButton);
//            waitForAjaxtoComplete();
//            click(warningVisitWebSite);
//            webDriver.switchTo().alert().accept();
//            webDriver.get(url);
//            waitForAjaxtoComplete();
//            sleep(4000);
//        }
//    }
//
//    /**
//     * Based URL load private method
//     *
//     * @param url
//     */
//    public void loadBasedUrl(String url) {
//        webDriver.get(url);
//    }
//
//    /**
//     * Method created specifically for analytics purposes. They require a page
//     * to open/load only once, when the tests are launched
//     */
//    public void openRootBasedURLOnce(String storefrontHost, String storefrontPath, String url) {
//        String username = "storefront";
//        String password = getDevelopmentPass();
//
//        String storePath = "".equals(storefrontPath) ? "/" : storefrontPath;
//        String baseURL = "https://" + username + ":" + password + "@" + storefrontHost;
//        webDriver.get(baseURL + storePath + url);
//    }
//
//    protected void openRootBasedUrlIE(String storefrontHost, String storefrontPath, String url, String protocol, String username, String password) {
//        /**
//         * Below check is necessary because of
//         * http://support.microsoft.com/kb/832414 There is an issue with XMLhttp
//         * requests with embedded credentials in IE For example if PDP is opened
//         * in IE with embedded credentials - add to cart button won't work
//         *
//         */
//        if (!isAuthenticated) {
//            String baseURL = "https" + "://" + username + ":" + password + "@" + storefrontHost;
//            String storePath = "".equals(storefrontPath) ? "/" : storefrontPath;
//            webDriver.get(baseURL + storePath + url);
//            webDriver.navigate().refresh();
//            isAuthenticated = true;
//        }
//
//        /*
//         * After the page is opened with embedded credentials they are persisted
//         * and the page is opened again without them to workaround
//         * http://support.microsoft.com/kb/832414
//         */
//        String baseURL = protocol + "://" + storefrontHost;
//        String storePath = "".equals(storefrontPath) ? "/" : storefrontPath;
//        webDriver.get(baseURL + storePath + url);
//    }
//
//    /**
//     * Click on a locator
//     *
//     * @param locator
//     */
//    public void click(By locator) {
//        clickAndHandleStaleElementReferenceException(locator);
//    }
//
//    /**
//     * Double click on a locator
//     *
//     * @param locator
//     */
//    public void doubleClick(By locator) {
//        doubleClickAndHandleStaleElementReferenceException(locator);
//    }
//
//    /**
//     * This methods waits for an element to be displayed.
//     * And after that clicks on it.
//     * In case of StaleElementReferenceException there are 2 reties more.
//     *
//     * @param locator
//     */
//    private void clickAndHandleStaleElementReferenceException(By locator) {
//        waitForElementToLoad(locator, TIMEOUT);
//        try {
//            findAndClickElement(webDriver.findElement(locator));
//        } catch (StaleElementReferenceException e1) {
//            //
//            // LOG.debug(ERROR_LOGGED, e1);
//            try {
//                findAndClickElement(findElement(locator));
//            } catch (StaleElementReferenceException e2) {
//                //LOG.debug(ERROR_LOGGED, e2);
//                findAndClickElement(findElement(locator));
//            }
//        }
//    }
//
//    /**
//     * This methods waits for an element to be displayed.
//     * And after that double clicks on it.
//     * In case of StaleElementReferenceException there are 2 reties more.
//     *
//     * @param locator
//     */
//    private void doubleClickAndHandleStaleElementReferenceException(By locator) {
//        waitForElementToLoad(locator, TIMEOUT);
//        Actions actions = new Actions(this.webDriver);
//        try {
//            WebElement element = findElement(locator);
//            actions.doubleClick(element).perform();
//        } catch (StaleElementReferenceException e1) {
//            //LOG.debug(ERROR_LOGGED, e1);
//            try {
//                WebElement element = findElement(locator);
//                actions.doubleClick(element).perform();
//            } catch (StaleElementReferenceException e2) {
//                //LOG.debug(ERROR_LOGGED, e2);
//                WebElement element = findElement(locator);
//                actions.doubleClick(element).perform();
//            }
//        }
//    }
//
//    /**
//     * Type into input element which is in Iframe
//     *
//     * @param iFrameLocator  - Iframe locator
//     * @param elementLocator - element locator
//     * @param data           - expected text value
//     */
//    public void typeByLocatorIFrame(By iFrameLocator, By elementLocator, String data) {
//        webDriver.switchTo().frame(findElement(iFrameLocator));
//        typeByLocator(elementLocator, data);
//        webDriver.switchTo().defaultContent();
//    }
//
//    /**
//     * Click into element in Iframe
//     *
//     * @param iFrameLocator  - Iframe locator
//     * @param elementLocator - element locator
//     */
//    public void clickByLocatorIFrame(By iFrameLocator, By elementLocator) {
//        webDriver.switchTo().frame(findElement(iFrameLocator));
//        click(elementLocator);
//        webDriver.switchTo().defaultContent();
//    }
//
//    /**
//     * Select text from drop down element which is in Iframe
//     *
//     * @param iFrameLocator  - Iframe locator
//     * @param elementLocator - element locator
//     * @param data           - expected text value
//     */
//    public void selectByVisibleTextIframe(By iFrameLocator, By elementLocator, String data) {
//        webDriver.switchTo().frame(findElement(iFrameLocator));
//        selectByVisibleText(elementLocator, data);
//        webDriver.switchTo().defaultContent();
//    }
//
//
//    /**
//     * Type in a locator
//     *
//     * @param locator - provide locator
//     * @param value   - provide value
//     */
//    public void typeByLocator(By locator, String value) {
//        if (value != null) {
//            typeByWebElement(webDriver.findElement(locator), value);
//        }
//    }
//
//    /**
//     * Type in a web element
//     *
//     * @param e     - provide web element
//     * @param value - provide value
//     */
//    public void typeByWebElement(WebElement e, String value) {
//        if (isMobile()) {
//            e.clear();
//        } else {
//            e.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        }
//        sleep(300);
//        if (System.getProperty("browser").equalsIgnoreCase("IE")) {
//            jsExecutor.executeScript(String.format("arguments[0].value='%s';", value), e);
//        } else {
//            e.sendKeys(value);
//        }
//    }
//
//    /**
//     * Find element by locator
//     *
//     * @param locator
//     * @return WebElement
//     */
//    public WebElement findElement(By locator) {
//        return webDriver.findElement(locator);
//    }
//
//    @Override
//    public String getPageSource() {
//        return null;
//    }
//
//    @Override
//    public void close() {
//
//    }
//
//    @Override
//    public void quit() {
//
//    }
//
//    @Override
//    public Set<String> getWindowHandles() {
//        return null;
//    }
//
//    @Override
//    public String getWindowHandle() {
//        return null;
//    }
//
//    @Override
//    public TargetLocator switchTo() {
//        return null;
//    }
//
//    @Override
//    public Navigation navigate() {
//        return null;
//    }
//
//    @Override
//    public Options manage() {
//        return null;
//    }
//
//    @Override
//    public void get(String s) {
//
//    }
//
//    @Override
//    public String getCurrentUrl() {
//        return null;
//    }
//
//    @Override
//    public String getTitle() {
//        return null;
//    }
//
//    /**
//     * Find list of elements by locator
//     *
//     * @param locator - provide locator
//     * @return List WebElement
//     */
//    public List<WebElement> findElements(By locator) {
//        return webDriver.findElements(locator);
//    }
//
//    /**
//     * Select an element (check-box, radio button) by provided locator
//     *
//     * @param locator - provide locator
//     */
//    public void selectElement(By locator) {
//        if (!webDriver.findElement(locator).isSelected()) {
//            click(locator);
//            sleep(ONE_SECOND_TIMEOUT);
//        }
//    }
//
//    /**
//     * Select an element (check-box, radio button) by provided locator
//     *
//     * @param locator - provide locator
//     */
//    public void selectElementByJavaScript(By locator) {
//        if (!webDriver.findElement(locator).isSelected()) {
//            clickByJavascript(locator);
//            sleep(ONE_SECOND_TIMEOUT);
//        }
//    }
//
//    /**
//     * Select an element (check-box, radio button) with JavaScript by provided locator
//     *
//     * @param locator - provide locator
//     */
//    public void clickByJavascript(By locator) {
//        jsExecutor.executeScript(JS_ARGUMENT_CLICK, webDriver.findElement(locator));
//    }
//
//    /**
//     * Click on provided locator (check-box, radio button) by JavaScript
//     *
//     * @param locator - provide locator
//     */
//    public void clickByJavascript(WebElement locator) {
//        jsExecutor.executeScript(JS_ARGUMENT_CLICK, locator);
//    }
//
//    /**
//     * Select an element (check-box, radio button) by provided locator
//     *
//     * @param locator - provide locator
//     * @param select  - true/false, whether to select or deselect the element
//     */
//
//    public void selectElementByJavaScript(By locator, boolean select) {
//        if (select != webDriver.findElement(locator).isSelected()) {
//            clickByJavascript(locator);
//            sleep(ONE_SECOND_TIMEOUT);
//        }
//    }
//
//    /**
//     * Select/Deselect check box using two locators
//     * <pre>
//     *  1. one for checking that element is selected
//     *  2. one checkbox element
//     *  </pre>
//     *
//     * @param locator1 - locator of visible element
//     * @param locator2 - locator of checkbox
//     * @param selected - True|False whether to be selected
//     */
//    public void selectCheckbox(By locator1, By locator2, boolean selected) {
//        if (selected) {
//            if (!webDriver.findElement(locator1).isSelected()) {
//                click(locator2);
//                sleep(ONE_SECOND_TIMEOUT);
//            }
//        } else {
//            if (webDriver.findElement(locator1).isSelected()) {
//                click(locator2);
//                sleep(ONE_SECOND_TIMEOUT);
//            }
//
//        }
//    }
//
//    /**
//     * Select / Deselect checkbox
//     *
//     * @param locator  - checkbox locator
//     * @param selected - True|False whether to be selected
//     */
//    public void selectCheckbox(By locator, boolean selected) {
//        selectCheckbox(locator, locator, selected);
//    }
//
//    /**
//     * Sometimes, when the checkbox element is located in an iframe, the click() method doesn`t work.
//     * This method is the same as selectCheckbox(By locator, boolean selected) with the difference that here is used webelement.click()
//     * instead of click(webelement)
//     *
//     * @param locator  - locator of the checkbox element
//     * @param selected - whether to select or deselect the element
//     */
//    public void selectCheckboxIFrame(By locator, boolean selected) {
//        WebElement checkbox = findElement(locator);
//        if (selected) {
//            if (!checkbox.isSelected()) {
//                checkbox.click();
//                sleep(ONE_SECOND_TIMEOUT);
//            }
//        } else {
//            if (checkbox.isSelected()) {
//                checkbox.click();
//                sleep(ONE_SECOND_TIMEOUT);
//            }
//
//        }
//    }
//
//    /**
//     * Selects or deselects checkbox field.
//     * First, checks if the state of the checkbox is different from @param select.
//     *
//     * @param locator - the locator of the formfield that contains ffCheckboxWrapper and the span that needs to be clicked
//     *                Example: locator will be By.cssSelector(".termsandconditions") for Terms and Conditions checkbox
//     *                when the element is <div class="formfield termsandconditions">
//     * @param select  - true or false - whether to select or deselect the checkbox
//     */
//    public void selectffCheckbox(By locator, boolean select) {
//        boolean isCurrentlySelected = false;
//        WebElement checkboxField = null;
//        for (WebElement e : findElements(locator)) {
//            if (e.isDisplayed()) {
//                checkboxField = e;
//                break;
//            }
//        }
//        try {
//            checkboxField.findElement(By.cssSelector(".ffCheckboxWrapper.on"));
//            isCurrentlySelected = true;
//        } catch (NoSuchElementException ex) {
//            //LOG.info(ex);
//        } catch (NullPointerException ex) {
//            throw new NoSuchElementException("Couldn't find element by locator" + locator);
//        }
//
//
//        if (select != isCurrentlySelected) {
//            checkboxField.findElement(By.cssSelector(".ffCheckboxWrapper")).click();
//            waitForAjaxtoComplete();
//        }
//
//    }
//
//    public void selectffCheckbox(By locator1, By locator2, boolean select) {
//        if (select != webDriver.findElement(locator1).isSelected()) {
//            scrollToElement(locator2, -100);
//            click(locator2);
//            sleep(ONE_SECOND_TIMEOUT);
//        }
//
//    }
//
//    public void clickAndSelectFromDropdown(By elementToClick, By allOptionsLocator, String optionToSelect) {
//        click(elementToClick);
//        waitForAjaxtoComplete();
//        Assert.assertTrue(iterateOverAllOptions(allOptionsLocator, optionToSelect));
//        waitForAjaxtoComplete();
//    }
//
//    /**
//     * Returns true|false whether a checkbox of type FF is currently selected.
//     *
//     * @param checkbox - checkbox webelement. It should contain an element with class .ffCheckboxWrapper
//     * @return - true|false - selected|not selected
//     */
//    public boolean isFFCheckboxSelected(WebElement checkbox) {
//        boolean isCurrentlySelected = false;
//        try {
//            checkbox.findElement(By.cssSelector(".ffCheckboxWrapper.on"));
//            isCurrentlySelected = true;
//        } catch (NoSuchElementException ex) {
//            //LOG.info(ex);
//        }
//        return isCurrentlySelected;
//    }
//
//    /**
//     * Returns true|false whether a checkbox of type FF is currently selected.
//     *
//     * @param locator - the locator of the formfield that contains ffCheckboxWrapper and the span that needs to be clicked
//     *                Example: locator will be By.cssSelector(".termsandconditions") for Terms and Conditions checkbox
//     *                when the element is <div class="formfield termsandconditions">
//     * @return - true|false - selected|not selected
//     */
//    public boolean isFFCheckboxSelected(By locator) {
//        return isFFCheckboxSelected(findElement(locator));
//    }
//
//    /**
//     * * @param locator - provide locator
//     *
//     * @param attribute - attribute of existing element
//     * @return - String  of attribute value
//     */
//    public String getTextFromLocatorAttribute(By locator, String attribute) {
//        try {
//            return findElement(locator).getAttribute(attribute);
//        } catch (NoSuchElementException ex) {
//            //LOG.info(ex);
//        }
//        return null;
//    }
//
//    /**
//     * Deselect an element (check-box, radio button) by provided locator
//     *
//     * @param locator - provide locator
//     */
//    public void deselectElement(By locator) {
//        if (webDriver.findElement(locator).isSelected()) {
//            click(locator);
//            sleep(ONE_SECOND_TIMEOUT);
//        }
//    }
//
//    /**
//     * An expectation for checking that an element is present on the DOM of a
//     * page and visible. Visibility means that the element is not only displayed
//     * but also has a height and width that is greater than 0.
//     *
//     * @param locator          - provide locator
//     * @param timeOutInSeconds - provide timeout in seconds
//     */
//    public boolean waitForElementToLoad(By locator, long timeOutInSeconds) {
//        boolean result = true;
//        try {
//            new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
//        } catch (TimeoutException e) {
//            result = false;
//        }
//        return result;
//    }
//
//    /**
//     * An expectation for checking that an element is present on the DOM of a
//     * page. This does not necessarily mean that the element is visible.
//     *
//     * @param locator          - provide locator
//     * @param timeOutInSeconds - provide timeout in seconds
//     */
//    public boolean waitForElementToBePresent(By locator, long timeOutInSeconds) {
//        boolean result = true;
//        try {
//            new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
//        } catch (TimeoutException e) {
//            result = false;
//        }
//        return result;
//    }
//
//    /**
//     * An expectation for checking that an element is clickable on the DOM of a
//     * page. This does not necessarily mean that the element is visible.
//     *
//     * @param locator          - provide locator
//     * @param timeOutInSeconds - provide timeout in seconds
//     */
//    public boolean waitForElementToBeClickable(By locator, long timeOutInSeconds) {
//        boolean result = true;
//        try {
//            new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
//        } catch (TimeoutException e) {
//            result = false;
//        }
//        return result;
//    }
//
//    /**
//     * An expectation for checking that an element is either invisible or not
//     * present on the DOM.
//     *
//     * @param locator          - provide locator
//     * @param timeOutInSeconds - provide timeout in seconds
//     */
//    public boolean waitForElementToDisappear(By locator, long timeOutInSeconds) {
//        boolean result = true;
//        try {
//            new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        } catch (TimeoutException e) {
//            result = false;
//        }
//        return result;
//    }
//
//    /**
//     * Accept alert in case is displayed
//     */
//    public void acceptAlert() {
//        boolean isAlertDismissed = false;
//        int retries = 0;
//        while (!isAlertDismissed && retries < 3) {
//            try {
//                webDriver.switchTo().alert().accept();
//                isAlertDismissed = true;
//            } catch (NoAlertPresentException ex) {
//                //LOG.debug(ERROR_LOGGED, ex);
//                try {
//                    Thread.sleep(ONE_SECOND_TIMEOUT);
//                } catch (InterruptedException e) {
//                    //LOG.debug(ERROR_LOGGED, e);
//                }
//            }
//            retries++;
//        }
//    }
//
//    /**
//     * Make current thread to sleep for <code>milliseconds</code> Not the best,
//     * but fast and easy.
//     *
//     * @throws InterruptedException
//     */
//    public void sleep(long miliseconds) {
//        try {
//            Thread.sleep(miliseconds);
//        } catch (InterruptedException e) {
//            //LOG.debug(ERROR_LOGGED, e);
//        }
//    }
//
//    /**
//     * Use this method if you want to find a element(like <i><SearchBox,Input
//     * field etc.</i>) type something in the found element and press the enter
//     * key. </br> <strong>Example:</strong> find a search box with a id of
//     * "Search_box1",type "Find me" in the search box and press the enter key.
//     * </br>
//     * <code>webDriver.typeByLocatorAndPressEnter(By.id("Search_box1"),"Find me");</code>
//     *
//     * @param locator - provide the locator(<i>id,className,xpath.. etc.</i>)
//     * @param txt     - provide the text you want to type in the located element
//     */
//    public void typeByLocatorAndPressEnter(By locator, String txt) {
//        webDriver.findElement(locator).sendKeys(txt, Keys.RETURN);
//    }
//
//    /**
//     * Use this method if you want to get the text of a element.</br>
//     * <strong>Example:</strong><i>We have a anchor tag inside a div and we want
//     * to get the text of that anchor</i>
//     *
//     * <pre>
//     * {@code
//     * <div class='container'>
//     * <a href="#"> Click Me Now!</a>
//     * </div>
//     * }
//     * </pre>
//     * <p>
//     * To get that anchor we can use this method with a xpath locator like
//     * so:</br>
//     *
//     * <pre>
//     * {@code}webDriver.getTextFromLocator(By.xpath(".//div[@class='container']/a"))
//     * </pre>
//     *
//     * @param locator - provide the locator(<i>id,className,xpath.. etc.</i>)
//     * @return - the method returns the elements text
//     */
////        public String getTextFromLocator(By locator) {
////            String text = "";
////
////            try {
////                text = webDriver.findElement(locator).getText();
////            } catch (StaleElementReferenceException e) {
////                LOG.debug(ERROR_LOGGED, e);
////                try {
////                    text = webDriver.findElement(locator).getText();
////                } catch (StaleElementReferenceException e2) {
////                    LOG.debug(ERROR_LOGGED, e2);
////                    text = webDriver.findElement(locator).getText();
////                }
////            }
////            return text;
////        }
//
//    /**
//     * Delete all the Cookies
//     */
//    public void deleteAllCookies() {
//        webDriver.manage().deleteAllCookies();
//    }
//
//
//    public String getValue(By selector) {
//        return findElement(selector).getAttribute("value");
//    }
//
//
//    public boolean waitForAjaxtoComplete() {
//        JavascriptExecutor je = (JavascriptExecutor) webDriver;
//        String jQueryActiveScript = "return typeof jQuery != 'undefined'?jQuery.active:document.readyState=='complete'?0:100;";
//        long activeQueries;
//        int i = 0;
//        do {
//            i++;
//            try {
//                activeQueries = (long) je.executeScript(jQueryActiveScript);
//            } catch (Exception e) {
//                //LOG.debug(ERROR_LOGGED, e);
//                activeQueries = 100L;
//                i = i + 5;
//            }
//            sleep(ONE_SECOND_TIMEOUT);
//        } while (activeQueries > 0 && i < 30);
//
//        // in case after the request is complete something on the
//        // page needs to be updated
//        sleep(ONE_SECOND_TIMEOUT);
//
//        return activeQueries == 0L;
//    }
//
//    /**
//     * A list of WebElements - single, multiple or empty number
//     *
//     * @param locator - provide locator
//     * @return A list of all WebElements, or an empty list if nothing matches.
//     */
//    public boolean isElementPresented(final By locator) {
//        return !webDriver.findElements(locator).isEmpty();
//    }
//
//    /**
//     * Whether or not the element is displayed and visible
//     *
//     * @param locator - provide locator
//     * @return The first matching element on the current context.
//     */
//    public boolean isElementDisplayed(final By locator) {
//        return isElementPresented(locator) ? webDriver.findElement(locator).isDisplayed() : false;
//    }
//
//    /**
//     * Verifies whether @string1 contains @string2
//     *
//     * @param text      - the text that is expected to contain the substring
//     * @param substring - the substring that is expected to be part of the text
//     */
//    public void verifyContains(String text, String substring) {
//        Assert.assertTrue(EXPECTED + text + TO_CONTAIN + substring, text.contains(substring));
//    }
//
//    /**
//     * @param text      - the text that is expected to contain the substring
//     * @param substring - the substring that is expected to be part of the text
//     */
//    public void verifyContainsIgnoreCase(String text, String substring) {
//        Assert.assertTrue(EXPECTED + text + TO_CONTAIN + substring, text.toLowerCase().contains(substring.toLowerCase()));
//    }
//
//    /**
//     * Verifies whether @string1 not contains @string2
//     *
//     * @param string1 - the text that is expected to not conatin in string2
//     * @param string2 - the text that is expected to be not part of string1
//     */
//    public void verifyNotContains(String string1, String string2) {
//        Assert.assertTrue(EXPECTED + string1 + TO_NOT_CONTAIN + string2, !string1.contains(string2));
//    }
//
//
//    /**
//     * Verifies whether two strings are equal
//     *
//     * @param expected - the expected string
//     * @param actual   - the actual string
//     */
//    public void verifyEquals(String expected, String actual) {
//        Assert.assertTrue(EXPECTED + expected + BUT_FOUND + actual, expected.equals(actual));
//    }
//
//    /**
//     * Verifies whether two booleans are equal
//     *
//     * @param expected - the expected boolean value
//     * @param actual   - the actual booalen value
//     */
//    public void verifyEquals(boolean expected, boolean actual) {
//        Assert.assertTrue(EXPECTED + expected + BUT_FOUND + actual, expected == actual);
//    }
//
//    /**
//     * Verifies whether two strings are equal using EqualsIgnoreCase
//     *
//     * @param expected - the expected string
//     * @param actual   - the actual string
//     */
//    public void verifyEqualsIgnoreCase(String expected, String actual) {
//        Assert.assertTrue(EXPECTED + expected + BUT_FOUND + actual, expected.equalsIgnoreCase(actual));
//    }
//
//    /**
//     * Selects from a dropdown by visible text
//     *
//     * @param select      - the locator of the select element
//     * @param visibleText - the visible text that will be selected
//     */
//    public void selectByVisibleText(By select, String visibleText) {
//        Select dropdown = new Select(findElement(select));
//        dropdown.selectByVisibleText(visibleText);
//    }
//
//    /**
//     * Selects from a dropdown by value
//     *
//     * @param select - the locator of the select element
//     * @param value  - the value of the option that will be selected
//     */
//    public void selectByValue(By select, String value) {
//        Select dropdown = new Select(findElement(select));
//        dropdown.selectByValue(value);
//    }
//
//    /**
//     * Checks if a popup is present and closes it.
//     *
//     * @param locator - locator of the popup
//     */
//    public void popupClose(By locator) {
//        try {
//            List<WebElement> elements = findElements(locator);
//            for (WebElement element : elements) {
//                if (element.isDisplayed()) {
//                    element.click();
//                }
//            }
//        } catch (NoSuchElementException | ElementNotVisibleException e) {
//            //LOG.info(e);
//        }
//        waitForAjaxtoComplete();
//    }
//
//    /**
//     * Scrolls to the top of the window in order to find the element throughout the page
//     * This is ideally recommended to use in case of "Element not clickable" exception
//     */
//    public void scrollToTop() {
//        jsExecutor.executeScript("window.scrollTo(0,0)");
//    }
//
//    /**
//     * Scrolls to the bottom of the window in order to find the element throughout the page
//     * This is ideally recommended to use in case of "Element not clickable" exception
//     */
//    public void scrollToBottom() {
//        jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
//    }
//
//    /**
//     * Scrolls to the x and (y-100px) coordinates of the element
//     * 100px is deducted to keep an element out of floating header
//     *
//     * @param x - provide x coordinate of element
//     * @param y - provide y coordinate of element
//     */
//    public void scrollToOffset(int x, int y) {
//        jsExecutor.executeScript("window.scrollTo(" + x + "," + (y - 100) + ")");
//    }
//
//    /**
//     * Scrolls to the element provided in parameter. This will in a way call scrollToOffset methods by providing x and y offsets
//     *
//     * @param locator - the locator where the focus to be made
//     */
//    public void scrollToElement(By locator) {
//        scrollToTop();
//        scrollToElement(findElement(locator));
//    }
//
//
//    /**
//     * Scrolls to the element provided in parameter. This will in a way call
//     * scrollToOffset methods by providing x and y offsets
//     *
//     * @param webElement - the locator where the focus to be made
//     */
//    public void scrollToElement(WebElement webElement) {
//        scrollToOffset(webElement.getLocation().x, webElement.getLocation().y);
//    }
//
//    /**
//     * Scrolls to the element provided in parameter. This will in a way call
//     * scrollToOffset methods by providing x and y offsets
//     *
//     * @param webElement - the locator where the focus to be made
//     * @param offset     - offset needed
//     */
//    public void scrollToElement(WebElement webElement, int offset) {
//        scrollToOffset(webElement.getLocation().x, webElement.getLocation().y + offset);
//    }
//
//    /**
//     * Scrolls to the element provided in parameter. This will in a way call scrollToOffset methods by providing x and y offsets
//     * There is an option to add more offset if needed.
//     *
//     * @param locator - the locator where the focus to be made
//     */
//    public void scrollToElement(By locator, int offset) {
//        scrollToTop();
//        scrollToElement(findElement(locator), offset);
//    }
//
//    /**
//     * Searches for element by given locator for specific amount of time.
//     *
//     * @param locator          - element which should be found
//     * @param timeOutInSeconds - how much time to check for that element
//     * @return - returns the WebElement on success or fails on failure
//     */
//    public WebElement findElement(By locator, int timeOutInSeconds) {
//        WebElement result = null;
//        try {
//            new WebDriverWait(webDriver, timeOutInSeconds, 500).until(ExpectedConditions.presenceOfElementLocated(locator));
//            result = findElement(locator);
//        } catch (TimeoutException e) {
//            Assert.assertTrue("Element " + locator + " was not found for " + timeOutInSeconds + " seconds.", false);
//        }
//        return result;
//    }
//
//    /**
//     * Iterates trough all tabs searching for the tab with the given title.
//     * If the tab is found switches to it and returns true,
//     * if not, switches back to the original one and returns false
//     *
//     * @param title - The title to look for
//     * @return - returns true on success or false on failure
//     */
//    public boolean switchToBrowserTabWithTitle(String title) {
//        String oldTab = webDriver.getWindowHandle();
//        ArrayList<String> allTabs = new ArrayList<>(webDriver.getWindowHandles());
//        allTabs.remove(oldTab);
//        for (String tab : allTabs) {
//            webDriver.switchTo().window(tab);
//            if (webDriver.getTitle().equalsIgnoreCase(title)) {
//                return true;
//            }
//        }
//        webDriver.switchTo().window(oldTab);
//        return false;
//    }
//
//    /**
//     * Switches to given tab index which starts with 1.
//     *
//     * @param tabIndex - tab index
//     */
//    public void switchToBrowserTabWithIndex(int tabIndex) {
//        ArrayList<String> allTabs = new ArrayList<>(webDriver.getWindowHandles());
//        webDriver.switchTo().window(allTabs.get(tabIndex - 1));
//    }
//
//    /**
//     * Moves the mouse over the WebElement provided
//     *
//     * @param element - The element on which to move over
//     */
//    public void moveMouseOver(WebElement element) {
//        Actions mouseActions = new Actions(webDriver);
//        mouseActions.moveToElement(element);
//        mouseActions.perform();
//    }
//
//    public void waitForURLChange(String url) {
//        String currentUrl = webDriver.getCurrentUrl();
//        String tmpUrl = currentUrl;
//        int count = 0;
//        while (currentUrl.equals(url) && count < 60) {
//            sleep(ONE_SECOND_TIMEOUT);
//            try {
//                currentUrl = webDriver.getCurrentUrl();
//                tmpUrl = currentUrl;
//            } catch (Exception e) {
//                currentUrl = tmpUrl;
//            }
//            count++;
//        }
//        if (count == 60) {
//            Assert.fail("Timeout after 60 seconds waiting for page to load: Current url is : " + currentUrl + " Initial url is  : " + url);
//        }
//    }
//
//    /**
//     * Method clicks on the WebElement
//     *
//     * @param element the Web Element
//     */
//    public void clickElement(WebElement element) {
//        if (BROWSERS_USING_JS.contains(getRemoteWebDriver().getCapabilities().getBrowserName())) {
//            jsExecutor.executeScript(JS_ARGUMENT_CLICK, element);
//        } else {
//            element.click();
//        }
//    }
//
//    /**
//     * Method clicks on the WebElement using Actions and handles StaleElementReferenceException and retries twice on
//     * failure
//     *
//     * @param locator the Web Element locator
//     */
//    public void clickElementUsingActions(By locator) {
//        Actions clickAction = new Actions(webDriver);
//
//        waitForElementToLoad(locator, TIMEOUT);
//
//        try {
//            clickAction.moveToElement(webDriver.findElement(locator)).click().perform();
//        } catch (StaleElementReferenceException e1) {
//            //LOG.debug(ERROR_LOGGED, e1);
//            try {
//                clickAction.moveToElement(webDriver.findElement(locator)).click().perform();
//            } catch (StaleElementReferenceException e2) {
//                //LOG.debug(ERROR_LOGGED, e2);
//                clickAction.moveToElement(webDriver.findElement(locator)).click().perform();
//            }
//        }
//    }
//
//
//    /**
//     * Method finds and clicks on the WebElement
//     *
//     * @param element the Web Element
//     */
//    public void findAndClickElement(WebElement element) {
//        clickElement(element);
//    }
//
//    /**
//     * Method selects and element from Select web element by a given Index
//     *
//     * @param element        the Web Element
//     * @param selectionIndex the index to select in the Select web element
//     */
//    public void selectElementFromDropDownByIndex(WebElement element, int selectionIndex) {
//        Select dropdown = new Select(element);
//        dropdown.selectByIndex(selectionIndex);
//    }
//
//    /**
//     * Moves the mouse over the WebElement provided via Javascript
//     *
//     * @param element - The element on which to move over
//     */
//    public void mouseHoverThroughJS(WebElement element) {
//        String mouseOverScript = "if(document.createEvent){" +
//                "var evObj = document.createEvent('MouseEvents');" +
//                "evObj.initEvent('mouseover', true, false); " +
//                "arguments[0].dispatchEvent(evObj);" +
//                "} else if(document.createEventObject) { " +
//                "arguments[0].fireEvent('onmouseover');" +
//                "}";
//        jsExecutor.executeScript(mouseOverScript,
//                element);
//    }
//
//
//    /**
//     * Method which extract only integer value from String
//     * <p>
//     * Can be used to extract - Price[$80]
//     * </p>
//     *
//     * @param value - expected value e.g. $150.44
//     * @return integer value from String
//     */
//    public BigDecimal getValueFromPrice(String value) {
//        BigDecimal result = BigDecimal.ZERO;
//        Pattern pattern = Pattern.compile(INTEGER_REGEX);
//        Matcher m = pattern.matcher(value.replaceAll(",", "."));
//        if (m.find()) {
//            Scanner sc = new Scanner(m.group());
//            result = sc.nextBigDecimal();
//            sc.close();
//        }
//        return result;
//    }
//
//    public void scrollToElementInIframe(By iframe, By elementSelector) {
//        scrollToTop();
//        int totalY = findElement(iframe).getLocation().getY();
//        webDriver.switchTo().frame(findElement(iframe));
//        if (!findElements(elementSelector).isEmpty()) {
//            totalY = totalY + findElement(elementSelector).getLocation().getY();
//        }
//        webDriver.switchTo().defaultContent();
//        scrollToOffset(0, totalY);
//    }
//
//    public void navigateBack() {
//        webDriver.navigate().back();
//    }
//
//    /**
//     * Get Content from HTML script tag as JSON
//     *
//     * @param cssSelector - expected script selector
//     * @return JSON
//     */
////    public com.google.gson.JsonObject getContentFromScriptTagAsJson(By cssSelector) {
////        JsonParser parser = new JsonParser();
////        String jsonString = findElement(cssSelector).getAttribute("innerHTML");
////        return parser.parse(jsonString).getAsJsonObject();
////    }
//
//    /**
//     * Get Content from HTML script tag as String
//     *
//     * @param cssSelector - expected script selector
//     * @return script content as string
//     */
//    public String getContentFromScriptTagAsString(By cssSelector) {
//        return findElement(cssSelector).getAttribute("innerHTML");
//    }
//
//    private int getBrowsersWindowViewportConstants() {
//        String browserName = ((RemoteWebDriver) this.webDriver).getCapabilities().getBrowserName();
//        return (this.browsersWindowViewportConstants.get(browserName) != null) ? this.browsersWindowViewportConstants.get(browserName) : 0;
//    }
//
//    /**
//     * Resize browser viewport size
//     *
//     * @param widthPixels  - expected view port width in pixels
//     * @param heightPixels - expected view port height in pixels
//     */
//    public void resizeBrowserViewportSize(int widthPixels, int heightPixels) {
//        if (!isMobile()) {
//            int newClientWidth;
//            int newClientHeight;
//            int correctionWidth = 0;
//            int correctionHeight = 0;
//            int browserWindowViewportConstant = getBrowsersWindowViewportConstants();
//            JavascriptExecutor executor = (JavascriptExecutor) this.webDriver;
//            Dimension dimension = new Dimension(widthPixels, heightPixels);
//
//            this.webDriver.manage().window().setSize(dimension);
//
//            for (int count = 0; count < 5; count++) {
//                newClientWidth = Integer.parseInt(executor.executeScript("return document.documentElement.clientWidth").toString());
//                newClientHeight = Integer.parseInt(executor.executeScript("return document.documentElement.clientHeight").toString());
//
//                if (widthPixels - newClientWidth != browserWindowViewportConstant || heightPixels - newClientHeight != browserWindowViewportConstant) {
//                    if (widthPixels - newClientWidth != browserWindowViewportConstant) {
//                        correctionWidth = correctionWidth + ((widthPixels - newClientWidth) - browserWindowViewportConstant);
//                        if (widthPixels + correctionWidth < 320) {
//                            continue;
//                        }
//                    }
//
//                    if (heightPixels - newClientHeight != browserWindowViewportConstant) {
//                        correctionHeight = correctionHeight + ((heightPixels - newClientHeight) - browserWindowViewportConstant);
//                        if (heightPixels + correctionWidth < 568) {
//                            continue;
//                        }
//                    }
//
//                    dimension = new Dimension(widthPixels + correctionWidth, heightPixels + correctionHeight);
//                    this.webDriver.manage().window().setSize(dimension);
//                }
//            }
//        }
//    }
//
//    public void verifyEquals(long expected, long actual, String message) {
//        Assert.assertEquals(message, expected, actual);
//    }
//
//    public void verifyLocatorSize(int expectedSize, By locator, String message) {
//        waitForElementToLoad(locator, TIMEOUT);
//        verifyEquals(findElements(locator).size(), expectedSize, message);
//    }
//
//    public List<String> getBrowserConsole() {
//        LogEntries logEntries = webDriver.manage().logs().get(LogType.BROWSER);
//        List<String> logList = new ArrayList<>();
//        for (LogEntry logEntry : logEntries.filter(Level.WARNING)) {
//            logList.add(logEntry.getMessage());
//        }
//        return logList;
//    }
//
//    public void editElementAttributesWithJavaScript(By locator, String attributeName, String attributeValue) {
//        editElementAttributesWithJavaScript(findElement(locator), attributeName, attributeValue);
//    }
//
//    public void editElementAttributesWithJavaScript(WebElement webElement, String attributeName, String attributeValue) {
//        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', arguments[1]);", webElement, attributeValue);
//    }
//
//    /**
//     * Method to verify the that the format of a given price has to digits after the decimal point or comma
//     *
//     * @param price - price value from site to be verified
//     */
//    public boolean verifyPriceHasTwoDigitsAfterThePoint(String price) {
//        Pattern patt = Pattern.compile("^[0-9]+[\\.|\\,][0-9]{2,2}$");
//        Matcher matcher = patt.matcher(price);
//        return matcher.find();
//    }
//
//    /**
//     * Returns a Map with coordinates of the specified element relevant to the current view area
//     *
//     * @param element
//     * @return A Map. Example:
//     * {
//     * "bottom"    :    121
//     * "height"    :    121
//     * "left"    :    0
//     * "right"    :    1519.2000732421875
//     * "top"    :    0
//     * "width"    :    1519.2000732421875
//     * }
//     */
//    public Map getBoundingClientRect(WebElement element) {
//        return (Map) jsExecutor.executeScript("return arguments[0].getBoundingClientRect();", element);
//    }
//
//    /**
//     * Asserts that a condition is true. If it isn't,
//     * an AssertionError, with the given message, is thrown.
//     *
//     * @param expected the condition to evaluate
//     * @param message  the assertion error message
//     */
//    public void verifyTrue(boolean expected, String message) {
//        Assert.assertTrue(message, expected);
//    }
//
//    /**
//     * mobileClick(By locator) that takes "By locator" as run time argument.
//     * This has been added because when testing for iOS mobile devices, click(By locator) was not able to click on the particular web element as there were JS calls
//     * happening with Safari browser (iOS mobile) once user clicks on the specific web element whereas in case of Android device click(By locator) is working fine.
//     * Thus to mitigate above issue we have introduced mobileClick(By locator) wherein we have provided If/else condition to go with clickByJavascript(locator) in case of iOS device else to go with click(locator) in case of android one.
//     */
//    public void mobileClick(By locator) {
//        if (isSafari()) {
//            clickByJavascript(locator);
//        } else {
//            click(locator);
//        }
//        waitForAjaxtoComplete();
//    }
//
//    public void mobileSelectByVisibleText(By selectElement, By selectOptions, String text) {
//        if (isSafari()) {
//            click(selectElement);
//            List<WebElement> allOptions = findElements(selectOptions);
//            for (WebElement e : allOptions) {
//                if (e.getText().trim().equalsIgnoreCase(text)) {
//                    e.click();
//                    break;
//                }
//            }
//        } else {
//            selectByVisibleText(selectElement, text);
//        }
//    }
//
//    /**
//     * Get CSS selector value as String by Given Class and Field names as Strings
//     * All names are case sensitive
//     * Method work only for By elements from [.pages] package
//     *
//     * @param className        - expected class name
//     * @param fieldName        - expected reflectedField name
//     * @param packageStructure - list with package structure
//     * @return - String value from respected By element
//     * @throws Exception
//     */
//    public String getCssSelectorByClassAndFieldName(String className, String fieldName, List packageStructure) throws Exception {
//
//        // Get class by string value
//        reflectedClass = getClassByStringValue(className, packageStructure);
//
//        // Create new instance of class passing webdriver as param
//        Constructor<?> constructor = reflectedClass.getConstructor(org.openqa.selenium.WebDriver.class);
//        Object instance = constructor.newInstance(webDriver);
//
//        try {
//
//            // Get reflected Field from class
//            reflectedField = instance.getClass().getDeclaredField(fieldName);
//            reflectedField.setAccessible(true);
//            reflectedField.get(instance);
//            By element = (By) reflectedField.get(instance);
//            return element.toString().split(":")[1].trim();
//
//        } catch (NoSuchFieldException e) {
//            throw new Exception("Class with name " + className + " doesn't have declared reflected Field with name: " + fieldName);
//        }
//    }
//
//    private Class getClassByStringValue(String className, List packageStructure) throws Exception {
//        while (currentPackageSize < packageStructure.size()) {
//            try {
//                if (Class.forName(packageStructure.get(currentPackageSize) + className).getName().contains(className)) {
//                    return Class.forName(packageStructure.get(currentPackageSize) + className);
//                }
//            } catch (ClassNotFoundException e) {
//                currentPackageSize++;
//                getClassByStringValue(className, packageStructure);
//            }
//        }
//
//        throw new Exception("Cannot find class " + className + ". Check if such class exist in project. Remember, Class names are case sensitive");
//    }
//
//    public void removeElementByJavascript(WebElement webElement) {
//        jsExecutor.executeScript("arguments[0].remove();", webElement);
//    }
//
//    /**
//     * Set cookie popup items in local storage in order to not show cookie popup on the page
//     * <p>
//     * Once this action is performed user will have to call page reload or refresh in order to hide the popup message
//     */
//    public void setLocalStorageCookies() {
//        jsExecutor.executeScript(("localStorage['truste.eu.cookie.notice_preferences'] = '{\"name\":\"truste.eu.cookie.notice_preferences\",\"value\":\"2:\",\"path\":\"/\"}'"));
//        jsExecutor.executeScript(("localStorage.removeItem('truste.eu.cookie.notice_poptime')"));
//        if (waitForElementToBePresent(feedBackLink, 10)) {
//            String feedBackLinkVersion = webDriver.findElement(feedBackLink).getAttribute("id").split("-")[2];
//            Cookie feedbackCookie = new Cookie("QSI_" + feedBackLinkVersion + "_intercept", "true");
//            webDriver.manage().addCookie(feedbackCookie);
//        }
//        Cookie signUpCookie = new Cookie(SIGNUP_COOKIE, "4");
//        webDriver.manage().deleteCookieNamed(SIGNUP_COOKIE);
//        webDriver.manage().addCookie(signUpCookie);
//        Cookie trackingCookie = new Cookie(TRACKING_COOKIE, "2");
//        webDriver.manage().deleteCookieNamed(TRACKING_COOKIE);
//        webDriver.manage().addCookie(trackingCookie);
//    }
//
//    /**
//     * Remove cookie popup item from local storage of the browser
//     * <p>
//     * Once this action is performed user will have to call page reload or refresh in order to show the popup message
//     */
//    public void clearLocalStorageCookies() {
//        jsExecutor.executeScript(("localStorage.removeItem('truste.eu.cookie.notice_preferences')"));
//    }
//
//    /**
//     * Clear local storage of the browser
//     */
//    public void clearLocalStorage() {
//        jsExecutor.executeScript("localStorage.clear();");
//    }
//
//    /**
//     * Do a browser refresh
//     */
//    public void refreshBrowserWindow() {
//        webDriver.navigate().refresh();
//    }
//
//    /**
//     * Do a back browser button
//     */
//    public void browserBackAction() {
//        webDriver.navigate().back();
//    }
//
//    /**
//     * Do a forward browser button
//     */
//    public void browserForwardAction() {
//        webDriver.navigate().forward();
//    }
//
//    /**
//     * Select the new type of checkboxes introduced with the new design
//     *
//     * @param locatorLabel - the label of checkbox
//     * @param locator      - the locator of checkbox
//     * @param select       - select state
//     */
//    public void selectNewTypeCheckbox(By locatorLabel, By locator, boolean select) {
//        boolean isCurrentlySelected = findElement(locator).isSelected();
//        if (select != isCurrentlySelected) {
//            click(locatorLabel);
//            waitForAjaxtoComplete();
//        }
//    }
//
//    /**
//     * Get and return the current browser vertical and horizontal scroll positions
//     *
//     * @return Point object
//     */
//    public Point getScrollPositions() {
//        int valueX = Math.toIntExact((Long) jsExecutor.executeScript("return window.pageXOffset;"));
//        int valueY = Math.toIntExact((Long) jsExecutor.executeScript("return window.pageYOffset;"));
//        return new Point(valueX, valueY);
//    }
//
//    public boolean isReebok() {
//        return webDriver.getCurrentUrl().toLowerCase().contains("reebok");
//    }
//
//    public void clearValueByLocator(By locator) {
//        WebElement e = webDriver.findElement(locator);
//        if (isMobile()) {
//            e.clear();
//        } else {
//            e.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
//        }
//    }
//
//    public String getCurrentURL() {
//        return webDriver.getCurrentUrl();
//    }
//}
