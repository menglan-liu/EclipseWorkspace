package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.HomePage;

public class RightClickOpen extends TestBase{
  @Test
  public void rightClickOpen() throws InterruptedException {
	  
	  HomePage hm = PageFactory.initElements(driver, HomePage.class);
	  
	  hm.clickWomen();
	  Thread.sleep(5000);
  }
}
