package interact;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDateandTime {
	public void testDemosite() {

		System.setProperty("webdriver.gecko.driver", "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://demo.guru99.com/test/");
		
		WebElement dateBox = driver.findElement(By.xpath("//form//input[@name='bdaytime']"));

        //Fill date as mm/dd/yyyy as 09/25/2013

        dateBox.sendKeys("09252013");

        //Press tab to shift focus to time field

        dateBox.sendKeys(Keys.TAB);

        //Fill time as 02:45 PM

        dateBox.sendKeys("0245PM");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
	    
	}

}
