package steps;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;
import tests.TestBase;
public class EndToEndTest extends TestBase{
	String productname = "Apple MacBook Pro 13-inch";
	HomePage homeObject;
	LoginPage loginObject;
	UserRegistrationPage userRegistrationObject;
	SearchPage searchObject;
	ShoppingCartPage shoppingCartObject;
	ProductDetailsPage productDetailsObject;
	OrderDetailsPage orderDetailsObject;
	CheckoutPage checkoutObject;
	@Given("^The user is in the home page$")
	public void the_user_is_in_the_home_page() throws Throwable {
	    homeObject = new HomePage(driver);
	    loginObject= new LoginPage(driver);
	    userRegistrationObject = new UserRegistrationPage(driver);
	    homeObject.openLoginPage();
	    Thread.sleep(2000);
	    loginObject.UserLogin("a@yahoo.com", "anaabnmasr");
	    Assert.assertTrue(userRegistrationObject.logoutLink.isDisplayed());
	}

	@When("^He search for \"([^\"]*)\"$")
	public void he_search_for(String productName) throws Throwable {
	    searchObject = new SearchPage(driver);
	    productDetailsObject = new ProductDetailsPage(driver);
	    Thread.sleep(2000);
	    searchObject.ProductSearchUsingAutoSuggest(productName);
	    Assert.assertTrue(productDetailsObject.productNamebreadCrumb.getText().contains(productName));
	    Thread.sleep(2000);
	    productDetailsObject.AddToCart();
	    Thread.sleep(2000);
	    productDetailsObject.goToShoppingCartFromSuccessMessage();
	}

	@When("^Choose buy two items$")
	public void choose_buy_two_items() throws Throwable {
	    shoppingCartObject = new ShoppingCartPage(driver);
	    Thread.sleep(3000);
	    shoppingCartObject.UpdateProductQuantityInCart("5");
	    Thread.sleep(2000);
	    shoppingCartObject.openCheckoutPage();
	}

	@When("^moves to ckeckout cart and entered personal data on checkout page and place the order$")
	public void moves_to_ckeckout_cart_and_entered_personal_data_on_checkout_page_and_place_the_order() throws Throwable {
	    checkoutObject = new CheckoutPage(driver);
	    Thread.sleep(2000);
	    checkoutObject.RegisteredUserCheckoutProduct("Egypt", "test address", "123456", "32445566677", "Cairo", productname);
	    Thread.sleep(2000);
	    checkoutObject.confirmOrder();
	    Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
	}

	@Then("^he can view the order and print the invoice$")
	public void he_can_view_the_order_and_print_the_invoice() throws Throwable {
	  checkoutObject = new CheckoutPage(driver);
	  orderDetailsObject = new OrderDetailsPage(driver);
	    Thread.sleep(2000);
	  checkoutObject.viewOrderDetails();
	    Thread.sleep(2000);
	  orderDetailsObject.DownloadPDFInvoice();
	    Thread.sleep(2000);
	  orderDetailsObject.PrintOrderDetails();
	}
}
