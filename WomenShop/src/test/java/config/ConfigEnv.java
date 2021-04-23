package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class ConfigEnv {
	static Properties prop = new Properties();
	InputStream input;

public String configEnv(String propertyfile,String name) throws IOException {
	input = new FileInputStream(propertyfile);
	prop.load(input);
	String config = prop.getProperty(name);
	return config;
}


}
