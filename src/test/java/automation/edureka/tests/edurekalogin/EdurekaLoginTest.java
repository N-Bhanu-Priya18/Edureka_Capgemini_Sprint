package automation.edureka.tests.edurekalogin;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import automation.edureka.utility.BaseClass;

public class EdurekaLoginTest extends BaseClass {

    // TC_LOGIN_001: Verify Login page opens successfully
    @Test(groups = {"smoke", "functional"})
    public void TC_LOGIN_001() throws InterruptedException {
        Reporter.log("TC_LOGIN_01: Verify Login page opens successfully", true);
        edurekaHomePage.clickLogin();
        Thread.sleep(2000);
        Assert.assertTrue(edurekaLoginPage.verifyEmailField(), "Email field is not displayed or enabled");
        Assert.assertTrue(edurekaLoginPage.verifyPWField(), "Password field is not displayed or enabled");
        Reporter.log("TC_LOGIN_01 passed: Login page opened successfully", true);
    }

    // TC_LOGIN_002: Verify login using valid credentials and navigate to All Courses
    @Test(priority = 1, groups = {"smoke","system", "integration"})
    public void TC_LOGIN_002() throws EncryptedDocumentException, IOException, InterruptedException {
        Reporter.log("TC_LOGIN_02: Verify login using valid credentials and navigate to All Courses", true);
        edurekaHomePage.clickLogin();
        edurekaLoginPage.enterEmail(testData.getTData("Login", 1, 0));
        edurekaLoginPage.enterPassword(testData.getTData("Login", 1, 1));
        Thread.sleep(2000);
        edurekaLoginPage.clickSubmitButton();
        Thread.sleep(2000);
        Assert.assertTrue(edurekaLoginPage.verifyLoginSuccess(), "Login was not successful");
        edurekaHomePage.clickAllCoursesLink();
        Assert.assertTrue(edurekaAllCoursesPage.verifyAllCoursesPage(), "All Courses page did not open after login");
        Reporter.log("TC_LOGIN_02 passed: Login and All Courses navigation worked successfully", true);
    }

    // TC_LOGIN_003: Verify login fails with an incorrect password
    @Test(groups = {"functional", "negative"})
    public void TC_LOGIN_003() throws EncryptedDocumentException, IOException {
        Reporter.log("TC_LOGIN_03: Verify login fails with an incorrect password", true);
        try 
        {
        edurekaHomePage.clickLogin();
        edurekaLoginPage.enterEmail(testData.getTData("Login", 2, 0));
        edurekaLoginPage.enterPassword(testData.getTData("Login", 2, 1));
        edurekaLoginPage.clickSubmitButton();
        Assert.assertTrue(edurekaLoginPage.verifyLoginFailure(), "Login failure message was not displayed");
        Reporter.log("TC_LOGIN_03 passed: Login was rejected with an incorrect password", true);}
        finally {
        	takeScreenshot("TC_LOGIN_003");
        }
    }

    // TC_LOGIN_004: Verify required-field validation for blank credentials
//    @Test(groups = {"functional", "negative"})
//    public void TC_LOGIN_004() throws IOException {
//        Reporter.log("TC_LOGIN_04: Verify required-field validation for blank credentials", true);
//        try {
//            edurekaHomePage.clickLogin();
//            edurekaLoginPage.clickSubmitButton();
//            Assert.assertTrue(edurekaLoginPage.verifyBlankCredentials(), "Required-field validation messages were not displayed");
//            Reporter.log("TC_LOGIN_04 passed: Mandatory email and password messages were displayed", true);
//        } finally {
//            takeScreenshot("TC_LOGIN_004");
//        }
//    }

    // TC_LOGIN_005: Verify login page reloads without errors
    @Test(groups = {"functional", "regression"})
    public void TC_LOGIN_005() throws InterruptedException {
        Reporter.log("TC_LOGIN_05: Verify login page reloads without errors", true);
        edurekaHomePage.clickLogin();
        driver.navigate().refresh();
        edurekaHomePage.clickLogin();
        Assert.assertTrue(edurekaLoginPage.verifyLoginPageAfterRefresh(), "Login page did not load correctly after refresh");
        Reporter.log("TC_LOGIN_05 passed: Login page reloaded successfully", true);
    }
}