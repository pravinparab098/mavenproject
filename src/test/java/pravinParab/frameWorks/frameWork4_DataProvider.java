package pravinParab.frameWorks;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pravinParab.TestComponents.BaseTest;
import pravinParab.TestComponents.RetryListeners;
import pravinParab.pageObjects.CartPage;
import pravinParab.pageObjects.CheckOutPage;
import pravinParab.pageObjects.ConformationPage;
import pravinParab.pageObjects.OrderPage;
import pravinParab.pageObjects.ProductCatalogue;

public class frameWork4_DataProvider extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer=RetryListeners.class)

	public void submitOrder(String email, String password, String productName)
			throws IOException, InterruptedException {

		// String productName = productName;

		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

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

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData", retryAnalyzer=RetryListeners.class) // OrderHistoryTest run only when submitOrder
																			// is successfully run
	public void OrderHistoryTest(String email, String password, String productName) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "anshika@gmail.com", "Iamking@000", "ZARA COAT 3" },
				{ "shetty@gmail.com", "Iamking@000", "IPHONE 13 PRO" } };

	}

}
