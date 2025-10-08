package utilities;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginUtility {
	
	
	@FindBy(xpath="//button//span[text()='Logout']") WebElement loggedInCheckLocator;
	
  //  private By loggedInCheckLocator = By.xpath("//button[@id='logout']"); // example, use actual logout button locator

    public LoginUtility() {
    	PageFactory.initElements(BaseClass.driver, this);
    }

    public void performLogin() {
    	 try {
             if (isAlreadyLoggedIn()) {
                 BaseClass.logger.info("User is already logged in. Skipping login step.");
                 return;
             }

             LoginPage lp = new LoginPage(BaseClass.driver);
             lp.enterEmailId(BaseClass.prop.getProperty("emailID"));
             lp.enterPassword(BaseClass.prop.getProperty("password"));
             lp.clickOnEyeIconPassword();
             lp.clickRememberMe();
             lp.clickOnSubmitButton();

             BaseClass.logger.info("Login performed successfully via LoginUtility");

         } catch (Exception e) {
             Assert.fail("Login failed via LoginUtility: " + e.getMessage());
         }
    }

    /** Checks if user is already logged in */
    private boolean isAlreadyLoggedIn() {
        try {
            return loggedInCheckLocator.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
