package DriverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.AddCustomerPage;
import Config.AppUtils;

import Utilities.ExcelFileUtils;

public class AppTest extends AppUtils
{
String inputpath = "./FileInput/CustomerList.xlsx";
String outputpath = "./FileOutput/CustomerAddResults.xlsx";
ExtentReports reports;
ExtentTest logger;
@Test
public void startTest() throws Throwable
{
	reports = new ExtentReports("./Reports/CustomerReports.html");
	ExcelFileUtils ex = new ExcelFileUtils(inputpath);
	AddCustomerPage cus = PageFactory.initElements(driver, AddCustomerPage.class);;
	int rc = ex.rowCount("Customer");
	for(int i=1;i<=rc;i++)
	{
		logger = reports.startTest("Customer module");
		logger.assignAuthor("Rahul");
		String cname = ex.getCellData("Customer", i, 0);
		String address = ex.getCellData("Customer", i, 1);
		String city = ex.getCellData("Customer", i, 2);
		String country = ex.getCellData("Customer", i, 3);
		String contactperson = ex.getCellData("Customer", i, 4);
		String phonenumber = ex.getCellData("Customer", i, 5);
		String email = ex.getCellData("Customer", i, 6);
		String mobilenumber = ex.getCellData("Customer", i, 7);
		String notes = ex.getCellData("Customer", i, 8);
		logger.log(LogStatus.INFO, cname+"  "+address+"   "+city+"    "+country+"   "+
			    contactperson+"   "+phonenumber+"  "+email+"   "+mobilenumber+"    "+notes);
		boolean res = cus.customer(cname, address, city, country, 
				contactperson, phonenumber, email, mobilenumber, notes);
		if(res)
		{
			ex.setCellData("Customer", i, 9, "pass", outputpath);
			logger.log(LogStatus.PASS, "customer added successfully");
		}else
		{
			ex.setCellData("Customer", i, 9, "fail", outputpath);
			logger.log(LogStatus.FAIL, "customer added failed");
		}
		reports.endTest(logger);
		reports.flush();
	}
}
}
