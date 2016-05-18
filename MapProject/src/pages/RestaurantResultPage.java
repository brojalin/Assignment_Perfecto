package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class RestaurantResultPage extends BasePages {
	
	By firstRestName = By.xpath("//*[@resource-id='com.google.android.apps.maps:id/title']");
		
	public RestaurantResultPage(AndroidDriver<WebElement> driver) {
		super(driver);
		// validate that we are on the Restaurant Result page by looking for the "First Restaurant" in result.
		try {
			driver.findElement(firstRestName);
		} catch (NoSuchElementException e) {
			Assert.fail("Restaurant Result Page  not loaded\n"+e.toString());
		}
	}
	
	
	public String getFirstRestaurantName()
	{
		return driver.findElement(firstRestName).getText();
	}

	public ResPlaDetailPage clickOnTheFirstRestaurant()
	{	
		driver.findElement(firstRestName).click();
		return new ResPlaDetailPage(driver);
	}
	
}
