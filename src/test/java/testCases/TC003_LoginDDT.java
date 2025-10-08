package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

import utilities.DataProvidersLoginCred;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginCredData", dataProviderClass = DataProvidersLoginCred.class, groups = "Datadriven")  // "dataProviderClass = DataProviders.class -> Getting data provider from different class so we are specifying the class name"
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {
		
		logger.info("****** Starting TC003_LoginDDT ***********");
	try	{
	//login
	LoginPage lp=new LoginPage(driver);	

	lp.enterEmailId(email); 
	lp.enterPassword(pwd);   
	lp.clickOnSubmitButton();
	
	
	//Dashboard
	DashboardPage dash= new DashboardPage(driver);
	boolean targetDPage=dash.isdashbodardPageExitsts();
	
	
	/*
	 * 	Data is valid - login success - test pass -Logout
	 * 				  - login failed - test fail
	 * 
	 * Data is invalid - login success - test fail -Logout
	 * 				   - login failed - test pass
	 * 
	 * */
	
			if(exp.equalsIgnoreCase("Valid")) {
				
				if(targetDPage==true) {
					
					dash.clickLogoutbutton();
					Assert.assertTrue(true);
					
				}else {
					Assert.assertTrue(false);
				}
				
			}
			
			if(exp.equalsIgnoreCase("Invalid")) {
				
				if(targetDPage==true) {
					
					dash.clickLogoutbutton();
					Assert.assertTrue(false);
					
				}else {
					Assert.assertTrue(true);
				}
			}
			
			
			}catch(Exception e) {
				Assert.fail();
				
			}
		
		Thread.sleep(5000);
		
		
				logger.info("****** Finished TC003_LoginDDT ***********");
				
		
	}
}
