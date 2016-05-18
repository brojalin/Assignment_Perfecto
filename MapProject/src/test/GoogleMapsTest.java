package test;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfectomobile.selenium.util.EclipseConnector;

import io.appium.java_client.android.AndroidDriver;

import pages.RestaurantResultPage;

import pages.WelcomePage;
import util.CommonUtils;
import util.ExcelReader;
import util.PerfectoLabUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;



public class GoogleMapsTest {
	
	private AndroidDriver<WebElement> driver;
	private DesiredCapabilities capabilities;
	private String host;
	private String reportPath;
	
	 @Test(dataProvider="restaurantInput")
	 public void findTimeToNearByRestaurant(String deviceLocation, String searchString) throws MalformedURLException {
	   
		setLocationAddress(deviceLocation);
		setDriverInstance();
			 
		cleanAndLaunchApp(); 	
     	driver.context("NATIVE_APP");
     	     	
     	RestaurantResultPage restntDetObj = new WelcomePage(driver).acceptWelcomeMsg()
     							.clickSearchTextView()
     								.searchForTheLocation(searchString)
     									.clickOnTheResultForRest(searchString);
     									
     	String restntName = restntDetObj.getFirstRestaurantName();								
     	String time = restntDetObj.clickOnTheFirstRestaurant().getTimeToDrive();
     	
     	
     	String consoleOut = " Map apps is reporting that it will take '"+time +"' to reach the nearby restaurant ( Restaurant Name: '"+restntName+"' )";
     	Reporter.log(consoleOut);
      	System.out.println(consoleOut);
     	     	     	
	 }
	   
	 
	 
	 @Test(dataProvider="specificPlaceInput")
	 public void findTimeToReachSpecificPlace(String deviceLocation, String destinationPlaces) throws MalformedURLException {
		 
		setLocationAddress(deviceLocation);
		setDriverInstance();
		 
		cleanAndLaunchApp(); 
     	driver.context("NATIVE_APP");
     	
	 	 String time  = new WelcomePage(driver).acceptWelcomeMsg()
					.clickSearchTextView()
						.searchForTheLocation(destinationPlaces)
							.clickOnTheResultForPlace(destinationPlaces)
								.acceptPullUpMessagePopup()
									.clickOnThePlace(destinationPlaces)
										.getTimeToDrive();
     	 
	 	String consoleOut = "Map apps is reporting that it will take '"+time +"' to reach '"+destinationPlaces+"'";     	
     	Reporter.log(consoleOut);
      	System.out.println(consoleOut);
     	
	 }
	 
	 
	   @Parameters({ "host", "user" , "password", "deviceID", "pakageName", "persona"})
	   @BeforeMethod 
	   public void beforeClass(String host, String user, String password, String deviceID, String pakageName, String persona) throws IOException{
		   System.out.println("Run started");

	        String browserName = "mobileOS";
	        capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
	       
	        this.host = host;
	        capabilities.setCapability("user", user);
	        capabilities.setCapability("password", password);	       
	        capabilities.setCapability("deviceName", deviceID);

	        // Framework to be used
	        capabilities.setCapability("automationName", "Appium");

	        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
	        setExecutionIdCapability(capabilities, host);
	        
	        capabilities.setCapability("appPackage", pakageName);
	        capabilities.setCapability("windTunnelPersona", persona);
	        setReportPath(deviceID);
	       
	   }





	@AfterMethod
	   public void afterClass() {
	     try{
	         // Close the browser
	         driver.close();
	             
	         // Download a pdf version of the execution report
	         //PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\temp\\report.pdf");
	         PerfectoLabUtils.downloadReport(driver, "pdf", reportPath+"\\report.pdf");
	         }
	         catch(Exception e){
	             e.printStackTrace();
	         }
	     driver.quit();
	   }

	   
	   @DataProvider(name="restaurantInput")
		public Iterator<Object[]> restaurantInputDataWorkbook() throws FileNotFoundException, IOException {	
			
			String filePath = "input/MapTestData.xlsx";
			String sheetName = "RestaurantInputData";
			ExcelReader exrd = new ExcelReader(filePath);
			ArrayList<Object[]> da = new ArrayList<Object[]>();
			
			for(int i=1; i<exrd.getRowCounts(sheetName)+1; i++)
			{
				Map<String, String> rowData = exrd.getSheetData(i, sheetName);
				if(rowData.get("RunFlag").equalsIgnoreCase("ON"))
				{
					if(!rowData.get("DeviceLocation").isEmpty())
						da.add(new Object[]{(Object)rowData.get("DeviceLocation").trim(), (Object)"restaurants"});
				}
			}
			
			return da.iterator();	
		}
	   
	   @DataProvider(name="specificPlaceInput")
		public Iterator<Object[]> specificPlaceInputDataWorkbook() throws FileNotFoundException, IOException {	
			
			String filePath = "input/MapTestData.xlsx";
			String sheetName = "SpecificPlaceInput";
			ExcelReader exrd = new ExcelReader(filePath);
			ArrayList<Object[]> da = new ArrayList<Object[]>();
			
			for(int i=1; i<exrd.getRowCounts(sheetName)+1; i++)
			{
				Map<String, String> rowData = exrd.getSheetData(i, sheetName);
				if(rowData.get("RunFlag").equalsIgnoreCase("ON"))
				{
					if(!rowData.get("DeviceLocation").isEmpty())
						da.add(new Object[]{(Object)rowData.get("DeviceLocation").trim(), (Object)(Object)rowData.get("DestinationPlace").trim()});
				}
			}
			
			return da.iterator();	
		}
	   
	   
	   
	    private static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException  {
	        EclipseConnector connector = new EclipseConnector();
	        String eclipseHost = connector.getHost();
	        if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
	            String executionId = connector.getExecutionId();
	            capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
	        }
	    }
	    
	    private void setLocationAddress(String addressLoc){	    	
	    	capabilities.setCapability("windTunnelLocationAddress", addressLoc);		    
	    }
	    
	    private void setDriverInstance() throws MalformedURLException
	    {
	    	  driver = new AndroidDriver<WebElement>(new java.net.URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		      //  driver = new IOSDriver<WebElement>(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		      // IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		      driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	    }
	    
	    private void cleanAndLaunchApp()
	    {
		    Map<String, Object> params = new HashMap<>();
	     	params.put("name", "Maps");
	     	params.put("identifier", "com.google.android.apps.maps");
	     	driver.executeScript("mobile:application:clean", params);
	     	
	     	driver.launchApp();  
	    }
	    
	   private void setReportPath(String deviceID) {
		   reportPath = System.getProperty("user.dir")+"\\reports\\DeviceID-"+deviceID+"_TimeStamp-"+CommonUtils.getTimeStamp();
	        new File(reportPath).mkdirs();	
	   }
	    
}
