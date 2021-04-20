package TestCases;

import org.testng.annotations.Test;

import Utilities.Constants;
import pages.Pg_Google;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TC_SearchGoogle {
	public WebDriver driver;

	@Test
	@Parameters({ "author", "searchKey" })
	public void googleSearch(String author, String searchKey) throws InterruptedException {
		WebElement searchbox = driver.findElement(Pg_Google.inputbox);
		searchbox.click();
		searchbox.clear();
		searchbox.sendKeys(searchKey);
		System.out.println("Welcome ->" + author + " Your search key is->" + searchKey);
		Thread.sleep(3000);
		System.out.println("Value in Google Search Box = " + searchbox.getAttribute("value")
				+ " ::: Value given by input = " + searchKey);
		Assert.assertEquals(searchbox.getAttribute("value"), searchKey, "Value is different.");
	}
	
	
	@Parameters("browser")
	@BeforeTest

	public void lauchtoGooglePage(String browser) {
		System.out.println("launching Google page");
		
		
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", Constants.driverPath);

			// Initializing the firefox driver (Gecko)
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.chromedriverPath);

			// Initialize the chrome driver

			driver = new ChromeDriver();

		}
		driver.manage().window().maximize();
		driver.get(Pg_Google.googleUrl);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

}