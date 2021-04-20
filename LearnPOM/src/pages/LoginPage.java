package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.Constants;
import Utilities.ExcelUtils;

public class LoginPage {

	private static WebElement element = null;
	public static String LoginPageUrl = "https://www.demoqa.com/login";
	static ExcelUtils excelUtils = new ExcelUtils();
	static String excelFilePath = Constants.Path_TestData + Constants.File_LoginPositive;

	// Method to locate username
	public static WebElement Username(WebDriver driver) {
		element = driver.findElement(By.id("userName"));
		return element;
	}

	// Method to enter password
	public static WebElement Password(WebDriver driver) {
		element = driver.findElement(By.id("password"));
		return element;
	}

	// Method to locatate Login button
	public static WebElement Login(WebDriver driver) {
		element = driver.findElement(By.id("login"));
		return element;
	}

	// Method to locate login fail msg
	public static WebElement FailMsg(WebDriver driver) {
		element = driver.findElement(By.id("name"));
		return element;
	}

	// Method to get login fail msg
	public static String getFailMsg(WebDriver driver) {
		String actualMsg = driver.findElement(By.id("name")).getText();
		return actualMsg;
	}

	public static void Login_action(WebDriver driver) throws IOException {
		excelUtils.setExcelFile(excelFilePath, "PLOGIN_DATA");
		WebElement un = Username(driver);
		WebElement pw = Password(driver);
		WebElement lg = Login(driver);

		for (int i = 0; i <= excelUtils.getRowCountInSheet(); i++) {
			// Enter the values read from Excel in firstname,lastname,mobile,email,address
			un.click();
			un.clear();
			un.sendKeys(excelUtils.getCellData(i, 0));
			pw.click();
			pw.clear();
			pw.sendKeys(excelUtils.getCellData(i, 1));

			lg.click();
			WebElement confirmationMessage = driver.findElement(By.id("userName-label"));
			if (confirmationMessage.isDisplayed()) {
				// if the message is displayed , write PASS in the excel sheet using the method
				// of ExcelUtils
				excelUtils.setCellValue(i, 4, "PASS", excelFilePath);
			} else {
				// if the message is not displayed , write FAIL in the excel sheet using the
				// method of ExcelUtils
				excelUtils.setCellValue(i, 4, "FAIL", excelFilePath);
			}
		}
	}
}
