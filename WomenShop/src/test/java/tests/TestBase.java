package tests;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import Utilities.Constants;
import config.ConfigEnv;

public class TestBase {

	public static WebDriver driver = null;
	ConfigEnv config = new ConfigEnv();

	@BeforeTest
	@Parameters({ "env" })

	public void lauchBrowser(final String env) throws IOException {
		String url = "";
		
		switch (env) {
		case "QA":
			url = config.configEnv(Constants.projectpath + "\\src\\test\\java\\config\\Env_QA.properties","url");
			break;
		case "DEV":
			url = config.configEnv(Constants.projectpath + "\\src\\test\\java\\config\\Env_DEV.properties","url");			
			break;
		}
		
		System.out.println("The base URL is:" + url);

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Hao\\eclipse-workspace\\WomenShop\\src\\test\\java\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterTest
	public void terminateBrowser() {
		driver.quit();
	}
}
