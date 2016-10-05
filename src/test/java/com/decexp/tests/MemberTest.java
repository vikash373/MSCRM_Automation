package com.decexp.tests;

/*
 * This test class is used to create prospect
 * or Member record
 */

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.decexp.beans.AddMemberDetails;
import com.decexp.fixtures.DataProviderAddMember;
import com.decexp.pages.AddMemberPage;
import com.decexp.pages.CRMLoginPage;
import com.decexp.pages.ContactsPage;
import com.decexp.pages.HomePage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;
import com.decexp.utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MemberTest extends BaseTest {
	private static final Logger logger = Logger.getLogger("MemberTest.class");
	private CRMLoginPage loginPage = null;
	private HomePage homePage = null;
	private AddMemberPage addMemberPage = null;
	private Utils utils = null;
	private ContactsPage contactsPage = null;
	private ExtentReports extentReport = null;
	private ExtentTest extentLogger = null;
	private SoftAssert softAssertion = null;
	
	@BeforeClass
	public void beforeClass() {
		loginPage = new CRMLoginPage(driver);
		homePage = new HomePage(driver);
		addMemberPage = new AddMemberPage(driver);
		contactsPage = new ContactsPage(driver);
		utils = new Utils(driver);
		softAssertion = new SoftAssert();
		
	}
	
	@Test(dataProvider="AddMember", dataProviderClass=DataProviderAddMember.class, description="This test case is used to create prospect")
	public void createNewMember(AddMemberDetails newMember) throws AutomationException{
		extentReport = new ExtentReports(System.getProperty("user.dir")+"\\Report\\AddMember.html");
		extentLogger = extentReport.startTest("Verify Add Member using Membership workflow");
		
		loginPage.loginAs("http://172.20.26.106/ASGBI/main.aspx");
		extentLogger.log(LogStatus.INFO, "Application is up and running");
		
		//loginPage.loginAs(ConfigurationDataProvider.getReader().getTestURL());
		//Assert.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		softAssertion.assertTrue(loginPage.verifyHomePage(),"Home Page is failed to load");
		
		extentLogger.log(LogStatus.INFO, "Home Page is loaded successfully");

		System.out.println("Click on Add Member button");
		utils.clickonAddMemberButton();
		//Assert.assertTrue(addMemberPage.verifyPageTitle(), "New Membership page is failed to load");
		softAssertion.assertTrue(addMemberPage.verifyPageTitle(), "New Membership page is failed to load");
		System.out.println("Start filling the Membership form-->");
		extentLogger.log(LogStatus.INFO, "New Membership Page is loaded successfully");
		
		extentLogger.log(LogStatus.INFO, "Start to create member using membership workflow");
		
		addMemberPage.switchToFrame();
		addMemberPage.setForeName(newMember.ForeName);
		addMemberPage.setSureName(newMember.SurName);
		//addMemberPage.setInitials(newMember.Initials);
		addMemberPage.setTitle(newMember.Title);
		addMemberPage.setGender(newMember.Gender);
		addMemberPage.setMobile(newMember.Mobile);
		addMemberPage.setPostCode(newMember.PostCode);
		addMemberPage.setEmail(newMember.Email);
		addMemberPage.setCountry(newMember.Country);
		addMemberPage.setBranch(newMember.Branch);
		addMemberPage.setDuplicateLookup();
		boolean conditionFlag = addMemberPage.setTermsandCondition(newMember.TermsAndCondition);
		if(conditionFlag==false){
		softAssertion.assertTrue(addMemberPage.getOrderText().contains("Order"), "Order Details section is not load");
		addMemberPage.selectProduct(newMember.Product);
		}
		addMemberPage.clickSave();
		String contactID = addMemberPage.getContactID();
		System.out.println("Created Contact ID is-->"+contactID);
		extentLogger.log(LogStatus.INFO, "Member is created successfully");
		utils.clickonContacts();
		//Assert.assertTrue(contactsPage.searchContactNo(contactID));
		softAssertion.assertTrue(contactsPage.searchContactNo(contactID), "Contact Number is not found");
		System.out.println("Contact is found");
		extentLogger.log(LogStatus.INFO, "Created Member verified");
		softAssertion.assertAll();
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			String screenshot_path = Utils.captureScreenshot(driver,result.getName());
			String image = extentLogger.addScreenCapture(screenshot_path);
			extentLogger.log(LogStatus.FAIL, "Create Member using membership workflow", image);
		}
		extentReport.endTest(extentLogger);
		extentReport.flush();
		
		driver.get(System.getProperty("user.dir")+"\\Report\\AddMember.html");
	}

}
