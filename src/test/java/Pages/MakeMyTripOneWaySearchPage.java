package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ElementUtils.webElementCommons;

public class MakeMyTripOneWaySearchPage extends webElementCommons{
	
	@FindBy (xpath ="//*[@for='toCity']")
	WebElement toCity;
	@FindBy (xpath ="//*[@for='fromCity']")
	WebElement fromCity;
	@FindBy (xpath ="//*[@data-cy='submit']//a")
	WebElement submitbutton;
	
	WebDriver browser;
	
	
	public MakeMyTripOneWaySearchPage(WebDriver browser)
	{
		this.browser=browser;
		PageFactory.initElements(browser, this);
	}
	
	public void ClickFromLocation()
	{
		ExplictWaitForClickable(browser, 60, By.xpath("//*[@for='fromCity']"));
		//ClickOnButton(browser.findElement(By.xpath("//*[@for='fromCity']")));	
		ClickOnButton(fromCity);	
	}
	
	public void ClickToLocation()
	{
		webElementCommons.ExplictWaitForClickable(browser, 60, By.xpath("//*[@for='toCity']"));
		//webElementCommons.ClickOnButton(browser.findElement(By.xpath("//*[@for='toCity']")));	
		ClickOnButton(toCity);	
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
		//webElementCommons.ClickOnButton(browser.findElement(By.xpath("//*[@data-cy='submit']//a")));
		ClickOnButton(submitbutton);
	}
	
	public void SelectTraveller(String totalAdult)
	{
		ClickOnTravellerandClass();
		SelectAdult(totalAdult);
		ClickApplyButtonInTraveller();
	}
	
	public void ClickOnTravellerandClass()
	{
		webElementCommons.ExplictWaitForClickable(browser, 60, By.xpath("//*[@for='travellers']"));
		webElementCommons.ClickOnButton(browser.findElement(By.xpath("//*[@for='travellers']")));
	}
	
	public void SelectAdult(String totalAdult)
	{
		webElementCommons.ClickOnButton(browser.findElement(By.xpath("(//*[contains(@class,'guestCounter')])[1]//li[last()]")));

		List<WebElement> allAdult = browser.findElements(By.xpath("(//*[contains(@class,'guestCounter')])[1]//li"));
		for(WebElement eachAdult :allAdult )
		{
			if(webElementCommons.GetText(eachAdult).equalsIgnoreCase(totalAdult))
			{
				eachAdult.click();
				break;
			}
		}
	}
	
	public void ClickApplyButtonInTraveller()
	{
		webElementCommons.ClickOnButton(browser.findElement(By.xpath("//button[@data-cy='travellerApplyBtn']")));
	}
	
	public String GetSameCityError()
	{
		webElementCommons.ExplictWaitForVisible(browser,60,By.xpath("//*[@data-cy='sameCityError']"));
		return webElementCommons.GetText(browser.findElement(By.xpath("//*[@data-cy='sameCityError']")));
		
	}
	
	public String ClickOnRoundTrip()
	{
		ClickOnRadioButton(browser.findElement(By.xpath("//*[@data-cy='roundTrip']//span")));
		return GetAtrributes(browser.findElement(By.xpath("//*[@data-cy='roundTrip']//span")),"class");
	}
	

}
