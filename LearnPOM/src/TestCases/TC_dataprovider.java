package TestCases;

import org.testng.annotations.Test;

import Utilities.Data_Provider;

public class TC_dataprovider {

  @Test (dataProvider = "data-provider", dataProviderClass = Data_Provider.class)
  public void myTest (String val) {
      System.out.println("Current Status : " + val);
  }
}
