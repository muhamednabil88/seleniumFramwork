package tests;



import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTFromExcelSheet extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	@DataProvider(name="userRegistrationData") 
	public Object[][] userRegistrationData() throws IOException{
				ExcelReader ER = new ExcelReader();
				return ER.getUserRegisterationDate();
		
	}
	@Test(priority=1,alwaysRun=true , dataProvider="userRegistrationData")
	public void UserCanRegisterSuccssfully(String fname, String lname, String email, String password) throws InterruptedException 
	{
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration(fname, lname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		Thread.sleep(2000);
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver); 
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		Thread.sleep(2000);
		registerObject.userLogout();
	}
}
