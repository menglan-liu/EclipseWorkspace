package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {	
	 private static WebElement element = null;
		 
	 
		 
	 //Method to locate login button
	 public static WebElement LoginBtn(WebDriver driver) {
		 element = driver.findElement(By.id("login"));
		 return element;
	 }

}
