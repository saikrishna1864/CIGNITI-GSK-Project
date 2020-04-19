package com.starhub.library;

import org.openqa.selenium.By;

import com.starhub.constants.BaseClass;

public class BankManagerHomePage extends BaseClass
{
	
	
	public boolean addCustomer()
	{

		click("addcustomerButton_CSS");
		
		/*driver.findElement(By.cssSelector("div.form-group>input[ng-model='fName']")).sendKeys(Locator.getProperty("firstname"));
		
		driver.findElement(By.cssSelector("div.form-group>input[ng-model='lName']")).sendKeys(Locator.getProperty("lastname"));
		
		driver.findElement(By.cssSelector("div.form-group>input[ng-model='postCd']")).sendKeys(Locator.getProperty("postcode"));
		
		driver.findElement(By.cssSelector("div.marTop>form[name='myForm']>button.btn.btn-default[type='submit']")).click();
		*/
		
		
		sendKeys("firstname_CSS", Locator.getProperty("firstname"));
		sendKeys("lastname_CSS", Locator.getProperty("lastname"));
		sendKeys("postcode_CSS", Locator.getProperty("postcode"));
		click("addcustomerSubmitButton_CSS");
		
		
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		String act = alertReceived().getText();
		
		//String act = alert.getText();
		
		String exp = "Customer added successfully with customer id";
		
		if (act.toLowerCase().contains(exp.toLowerCase()))
		{
			
			return true;
		} else {

			return false;
		}
		
		
		
	}
	
	
	
	public boolean openAccount() throws InterruptedException
	{
		
		//driver.findElement(By.cssSelector("div.center>button.btn.btn-lg.tab:nth-child(2)")).click();
		
		click("openaccountButton_CSS");
		
		BaseClass.selectOption("customername_CSS", Locator.getProperty("customer_name"));
		
		Thread.sleep(3000);
		
		log.debug("Customer selected");
		
		BaseClass.selectOption("currency_CSS", Locator.getProperty("currency_type"));
		
		Thread.sleep(3000);
		
		log.debug("currency selected");
		
		/*Select fromlist = new Select(driver.findElement(By.cssSelector("div.form-group>select#userSelect[name='userSelect'][ng-model='custId']")));
		
		
		
		fromlist.selectByVisibleText(Locator.getProperty("customer_name"));
		
		Select fmlist = new Select(driver.findElement(By.cssSelector("div.form-group>select#currency[name='currency'][ng-model='currency']")));
		
		fmlist.selectByVisibleText(Locator.getProperty("currency_type"));
		*/
		
		
		//driver.findElement(By.cssSelector("div.ng-scope>div.marTop>form[name='myForm']>button[type='submit']")).click();
		
		click("openaccountSubmitButton_CSS");
		
		//String act = driver.switchTo().alert().getText();
		
		Thread.sleep(2000);
		
		String act = alertReceived().getText();
		
		log.debug(act);
		
		Thread.sleep(2000);
		
		String exp = "Account created successfully with account number";
		
		
		
		if (act.toLowerCase().contains(exp.toLowerCase()))
		{
			return true;
			
		} else {

			return false;
		}
		
	}
	
}



