package TestCases;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pages.DashboardPage;
import pages.HomePage;
import pages.LoginPage;
import Utilities.Constants;

public class TC_cross_Login_logout {
		
	public WebDriver driver;	
	String user = "mogumogu";
	String pass = "Mogumogu!123";
	
	HomePage hplogin;
	LoginPage login;
	DashboardPage logout;
	
  @Test(priority = 0)
  public void GotoLoginPage() {
	  HomePage.LoginBtn(driver).click();	  
  }
  
  @Test(priority = 1)
  public void Loginto() {
	  LoginPage.Username(driver).sendKeys(user);
	  LoginPage.Password(driver).sendKeys(pass);
	  LoginPage.Login(driver).click();
	  	  
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
	  driver.get(Constants.HomepageUrl);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }
  @AfterTest
	public void terminateBrowser() {
		driver.quit();
	}
}
