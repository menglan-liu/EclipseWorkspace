package TestCases;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import Utilities.Constants;


public class TC_Login_logout {
		
	public WebDriver driver;	
	String user = "mogumogu";
	String pass = "Mogumogu!123";
	
	DashboardPage logout;
	
  @Test(priority = 0)
  public void GotoLoginPage() {
	  HomePage.LoginBtn(driver).click();	  
  }
  
  @Test(priority = 1)
  public void Loginto() throws IOException {
	  LoginPage.Login_action(driver);  	  	  
  }
  
  @Test(priority = 2)
  public void Search() {
	  logout = new DashboardPage(driver);
	  logout.enterSearchStr("Git Pocket Guide");	  
  }
  
  @Test(priority = 3)
  public void Logout() {
	  logout = new DashboardPage(driver);
	  String actualhead = logout.getHeading();
	  System.out.println("page head is:"+ actualhead);
	  logout.clickLogout(); 
  }
  
  @BeforeTest
  public void lauchtoLoginPage() {
	
  	//System.out.println("Lauching to login page");
  	System.setProperty("webdriver.gecko.driver", Constants.driverPath);
  	driver = new FirefoxDriver();
  	driver.get(Constants.HomepageUrl);
  	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
  }

  @AfterTest
  public void terminateBroswer() {
	 
  	driver.close();
  }
}