package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC001_LoginPageTest extends BaseClass{
	
	
	@Test
	public void verify_login_page() {
		
		logger.info("*******Strating TC001_LoginPageTest ***********");
		
		try {			
			
		//login
		LoginPage lp=new LoginPage(BaseClass.driver);	
		
		//Verify login page UI before login
		 Assert.assertTrue(lp.isACMELogoDisplayed(), "ACME logo should be displayed");
		 logger.info("******* ACME Logo is displayed ***********");
		 
	     Assert.assertEquals(lp.getLoginText(), "Sign in to your account", "Login page text is incorrect");
	     logger.info("******* ACME login Text is displayed - Sign in to your account ***********");
	     
	     Assert.assertTrue(lp.isPasswordEyeIconIsPresent(), "Eye icon should be displayed");
	     logger.info("******* Password masked icon is present ***********");
		
	     Assert.assertTrue(lp.isEmailFieldEnabled(), "Submit Button is enabled");
	     logger.info("******* Email field is enabled  ***********");
	     
	     Assert.assertTrue(lp.isPasswordFieldEnabled(), "Submit Button is enabled");
	     logger.info("******* Password field is enabled ***********");
	     
		lp.enterEmailId(prop.getProperty("emailID"));  
		logger.info("******* Email field is clickable and entered Email ID ***********");
		
		lp.enterPassword(prop.getProperty("password")); 
		logger.info("******* Password filed is clickable and entered Password ***********");
		
		lp.clickOnEyeIconPassword();
		logger.info("******* Password masked icon is present ***********");
		
		lp.clickRememberMe();
		logger.info("******* clicked on Remember me check box masked icon is present ***********");
		
		Assert.assertTrue(lp.isRememberMeSelected(), "Remember Me should be selected");
		System.out.println("Successfully entered email and password");
		logger.info("******* Validated Remember me check box = True ***********");
		
		Assert.assertTrue(lp.isSubmitEnabled(), "Submit Button is enabled");
		logger.info("******* Submit button is enabled  ***********");
		
		lp.clickOnSubmitButton();
		logger.info("******* Password masked icon is present ***********");
		
		
		// Validate dashboard/home page
        String actualTitle = driver.getTitle();
        System.out.println("Actual Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, "Acme", "Login failed - incorrect page title");
		
			
		}catch (Exception e) {
			
			e.printStackTrace();
	        Assert.fail("Test case failed due to exception: " + e.getMessage());
		}
		
		logger.info("******* Finished TC001_LoginPageTest ***********");
		
	}
	
	
	
}
