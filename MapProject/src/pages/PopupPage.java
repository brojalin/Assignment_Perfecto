package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PopupPage extends BasePages {
	
	By pullUpMessage = By.xpath("//*[@text='Pull up for details about this place']");
	By popUpAccMsgBtn = By.xpath("//*[@resource-id='com.google.android.apps.maps:id/tutorial_pull_up_got_it']");
	
	
	
	public PopupPage(AndroidDriver<WebElement> driver) {
		super(driver);
	}

	public PlacesResultPage acceptPullUpMessagePopup()
	{
		try{
		driver.findElement(popUpAccMsgBtn).click();
		}catch(Exception e){}
		
		return new PlacesResultPage(driver);
	}
	
}
