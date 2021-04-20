package TestCases;

import org.testng.annotations.Test;

import Utilities.Constants;
import Utilities.SearchProvider;
import pages.Pg_Google;

import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TC_dp_searchGoogle {
	public WebDriver driver;
	
  @Test(dataProvider="SearchProvider",dataProviderClass = SearchProvider.class)

  public void googleSearch(String author,String searchKey) throws InterruptedException {
	  WebElement searchbox = driver.findElement(Pg_Google.inputbox);
	  searchbox.click();
	  searchbox.clear();
	  searchbox.sendKeys(searchKey);
	  System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
	  Thread.sleep(3000);
	  System.out.println("Value in Google Search Box = "+searchbox.getAttribute("value") +" ::: Value given by input = "+searchKey);
	  Assert.assertEquals(searchbox.getAttribute("value"),searchKey,"Value is different.");
  }
  @BeforeMethod
  public void lauchtoGooglePage() {
	  System.out.println("launching Google page");
		System.setProperty("webdriver.gecko.driver", Constants.driverPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(Pg_Google.googleUrl);		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	  
  }

  @AfterMethod
  public void terminateBrowser() {
		driver.close();
	}

}
