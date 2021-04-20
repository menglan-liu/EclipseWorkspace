package TestCases;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import Utilities.Constants;
import Utilities.Data_Provider;


public class TC_negativeLogin {
	WebDriver driver;
	LoginPage login;
	
@Test(dataProvider="loginFail_Dp",dataProviderClass = Data_Provider.class)
public void FailtoLogin(String un,String pw) {
	LoginPage.Username(driver).sendKeys(un);
	LoginPage.Password(driver).sendKeys(pw);
	
	LoginPage.Login(driver).click();
	
	
	String expected = "Invalid username or password!";
	String actual= LoginPage.getFailMsg(driver);
	Assert.assertEquals(expected,actual,"Login Fail msg did not show.");
	System.out.println("Login failed!");
	
}

@BeforeTest
public void lauchtoLoginPage() {
	System.out.println("Lauching to login page");
	System.setProperty("webdriver.gecko.driver", Constants.driverPath);
	driver = new FirefoxDriver();
	driver.get(LoginPage.LoginPageUrl);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
}

@AfterTest
public void terminateBroswer() {
	driver.close();
	
}
}
