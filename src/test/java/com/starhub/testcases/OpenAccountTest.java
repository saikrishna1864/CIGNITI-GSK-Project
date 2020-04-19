package com.starhub.testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.starhub.constants.BaseClass;
import com.starhub.constants.CustomTestListners;
import com.starhub.library.BankManagerHomePage;

@Listeners(CustomTestListners.class)
public class OpenAccountTest extends BaseClass 
{
	
	@Test(priority = 2)
	public void verifyOpenAccouontTest() throws InterruptedException
	{
		
		//clickOnBankManagerLink("bankManager_CSS");
		
		log.debug("Stated execution of open account test");
		
		//driver.findElement(By.cssSelector("div.center>button.btn.btn-primary.btn-lg[ng-click='manager()']")).click();
		
		log.debug("Clicked  on bank manager link");
		
		BankManagerHomePage bhome = new BankManagerHomePage();
		
		log.debug("Created object for bhome for open account test");
		
		boolean res = bhome.openAccount();
		
		log.debug(res);
		
		Assert.assertTrue(res);
		
		log.debug(res);
		
		alertReceived().accept();
		
		log.debug("open account test alert was accepted");
	}

}
