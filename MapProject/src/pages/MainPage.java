package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;

public class MainPage extends BasePages {
	
	By searchTextView = By.xpath("//*[contains(@resource-id, 'id/search_omnibox_text_box')]/android.widget.TextView");
		
	public MainPage(AndroidDriver<WebElement> driver) {
		super(driver);
		// validate that we are on the Main page by looking for the "First Screen" button.
		try {
			driver.findElement(searchTextView);
		} catch (NoSuchElementException e) {
			Assert.fail("Main page not loaded\n"+e.toString());
		}
	}

	public SearchPage clickSearchTextView()
	{
		driver.findElement(searchTextView).click();
		return new SearchPage(driver);
	} 
}
