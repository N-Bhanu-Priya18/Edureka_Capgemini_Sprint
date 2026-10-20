package automation.edureka.pages;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdurekaLoginPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public EdurekaLoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    //Locators
    @FindBy(id="loginFormEmail")
    private WebElement emailField;
    @FindBy(id="loginPassword")
    private WebElement passwordField;
    @FindBy(xpath = "//button[text()='LOG IN']")
    private WebElement submitButton;
    @FindBy(className = "form_auth_error__ggGTW")
    private WebElement loginErrorMessage;
    @FindBy(xpath = "//p[text()='Mandatory email address']")
    private WebElement emailError;
    @FindBy(xpath = "//p[text()='Mandatory password']")
    private WebElement passwordError;
    
    //Getters and Verification Methods
    public boolean verifyEmailField() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        return emailField.isDisplayed() && emailField.isEnabled();
    }
    public boolean verifyPWField() {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        return passwordField.isDisplayed() && passwordField.isEnabled();
    } 
    public boolean verifyLoginSuccess() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Log in']")));
        } catch (Exception e) {
            return false;
        }
    }
    
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
    }
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
        passwordField.sendKeys(password);
    }
    public void clickSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
     
    public boolean verifyLoginFailure() {
        return wait.until(ExpectedConditions.visibilityOf(loginErrorMessage)).isDisplayed();
    }
     
    public boolean verifyBlankCredentials() {
        return wait.until(ExpectedConditions.visibilityOf(emailError)).isDisplayed() && wait.until(ExpectedConditions.visibilityOf(passwordError)).isDisplayed();
    }
 
    public boolean verifyLoginPageAfterRefresh() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        return emailField.isDisplayed() && passwordField.isDisplayed();
    }
}


