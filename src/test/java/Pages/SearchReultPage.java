package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ElementUtils.webElementCommons;

public class SearchReultPage {

	WebDriver browser;
	
	public SearchReultPage(WebDriver browser)
	{
		this.browser=browser;
	}
	
	public String GetNetworkError()
	{
		webElementCommons.ExplictWaitForVisible(browser,60,By.xpath("//*[@class='error-title']"));
		return webElementCommons.GetText(browser.findElement(By.xpath("//*[@class='error-title']")));
		
	}
	
}
