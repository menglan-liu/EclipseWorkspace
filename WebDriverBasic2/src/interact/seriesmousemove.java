package interact;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class seriesmousemove {
	public void SMTest() {
		System.setProperty("webdriver.gecko.driver","E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe");
		
		String baseUrl = "http://www.facebook.com/"; 
		WebDriver driver = new FirefoxDriver();
		driver.get(baseUrl);
		
		WebElement txtUsername = driver.findElement(By.id("email"));
		
		Actions builder = new Actions(driver);

		
		int i = 0;
		Action seriesOfActions;
		
		for(i=0;i<10;i++) {
			seriesOfActions = builder
					.moveToElement(txtUsername)
					.click()
					.keyDown(txtUsername, Keys.SHIFT)
					.sendKeys(txtUsername, "hello: " + i)
					.keyUp(txtUsername, Keys.SHIFT)
					.doubleClick(txtUsername)
					.build();
				
				seriesOfActions.perform();	
				}
		driver.close();

	}

}
