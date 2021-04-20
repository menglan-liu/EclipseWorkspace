package interact;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class mousekeyboad {
	
	public void MKTest() {
        String baseUrl = "http://demo.guru99.com/test/newtours/";
        System.setProperty("webdriver.gecko.driver","E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe");
                WebDriver driver = new FirefoxDriver();

                driver.get(baseUrl);           
                WebElement link_Home = driver.findElement(By.linkText("Flights"));
               
                 
                Actions builder = new Actions(driver);
                Action mouseOverHome = builder
                        .moveToElement(link_Home)
                        .build();
                 
               mouseOverHome.perform();
               link_Home.click();
               driver.close();
	}

}
