package Testcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;

import Browsers.BrowserLaunch;
import ElementUtils.webElementCommons;
import Pages.MakeMyTripOneWaySearchPage;
import Pages.SearchReultPage;
import Utils.ExcelFileRead;
import Utils.propertyFile;

public class MakeMytripSearch extends BrowserLaunch{

	@BeforeSuite
	public void Launch() throws IOException
	{
		LaunchBrowser();
	}
	
	@BeforeTest
	public void EnterURL() throws IOException
	{
		String URL = propertyFile.ReadDataFromProperty().getProperty("url");
		browser.get(URL);
	}
	
	@BeforeClass
	public void Closepopup()
	{
		webElementCommons.CloseLoginPopup(browser);
	}
	
	String filePath =null;
	int count=0;
	@Test(priority=0,dataProvider="GetValidSearchData",dataProviderClass=MakeMytripDataProvider.class)
	public void SearchFlightWithValidCountry(String from,String to,String date, String totalPassenger)
	{
		test = extent.createTest("Test Case 1", "PASSED test case");
		test.log(Status.INFO, "Launch Browser");
		count=count+1;
		/*
		 * Select From location
		 * Select to Location
		 * Select date
		 * Click OnSearch
		 * Assert the result displayed for search criteria
		 */
		MakeMyTripOneWaySearchPage M = new MakeMyTripOneWaySearchPage(browser);
		M.ClickFromLocation();
		M.SelectDataFromList(from);
		test.log(Status.INFO, "Select the from location as :"+from);
		M.ClickToLocation();
		M.SelectDataFromList(to);
		test.log(Status.INFO, "Select the to location as : "+to);
		M.SelectDate(date);
		test.log(Status.INFO, "Select the Date as " +date);
		M.SelectTraveller(totalPassenger);
		M.clickOnSearchButton();
		test.log(Status.INFO, "SearchButton is cicked");
		SearchReultPage sp = new SearchReultPage(browser);
		Assert.assertEquals(sp.GetNetworkError(), "NETWORK PROBLEM");
		filePath = webElementCommons.Screenshot(browser,"SearchFlightWithValidCountry"+count);
		browser.navigate().back();
		//test.log(Status.PASS, "SearchFlightWithValidCountry is completed");
		
	}
	
	@Test(priority=1,dataProvider="SameCityValidationData",dataProviderClass=MakeMytripDataProvider.class)
	public void SameCityErroValidation(String from,String to)
	{
		count=count+1;
		test = extent.createTest("Test Case 2", "PASSED test case");
		test.log(Status.INFO, "Launch Browser");
		/*
		 * Select From location
		 * Select to Location
		 * Select date
		 * Click OnSearch
		 * Assert the result displayed for search criteria
		 */
		MakeMyTripOneWaySearchPage M = new MakeMyTripOneWaySearchPage(browser);
		M.ClickFromLocation();
		M.SelectDataFromList(from);
		test.log(Status.INFO, "Select the from location as : "+from);
		M.ClickToLocation();
		M.SelectDataFromList(to);
		test.log(Status.INFO, "Select the to location as : "+to);
		Assert.assertEquals(M.GetSameCityError(), "From & To airports cannot be the same");
		filePath = webElementCommons.Screenshot(browser,"SameCityErroValidation"+count);
		//test.log(Status.PASS, "SearchFlightWithValidCountry is completed");
		
	}
	
	@AfterMethod
	public void WriteInReport(ITestResult result) throws IOException
	{
		if (result.getStatus()==ITestResult.SUCCESS) {
			
		     test.log(Status.INFO,test.addScreenCaptureFromPath(filePath).toString());
            test.log(Status.PASS,"Test Method named as : "+ result.getName()+" is passed");

        }else if(result.getStatus()==ITestResult.FAILURE) {
            test.log(Status.PASS,"Test Method named as : "+ result.getName()+" is FAILED");
            test.log(Status.FAIL,"Test failure : "+ result.getThrowable());
        }
        else if(result.getStatus()==ITestResult.SKIP) {
            test.log(Status.PASS,"Test Method named as : "+ result.getName()+" is skipped");
        }
	}
	
	

	
	
	@AfterSuite
	public void Teardown()
	{
		CloseReport();
		CloseBrowser();
	}
}
 