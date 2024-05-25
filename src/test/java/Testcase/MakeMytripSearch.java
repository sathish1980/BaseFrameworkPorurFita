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
	@Test(priority=0)
	public void SearchFlightWithValidCountry()
	{
		test = extent.createTest("Test Case 1", "PASSED test case");
		test.log(Status.INFO, "Launch Browser");
		/*
		 * Select From location
		 * Select to Location
		 * Select date
		 * Click OnSearch
		 * Assert the result displayed for search criteria
		 */

		ClickFromLocation();
		SelectDataFromList("PNQ");
		test.log(Status.INFO, "Select the from location as PNQ");
		ClickToLocation();
		SelectDataFromList("MAA");
		test.log(Status.INFO, "Select the to location as MAA");
		SelectDate("12");
		test.log(Status.INFO, "Select the Date as 25");
		clickOnSearchButton();
		test.log(Status.INFO, "SearchButton is cicked");
		Assert.assertEquals(GetNetworkError(), "NETWORK PROBLEM ");
		//test.log(Status.PASS, "SearchFlightWithValidCountry is completed");
		
	}
	
	@Test(priority=1)
	public void SameCityErroValidation()
	{
		test = extent.createTest("Test Case 2", "PASSED test case");
		test.log(Status.INFO, "Launch Browser");
		/*
		 * Select From location
		 * Select to Location
		 * Select date
		 * Click OnSearch
		 * Assert the result displayed for search criteria
		 */
		browser.navigate().back();
		ClickFromLocation();
		SelectDataFromList("PNQ");
		test.log(Status.INFO, "Select the from location as PNQ");
		ClickToLocation();
		SelectDataFromList("PNQ");
		test.log(Status.INFO, "Select the to location as MAA");
		Assert.assertEquals(GetSameCityError(), "From & To airports cannot be the same");
		//test.log(Status.PASS, "SearchFlightWithValidCountry is completed");
		
	}
	
	@AfterMethod
	public void WriteInReport(ITestResult result)
	{
		if (result.getStatus()==ITestResult.SUCCESS) {
            test.log(Status.PASS,"Test Method named as : "+ result.getName()+" is passed");

        }else if(result.getStatus()==ITestResult.FAILURE) {
            test.log(Status.PASS,"Test Method named as : "+ result.getName()+" is FAILED");
            test.log(Status.FAIL,"Test failure : "+ result.getThrowable());
        }
        else if(result.getStatus()==ITestResult.SKIP) {
            test.log(Status.PASS,"Test Method named as : "+ result.getName()+" is skipped");
        }
	}
	
	public void ClickFromLocation()
	{
		webElementCommons.ExplictWaitForClickable(browser, 60, By.xpath("//*[@for='fromCity']"));
		webElementCommons.ClickOnButton(browser.findElement(By.xpath("//*[@for='fromCity']")));	
	}
	
	public void ClickToLocation()
	{
		webElementCommons.ExplictWaitForClickable(browser, 60, By.xpath("//*[@for='toCity']"));
		webElementCommons.ClickOnButton(browser.findElement(By.xpath("//*[@for='toCity']")));	
	}
	
	public void SelectDataFromList(String expectedCountryCode)
	{
		webElementCommons.SelectValueFromList(browser,expectedCountryCode);
	}
	
	public void SelectDate(String expectedDate)
	{
		webElementCommons.SelectDateFromCalender(browser,expectedDate);
	}
	
	public void clickOnSearchButton()
	{
		webElementCommons.ClickOnButton(browser.findElement(By.xpath("//*[@data-cy='submit']//a")));
	}
	
	public String GetNetworkError()
	{
		webElementCommons.ExplictWaitForVisible(browser,60,By.xpath("//*[@class='error-title']"));
		return webElementCommons.GetText(browser.findElement(By.xpath("//*[@class='error-title']")));
		
	}
	
	public String GetSameCityError()
	{
		webElementCommons.ExplictWaitForVisible(browser,60,By.xpath("//*[@data-cy='sameCityError']"));
		return webElementCommons.GetText(browser.findElement(By.xpath("//*[@data-cy='sameCityError']")));
		
	}
	
	@AfterSuite
	public void Teardown()
	{
		CloseReport();
		CloseBrowser();
	}
}
 