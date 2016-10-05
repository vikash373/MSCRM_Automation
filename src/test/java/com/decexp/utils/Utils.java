package com.decexp.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Utils extends BaseTest {
	private static WebDriver driver;
	private static final Logger logger = Logger.getLogger(Utils.class);
	@FindBy(xpath="//*[@id='de_membershipworkflow|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.de_membershipworkflow.NewRecord']/span/a")
	WebElement addMemberButton;
	
	
	@FindBy(xpath="//span[@id='TabMembership']/a")
	WebElement membershipDropdown;
	
	@FindBy(linkText="Contacts")
	WebElement contactsButton;
	
	public Utils(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickonAddMemberButton() throws AutomationException{
		try{
		
		Thread.sleep(5000);
		addMemberButton.click();
		System.out.println("Add Member Button is clicked");
		Thread.sleep(5000);
		}catch(Exception e){
			throw new AutomationException("Add Member button is not found");
		}
	}
	
	public void clickonContacts(){
		try{
		driver.switchTo().defaultContent();
		logger.info("Click on Membership dropdown link");
		membershipDropdown.click();
		Thread.sleep(1000);
		logger.info("Click on Contact link");
		contactsButton.click();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String captureScreenshot(WebDriver driver, String screenshotName){
		try{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			String dest = System.getProperty("user.dir")+"\\Screenshot\\"+screenshotName+".png";
			File destination = new File(dest);
			FileUtils.copyFile(src, destination);
			System.out.println("Screenshot Taken");
			return dest;
		}catch(Exception e){
			System.out.println("Exception while taking screenshot");
			return e.getMessage();
			}
	}

}
