package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage 
{
@FindBy(id = "btnreset")
WebElement Objresetbtn;
@FindBy(xpath = "//input[@id='username']")	
WebElement Objuser;
@FindBy(name = "password")
WebElement Objpass;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement ObjLoginbtn;
public void adminlogin(String user,String pass)
{
	Objresetbtn.click();
	Objuser.sendKeys(user);
	Objpass.sendKeys(pass);
	ObjLoginbtn.click();
}
}
