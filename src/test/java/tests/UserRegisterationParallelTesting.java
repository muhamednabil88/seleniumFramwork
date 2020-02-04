package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegisterationParallelTesting extends TestBase2 {
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 
	Faker fakeData = new Faker();
	String firstname = fakeData.name().firstName();
	String lastname = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String password = fakeData.number().digits(8).toString();
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() throws InterruptedException 
	{
		
		homeObject = new HomePage(getDriver()); 
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(getDriver()); 
		Thread.sleep(2000);
		registerObject.userRegistration(firstname, lastname, email, password);
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		Thread.sleep(2000);
		homeObject.openLoginPage();
		loginObject = new LoginPage(getDriver()); 
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		Thread.sleep(2000);
		registerObject.userLogout();
	}
}
