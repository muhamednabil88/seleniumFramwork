package steps;

import org.testng.Assert;

import com.github.javafaker.Faker;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegistration extends TestBase{
	HomePage homeObject;
	UserRegistrationPage userRegistrationObject;
	Faker fakeData = new Faker();
	/*String firstName = fakeData.name().firstName();
	String lastName = fakeData.name().lastName();
	String email = fakeData.internet().emailAddress();
	String password = fakeData.number().digits(10);*/
	@Given("^The user is in home page$")
	public void the_user_is_in_home_page() throws Throwable {
	    homeObject = new HomePage(driver);
	    homeObject.openRegistrationPage();
	}

	@When("^Click on register link$")
	public void click_on_register_link() throws Throwable {
	   Assert.assertTrue(driver.getCurrentUrl().contains("register"));
	}

	/*@When("^I entered the user data$")
	public void i_entered_the_user_data() throws Throwable {
	    userRegistrationObject = new UserRegistrationPage(driver);
	    userRegistrationObject.userRegistration(firstName, lastName, email, password);
	}*/
	@When("^I entered \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void i_entered(String firstName, String lastName, String email, String password) throws InterruptedException{
		userRegistrationObject = new UserRegistrationPage(driver);
	    homeObject = new HomePage(driver);
		Thread.sleep(2000);
	    userRegistrationObject.userRegistration(firstName, lastName, email, password);
	    Assert.assertTrue(userRegistrationObject.successMessage.getText().contains("Your registration completed"));
	    userRegistrationObject.userLogout();
		Thread.sleep(2000);
	    homeObject.openRegistrationPage();
	}

	@Then("^The Registration page displayed success$")
	public void the_Registeration_page_displayed_success() throws Throwable {
		homeObject = new HomePage(driver);
		Assert.assertTrue(homeObject.registerLink.isDisplayed());
	    
	}
}
