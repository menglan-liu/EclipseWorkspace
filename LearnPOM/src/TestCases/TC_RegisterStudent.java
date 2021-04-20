package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilities.Constants;
import Utilities.ExcelUtils;
import pages.Page_StudentRegister;

public class TC_RegisterStudent {
	public WebDriver driver;
	// creating object of ExcelUtils class
	static ExcelUtils excelUtils = new ExcelUtils();

	// using the Constants class values for excel file path
	static String excelFilePath = Constants.Path_TestData + Constants.File_TestData;

	Page_StudentRegister studentregister;

	@BeforeTest
	public void LauchtoStudentRegisterPage() {
		System.out.println("launching student register page");
		System.setProperty("webdriver.gecko.driver", Constants.driverPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(Constants.StudentRegisterUrl);		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.close();
	}

	@Test
	public void RegisterNew() throws Exception {
		studentregister = new Page_StudentRegister(driver);
		studentregister.FillinRegisterInfo();

		
	}

}
