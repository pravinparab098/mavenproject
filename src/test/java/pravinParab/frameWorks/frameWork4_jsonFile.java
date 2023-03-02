package pravinParab.frameWorks;

import java.io.IOException;
import java.util.HashMap;
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

public class frameWork4_jsonFile extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer=RetryListeners.class)

	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		// String productName = productName;

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
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
	public void OrderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("productName")));
	}
 
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\pravinParab\\data\\purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
}
