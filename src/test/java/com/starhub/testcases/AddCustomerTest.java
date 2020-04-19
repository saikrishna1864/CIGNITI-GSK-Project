package com.starhub.testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.starhub.constants.BaseClass;
import com.starhub.constants.CustomTestListners;
import com.starhub.library.BankManagerHomePage;

@Listeners(CustomTestListners.class)
public class AddCustomerTest extends BaseClass 
{
	
	
	
	@Test(priority = 1)
	public void verifyAddCustomerTest() throws InterruptedException
	{
		
		//clickOnBankManagerLink("bankManager_CSS");
		
		log.debug("Reached first point of add customer test");
		
		//driver.findElement(By.cssSelector("div.center>button.btn.btn-primary.btn-lg[ng-click='manager()']")).click();
	
		click("bankmanagerButton_CSS");
		
		log.debug("Bank manager link is clicked");
		BankManagerHomePage bmhome = new BankManagerHomePage();
		log.debug("object for bhome has been created");
		boolean res = bmhome.addCustomer();
		log.debug(res);
		Assert.assertTrue(res);
		
		log.debug("add customer test assert is passed");

		alertReceived().accept();
		
		log.debug("add customer test alert is clicked");
		Thread.sleep(5000);
		
		
	}
	
	

}
