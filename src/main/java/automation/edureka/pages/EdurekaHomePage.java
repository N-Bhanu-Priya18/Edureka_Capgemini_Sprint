package automation.edureka.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdurekaHomePage {
    WebDriver driver;
    WebDriverWait wait;
    public EdurekaHomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    
    //Locators
    @FindBy(xpath = "//button[normalize-space()='Log in']")
    private WebElement loginButton;
    @FindBy(xpath = "(//a[text()='Artificial Intelligence'])[1]")
    private WebElement aiLink;
    @FindBy(xpath = "//a[text()='All Courses']")
    private WebElement allCoursesLink;
    @FindBy(id = "dropdown-user_drop")
    private WebElement userDropdown;
    @FindBy(xpath = "//a[normalize-space()='Log Out']")
    private WebElement logoutButton;
    
    //Getters and Methods
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    public void clickAILink() {
        wait.until(ExpectedConditions.elementToBeClickable(aiLink)).click();
    }
    public WebElement getAILink() {
        return aiLink;
    }
    public void clickAllCoursesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(allCoursesLink)).click();
    }
    public void clickUserDropdown() {
        userDropdown.click();
        logoutButton.click();
    }
}
  
