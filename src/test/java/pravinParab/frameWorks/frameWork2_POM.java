package pravinParab.frameWorks;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pravinParab.TestComponents.BaseTest;
import pravinParab.pageObjects.CartPage;
import pravinParab.pageObjects.CheckOutPage;
import pravinParab.pageObjects.ConformationPage;
import pravinParab.pageObjects.ProductCatalogue;

public class frameWork2_POM extends BaseTest {

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
}
