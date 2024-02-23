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

public class GenarateReports 
{
	WebDriver driver;
	ExtentReports reports;
	ExtentTest logger;
	@BeforeTest
	public void ReportsBlog() 
	{
		reports = new ExtentReports("./ExtentReports/reports.html");		
	}
	@BeforeMethod
	public void SetUp()
	{
		driver = new  ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://google.com");
	}
	@Test
	public void TestPass()
	{
		logger=reports.startTest("Test Pass");
		logger.assignAuthor("Rowdy");
		logger.assignCategory("Functional");
		String Expected_Title="Google";
		String Actual_title=driver.getTitle();
		if(Actual_title.equalsIgnoreCase(Expected_Title))
		{
			logger.log(LogStatus.PASS, "Test Pass"+"-----"+Actual_title+"----"+Expected_Title);
		}
		else
		{
			logger.log(LogStatus.FAIL, "Test Fail"+"-----"+Actual_title+"-----"+Expected_Title);
		}
	}
	@Test
	public void TestFail() 
	{
		logger=reports.startTest("Test Fail");
		logger.assignAuthor("Rowdy");
		logger.assignCategory("Functional");
		String Expected_Title="Gmail";
		String Actual_title=driver.getTitle();
		if(Actual_title.equalsIgnoreCase(Expected_Title))
		{
			logger.log(LogStatus.PASS, "Test Pass"+"-----"+Actual_title+"----"+Expected_Title);
		}
		else
		{
			logger.log(LogStatus.FAIL, "Test Fail"+"-----"+Actual_title+"-----"+Expected_Title);
		}
	}
	@AfterMethod
	public void TearDown() 
	{
		reports.endTest(logger);
		reports.flush();
		driver.quit();
	}
}
