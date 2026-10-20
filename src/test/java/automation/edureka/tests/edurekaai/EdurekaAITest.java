package automation.edureka.tests.edurekaai;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import automation.edureka.utility.BaseClass;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EdurekaAITest extends BaseClass {

    // TC_AI_001: Verify AI category page opens
//    @Test(groups = {"smoke", "functional", "regression"})
//    public void TC_AI_001() throws EncryptedDocumentException, IOException {
//        Reporter.log("TC_AI_01: Verify Artificial Intelligence category page opens", true);
//        edurekaHomePage.clickAILink();
//        String expectedAIPageUrl = testData.getTData("AI", 1, 1);
//        Assert.assertTrue(driver.getCurrentUrl().contains(expectedAIPageUrl), "AI category URL is incorrect");
//        Assert.assertTrue(edurekaAIPage.verifyAIPage(), "AI page heading is not displayed");
//        Assert.assertTrue(edurekaAIPage.verifyPageSubHeading(), "AI introductory content is not displayed");
//        Reporter.log("TC_AI_01 passed: AI category URL, heading and introductory content are verified", true);
//    }

    // TC_AI_002: Verify AI course titles are displayed
    @Test(groups = {"functional", "smoke", "system"})
    public void TC_AI_002() throws InterruptedException {
        Reporter.log("TC_AI_02: Verify AI course titles are displayed", true);
        edurekaHomePage.clickAILink();
        Thread.sleep(2000);        
        Assert.assertTrue(edurekaAIPage.verifyAIPage(), "AI category page did not open");
        Assert.assertTrue(edurekaAIPage.verifyCourseTitles(), "AI course titles are missing or blank");
        Reporter.log("TC_AI_02 passed: AI course titles are displayed successfully", true);
    }

    // TC_AI_003: Verify selected AI course opens its details page
    @Test(groups = {"functional", "integration"})
    public void TC_AI_003() {
        Reporter.log("TC_AI_03: Verify selected AI course opens its details page", true);
        edurekaHomePage.clickAILink();
        String parentWindow = driver.getWindowHandle();
        String oldUrl = driver.getCurrentUrl();
        edurekaAIPage.clickViewProgram(0);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
        Assert.assertNotEquals(driver.getCurrentUrl(), oldUrl, "Course details page did not open");
        Reporter.log("TC_AI_03 passed: Course details page opened successfully", true);
    }

    // TC_AI_004: Verify enquiry form rejects invalid email
    @Test(groups = {"negative", "regression"})
    public void TC_AI_04_verifyInvalidEmailIsRejected() throws EncryptedDocumentException, IOException {
        Reporter.log("TC_AI_04: Verify enquiry form rejects invalid email", true);
        try {
            edurekaHomePage.clickAILink();
            edurekaAIPage.scrollToEnquiryForm();
            edurekaAIPage.enterEmail(testData.getTData("AI", 2, 1));
            edurekaAIPage.enterPhone(testData.getTData("AI", 3, 1));
            Assert.assertTrue(edurekaAIPage.isEmailInvalid(), "Invalid email was not detected");
            edurekaAIPage.clickStartApplication();
            Assert.assertTrue(edurekaAIPage.isEmailInvalid(), "Invalid email was accepted");
            Reporter.log("TC_AI_04 passed: Invalid email was rejected", true);
        } finally {
            takeScreenshot("TC_AI_04");
        }
    }

    // TC_AI_005: Verify AI page at different browser widths
//    @Test(groups = {"functional", "regression"})
//    public void TC_AI_05_verifyAIPageAtDifferentWidths() {
//        Reporter.log("TC_AI_05: Verify AI page content at different browser widths", true);
//        edurekaHomePage.clickAILink();
//        edurekaAIPage.setBrowserWidth(1024);
//        Assert.assertTrue(edurekaAIPage.verifyAIPage(), "AI page heading is not displayed at 1024px width");
//        edurekaAIPage.setBrowserWidth(768);
//        Assert.assertTrue(edurekaAIPage.verifyAIPage(), "AI page heading is not displayed at 768px width");
//        Reporter.log("TC_AI_05 passed: AI page heading is displayed at different widths", true);
//    }

    // TC_AI_006: Verify AI courses after page refresh
//    @Test(groups = {"functional", "regression"})
//    public void TC_AI_06_verifyAICoursesAfterRefresh() throws InterruptedException {
//        Reporter.log("TC_AI_06: Verify AI courses after page refresh", true);
//        edurekaHomePage.clickAILink();
//        Assert.assertTrue(edurekaAIPage.verifyAIPage(), "AI page did not open");
//        Thread.sleep(1000);        
//        driver.navigate().refresh();
//        Thread.sleep(2000);
//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Artificial Intelligence']")));
//        Assert.assertTrue(edurekaAIPage.verifyCourseTitles(), "AI course titles are not displayed after refresh");
//        Reporter.log("TC_AI_06 passed: AI courses are displayed after refresh", true);
//    }
}