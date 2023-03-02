package pravinParab.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pravinParab.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	/*
	 *  this page is contains locators which are belongs to landing page -- like
	 *  userEmail, password, logIn Button
	 *  so lets past all related webElements
	 *  
	 *  driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
	 *  driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
	 *  driver.findElement(By.id("login")).click();
	 *  
	 *  but if you observe there is a error for driver so lets create webDriver with driver variable
	 *  and same time a constructor with className -- this is a one anything in constructor is execute first 
	 *  so provide the instruction which use to initialize the test
	 *  like this.driver = driver -- this contains current class instance variable called driver
	 *  and other driver is from where you actually created webDriver driver = chromeDriver; (frameWork2_POM class)
	 *  and in the frameWork2_POM page create one object for LandingPage landingPage = new LanndingPage(driver); with the argument of the driver
	 *  and also give the same to the constructor  
	 *  public LandingPage(webDriver driver)
	 *  
	 *  Now PageFacory
	 *  webElement userEmail = driver.findElement(By.id("userEmail")); -- this will simply replace by using pageFactory method
	 *  @FindBy(id="userEmail");	-- one problem that there is no driver information so we provide pageFactory information in constructor class 
	 *  webElement userEmail;
	 *  
	 *  PageFactory.initElements(driver,this); -- initialize the element with driver information stored in this variable
	 *  	
	 */
		
		WebDriver driver;
		
		public LandingPage (WebDriver driver)
		{
			//Information for Initialization of testCase
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		//EmailID
		@FindBy(id="userEmail")
		WebElement userEmail;
		//Password
		@FindBy(id="userPassword")
		WebElement userPassword;
		//LogInButton
		@FindBy(id="login")
		WebElement logIn;
		//errorMessage
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		
		
		/*
		 * Now write the actionMethods like sendKeys and click etc.. 
		 * so now WebElement userEmail contains driver information so just provide 
		 * action method but its different for different test case so don't be 
		 * structure for one test case hence provede String email as sendkeys 
		 */
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			System.out.println("Error Message : "+ errorMessage.getText());
			return errorMessage.getText();
		}
		
		public ProductCatalogue loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			logIn.click();
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			return productCatalogue;
		}
		
}
