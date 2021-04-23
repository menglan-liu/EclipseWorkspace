package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.Select;

public class FadedShortSleeveTshirts {
	
	WebDriver driver;
	
	public FadedShortSleeveTshirts(WebDriver driver) {
		this.driver = driver;
		
	}
	
	@FindBy(name = "qty")
	WebElement QuantityBox;
	
	@FindBy(name = "group_1")
	WebElement SizeBox;
	
	@FindBy(name = "Blue")
	WebElement ColorBlue;
	
	@FindBy(name = "Orange")
	WebElement ColorOrange;
	
	@FindBy(css = "#add_to_cart > button")
	WebElement AddtoCartBtn;
    
	@FindBy(css = "#header > div:nth-child(3) > div > div > div:nth-child(3) > div > a")
	WebElement CartBtn;
	
	@FindBy(linkText = "Proceed to checkout")
	WebElement ProceedtoCheckoutBtn;
	
	public void setQuantity(String quantity) {
		QuantityBox.clear();
		QuantityBox.click();
		QuantityBox.sendKeys(quantity);
	}
	
	public void setSize(String size) {
		Select drpsize = new Select(SizeBox);
		SizeBox.click();
		drpsize.selectByVisibleText(size);
	}
	
	public void setColorBlue() {
		ColorBlue.click();
	}
	
	public void setColorOrange() {
		ColorOrange.click();
	}
	
	public void addToCart() {
		AddtoCartBtn.click();
	}
	
	public void proceedToCheckout() {
		ProceedtoCheckoutBtn.click();
	}
}
