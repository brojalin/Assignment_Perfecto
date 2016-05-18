package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class ResPlaDetailPage extends BasePages {	

	By timeToDrive = By.xpath("//*[@content-desc='Directions']/*[contains(@text, 'min')]");
	
	public ResPlaDetailPage(AndroidDriver<WebElement> driver) {
		super(driver);
		// validate that we are on the Restaurant Detail page by looking for the "Time to drive" element.
		try {
			driver.findElement(timeToDrive);
		} catch (NoSuchElementException e) {
			Assert.fail("Restaurant Detail Page  not loaded\n"+e.toString());
		}
	}
	
	public String getTimeToDrive()
	{
		return driver.findElement(timeToDrive).getText();
	}
	
	
}
