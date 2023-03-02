package pravinParab.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pravinParab.TestComponents.BaseTest;
import pravinParab.pageObjects.CartPage;
import pravinParab.pageObjects.CheckOutPage;
import pravinParab.pageObjects.ConformationPage;
import pravinParab.pageObjects.LandingPage;
import pravinParab.pageObjects.ProductCatalogue;

public class stepDefinationsImp extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConformationPage conformationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void When_I_add_product_to_cart(String productName) throws IOException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		conformationPage = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_ConfirmationPage(String string) {
		String confirmMessage = conformationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		System.out.println("Success Statement : " + driver.findElement(By.cssSelector(".hero-primary")).getText());
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {
	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
	driver.close();
	}
}
