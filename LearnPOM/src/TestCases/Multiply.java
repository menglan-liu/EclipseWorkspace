package TestCases;


import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


 
public class Multiply
{
 
    @Test
    @Parameters ({"val1", "val2"})
    public void mul(@Optional("8") int v1, @Optional("8")int v2) {
     int prod = v1*v2;
        System.out.println("The Product Of Value 1 and 2 is " + prod);
    }
 
}