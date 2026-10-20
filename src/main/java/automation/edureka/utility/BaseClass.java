package automation.edureka.utility;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Parameters;
import automation.edureka.pages.EdurekaAllCoursesPage;
import automation.edureka.pages.EdurekaAIPage;
import automation.edureka.pages.EdurekaLoginPage;
import automation.edureka.pages.EdurekaHomePage;

public class BaseClass extends CommonData 
{
    public WebDriver driver = null;
    public TestData testData;
    public EdurekaHomePage edurekaHomePage;
    public EdurekaLoginPage edurekaLoginPage;
    public EdurekaAIPage edurekaAIPage;
    public EdurekaAllCoursesPage edurekaAllCoursesPage;
    
    //Take Screenshot
    public void takeScreenshot(String testName) throws IOException {
        File folder = new File(System.getProperty("user.dir"), "EdurekaSS");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(folder, testName + ".png");
        FileHandler.copy(src, dest);
    }  
    
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void launchApplication(@Optional("chrome") String browser) throws IOException {
        testData = new TestData();
        if (browser.trim().equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.trim().equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.trim().equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.firefox.logfile", "target/geckodriver.log");
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Users\\bhanu\\.cache\\selenium\\firefox\\win64\\157.0.1\\firefox.exe");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
        edurekaHomePage = new EdurekaHomePage(driver);
        edurekaLoginPage = new EdurekaLoginPage(driver);
        edurekaAIPage = new EdurekaAIPage(driver);
        edurekaAllCoursesPage = new EdurekaAllCoursesPage(driver);
    }
    
    @AfterClass(alwaysRun = true)
    public void closeApplication() {
        if (driver != null) {
            driver.quit();
            Reporter.log("Browser closed successfully", true);
        }
    }
    
    @BeforeMethod(alwaysRun = true)
    public void Navigation() throws IOException {
        if (driver == null) {
            throw new IllegalStateException("Browser was not initialized successfully");
        }
        driver.get(getCData("url"));
        Reporter.log("Edureka application opened successfully", true);
    }
}