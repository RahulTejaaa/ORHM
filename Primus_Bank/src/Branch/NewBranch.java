package Branch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewBranch 
{
	WebDriver driver;
	Properties p;
	@BeforeTest
	public void login() throws Throwable
	{
		p=new Properties();
		p.load(new FileInputStream("branch.properties"));
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("Url"));
		driver.findElement(By.xpath(p.getProperty("ObjUser"))).sendKeys(p.getProperty("Username"));
		driver.findElement(By.xpath(p.getProperty("ObjPass"))).sendKeys(p.getProperty("Password"));
		driver.findElement(By.xpath(p.getProperty("ObjLogin"))).click();
	}
	@Test
	public void newbranch() throws InterruptedException
	{
		driver.findElement(By.xpath(p.getProperty("ObjBranches"))).click();
		driver.findElement(By.xpath(p.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(p.getProperty("ObjBranchName"))).sendKeys(p.getProperty("BranchName"));
		driver.findElement(By.xpath(p.getProperty("ObjAdd1"))).sendKeys(p.getProperty("Add1"));
		driver.findElement(By.xpath(p.getProperty("ObjAdd2"))).sendKeys(p.getProperty("Add2"));
		driver.findElement(By.xpath(p.getProperty("ObjAdd3"))).sendKeys(p.getProperty("Add3"));
		driver.findElement(By.xpath(p.getProperty("ObjArea"))).sendKeys(p.getProperty("Area"));
		driver.findElement(By.xpath(p.getProperty("ObjZip"))).sendKeys(p.getProperty("ZIP"));
		new Select(driver.findElement(By.xpath(p.getProperty("ObjCountry")))).selectByVisibleText(p.getProperty("Country"));
		new Select(driver.findElement(By.xpath(p.getProperty("ObjState")))).selectByVisibleText(p.getProperty("State"));
		new Select(driver.findElement(By.xpath(p.getProperty("ObjCity")))).selectByVisibleText(p.getProperty("City"));
		driver.findElement(By.xpath(p.getProperty("ObjSubmit"))).click();
		Thread.sleep(2000);
		String alert= driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Reporter.log(alert,true);

	}
	@AfterTest
	public void quit()
	{
		driver.quit();
	}
}
