package Utilities;

import org.testng.annotations.DataProvider;

public class SearchProvider {
	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { { "Guru99", "India" }, { "Krishna", "UK" }, { "Bhupesh", "USA" } };
	}
	
	
}
