package com.decexp.tests;

import org.testng.annotations.Test;


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
import com.decexp.pages.WebJoinerPage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;
import com.decexp.utils.Utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebJoinerTest extends BaseTest {
	//private static final Logger logger = Logger.getLogger("WebJoinerTest.class");
		
	private Utils utils = null;
	private WebJoinerPage webjoinerpage = null;
	private ExtentReports extentReport = null;
	private ExtentTest extentLogger = null;
	private SoftAssert softAssertion = null;
	
	
	@BeforeClass
	public void beforeClass() throws AutomationException {
		
		utils = new Utils(driver);
		softAssertion = new SoftAssert();
		webjoinerpage = new WebJoinerPage(driver);
	}
	
	@Test(priority=1)
	public void openJoinersApp() throws AutomationException, InterruptedException {
		
		webjoinerpage.openJoinersApplication("https://surgicalmembershipportal.co.uk/ASGBITest/member-area/Web-Joiner");
		
		if(webjoinerpage.verifyHomePage()==false){
			//## Retry After 10 Secs if due to trail version ADX Web Joiners Application doesn't comes up
			Thread.sleep(11000);
			webjoinerpage.openJoinersApplication("https://surgicalmembershipportal.co.uk/ASGBITest/member-area/Web-Joiner");
		}
		
		Thread.sleep(2000);
		Assert.assertTrue(webjoinerpage.verifyHomePage(), " Joiner app is failed to load");
					
	}
	
	
	@Test(dependsOnMethods="openJoinersApp",priority=2)
	public void registerforPaidMembeship() throws AutomationException{
		
		webjoinerpage.registerForMemberSubscription();
		
	}
	
}
