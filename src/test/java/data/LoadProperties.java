package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
	public static Properties userRegistrationData = loadProperties(System.getProperty("user.dir")+"\\src\\main\\java\\properties\\userregistrationdata.properties");
	
	private static Properties loadProperties(String path) {
		Properties pro = new Properties();
		try {
			FileInputStream stream = new FileInputStream(path);
			pro.load(stream);
		}
		catch (FileNotFoundException e) {
			System.out.println("Error :"+e.getMessage());	
			}
		catch (IOException e) {
		System.out.println("Error :"+e.getMessage());	
		}
		catch (NullPointerException e) {
			System.out.println("Error :"+e.getMessage());	
			}
		return pro;

	}

}
