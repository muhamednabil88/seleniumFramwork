package tests;



import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.stream.JsonReader;

import data.JsonDataReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTFromJsonFile extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() throws IOException, ParseException, InterruptedException 
	{
		JsonDataReader jsonReader = new JsonDataReader();
		jsonReader.JsonReader();
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration(jsonReader.firstname, jsonReader.lastname, jsonReader.email, jsonReader.password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		Thread.sleep(2000);
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(jsonReader.email, jsonReader.password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		Thread.sleep(2000);
		registerObject.userLogout();

	}
	
	
}
