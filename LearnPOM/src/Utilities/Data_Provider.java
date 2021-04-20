package Utilities;

import org.testng.annotations.DataProvider;

public class Data_Provider {

	@DataProvider(name = "data-provider")
	public Object[][] dpMethod() {
		return new Object[][] { { "First-Value" }, { "Second-Value" } };
	}
	
	@DataProvider(name = "loginFail_Dp")
	public Object[][] dpLoginFail(){
		return new Object[][] {{"un1wroing"," "},{"un222 ","pw222"},{"un33333 ","pw222"},{"un44444 ","pw222"}};
	}
}