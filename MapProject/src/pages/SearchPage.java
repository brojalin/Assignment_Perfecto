package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class SearchPage extends BasePages {
	
	By searchEditTextBox = By.xpath("//*[contains(@resource-id, 'id/search_omnibox_text_box')]/android.widget.EditText");
	String dynmaicResult = "//*[contains(@resource-id, 'id/fullscreen_group')]//android.widget.TextView[contains(translate(@text, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'$variable$')]";
	
	
	public SearchPage(AndroidDriver<WebElement> driver) {
		super(driver);
		// validate that we are on the Main page by looking for the "First Screen" button.
		try {
			driver.findElement(searchEditTextBox);
		} catch (NoSuchElementException e) {
			Assert.fail("Search Page  not loaded\n"+e.toString());
		}
	}

	public SearchPage searchForTheLocation(String val)
	{
		driver.findElement(searchEditTextBox).sendKeys(val);		
		return new SearchPage(driver);
	} 
	
	public RestaurantResultPage clickOnTheResultForRest(String val)
	{	
		driver.findElementByXPath(dynmaicResult.replace("$variable$", val.toLowerCase())).click();
		return new RestaurantResultPage(driver);
	}
	
	public PopupPage clickOnTheResultForPlace(String val)
	{	
		driver.findElementByXPath(dynmaicResult.replace("$variable$", val.toLowerCase())).click();
		return new PopupPage(driver);
	}
	
}
