package automation.edureka.pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdurekaAllCoursesPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public EdurekaAllCoursesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    //Locators
    @FindBy(xpath="//div[@class='page_top_title']")
    private WebElement pageTopTitle;  
    @FindBy(xpath="//div[@class='page_top_sub_title']")
    private WebElement pageSubTitle;
    @FindBy(xpath = "(//a[text()='Project Management and Methodologies'])[1]")
    private WebElement selectCategory;
    @FindBy(xpath = "(//h2[@class='title'])[1]")
    private WebElement courseTitle;
    @FindBy(xpath = "(//button[text()='View Course Details'])[2]")
    private WebElement viewCourseDetailsButton;
    @FindBy(xpath = "//a[@class='category_nav_link' and text()='Robotic Process Automation']")
    private WebElement roboticProcessAutomationCategory; 
    @FindBy(xpath = "//h1[text()='Sorry, this page could not be found.']")
    private WebElement pageNotFoundMessage;
    @FindBy(xpath = "(//div[@class='topic_more'])[2]")
    private WebElement courseDescription;
    @FindBy(xpath = "//a[@class='page-link' and normalize-space()='Next']")
    private WebElement nextButton;
    @FindBy(xpath = "//a[@class='page-link' and normalize-space()='Previous']")
    private WebElement previousButton;

    //Getters and Verification Methods
    public String getPageTopTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pageTopTitle)).getText().trim();
    }  
    public String getpageSubTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pageSubTitle)).getText().trim();
    }  
    public boolean verifyAllCoursesPage() {
        return getPageTopTitle().equals("Choose a category to find your course");
    }
    public boolean verifyPageSubHeading() {
        return !getpageSubTitle().isEmpty();
    }
      
    public void clickSelectCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(selectCategory)).click();
    }
    public String getCourseTitle() {
        return wait.until(ExpectedConditions.visibilityOf(courseTitle)).getText().trim();
    }
    public boolean verifyCourseTitleDisplayed() {
        return !getCourseTitle().isEmpty();
    }
    
    public void clickViewCourseDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCourseDetailsButton)).click();
    }
    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }
    public boolean verifyCourseDetailsPage(String oldUrl) {
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
        return !driver.getCurrentUrl().equals(oldUrl);
    }
  
    public void clickRPACategory() {
        WebElement category = wait.until(ExpectedConditions.visibilityOf(roboticProcessAutomationCategory));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", category);
        wait.until(ExpectedConditions.elementToBeClickable(category)).click();
    }
    public boolean verifyPageNotFoundMessage() {
        return wait.until(ExpectedConditions.visibilityOf(pageNotFoundMessage)).isDisplayed();
    }

    public String getCourseDescription() {
        return wait.until(ExpectedConditions.visibilityOf(courseDescription)).getText().trim();
    }  
    public boolean verifyCourseDescription() {
        return wait.until(ExpectedConditions.visibilityOf(courseDescription)).isDisplayed();
    }
   
    public void clickNextPage() {
        String currentUrl = driver.getCurrentUrl();
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", next);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
    }
    public void clickPreviousPage() {
        String currentUrl = driver.getCurrentUrl();
        WebElement previous = wait.until(ExpectedConditions.elementToBeClickable(previousButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", previous);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", previous);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
    }  
    public boolean verifyNextPage(String expectedParameter) {
        return driver.getCurrentUrl().contains(expectedParameter);
    }
    public boolean verifyPreviousPage(String expectedParameter) {
        return !driver.getCurrentUrl().contains(expectedParameter);
    }
}