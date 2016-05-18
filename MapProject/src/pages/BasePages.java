package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class BasePages {

	protected final AndroidDriver<WebElement> driver;
	

	// constructor verifies that the page objects have pointer to the driver instance
	public BasePages(AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}

	

}
