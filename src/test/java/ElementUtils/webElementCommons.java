package ElementUtils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webElementCommons {
	
	public void EnterText(WebElement textbox,String textToBeEnter)
	{
		if(textbox.isDisplayed())
		{
			textbox.clear();
			textbox.sendKeys(textToBeEnter);
		}
	}
	
	public static void ClickOnButton(WebElement button)
	{
		if(button.isDisplayed())
		{
			button.click();
		}
	}

	public static String GetText(WebElement textbox)
	{
		if(textbox.isDisplayed())
		{
			return textbox.getText();
		}
		return null;
	}
	
	public static String GetAtrributes(WebElement textbox,String attributeName)
	{
		if(textbox.isDisplayed())
		{
			return textbox.getAttribute(attributeName);
		}
		return null;
	}
	
	public void ImplictWait(WebDriver driver,int timeOut)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}
	
	public static void ExplictWaitForClickable(WebDriver driver,int timeOut,By element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void ExplictWaitForPresenceOFElement(WebDriver driver,int timeOut,By element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	public static void ExplictWaitForVisible(WebDriver driver,int timeOut,By element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}
	
	public static void SelectValueFromList(WebDriver browser, String expectedText)
	{
		List<WebElement> AllList = browser.findElements(By.xpath("(//ul[@role='listbox'])[last()]//li"));
		for(int i=1;i<AllList.size();i++)
		{
			WebElement eachCountry =browser.findElement(By.xpath("((//ul[@role='listbox'])[last()]//li["+i+"]//div[contains(@class,'spaceBetween')]//div)[last()]"));
			String actualCountrycode = webElementCommons.GetText(eachCountry);
			if(actualCountrycode.equalsIgnoreCase(expectedText))
			{
				eachCountry.click();
				break;
			}
		}
	}
	
	public static void CloseLoginPopup(WebDriver browser)
	{
		try
		{
		ExplictWaitForClickable(browser,60,By.xpath("//*[@data-cy='closeModal']"));
		ClickOnButton(browser.findElement(By.xpath("//*[@data-cy='closeModal']")));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public static void SelectDateFromCalender(WebDriver browser,String expectedDate)
	{
		List<WebElement> allWeeks = browser.findElements(By.xpath("(//*[@class='DayPicker-Month'])[last()]//div[@class='DayPicker-Body']//div[@class='DayPicker-Week']"));
		for(WebElement eachWeek : allWeeks)
		{
			List<WebElement> allDaysInaWeek=	eachWeek.findElements(By.cssSelector("div[class^='DayPicker-Day']"));
			for (WebElement eachday : allDaysInaWeek)
			{
				
				String className = webElementCommons.GetAtrributes(eachday,"class");
				if(!(className.contains("outside")||className.contains("disabled")||className.contains("selected")))
				{
					String actualDate =webElementCommons.GetText(eachday.findElement(By.tagName("p")));
					if(actualDate.equalsIgnoreCase(expectedDate))
					{
						webElementCommons.ClickOnButton(eachday);
						return ;
					}
				}
			}
		}
	}
}
