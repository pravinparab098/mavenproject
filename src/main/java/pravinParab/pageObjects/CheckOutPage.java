package pravinParab.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pravinParab.abstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By results = By.cssSelector(".ta-results");
	
	@FindBy (css = "a[class*='action__submit']")
	WebElement submit;
	
	@FindBy (css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy (xpath = "(//button[contains(@class,'ta-item')]) [2]")
	WebElement selectCountry;
	
	
	public void selectCountry(String countryName)
	{
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		waitForElementAppear(results);
		selectCountry.click();
	}
	
	public ConformationPage submitOrder() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		submit.click();
		return new ConformationPage(driver);
	}
}
