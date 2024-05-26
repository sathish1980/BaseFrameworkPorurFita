package Testcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;

import Browsers.BrowserLaunch;
import ElementUtils.webElementCommons;
import Pages.MakeMyTripOneWaySearchPage;
import Pages.SearchReultPage;
import Utils.propertyFile;

public class MakeMytriproundTrip extends BrowserLaunch{

	
	@BeforeClass
	public void ClosepopupAndSelectRoundTrip()
	{
		webElementCommons.CloseLoginPopup(browser);
		MakeMyTripOneWaySearchPage M = new MakeMyTripOneWaySearchPage(browser);
		M.ClickOnRoundTrip();
	}
	
	@Test(priority=0)
	public void SearchFlightWithValidCountry()
	{
		test = extent.createTest("Test Case 3", "PASSED test case");
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
		M.SelectDataFromList("PNQ");
		test.log(Status.INFO, "Select the from location as PNQ");
		M.ClickToLocation();
		M.SelectDataFromList("MAA");
		test.log(Status.INFO, "Select the to location as MAA");
		M.SelectDate("12");
		test.log(Status.INFO, "Select the Date as 12");
		M.SelectDate("15");
		test.log(Status.INFO, "Select the Date as 15");
		M.clickOnSearchButton();
		test.log(Status.INFO, "SearchButton is cicked");
		SearchReultPage sp = new SearchReultPage(browser);
		Assert.assertEquals(sp.GetNetworkError(), "NETWORK PROBLEM");
		//test.log(Status.PASS, "SearchFlightWithValidCountry is completed");
		
	}
	
	@Test(priority=1)
	public void SameCityErroValidation()
	{
		test = extent.createTest("Test Case 3", "PASSED test case");
		test.log(Status.INFO, "Launch Browser");
		/*
		 * Select From location
		 * Select to Location
		 * Select date
		 * Click OnSearch
		 * Assert the result displayed for search criteria
		 */
		MakeMyTripOneWaySearchPage M = new MakeMyTripOneWaySearchPage(browser);
		browser.navigate().back();
		M.ClickFromLocation();
		M.SelectDataFromList("PNQ");
		test.log(Status.INFO, "Select the from location as PNQ");
		M.ClickToLocation();
		M.SelectDataFromList("PNQ");
		test.log(Status.INFO, "Select the to location as MAA");
		Assert.assertEquals(M.GetSameCityError(), "From & To airports cannot be the same");
		//test.log(Status.PASS, "SearchFlightWithValidCountry is completed");
		
	}
	
	
	@AfterMethod
	public void WriteInReport(ITestResult result) throws IOException
	{
		if (result.getStatus()==ITestResult.SUCCESS) {
			String filePath = webElementCommons.Screenshot(browser,result.getName());
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
	
}
 