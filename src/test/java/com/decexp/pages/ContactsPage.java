package com.decexp.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

public class ContactsPage {
	private static WebDriver driver;
	private static final Logger logger = Logger.getLogger(ContactsPage.class);
	
	@FindBy(id="crmGrid_findCriteria")
	WebElement searchTextField;
	
	@FindBy(xpath="//td[@id='crmGrid_RecordSelectInfo']/span[1]")
	WebElement searchCount;
	
	public ContactsPage(WebDriver driver){
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	public boolean searchContactNo(String contactID){
	try{
		logger.info("Switch to IFrame");
		driver.switchTo().frame("contentIFrame1");
		logger.info("Clear the Search Text Field");
		searchTextField.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		logger.info("Search the Contact ID"+contactID);
		searchTextField.sendKeys(contactID);
		searchTextField.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		}catch(Exception e){
		e.printStackTrace();
		}
		if(searchCount.getText().equalsIgnoreCase("1")){
			return true;
		}else{
		return false;
		}
	}
}
	
