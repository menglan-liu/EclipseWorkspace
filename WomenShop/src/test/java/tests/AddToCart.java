package tests;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;
import pages.FadedShortSleeveTshirts;
import pages.HomePage;

public class AddToCart extends TestBase{
	@Test		
	public void addTocart() throws InterruptedException {
		HomePage hp = PageFactory.initElements(driver,HomePage.class);
		hp.clickFadedTshirt();
		FadedShortSleeveTshirts ft = PageFactory.initElements(driver,FadedShortSleeveTshirts.class);
		
		ft.setQuantity("2");
		ft.setSize("M");
		ft.setColorBlue();
		ft.addToCart();
		ft.proceedToCheckout();
		Thread.sleep(2000);
	}
}
	

