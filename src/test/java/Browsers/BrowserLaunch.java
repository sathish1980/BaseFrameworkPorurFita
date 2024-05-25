package Browsers;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.propertyFile;

public class BrowserLaunch {
	
	public static WebDriver browser;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	    //helps to generate the logs in test report.
	public static ExtentTest test;
	
	String reportpath = System.getProperty("user.dir")+"\\Reports\\testReport.html";
	
	public void LaunchBrowser() throws IOException
	{
		String browserName = propertyFile.ReadDataFromProperty().getProperty("browser");
		if(browserName.equalsIgnoreCase("firefox"))
		{
		browser = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome"))
		{
		browser = new ChromeDriver();
		}
		else
		{
			browser = new EdgeDriver();
		}
		browser.manage().window().maximize();
		LauchReports();
	}
	
	public void LauchReports()
	{
		htmlReporter = new ExtentHtmlReporter(reportpath);
	     
	     //initialize ExtentReports and attach the HtmlReporter
	     extent = new ExtentReports();
	     extent.attachReporter(htmlReporter);
	     htmlReporter.config().setChartVisibilityOnOpen(true);
	     htmlReporter.config().setDocumentTitle("Extent Report Demo");
	     htmlReporter.config().setReportName("Test Report");
	     htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	     htmlReporter.config().setTheme(Theme.DARK);
	     htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		
	}
	
	public void CloseReport()
	{
		extent.flush();
	}
	
	public void CloseBrowser()
	{
		browser.quit();
	}

}
