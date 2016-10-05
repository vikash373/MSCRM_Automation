package com.decexp.pages;

import org.testng.log4testng.Logger;
import org.openqa.selenium.WebDriver;

import com.decexp.utils.AutoItUtills;
import com.decexp.utils.AutomationException;

public class WebJoinerPage {
	
	private static WebDriver driver;
	private final static Logger logger = Logger.getLogger(WebJoinerPage.class);
	private static String joinershomePageTitle = "Web Joiner · Conference Portal";
	
	public WebJoinerPage(WebDriver driver){
		this.driver=driver;
	}
	
	public static void openJoinersApplication(String joinersURl) throws AutomationException{
		try{
			
			driver.get(joinersURl);
			//Thread.sleep(1000);							
			logger.info(joinersURl +" is loading and wait for 5 seconds");
			Thread.sleep(5000);
		}catch(Exception e){
			throw new AutomationException(e.getMessage());
		}
		
	}
	
	public boolean verifyHomePage(){
		return driver.getTitle().startsWith(joinershomePageTitle);
				
	}
		
	public void selectMembershipProduct() {
		// TODO Auto-generated method stub
		
	}
	
	

}
