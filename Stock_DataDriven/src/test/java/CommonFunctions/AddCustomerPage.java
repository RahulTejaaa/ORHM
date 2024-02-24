package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddCustomerPage 
{
	@FindBy(xpath =  "(//a[starts-with(text(),'Customers')])[2]")
	WebElement ObjCustomerbtn;
	@FindBy(xpath = "(//span[contains(@class,'glyphicon glyphicon-plus ewIcon')])[1]")
	WebElement ObjAdd;
	@FindBy(id = "x_Customer_Number")
	WebElement ObjCustomerNumber;
	@FindBy(name = "x_Customer_Name")
	WebElement ObjCustomerName;
	@FindBy(id = "x_Address")
	WebElement ObjAddress;
	@FindBy(id = "x_City")
	WebElement ObjCity;
	@FindBy(id = "x_Country")
	WebElement ObjCountry;
	@FindBy(name = "x_Contact_Person")
	WebElement ObjContactPerson;
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement ObjPhoneNumber;
	@FindBy(xpath = "//input[@id='x__Email']")
	WebElement ObjEmail;
	@FindBy(id = "x_Mobile_Number")
	WebElement ObjMobileNumber;
	@FindBy(id = "x_Notes")
	WebElement ObjNotes;
	@FindBy(xpath = "//button[@id='btnAction']")
	WebElement ObjAddBtn;
	@FindBy(xpath = "//button[contains(text(),'OK!')]")
	WebElement ObjConfirmOkBtn;
	@FindBy(xpath = "(//button[starts-with(text(),'OK')])[6]")
	WebElement ObjAlertOkBtn;
	@FindBy(xpath = "//button[contains(@data-toggle,'button')]")
	WebElement ObjSearchPanel;
	@FindBy(name = "psearch")
	WebElement ObjSearchText;
	@FindBy(name = "btnsubmit")
	WebElement ObjSearchBtn;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement table;
	public boolean customer(String cname,String address,String city,
			String country,String contactperson,String phonenumber,String email,String mobilenumber,String notes) throws Throwable
	{
		ObjCustomerbtn.click();
		Thread.sleep(5000);
		ObjAdd.click();
		String exp_cnum = ObjCustomerNumber.getAttribute("value");		
		ObjCustomerName.sendKeys(cname);
		ObjAddress.sendKeys(address);
		ObjCity.sendKeys(city);
		ObjCountry.sendKeys(country);
		ObjContactPerson.sendKeys(contactperson);
		ObjPhoneNumber.sendKeys(phonenumber);
		ObjEmail.sendKeys(email);
		ObjMobileNumber.sendKeys(mobilenumber);
		ObjNotes.sendKeys(notes);
		ObjAddBtn.click();
		Thread.sleep(2000);
		ObjConfirmOkBtn.click();
		Thread.sleep(2000);
		ObjAlertOkBtn.click();
		Thread.sleep(2000);
		if(!ObjSearchText.isDisplayed())
			ObjSearchPanel.click();
		ObjSearchText.clear();
		ObjSearchText.sendKeys(exp_cnum);		
		ObjSearchBtn.click();
		Thread.sleep(3000);
		String act_cnum = table.getText();
		Thread.sleep(3000);
		if(exp_cnum.equals(act_cnum))
		{
			Reporter.log("Customer Number is Matching"+act_cnum+"------"+exp_cnum,true);
			return true;
		}
		else
		{
			Reporter.log("Customer is Not Matching"+act_cnum+"-----"+exp_cnum,true);
			return false;
		}
	}
}
