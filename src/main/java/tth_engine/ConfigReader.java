package tth_engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{	

	public Properties setUpConfig() throws FileNotFoundException, IOException {

	String rootPath = System.getProperty("user.dir");
	String appConfigPath = rootPath + "/src/main/java/resources/application.properties";
	Properties appProps = new Properties();
	appProps.load(new FileInputStream(appConfigPath));

		return appProps;
	}
	
	public String getProperty(String key) {
		Properties appProps;
		try {
			appProps = setUpConfig();
			return appProps.getProperty(key);
		} catch (IOException e) {
			System.out.println("IOException caught in ConfigReader " + e);
		}
		
		return null;
	}
}
