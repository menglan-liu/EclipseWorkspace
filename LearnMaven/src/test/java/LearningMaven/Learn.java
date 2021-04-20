package LearningMaven;

import org.testng.annotations.Test;
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;		
import org.testng.Assert;		
import org.testng.annotations.BeforeTest;	
import org.testng.annotations.AfterTest;

public class Learn {		
    private WebDriver driver;	
    public String baseUrl = "http://demo.guru99.com/test/guru99home/";
    String driverPath = "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe";     
    String actual = null;
    String expected = null;
    
	@Test				
	public void testEasy() {	
		driver.get(baseUrl);  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Demo Guru99 Page")); 		
	}	
	@BeforeTest
	 public void launchBrowser() throws InterruptedException {
        System.out.println("launching firefox browser"); 
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
    }
			
	@AfterTest
	public void terminateBrowser(){
        driver.close();
    }
}	
