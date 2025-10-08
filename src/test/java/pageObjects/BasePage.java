package pageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.DatePickerUtility;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	protected Robot robot;
	protected JavascriptExecutor js;
	protected DatePickerUtility datePicker;
	
	public BasePage (WebDriver driver) throws AWTException {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
		this.datePicker = new DatePickerUtility(driver);
		
		try {
		    robot = new Robot();
		} catch (AWTException e) {
		    e.printStackTrace();
		}
		
	}
	

	
	// ======================
    // Common Wait Utilities
    // ======================
	
	//Wait for the visibility of element
    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    //Wait until element to be clickability
    public void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    //Validate field is Enabled
    public boolean fieldIsEnabled(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	return element.isEnabled();
    }
    
    //Validate field is Disabled
    public boolean fieldIsDisabled(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	return !element.isEnabled();
    }
    
    //Validate field is Shows
    public boolean fieldIsDisplayed(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	return element.isDisplayed();
    }
    
    //Validate field is Hidden
    public boolean fieldIsHidden(WebElement element) {
    	return !element.isDisplayed();
    }
    
    //Validate button is Enabled
    public boolean buttonIsEnabled(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	return element.isEnabled();
    }
    
    //Validate button is Disabled
    public boolean buttonIsDisabled(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	return !element.isEnabled();
    }
    
    //Validate field is Shown
    public boolean buttonIsDisplayed(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	return element.isDisplayed();
    }
    
    //Validate field is Hidden
    public boolean buttonIsHidden(WebElement element) {
    	return !element.isDisplayed();
    }
    
    
    //Click on Button
    public void clickOnButton(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
        if (element.isEnabled()) {
        	actions.click(element).click().build().perform();
        }
    }


    
    //Select the previously entered text and Clear text
    public void clearOnElement(WebElement element) {
    	wait.until(ExpectedConditions.visibilityOf(element));
    	actions.moveToElement(element).click().build().perform();
    	element.sendKeys(Keys.CONTROL + "a"); // Select all text
        element.sendKeys(Keys.DELETE);        // Delete selected text
        System.out.println("✅ Cleared the search field");
    }


    // ======================
    // Click & SendKeys
    // ======================
    public void click(WebElement element) {
    	  try {
    	        waitForClickability(element);
    	        element.click();
    	    } catch (Exception e) {
    	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    	    }
    }

    public void typeText(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    // ======================
    // Dropdown Handling
    // ======================

    // For native <select>
    public void selectByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    // For custom dropdowns (like React Select)
    public void selectDropdown(WebElement dropdown, String value) {
        dropdown.click();
        WebElement option = driver.findElement(
            By.xpath("//div[contains(@class,'option') and text()='" + value + "']")
        );
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    
    // for get text of the element
    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    
    //for element is displayed or not
    public boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    //=====================
    // 🔹 Actions class reusable methods
    //========================
    	//mouse hover
    public void hoverOverElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }
    
    	//double click on element
    public void doubleClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.doubleClick(element).perform();
    }
    
    	//right click on element
    public void rightClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.contextClick(element).perform();
    }
    	//drag and drop of an source and target element
    public void dragAndDrop(WebElement source, WebElement target) {
        wait.until(ExpectedConditions.visibilityOf(source));
        wait.until(ExpectedConditions.visibilityOf(target));
        actions.dragAndDrop(source, target).perform();
    }
    
    	//scroll to an web element
    public void scrollToElementUsingAction(WebElement element) {
        actions.moveToElement(element).perform();
    }
    
    	//scroll to an element using JavaScript Executor
    public void scrollBy(int x, int y) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
    
    public void selectDropdownValue(WebElement element, String value) throws InterruptedException {
    	
        actions.moveToElement(element).click().sendKeys(value).build().perform();
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(1500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
    }

    
    public void selectDropdownValuesFromExcelSheet(WebElement element, String value) throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).click().sendKeys(value).build().perform();
        Thread.sleep(2000);
        // Directly hit ENTER to select the first result
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(500);
    }
    
    
    public void selectDropdownAdanceFilters(WebElement element, String value) {
        // Wait until the dropdown is clickable
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    	actions.moveToElement(element).click().build().perform();

    	// Step 2: Type the value
        actions.sendKeys(value).pause(500).sendKeys(Keys.ENTER).build().perform();

        // Step 3: Optional – move focus away with TAB (to next field)
        actions.sendKeys(Keys.TAB).build().perform();

        // 4. Wait until dropdown is closed (optional safety)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//div[contains(@class,'react-select__menu')]") ));
       
    	}
    
	
	//====================
	//Robot Class
	//=====================
	

    // ======================
    // Scrolling with JavaExecuter
    // ======================
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ======================
    // Alert Handling
    // ======================
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    // ======================
    // Frame & Window Handling
    // ======================
    public void switchToFrame(WebElement frame) {
        driver.switchTo().frame(frame);
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }
    

}
