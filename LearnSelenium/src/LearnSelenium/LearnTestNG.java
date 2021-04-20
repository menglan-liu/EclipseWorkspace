package LearnSelenium;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LearnTestNG {
	private String baseUrl = "https://demoqa.com/";
	private String driverPath = "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe";
	private WebDriver driver;
	String actual = null;
	String expected = null;

	@Test(priority = 0)
	public void start_Homepage() {
		System.out.println("This is the starting point of the test");
		// Initialize Firefox Driver
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void check_Title() {
		String expected = "ToolsQA";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 2)
	public void click_Elements() {
		driver.findElement(By.cssSelector("#app > div > div > div.home-body > div > div:nth-child(1) > div > div.avatar.mx-auto.white > svg > path")).click();
		System.out.println("Click on Elements");
		driver.findElement(By.cssSelector("#app > header > a > img")).click();
	}

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.out.println("launching firefox browser");
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		Thread.sleep(3000);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
}
