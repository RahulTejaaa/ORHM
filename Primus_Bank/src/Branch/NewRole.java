package Branch;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewRole 
{
	WebDriver driver;
	Properties p;
	@BeforeTest
	public void setup() throws Throwable
	{
		p= new Properties();
		p.load(new FileInputStream("branch.properties"));
		driver = new ChromeDriver();
		driver.get(p.getProperty("Url"));
		driver.findElement(By.xpath(p.getProperty("ObjUser"))).sendKeys(p.getProperty("Username"));
		driver.findElement(By.xpath(p.getProperty("ObjPass"))).sendKeys(p.getProperty("Password"));
		driver.findElement(By.xpath(p.getProperty("ObjLogin"))).click();
	}
	@Test
	public void Newrole() 
	{
		driver.findElement(By.xpath(p.getProperty("ObjRole"))).click();
		driver.findElement(By.xpath(p.getProperty("ObjNewRole"))).click();
		driver.findElement(By.xpath(p.getProperty("ObjRoleName"))).sendKeys(p.getProperty("RoleName"));
		driver.findElement(By.xpath(p.getProperty("ObjRoleDesc"))).sendKeys(p.getProperty("RoleDesc"));
		new Select(driver.findElement(By.xpath(p.getProperty("ObjRoleType")))).selectByVisibleText(p.getProperty("Roletype"));
		driver.findElement(By.xpath(p.getProperty("ObjSubmit"))).click();
		String alertmsg = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Reporter.log(alertmsg,true);
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
