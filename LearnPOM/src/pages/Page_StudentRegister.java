package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Utilities.Constants;
import Utilities.ExcelUtils;

public class Page_StudentRegister {

	WebDriver driver;
	static ExcelUtils excelUtils = new ExcelUtils();

	// using the Constants class values for excel file path
	static String excelFilePath = Constants.Path_TestData + Constants.File_TestData;

	public Page_StudentRegister(WebDriver driver) {
		this.driver = driver;
	}

	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By email = By.id("userEmail");
	By mobile = By.id("userNumber");
	By address = By.id("currentAddress");

	// Locator for gender male radio button
	By maleRadio = By.id("gender-radio-1");
	// Locator for gender male radio button
	By femaleRadio = By.id("gender-radio-2");

	// Locator for sports checkbox
	By sportsCheckbox = By
			.cssSelector("div.custom-control.custom-checkbox.custom-control-inline > label.custom-control-label");
	// locator for select state
	By selectState = By.xpath("//div[@id='state']/div/div[2]/div");
	// locator for select city
	By selectCity = By.xpath("//div[@id='city']/div/div");
	// locator for submit button
	By submitBtn = By.id("submit");

	public void FillinRegisterInfo() throws Exception {
		// calling the ExcelUtils class method to initialise the workbook and sheet
		excelUtils.setExcelFile(excelFilePath, "STUDENT_DATA");
		System.out.println(excelUtils.getRowCountInSheet());
		// iterate over all the row to print the data present in each cell.
		for (int i = 0; i <= excelUtils.getRowCountInSheet(); i++) {
			// Enter the values read from Excel in firstname,lastname,mobile,email,address
			driver.findElement(firstName).sendKeys(excelUtils.getCellData(i, 0));
			driver.findElement(lastName).sendKeys(excelUtils.getCellData(i, 1));
			driver.findElement(email).sendKeys(excelUtils.getCellData(i, 2));
			driver.findElement(mobile).sendKeys(excelUtils.getCellData(i, 3));
			driver.findElement(address).sendKeys(excelUtils.getCellData(i, 4));

			// Click on the gender radio button 
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(maleRadio)).click().perform();

			// Click on submit button		
			actions.moveToElement(driver.findElement(submitBtn)).click().perform();

			// Verify the confirmation message
			WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

			// check if confirmation message is displayed
			if (confirmationMessage.isDisplayed()) {
				// if the message is displayed , write PASS in the excel sheet using the method
				// of ExcelUtils
				excelUtils.setCellValue(i, 6, "PASS", excelFilePath);
			} else {
				// if the message is not displayed , write FAIL in the excel sheet using the
				// method of ExcelUtils
				excelUtils.setCellValue(i, 6, "FAIL", excelFilePath);
			}

			// wait for page to come back to registration page after close button is clicked
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

			WebElement closebtn = driver.findElement(By.id("closeLargeModal"));
			closebtn.click();
		}
	}

}
