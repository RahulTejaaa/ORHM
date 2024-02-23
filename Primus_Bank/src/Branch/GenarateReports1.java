package Branch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenarateReports1 
{
	WebDriver driver;
	ExtentReports reports;
	ExtentTest logger;
	@BeforeTest
	public void CreateReport() 
	{
		reports = new ExtentReports("./ExtentReports/reports.html");
	}
	@BeforeMethod
	public void LaunchApp() 
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://google.com");
	}
	@Test
	public void TestPass()
	{
		logger = reports.startTest("Test Pass have done");
		logger.assignAuthor("Rahul");
		logger.assignCategory("Functional");
		String Expected_Title = "Google";
		String Actual_Title = driver.getTitle();
		if(Actual_Title.equalsIgnoreCase(Expected_Title))
		{
			logger.log(LogStatus.PASS, "test is pass"+"------"+Actual_Title+"-------"+Expected_Title);
		}
		else
		{
			logger.log(LogStatus.FAIL, "test is fail"+"------"+Actual_Title+"-------"+Expected_Title);
		}
	}
	@Test
	public void TestFail() 
	{
		logger = reports.startTest("Test Fail");
		logger.assignAuthor("Rahul");
		logger.assignCategory("Functional");
		String Expected_Title = "Gmail";
		String Actual_Title = driver.getTitle();
		if(Actual_Title.equalsIgnoreCase(Expected_Title))
		{
			logger.log(LogStatus.PASS, "test is pass"+"------"+Actual_Title+"-------"+Expected_Title);
		}
		else
		{
			logger.log(LogStatus.FAIL, "test is fail"+"------"+Actual_Title+"-------"+Expected_Title);
		}
	}
	@AfterMethod
	public void teardown() 
	{
		reports.endTest(logger);
		reports.flush();
		driver.quit();
	}
}
