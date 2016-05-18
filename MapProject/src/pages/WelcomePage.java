package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class WelcomePage extends BasePages {
	
	By welcomeMessage = By.xpath("//*[@text='Welcome to Google Maps']");
	By acceptContinueBut = By.xpath("//android.widget.TextView[@text='Accept & continue']");
		
	public WelcomePage(AndroidDriver<WebElement> driver) {
		super(driver);
		// validate that we are on the Welcome page by looking for the "Welcome Message" present.
		try {
			driver.findElement(welcomeMessage);
		} catch (NoSuchElementException e) {
			Assert.fail("Welcome message  not loaded\n"+e.toString());
		}
	}

	public MainPage acceptWelcomeMsg()
	{
		driver.findElement(acceptContinueBut).click();		
		return new MainPage(driver);
	}
	
}
