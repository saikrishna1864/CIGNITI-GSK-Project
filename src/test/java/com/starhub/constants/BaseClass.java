package com.starhub.constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

public class BaseClass 
{
	
	
	public static WebDriver driver ;
	
	public static Logger log = Logger.getLogger("saikrishna");
	
	public static FileInputStream fis;
	
	public static WebDriverWait wait;
	
	public static String browser;
	
	public static Properties config = new Properties();
	
	public static Properties Locator = new Properties();
	
	@BeforeSuite
	public  static void openApp()
	{
		
		if (driver==null) {
			
		
		
		log.debug("About to start @BeforeSuite");
		
			try {
				fis = new FileInputStream("C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\src\\test\\resources\\PropertyFiles\\Locator.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			try {
				Locator.load(fis);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
				
			
			try {
				fis = new FileInputStream("C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\src\\test\\resources\\PropertyFiles\\Config.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
			if (System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
			{
				
				browser = System.getenv("browser");
				
				
			} else {
				
				browser = config.getProperty("browser");

			}
			
			
			config.setProperty("browser", browser);
			
			log.debug("Browser is configured as "+browser);
			
			if (config.getProperty("browser").equals("chrome"))
			{
		

				System.setProperty("webdriver.chrome.driver", "C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\chromedriver.exe");
				
				driver = new ChromeDriver();
				
				log.debug("About to open chrome browser");
				
			} else if(config.getProperty("browser").equals("firefox")) 
			{


				System.setProperty("webdriver.gecko.driver", "C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\src\\test\\resources\\Runnables\\geckodriver.exe");
				
				driver = new FirefoxDriver();
				
				log.debug("About to open firefox brower");
			}
			
			
			

			
			
			
			driver.manage().deleteAllCookies();
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			log.debug("About to open the application under test");
			
			driver.get(config.getProperty("AUTSite"));
			
			log.debug("Opened application under test");
			
			 wait = new WebDriverWait(driver, 20);

			
		
		
		}
		
		
/*		System.setProperty("webdriver.chrome.driver", "C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://www.way2automation.com/angularjs-protractor/banking");
		
		 wait = new WebDriverWait(driver, 20);

		*/

		
	}
	
	
	@AfterSuite
	public  void closeApp()
	{
		
		driver.close();
		
		log.debug("Closed the application");
		
	}
	
	
	public static Alert alertReceived()
	{
		
		Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
		
		return alert;
	}
	
	
	
	public static void click(String locator)
	{

		if (locator.endsWith("_CSS"))
		{
		
			driver.findElement(By.cssSelector(Locator.getProperty(locator))).click();
		} else if (locator.endsWith("_XPTAH"))
		{
			 driver.findElement(By.xpath(Locator.getProperty(locator))).click();

		}else if (locator.endsWith("_ID"))
		{
			 driver.findElement(By.id(Locator.getProperty(locator))).click();

		}
		
		CustomTestListners.testreport.get().log(Status.INFO , "Clicked the element "+locator);
		
	}

	
	
	public static void sendKeys(String locator,String value)
	{

		if (locator.endsWith("_CSS"))
		{
		
			driver.findElement(By.cssSelector(Locator.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPTAH"))
		{
			 driver.findElement(By.xpath(Locator.getProperty(locator))).sendKeys(value);

		}else if (locator.endsWith("_ID"))
		{
			 driver.findElement(By.id(Locator.getProperty(locator))).sendKeys(value);

		}
	
		
		CustomTestListners.testreport.get().log(Status.INFO , "Found the element "+locator+" Value given as input is "+value);
	}

	
	public static void verifyEquals(String exp , String act) throws IOException
	{
		
		
		try {
			
			Assert.assertEquals(exp, act);
			
		} catch (Throwable T)
		{
			
			captureStepError();
			
			log.debug("screenshot for test step has been captured");
			
			CustomTestListners.testreport.get().log(Status.FAIL, "Verification failed with execption :"+T.getMessage());
			
			CustomTestListners.testreport.get().addScreenCaptureFromPath(screenshotname);
			
				//	CustomTestListners.testreport.get().addScreencastFromPath(BaseClass.screenshotname);
			
			
			
			
		}
	}
	
	public static String screenshotname ;

	public static void captureStepError()
	{
		
		
		Date d = new Date();
		
		 screenshotname = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotname));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void captureScreenShot(String testmethodname)
	{
		
		
		Date d = new Date();
		
		
		
		String timestamp = d.toString().replace(":", "_").replace(" ", "_");
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(src, new File("C:\\Users\\KNOT\\eclipse-workspace\\EndToEndFlow\\src\\test\\resources\\Screenshots\\"+testmethodname+timestamp+".jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	static WebElement dropdown;
	
	public static void selectOption(String locator , String value)
	{
		
		
		if (locator.endsWith("_CSS"))
		{
		
			log.debug("CSS is the selector given");
			dropdown = driver.findElement(By.cssSelector(Locator.getProperty(locator)));
		} else if (locator.endsWith("_XPTAH"))
		{
			dropdown = driver.findElement(By.xpath(Locator.getProperty(locator)));

		}else if (locator.endsWith("_ID"))
		{
			dropdown = driver.findElement(By.id(Locator.getProperty(locator)));

		}

		
		Select fromlist = new Select(dropdown);
		
		fromlist.selectByVisibleText(value);
		
	}







}



