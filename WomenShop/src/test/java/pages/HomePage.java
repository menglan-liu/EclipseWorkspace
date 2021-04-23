package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;


public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		
	}
	
    @FindBy(name = "search_query")
    WebElement SearchBox;
    
	@FindBy(linkText = "Women")
	WebElement NavigateWomen;
	
	@FindBy(linkText = "Dresses")
	WebElement NavigateDresses;
	
	@FindBy(linkText = "Popular")
	WebElement NavigatePopular;
	
	@FindBy(css = "#homefeatured > li.ajax_block_product.col-xs-12.col-sm-4.col-md-3.first-in-line.first-item-of-tablet-line.first-item-of-mobile-line > div > div.left-block > div > a.product_img_link > img")
	WebElement FadedTshirt;
	
	public void rightClickWomen() {
		Actions b = new Actions(driver);
		b.moveToElement(NavigateWomen).keyDown(Keys.CONTROL).click();
	}
	
	public void clickWomen() {
		NavigateWomen.click();
	}
	
	public void clickFadedTshirt() {
		FadedTshirt.click();
	}
	
	
	
}
