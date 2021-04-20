package ecommerceLive;

import java.io.File;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EcommerceTest {
	public String firstName = "BERRY"; // These testdata stuff will be placed
	public String lastName = "BERRYTWO"; // in a TestData EXCEL file at some stage
	public String vEmail = "apple2935@tpg.com.au";
	public String vPW = "mar8mar";
	
	Properties obj;
	String mobilelink, myaccountlink;

	public EcommerceTest() throws IOException {
		obj = new Properties();
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "\\application.properties");
		obj.load(objfile);
		mobilelink = obj.getProperty("MobileLink");
		myaccountlink = obj.getProperty("MyAccountLink");
	}

	public String baseUrl = "http://live.demoguru99.com/";
	String driverPath = "E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe";
	public WebDriver driver;
	private PrintStream verificationErrors;

	@BeforeTest
	public void launchBrowser() {

		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 4)
	public void sortbyname() throws Exception {

		driver.get(baseUrl);
		driver.manage().window().maximize();
		// Step 2. Verify Title of the page
		String demoSite = driver.findElement(By.cssSelector(
				"body > div.wrapper > div > div.main-container.col2-right-layout > div > div.col-main > div > div > h2"))
				.getText();
		System.out.println(demoSite);
		try {
			Assert.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		// Step 3. Click on �MOBILE� menu
		driver.findElement(By.linkText(mobilelink)).click();
		String mobileSite = driver.findElement(By.cssSelector(".page-title h1")).getText();
		System.out.println(mobileSite);
		try {
			Assert.assertEquals("MOBILE", mobileSite);
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		// Step 5. In the list of all mobile , select �SORT BY� dropdown as �name�
		new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");

		// Step 6. Verify all products are sorted by name
		// this will take a screen shot of the manager's page after a successful login

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String png = ("C:\\Users\\Hao\\eclipse-workspace\\ecommerceLive\\src\\ecommerceLive\\Mobile Products are sorted"
				+ ".png");
		FileUtils.copyFile(scrFile, new File(png));
	}

	@Test(priority = 1)
	public void verifyCost() throws Exception {

		// 1. Go to http://live.demoguru99.com
		driver.get(baseUrl);
		driver.manage().window().maximize();

		// 2. Click on Mobile menu
		driver.findElement(By.linkText("MOBILE")).click();

		// 3. In the list of all mobile , read the cost of Sony Xperia mobile (which is
		// $100)
		String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();

		// 4. Click on Sony Xperia mobile
		driver.findElement(By.id("product-collection-image-1")).click();

		// 5. Read the XPeria mobile price from details page
		String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();

		// Product price in list and details page should be equal ($100)
		try {
			Assert.assertEquals(XPeriaPrice, detailPrice);
			System.out.println("Price are the same.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void addtocart() throws Exception {
		// 1. Go to http://live.demoguru99.com
		driver.get(baseUrl);
		driver.manage().window().maximize();

		// 2. Click on Mobile menu
		driver.findElement(By.linkText(mobilelink)).click();

		// 3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
		driver.findElement(
				By.xpath(".//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/button"))
				.click();

		// 4. Change �QTY� value to 1000 and click �UPDATE� button.
		// Expected "The requested quantity for "Sony Xperia" is not available." error
		// message is displayed.

		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("1000");
		driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();

		// 5. Verify the error message
		String reqQty = driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[2]/p")).getText();
		String msgQty = ("* The maximum quantity allowed for purchase is 500.");
		try {
			Assert.assertEquals(reqQty, msgQty);
			System.out.println("Error massage showed.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 6. Click on �EMPTY CART� link in the footer of list of all mobiles. A message
		// "SHOPPING CART IS EMPTY" is shown.

		driver.findElement(By.xpath("//*[@id=\"empty_cart_button\"]/span/span")).click();

		// 7. Verify cart is empty
		String noItemsStg = ("You have no items in your shopping cart.");
		String noItemsMsg = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]"))
				.getText();
		System.out.println("You have no items msg = " + noItemsMsg);

		try {
			Assert.assertEquals(noItemsStg, noItemsMsg);
			System.out.println("Cart is emptied.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 0)
	public void compareProduct() throws Exception {

		// 1. Go to http://live.demoguru99.com
		driver.get(baseUrl);
		driver.manage().window().maximize();

		// 2. Click on Mobile menu
		driver.findElement(By.linkText(mobilelink)).click();
		Thread.sleep(1000);

		// 3. In mobile products list , click on �Add To Compare� for 2 mobiles (Iphone
		// & Sony Xperia)

		// note: store the title of the 2 mobiles for comparison for verification later
		// when popup page comes up
		driver.findElement(By.cssSelector(
				"li.item:nth-child(2) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(2)"))
				.click();
		String mainMobile1 = driver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText(); // text captured -
																								// upperCase "IPHONE"
		System.out.println("mainMobile1 = " + mainMobile1);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(
				"body > div.wrapper > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > div.actions > ul > li:nth-child(2) > a"))
				.click();
		String mainMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText(); // text captured -
																										// upperCase
																										// "SONY XPERIA"
		System.out.println("mainMobile2 = " + mainMobile2);
		Thread.sleep(1000);

		// 4. Click on �COMPARE� button. A popup window opens
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		Thread.sleep(1000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		// 5. Verify the pop-up window and check that the products are reflected in it
		// Heading "COMPARE PRODUCTS" with selected products in it.
		String strHead = ("COMPARE PRODUCTS");
		String compHead = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div[1]/h1")).getText();
		System.out.println("compHead = " + compHead);
		String popupMobile2 = driver.findElement(By.xpath(".//*[@id=\"product_comparison\"]/tbody[1]/tr[1]/td[1]/h2/a"))
				.getText(); // text captured is "IPHONE" in uppercase
		String popupMobile1 = driver.findElement(By.xpath(".//*[@id=\"product_comparison\"]/tbody[1]/tr[1]/td[2]/h2/a"))
				.getText(); // text captured "SONY XPERIA" in uppercase
		System.out.println("popupMobile1 = " + popupMobile1);
		System.out.println("popupMobile2 = " + popupMobile2);
		Thread.sleep(1000);
		// to check the popup heading/title
		try {
			Assert.assertEquals(strHead, compHead);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// to check the 2 mobiles selected are the two in the popup - this is tp check
		// the IPhone
		try {
			Assert.assertEquals(mainMobile1, popupMobile1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// to check the 2 mobiles selected are the two in the popup - this is to check
		// Sony X
		try {
			Assert.assertEquals(mainMobile2, popupMobile2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 6. Close the Popup Windows
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}

	@Test(priority = 3)
	public void createAccandWishlist() throws Exception {
		String firstname = "BERRY";
		String lastname = "BERRYTWO";
		// 1. Go to http://live.demoguru99.com
		driver.get(baseUrl);
		driver.manage().window().maximize();

		// 2. Click on my account link
		driver.findElement(By.linkText(myaccountlink)).click();
		Thread.sleep(2000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		// 3a. Click Create an Account link
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		Thread.sleep(2000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		// 3b. and fill New User information
		driver.findElement(By.id("firstname")).clear();
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).clear();
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys("apple2935@tpg.com.au");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("mar8mar");
		driver.findElement(By.id("confirmation")).clear();
		driver.findElement(By.id("confirmation")).sendKeys("mar8mar");

		// 4. Click Register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Thread.sleep(5000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		// 5. Verify Registration is done. Expected account registration done.
		String vWelcome = ("WELCOME, " + firstname + " " + lastname + "!");
		String tWelcome = driver.findElement(By.cssSelector("p.welcome-msg")).getText();
		System.out.println("tWelcome = " + tWelcome);

		try {
			Assert.assertEquals(tWelcome, vWelcome);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 6. Go to TV menu
		driver.findElement(By.xpath(".//*[@id='nav']/ol/li[2]/a")).click();
		Thread.sleep(2000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		// 7. Add product in your wish list - use product - LG LCD
		driver.findElement(By.xpath("//li/a[@class='link-wishlist']")).click();

		Thread.sleep(2000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		// 8. Click SHARE WISHLIST
		driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

		Thread.sleep(2000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}

		// 9. In next page enter Email and a message and click SHARE WISHLIST
		driver.findElement(By.id("email_address")).clear();
		driver.findElement(By.id("email_address")).sendKeys("support@guru99.com");
		driver.findElement(By.id("message")).clear();
		driver.findElement(By.id("message"))
				.sendKeys("Hey Mary!! this LCD TV looks ok, check it out !!.. cheers .. Berry");

		driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
		// the above click will still be in the same page as prior page, so no need take
		// another handle to another window

		Thread.sleep(2000);

		// 10. Check wishlist is shared. Expected wishlist shared successfully.
		String vWishList = "Your Wishlist has been shared.";
		String wishList = driver
				.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span"))
				.getText();
		System.out.println("wishList = " + wishList);
		try {
			Assert.assertEquals(vWishList, wishList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread.sleep(2000);
	}

	@Test
	public void purchaseProduct() throws Exception {

		// 1. Go to http://live.demoguru99.com
		driver.get(baseUrl);

		// 2. Click on my account link
		driver.findElement(By.linkText(myaccountlink)).click();

		Thread.sleep(2000);

		// switching to new window

		// 3. Login in application using previously created credential
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("apple2935@tpg.com.au");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("mar8mar");
		driver.findElement(By.id("send2")).click(); // this is the Login button

		Thread.sleep(3000);

		// 4. Click on MY WISHLIST link
		driver.findElement(By.linkText("MY WISHLIST")).click();

		Thread.sleep(3000);

		// 5. In next page, Click ADD TO CART link
		driver.findElement(By.xpath("//header[@id='header']/div/div[2]/div/div/a/span[2]")).click();
		driver.findElement(By.cssSelector("a.cart-link")).click();

		// 6. Enter general shipping country, state/province and zip for the shipping
		// cost estimate
		driver.findElement(By.id("region_id")).click();
		new Select(driver.findElement(By.id("region_id"))).selectByVisibleText("New York");
		driver.findElement(By.id("region_id")).click();
		driver.findElement(By.id("postcode")).click();
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("postcode")).sendKeys("542896");

		// 7. Click Estimate
		driver.findElement(By.cssSelector("#shipping-zip-form > div > button > span > span")).click(); // this is the
																										// ESTIMATE link

		// 8. Verify Shipping cost generated
		String sFlatRate = "Flat Rate";
		String flatRate = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();
		try {
			System.out.println("sFlatRate = " + sFlatRate);
			System.out.println("flatRate = " + flatRate);
			Assert.assertEquals(sFlatRate, flatRate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sFlatRatePrice = "Fixed - $5.00";
		String flatRatePrice = driver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label"))
				.getText();
		try {
			System.out.println("sFlatRatePrice = " + sFlatRatePrice);
			System.out.println("flatRatePrice = " + flatRatePrice);
			Assert.assertEquals(sFlatRatePrice, flatRatePrice);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 9. Select Shipping Cost (already selected as default), Update Total
		driver.findElement(By.id("s_method_flatrate_flatrate")).click(); // RADIO button - Fixed - $5.00
		driver.findElement(By.xpath("//button[@title='Update Total']")).click();

		// 10. Verify shipping cost is added to total
		String vFlatRatePrice = "$5.00";
		String shippingCostIncluded = driver
				.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();

		try {
			System.out.println("vFlatRatePrice = " + vFlatRatePrice);
			System.out.println("shippingCostIncluded = " + shippingCostIncluded);
			Assert.assertEquals(vFlatRatePrice, shippingCostIncluded);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 11. Click PROCEED TO CHECKOUT
		driver.findElement(By.xpath("//button[@title='Proceed to Checkout']")).click();

		Thread.sleep(3000);

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(2000);

		// ********************************************************************************************************
		// *
		// * BILLING ADDRESS
		// *
		// ********************************************************************************************************
		// Check if Select element is present. If not present, it is the first time a
		// customer has logged back in, to process what is in his/her wishlist

		try {
			Select bAddr = new Select(driver.findElement(By.name("billing_address_id")));
			int bAddrSize = bAddr.getOptions().size();
			bAddr.selectByIndex(bAddrSize - 1);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("No dropdown element present");
		}

		driver.findElement(By.id("billing:firstname")).clear();
		driver.findElement(By.id("billing:firstname")).clear();
		driver.findElement(By.id("billing:firstname")).sendKeys("BERRY");
		driver.findElement(By.id("billing:lastname")).clear();
		driver.findElement(By.id("billing:lastname")).sendKeys("BERRYTWO");
		driver.findElement(By.id("billing:company")).clear();

		driver.findElement(By.id("billing:street1")).clear();
		driver.findElement(By.id("billing:street1")).sendKeys("148 Crown Street");
		new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']"))).selectByIndex(14);
		Thread.sleep(5000);
		driver.findElement(By.id("billing:city")).clear();
		driver.findElement(By.id("billing:city")).sendKeys("Sydney");
		driver.findElement(By.id("billing:region")).clear();
		driver.findElement(By.id("billing:region")).sendKeys("New South Wales");
		driver.findElement(By.id("billing:postcode")).clear();
		driver.findElement(By.id("billing:postcode")).sendKeys("2000");
		driver.findElement(By.id("billing:telephone")).clear();
		driver.findElement(By.id("billing:telephone")).sendKeys("8850 6789");

		// check radio button to "Ship to different address"
		driver.findElement(By.id("billing:use_for_shipping_no")).click();

		// click the CONTINUE button

		// after the click above, it is still on same web page:
		// live.guru99.com/index.php/checkout/onepage/
		driver.findElement(By.xpath(".//*[@id='billing-buttons-container']/button")).click();

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(2000);

		// ********************************************************************************************************
		// *
		// * SHIPPING ADDRESS
		// *
		// ********************************************************************************************************
		// Check if Select element is present or not. If not present, it is first time
		// user has logged back in to process his/her order

		try {
			Select sAddr = new Select(driver.findElement(By.name("shipping_address_id")));
			int sAddrSize = sAddr.getOptions().size();
			sAddr.selectByIndex(sAddrSize - 1);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("No dropdown element present");
		}

		Thread.sleep(3000);

		driver.findElement(By.id("shipping:firstname")).clear();
		driver.findElement(By.id("shipping:firstname")).clear();
		driver.findElement(By.id("shipping:firstname")).sendKeys("BERRY");
		driver.findElement(By.id("shipping:lastname")).clear();
		driver.findElement(By.id("shipping:lastname")).sendKeys("BERRYTWO");
		driver.findElement(By.id("shipping:company")).clear();

		driver.findElement(By.id("shipping:street1")).clear();
		driver.findElement(By.id("shipping:street1")).sendKeys("50 Berry Street");
		driver.findElement(By.id("shipping:street2")).clear();
		// shipping country must be selected first from dropdown
		new Select(driver.findElement(By.xpath("//select[@id='shipping:country_id']"))).selectByIndex(14);
		// once Australia is selected, then the region and city becomes simply a text
		// input, instead of dropdown
		driver.findElement(By.id("shipping:region")).clear();
		driver.findElement(By.id("shipping:region")).sendKeys("New Sounth Wales");
		driver.findElement(By.id("shipping:city")).clear();
		driver.findElement(By.id("shipping:city")).sendKeys("Sydney");
		driver.findElement(By.id("shipping:postcode")).clear();
		driver.findElement(By.id("shipping:postcode")).sendKeys("2000");
		driver.findElement(By.id("shipping:telephone")).clear();
		driver.findElement(By.id("shipping:telephone")).sendKeys("8034 1234");

		Thread.sleep(3000);

		driver.findElement(By.xpath(".//*[@id='shipping-buttons-container']/button")).click();

		// switching to new window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		Thread.sleep(2000);

		// 13. In Shipping Method, Click Continue
		driver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();

		Thread.sleep(2000);

		// 14. In Payment Information select 'Check/Money Order' radio button. Click
		// Continue
		driver.findElement(By.xpath("//input[@title='Check / Money order']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath(".//*[@id='payment-buttons-container']/button")).click();

		Thread.sleep(3000);

		// 15. Click 'PLACE ORDER' button
		driver.findElement(By.xpath(".//*[@id='review-buttons-container']/button")).click();

		Thread.sleep(3000);

		// 16. Verify Oder is generated. Note the order number
		String orderNum = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/p[1]/a"))
				.getText();
		System.out.println("*** Your order number for your record = " + orderNum);

	}
	
	
	
	@Test
	public void pdf() throws Exception {
		  
		  // Step 1. Go to http://live.demoguru99.com                                         
		    driver.get(baseUrl); 
		    
		    // Step 2. Click on My Account link
		    driver.findElement(By.linkText("MY ACCOUNT")).click();
		    
		    Thread.sleep(2000);  
		    
		    // switching to new window                                                    
		    for (String handle : driver.getWindowHandles()) {
		    	driver.switchTo().window(handle);
		    	}
		    
		    // Step 3. Login in application using previously created credential 
		    driver.findElement(By.id("email")).clear();	   
		    driver.findElement(By.id("email")).sendKeys(vEmail); 
		    driver.findElement(By.id("pass")).clear();	    
		    driver.findElement(By.id("pass")).sendKeys(vPW);
		    driver.findElement(By.id("send2")).click();	 // this is the Login button   
		    
		    Thread.sleep(2000);  
		    
		    // switching to new window                                                                               
			    for (String handle : driver.getWindowHandles()) {
			    	driver.switchTo().window(handle);
			    	}
		  
			    // Step 4. Click on 'My Orders'
				// note: clicking My Orders at this point is not in the sequence,  in order to reach step 6,  
			    //          because immediately after logging in, it goes straight to display of Recent Orders page
				// driver.findElement(By.linkText("My Orders")).click();
					    
				// Step 5. Click on 'View Order' 
				// note: clicking View Order at this point is not in the sequence, in order to reach step 6, 
			    //		 because immediately after logging in, it goes straight to display of Recent Orders page
				// driver.findElement(By.linkText("View Order")).click();

				
			    //********************************************************************************************************	  
			    // Step 6. Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
			    // note: RECENT ORDERS page is displayed immediately after customer/user has logged in
			    
			    try {
			        Assert.assertEquals("RECENT ORDERS", driver.findElement(By.cssSelector("h2")).getText());
			        System.out.println("*** RECENT ORDERS is confirmed as the correct page to be in. ***");
			      } catch (Error e) {
			    	  System.out.println("*** RECENT ORDERS failed to get displayed on the page. ***");
			    	  e.printStackTrace();	
			      }
			    
			    try {
			    	 Assert.assertEquals("Pending", driver.findElement(By.cssSelector("em")).getText());
			    	System.out.println("*** Status of Pending is correctly displayed for this recent order. ***");
			      } catch (Error e) {
			    	  System.out.println("*** Status of Pending failed to be confirmed for this recent order. ***");
			    	  e.printStackTrace();	
			      }
			    
		  
			    // Step 7. Click on 'Print Order' link
			    driver.findElement(By.linkText("VIEW ORDER")).click();
			    
			    Thread.sleep(2000);  
			    
			    // switching to new window                                                                               
			    for (String handle : driver.getWindowHandles()) {
			    	driver.switchTo().window(handle);
			    	}
			    			    
			    driver.findElement(By.linkText("Print Order")).click();
			    
			    Robot r = new Robot();
			    r.delay(1000);
			    
			    Thread.sleep(2000); 
			    
			    r.keyPress(KeyEvent.VK_ENTER);
		        r.keyRelease(KeyEvent.VK_ENTER);
		  
			    // Step 8. Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.  
			    // note:  There is no "Change...." link 
		
	   
			    // Step 9. Click on 'Save' button and save the file in some location.
			    // note: unable to find this Save button
		  
			    // Step 10.Verify Order is saved as PDF
			    // unable to perform any verification because there is no option to save as PDF
		  
	  }

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}
}
