package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
public class DashboardPage {
 
 WebDriver driver;
 
 //Constructor that will be automatically called as soon as the object of the class is created
 public DashboardPage(WebDriver driver) {
 this.driver=driver;
 }
 
 //Locators for the page title and the logout button
 By heading = By.cssSelector("#app > div > div > div.pattern-backgound.playgound-header > div");
 By logoutBtn = By.id("submit");
 
 By searchField = By.id("searchBox");
 By searchBtn = By.xpath("//*[@id=\"basic-addon2\"]");
 
 //Method to capture the page heading
 public String getHeading() {
 String head = driver.findElement(heading).getText();
 return head;
 }
 
 
 //Method to enter search string and display the same on console
 public void enterSearchStr(String str) {
 driver.findElement(searchField).sendKeys(str);
 System.out.println("The search string is : " +str);
 driver.findElement(searchBtn).click();
 }
 
 
 //Method to click on Logout button
 public void clickLogout() {
 driver.findElement(logoutBtn).click();
 }
}