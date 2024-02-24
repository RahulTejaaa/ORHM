package Config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import CommonFunctions.AdminLoginPage;
import CommonFunctions.AdminLogoutPage;


public class AppUtils 
{
public static Properties p;
public static WebDriver driver;
@BeforeTest
public static void setUp() throws Throwable
{
	p=new Properties();
	p.load(new FileInputStream("./Property/Login.properties"));
	if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(p.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AdminLoginPage login=PageFactory.initElements(driver, AdminLoginPage.class);
		login.adminlogin("admin", "master");
	}
	else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.get(p.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
		login.adminlogin("admin", "master");
	}else
	{
		Reporter.log("browser is not matching",true);
	}
}
@AfterTest
public static void tearDown()
{
	AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
	logout.adminlogout();
	driver.quit();
}

}
