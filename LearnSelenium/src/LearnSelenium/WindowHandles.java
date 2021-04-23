package LearnSelenium;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WindowHandles {
	
	
	private String baseUrl = "https://www.naukri.com/";
	private String driverPath = "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe";
	private WebDriver driver;
	
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.out.println("launching firefox browser");
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		Thread.sleep(3000);
		
		String titleofparent = driver.getTitle();
		
		System.out.println("----The parent window title is:" + driver.getTitle());
		String parentWindow = driver.getWindowHandle();
	    System.out.println("The parent window id is:" + parentWindow);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
	
  @Test
  public void f() {
	  
	  Set<String> allWindows = driver.getWindowHandles();
	  ArrayList<String> tabs = new ArrayList<>(allWindows);
	  
	  System.out.println("----There are "+tabs.size()+" windows.");
	  
	  driver.switchTo().window(tabs.get(1));
	  String titleoftab1 = driver.getTitle();
	  System.out.println("----The tab1 window title is: "+titleoftab1);
	  System.out.println("The tab1 window id is:"+ tabs.get(1));
	  driver.close();
	  
	  driver.switchTo().window(tabs.get(2));
	  String titleoftab2 = driver.getTitle();
	  System.out.println("----The tab1 window title is: "+titleoftab2);
	  System.out.println("The tab1 window id is:"+ tabs.get(2));
	  driver.close();
	  
	  driver.switchTo().window(tabs.get(3));
	  String titleoftab3 = driver.getTitle();
	  System.out.println("----The tab1 window title is: "+titleoftab3);
	  System.out.println("The tab1 window id is:"+ tabs.get(3));
	  driver.close();
	  
	  driver.switchTo().window(tabs.get(0));
	  String titleoftab0 = driver.getTitle();
	  Assert.assertEquals(titleoftab0,"Jobs - Recruitment - Job Search - Employment -Job Vacancies - Naukri.com");
	  System.out.println("----The tab0 window title is: "+titleoftab0);
	  System.out.println("The tab0 window id is:"+ tabs.get(0));
	  driver.close();
	  
	    
  }
}
