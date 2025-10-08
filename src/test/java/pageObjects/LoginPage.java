package pageObjects;

import java.awt.AWTException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) throws AWTException {
		super(driver);
		
	}
	
	@FindBy(xpath="//img[@alt='Acme-Logo']")
	WebElement acmeLogo;
	
	@FindBy(xpath="//h3[text()='Sign in to your account']")
	WebElement loginPageText;
	
	@FindBy(xpath = "//input[@placeholder='Email']") 
	 WebElement emailIDField;
	
	@FindBy(xpath = "//input[@placeholder='Password']") 
	WebElement passwordField;
	
	@FindBy(xpath="//i[@class='bi bi-eye-fill password-icon']")
	WebElement eyeIconOfPasswordFill;
	
	@FindBy(xpath="//input[@class='form-check-input']")
	WebElement rememberMeCheckBox;
	
	@FindBy(xpath="//label[@class='form-check-label']")
	WebElement rememberMeLabel;
	
	@FindBy(xpath = "//button[@type='submit']") 
	 WebElement submitButton;
	
	@FindBy(xpath="//*[contains(text(),'Invalid email or password')]")
	WebElement errorMessage;
	
	public boolean isACMELogoDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(acmeLogo));
        return acmeLogo.isDisplayed();
    }

    public String getLoginText() {
    	wait.until(ExpectedConditions.visibilityOf(loginPageText));
        return loginPageText.getText();
    }
	
    public boolean isPasswordEyeIconIsPresent() {
    	wait.until(ExpectedConditions.visibilityOf(eyeIconOfPasswordFill));
    	return eyeIconOfPasswordFill.isDisplayed();	
    }
    
    public void clickOnEyeIconPassword() {
    	wait.until(ExpectedConditions.visibilityOf(eyeIconOfPasswordFill));
    	eyeIconOfPasswordFill.click();
    }
    
    public boolean isEmailFieldEnabled() {
    	return emailIDField.isEnabled();
    }
    
    public boolean isPasswordFieldEnabled() {
    	return passwordField.isEnabled();
    }
   
	public void enterEmailId(String emailId) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emailIDField));
		emailIDField.click();
		emailIDField.sendKeys(Keys.CONTROL + "a");
		emailIDField.sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		emailIDField.sendKeys(emailId);
	}
	
	public void enterPassword(String password) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(passwordField));
		passwordField.click();
		passwordField.sendKeys(Keys.CONTROL + "a");
		passwordField.sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		passwordField.sendKeys(password);
		
	}
	
	public void clickRememberMe() {
        if (!rememberMeCheckBox.isSelected()) {
            rememberMeCheckBox.click();;
        }
    }
	
	public boolean isSubmitEnabled() {
        return submitButton.isEnabled();
    }
	
	public boolean isRememberMeSelected() {
		return rememberMeCheckBox.isSelected();
		
	}

    
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void login(String emailId, String password, boolean rememberMe) throws InterruptedException {
    	emailIDField.click();
		emailIDField.sendKeys(Keys.CONTROL + "a");
		emailIDField.sendKeys(Keys.DELETE);
		Thread.sleep(2000);
		emailIDField.sendKeys(emailId);
    	
		passwordField.click();
		passwordField.sendKeys(Keys.CONTROL + "a");
		passwordField.sendKeys(Keys.DELETE);
		Thread.sleep(2000);
		passwordField.sendKeys(password);
        
        if (rememberMe) {
            clickRememberMe();
        }
        clickOnSubmitButton();
    }
	
	public void clickOnSubmitButton() {
		submitButton.click();
	}
	
		/*
		//sol-02
		loginButton.submit();
		
		//sol-03
		Actions act= new Actions(driver);
		act.moveToElement(loginButton).click().perform();
		
		//sol-04
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginButton);
		
		//sol-05
		loginButton.sendKeys(Keys.ENTER);
		
		//sol-06
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		
		*/
		
	
}
