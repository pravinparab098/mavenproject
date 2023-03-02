package pravinParab.frameWorks;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pravinParab.TestComponents.BaseTest;
import pravinParab.TestComponents.RetryListeners;
import pravinParab.pageObjects.CartPage;
import pravinParab.pageObjects.CheckOutPage;
import pravinParab.pageObjects.ConformationPage;
import pravinParab.pageObjects.OrderPage;
import pravinParab.pageObjects.ProductCatalogue;

public class frameWork3_DependencyAttribute extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test

	public void submitOrder() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingPage.loginApplication("pravin098@gmail.com", "Pravin@098");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		ConformationPage conformationPage = checkOutPage.submitOrder();

		String confirmMessage = conformationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Success Statement : " + driver.findElement(By.cssSelector(".hero-primary")).getText());
	}

	@Test(dependsOnMethods = { "submitOrder" }, retryAnalyzer=RetryListeners.class) // OrderHistoryTest run only when submitOrder is successfully run
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
}
