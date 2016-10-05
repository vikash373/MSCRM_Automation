package com.decexp.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.decexp.pages.ContactsPage;
import com.decexp.pages.AddMemberPage;
import com.decexp.utils.AutomationException;
import com.decexp.utils.BaseTest;

public class ContactsTest extends BaseTest {
		
	ContactsPage contactPage = new ContactsPage(driver);
	AddMemberPage newMemberPage = new AddMemberPage(driver);
	
	public void verifyContactCreation() throws AutomationException{
		try{
			String contactID = newMemberPage.getContactID();
			
			Assert.assertEquals(contactPage.searchContactNo(contactID), "Contact ID is not searched");
				
		}catch(Exception e){
		throw new AutomationException(e.getMessage());
	
	}
	}
}
