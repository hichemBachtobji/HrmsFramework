package com.hrms.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrms.testbase.PageInitializer;

public class CommonMethods extends PageInitializer{

	/**
	 * Method that clears and sends keys
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * Method checks if radio/checkbox is enabled and clicks it
	 * @param radioOrcheckbox
	 * @param value
	 */
	public static void clickRadioOrCheckbox(List<WebElement> radioOrcheckbox, String value) {
		
		String actualValue;
		
		for(WebElement el:radioOrcheckbox) {
			actualValue=el.getAttribute("value").trim();
			if(el.isEnabled()&& actualValue.equals(value)) {
				el.click();
				break;
			}
		}
		
	}
	
	/**
	 * Method that checks if text is there and then selects it
	 * 
	 * @param element
	 * @param textToSelect
	 */
	public static void selectDdValue(WebElement element, String textToSelect) {
		
		try {
			Select select=new Select(element);
			List<WebElement> options=select.getOptions();
			for (WebElement el : options) {
				if(el.getText().equals(textToSelect)) {
					select.selectByVisibleText(textToSelect);
					break;
				}
			}
		}catch(UnexpectedTagNameException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Method that selects value by index
	 * 
	 * @param element
	 * @param index
	 */
	
	public static void selectDdValue(WebElement element, int index) {
		try {
			Select select=new Select(element);
			int size=select.getOptions().size();
			if(size>index) {
				select.selectByIndex(index);
			}
			
		}catch(UnexpectedTagNameException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Methods that accept alerts and catches exception if alert is not present
	 */
	
	public static void acceptAlert() {
		
		try {
		Alert alert=driver.switchTo().alert();
		alert.accept();
		
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methods that dismiss alerts and catches exception if alert is not present
	 */
	
	public static void dismissAlert() {
		
		try {
			Alert alert= driver.switchTo().alert();
			alert.dismiss();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methods that gets text of alert and catches exception if alert is not present
	 * 
	 * @return String alert text
	 */
	
	public static String getAlertText() {
		String alertText = null;
		try {
			Alert alert= driver.switchTo().alert();
			alertText= alert.getText();	
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
		return alertText;
		
	}
	
	/**
	 * Methods that sends text to alert and catches exception if alert is not
	 * present
	 * 
	 */
	
	public static void sendAlertText(String textToSend) {
		
		try {
			Alert alert=driver.switchTo().alert();
			alert.sendKeys(textToSend);
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method that switches to frame with name or ID and catches exception if frame is not present
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId) {
		
		try {
			driver.switchTo().frame(nameOrId);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * method that switches to frame with WebElement and catches exception if frame is not present
	 * @param nameOrId
	 */
	
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * method that switches to frame with index and catches exception if frame is not present
	 * @param nameOrId
	 */
	public static void switchToFrame(int index) {
		
		try {
		driver.switchTo().frame(index);
		}catch(NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void switchToChildWindow() {
		String parentWindow=driver.getWindowHandle();
		Set<String> childWindow = driver.getWindowHandles();
		for (String child : childWindow) {
			if(!parentWindow.equals(childWindow)) {
				driver.switchTo().window(child);
				break;
			}
			
		}
	}
	
	public static WebDriverWait getWaitObject() {
		WebDriverWait wait=new WebDriverWait(driver,Constants.EXPLICIT_WAIT_TIME);
		return wait;	
	}
	
	
	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
		
	}
	
	public static void waitForVisibility(WebElement element) {
		getWaitObject().until(ExpectedConditions.visibilityOfElementLocated((By) element));
	}
	
	public static void waitForInvisibility(WebElement element) {
		getWaitObject().until(ExpectedConditions.invisibilityOf(element));
	}
	
	public static String TakesScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screen = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile=Constants.SCREENSHOT_FILEPATH+fileName+getTimeStamp() +".png";
		
		try {
			FileUtils.copyFile(screen, new File(destinationFile));
		} catch (IOException e) {
			System.out.println("Cannot take screenshot");
			//e.printStackTrace();
		}
		return destinationFile;
	}
	
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}
	
	public static  JavascriptExecutor getJSObject() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		return js;
	}
	
	public static void jsClick(WebElement element) {
		getJSObject().executeScript("arguments[0].click();", element);
	}
	
	public static void scrollToElement(WebElement element) {
		getJSObject().executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * Method that will scroll the page down based on the passed pixel parameters
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel) {
		getJSObject().executeScript("window.scrollBy(0," + pixel + ")");
	}

	/**
	 * Method that will scroll the page up based on the passed pixel parameters
	 * 
	 * @param pixel
	 */
	public static void scrollUp(int pixel) {
		getJSObject().executeScript("window.scrollBy(0,-" + pixel + ")");
	}
	
	
	
	public static void wait(int second) {
		
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
}
