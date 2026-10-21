package automation.edureka.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdurekaAIPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public EdurekaAIPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    //Locators
    @FindBy(xpath = "//h2[text()='Artificial Intelligence']")
    private WebElement pageTopHeading;
    @FindBy(xpath = "//h3[@class='certification_certification_subtitle__3njra']")
    private WebElement pageSubHeading;
    @FindBy(xpath = "//a[@class='certification_certification_button__3kscV']")
    private WebElement exploreAICourse;
    @FindBy(xpath = "//div[@class='popularcourses_course_title__1Y0z4']")
    private List<WebElement> courseTitles;
    @FindBy(xpath="//button[@class='popularcourses_view_program_btn__2-OMj']")
    private List<WebElement> viewProgramButtons;
    @FindBy(xpath = "//h1[@class='main_title_pgp']")
    private WebElement courseDetailsHeading;
    @FindBy(name = "email")
    WebElement emailField;
    @FindBy(name = "phone")
    WebElement phoneField;
    @FindBy(xpath = "//button[@type='submit' and contains(.,'Start Application')]")
    WebElement startApplicationButton;
    
    //Getters and Verification Methods
    public String getPageHeading() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Artificial Intelligence']"))).getText().trim();
    }
    public boolean verifyAIPage() {
        return getPageHeading().equalsIgnoreCase("Artificial Intelligence");
    } 
    public boolean verifyPageSubHeading() {
        return !wait.until(ExpectedConditions.visibilityOf(pageSubHeading)).getText().trim().isEmpty();
    }
    
    public List<WebElement> getCourseTitles() {
        return courseTitles;
    }
    public void clickExploreAICourse() {
        wait.until(ExpectedConditions.elementToBeClickable(exploreAICourse)).click();
    }
    public boolean verifyCourseTitles() {
        wait.until(ExpectedConditions.visibilityOfAllElements(courseTitles));
        if (courseTitles.isEmpty()) {
            return false;
        }
        for (WebElement title : courseTitles) {
            if (title.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public String getCourseTitle(int index) {
        wait.until(ExpectedConditions.visibilityOfAllElements(courseTitles));
        return courseTitles.get(index).getText().trim();
    }    
    public void clickViewProgram(int index) {
        By button = By.xpath("(//button[text()='View Program'])[" + (index + 1) + "]");
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }   
    public boolean verifyCourseDetailsPage(String courseTitle) {
        String actualTitle = wait.until(ExpectedConditions.visibilityOf(courseDetailsHeading)).getText().trim();
        return actualTitle.equalsIgnoreCase(courseTitle);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).clear();
        emailField.sendKeys(email);
    }
    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneField)).clear();
        phoneField.sendKeys(phone);
    }
    public void clickStartApplication() {
        wait.until(ExpectedConditions.elementToBeClickable(startApplicationButton)).click();
    }
    public boolean isEmailInvalid() {
        return emailField.getAttribute("type").equals("email") && !emailField.getAttribute("value").contains("@");
    }
    public void scrollToEnquiryForm() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior:'instant', block:'center'});", emailField);
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
    }
    public boolean verifyEmailValidation() {
        return emailField.getAttribute("type").equals("email") && !emailField.getAttribute("value").contains("@");
    }
   
    public void setBrowserWidth(int width) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, 800));
    }
}
    
