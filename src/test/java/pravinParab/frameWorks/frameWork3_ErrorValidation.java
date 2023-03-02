package pravinParab.frameWorks;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pravinParab.TestComponents.BaseTest;
import pravinParab.TestComponents.RetryListeners;
import pravinParab.pageObjects.CartPage;
import pravinParab.pageObjects.ProductCatalogue;

public class frameWork3_ErrorValidation extends BaseTest {

	@Test (groups = {"ErrorHandling"})

	public void LogInErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("pravin98@gmail.com", "Pravin@098");
		// Errro Validation :-
		Assert.assertEquals("Incorrect email or password", landingPage.getErrorMessage());
							//Correct one :-  Incorrect email or password.
	}

	@Test (groups = {"ErrorHandling"}, retryAnalyzer=RetryListeners.class)
	public void ProductErrorValidation() {
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("pravin098@gmail.com", "Pravin@098");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 03");
		Assert.assertFalse(match);

	}
}
