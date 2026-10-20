package automation.edureka.tests.edurekaallcourses;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;
import automation.edureka.utility.BaseClass;

public class EdurekaAllCoursesTest extends BaseClass {

    // TC_AC_001: Verify All Courses page loads
    @Test(groups = {"smoke","system"})
    public void TC_AC_001() throws EncryptedDocumentException, IOException {
        Reporter.log("TC_AC_001: Verify All Courses Page loads", true);
        edurekaHomePage.clickAllCoursesLink();
        String expectedUrl = testData.getTData("AllCourses", 3, 1);
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl), "All Courses URL is incorrect");
        Assert.assertTrue(edurekaAllCoursesPage.verifyAllCoursesPage(), "Page heading is not displayed");
        Assert.assertTrue(edurekaAllCoursesPage.verifyPageSubHeading(), "Page subheading is not displayed");
        Reporter.log("TC_AC_001 passed: All Courses page heading, URL and subheading are verified", true);
    }

    // TC_AC_002: Verify Project Management category displays relevant course title
//    @Test(groups = {"functional", "regression"})
//    public void TC_AC_002() throws EncryptedDocumentException, IOException {
//        Reporter.log("TC_AC_002: Verify Project Management category displays relevant course title", true);
//        edurekaHomePage.clickAllCoursesLink();
//        edurekaAllCoursesPage.clickSelectCategory();
//        String expectedCourseTitle = testData.getTData("AllCourses", 1, 1).trim();
//        String actualCourseTitle = edurekaAllCoursesPage.getCourseTitle().trim();
//        System.out.println("Expected Course Title from Excel: [" + expectedCourseTitle + "]");
//        System.out.println("Actual Course Title from Application: [" + actualCourseTitle + "]");
//        Assert.assertTrue(edurekaAllCoursesPage.verifyCourseTitleDisplayed(), "Course title is not displayed");
//        Assert.assertTrue(actualCourseTitle.contains(expectedCourseTitle), "Expected course title is not displayed");
//        Reporter.log("TC_AC_002 passed: Expected course title is displayed", true);
//    }

    // TC_AC_003: Verify course details page opens
    @Test(groups = {"functional", "integration"})
    public void TC_AC_003() {
        Reporter.log("TC_AC_003: Verify course details page opens", true);
        edurekaHomePage.clickAllCoursesLink();
        edurekaAllCoursesPage.clickSelectCategory();
        String beforeClick = edurekaAllCoursesPage.getCurrentPageUrl();
        edurekaAllCoursesPage.clickViewCourseDetails();
        String afterClick = edurekaAllCoursesPage.getCurrentPageUrl();
        Assert.assertNotEquals(afterClick, beforeClick, "URL did not change after clicking View Course Details");
        Reporter.log("TC_AC_003 passed", true);
    }

    // TC_AC_004: Verify Robotic Process Automation category behavior
    @Test(groups = {"functional", "negative"})
    public void TC_AC_004() throws IOException {
        Reporter.log("TC_AC_004: Verify Robotic Process Automation category behavior", true);
        try {
            edurekaHomePage.clickAllCoursesLink();
            edurekaAllCoursesPage.clickRPACategory();
            Assert.assertTrue(edurekaAllCoursesPage.verifyPageNotFoundMessage(), "Page not found message is not displayed");
            Reporter.log("TC_AC_004 passed: Page not found message is displayed", true);
        } finally {
            takeScreenshot("TC_AC_004");
        }
    }

    // TC_AC_005: Verify course description
//    @Test(groups = {"functional", "regression"})
//    public void TC_AC_005() {
//        Reporter.log("TC_AC_005: Verify course description", true);
//        edurekaHomePage.clickAllCoursesLink();
//        Assert.assertTrue(edurekaAllCoursesPage.verifyAllCoursesPage());
//        Assert.assertTrue(edurekaAllCoursesPage.verifyCourseDescription());
//        Assert.assertFalse(edurekaAllCoursesPage.getCourseDescription().isEmpty());
//        driver.manage().window().setSize(new Dimension(1366, 768));
//        Assert.assertTrue(edurekaAllCoursesPage.verifyCourseDescription());
//        driver.manage().window().setSize(new Dimension(1024, 768));
//        Assert.assertTrue(edurekaAllCoursesPage.verifyCourseDescription());
//        Reporter.log("TC_AC_005 passed", true);
//    }

    // TC_AC_006: Verify All Courses pagination
//    @Test(groups = {"functional", "regression"})
//    public void TC_AC_006() throws EncryptedDocumentException, IOException {
//        Reporter.log("TC_AC_006: Verify All Courses pagination", true);
//        edurekaHomePage.clickAllCoursesLink();
//        Assert.assertTrue(edurekaAllCoursesPage.verifyAllCoursesPage());
//        String expectedNextPageParameter = testData.getTData("AllCourses", 2, 1);
//        edurekaAllCoursesPage.clickNextPage();
//        Assert.assertTrue(edurekaAllCoursesPage.verifyNextPage(expectedNextPageParameter), "Next button failed");
//        edurekaAllCoursesPage.clickPreviousPage();
//        Assert.assertTrue(edurekaAllCoursesPage.verifyPreviousPage(expectedNextPageParameter), "Previous button failed");
//        Reporter.log("TC_AC_006 passed", true);
//    }
}