package interact;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class upload {
	public void UpTest() {
		System.setProperty("webdriver.gecko.driver","E:\\Download\\geckodriver-v0.29.0-win32\\geckodriver.exe");
        String baseUrl = "http://demo.guru99.com/test/upload/";
        WebDriver driver = new FirefoxDriver();

        driver.get(baseUrl);
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));

       
        uploadElement.sendKeys("E:\\java 11.0.2\\release");

       
        driver.findElement(By.id("terms")).click();

       
        driver.findElement(By.name("send")).click();
        }
}
