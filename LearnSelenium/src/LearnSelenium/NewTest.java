package LearnSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	
	
	private String baseUrl = "https://www.amazon.ca/s?k=toilet+paper&crid=2WO1QIHR7JFG9&sprefix=t%2Caps%2C194&ref=nb_sb_ss_ts-doa-p_1_1";
	private String driverPath = "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe";
	private WebDriver driver;
	
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.out.println("launching firefox browser");
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.get(baseUrl);
		Thread.sleep(3000);
		
		String parentWindow = driver.getWindowHandle();
	    System.out.println("The parent window id is:" + parentWindow);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}
	
  @Test
  public void f() {
	  
	  driver.findElement(By.cssSelector("#search > div.s-desktop-width-max.s-opposite-dir > div > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div:nth-child(3) > div > span > div > div > div > div > span > a > div > img")).click();
	    // Label: choose amount
	    driver.findElement(By.id("quantity")).click();
	    new Select(driver.findElement(By.id("quantity"))).selectByVisibleText("2");
	    driver.findElement(By.id("quantity")).click();
  }
}
