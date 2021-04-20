package interact;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Demosite {
	
	private String siteUrl;
	public String siteName = "haha";
	
	public Demosite(String url) {
		siteUrl = url;
	}

	public void testDemosite() {

		System.setProperty("webdriver.gecko.driver", "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		// Label: Test
		// ERROR: Caught exception [ERROR: Unsupported command [resizeWindow | 1920,880
		// | ]]
		driver.get(siteUrl);
		//driver.get("http://demo.guru99.com/test/login.html");
		// Label: login
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("abcd@gmail.com");
		driver.findElement(By.id("passwd")).clear();
		driver.findElement(By.id("passwd")).sendKeys("abcdefghlkjl");
		driver.findElement(By.cssSelector("#SubmitLogin > span")).click();
	}
}