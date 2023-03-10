package pravinParab.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pravinParab.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> cartProducts =
	// driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	// driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckOut() {
		checkOutEle.click();
		return new CheckOutPage(driver);
	}

}
