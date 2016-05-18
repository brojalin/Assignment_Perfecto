package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class PlacesResultPage extends BasePages {
	
	By placeName = By.xpath("//*[contains(@resource-id, 'id/recycler_view')]//*[contains(@resource-id, 'id/title_textbox')]");
	String placeNamePath = "//*[@resource-id='com.google.android.apps.maps:id/recycler_view']//*[contains(@text, '$value$')]";
	
	public PlacesResultPage(AndroidDriver<WebElement> driver) {
		super(driver);
		// validate that we are on the Places Result page by looking for the "Places Name" element.
		try {
			driver.findElement(placeName);
		} catch (NoSuchElementException e) {
			Assert.fail("Restaurant Result Page not loaded\n"+e.toString());
		}
	}
	
	public ResPlaDetailPage clickOnThePlace(String placeName)
	{	
		driver.findElement(By.xpath(placeNamePath.replace("$value$", placeName))).click();
		return new ResPlaDetailPage(driver);
	}

	
	
}
