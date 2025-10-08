package testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import testBase.BaseClass;
import utilities.LoginUtility;

public class TC002_DashboardPageTest extends BaseClass {
	
	
	DashboardPage dash;
    LoginUtility loginUtil;
	
    @BeforeClass
    public void setupPageObjects() throws AWTException {
        dash = new DashboardPage(BaseClass.driver);
        loginUtil = new LoginUtility();
    }
    
    @Test(priority = 1)
    public void loginIfNeeded() {
    	try {
    		loginUtil.performLogin();
		} catch (Exception e) {
			Assert.fail("LoginPage Test failed: " + e.getMessage());
		}
        
    }


	@Test (priority = 2)
	public void verify_AllTest() {
		logger.info("********* Staring TC002_DashboardTest **********");
		
		
		try {
			
			
			Assert.assertTrue(dash.isdashbodardPageExitsts(), "Dashboard page is not displayed..");
			logger.info("********* Dashboard page exists**********");

			Assert.assertTrue(dash.isLogoutButtonDisplayed(), "LogOut button is not displyaed");
			logger.info("********* SideBarButton shown **********");

			Assert.assertTrue(dash.isSideBarColapseButtonDisplayed(), "Sidebar button visible");
			logger.info("********* SideCollapse button shown **********");

			// collapse
			dash.clickOnSideBarButton();
			dash.waitForCollapsed();
			Assert.assertTrue(dash.isSidebarCollapsed(), "Sidebar should be collapsed!");
			logger.info("********* Clicked on SideBar button and Collapsed **********");

			// Expand
			dash.clickOnSideBarButton();
			dash.waitForExpanded();
			Assert.assertFalse(dash.isSidebarCollapsed());
			logger.info("********* Clicked on SideBar button and its again expanded **********");
			
			// Verify count
			Assert.assertEquals(dash.getSidebarTabCount(), 11, "Tab count mismatch!");
			
			//dash.printAllTabs();
			//logger.info("********* Successfully prints all the Names present in the side bar**********");

			// Click by name
			dash.clickTabByName("Dashboard");
			
			Assert.assertTrue(dash.isdashbodardPageExitsts(), "Dashboard page is not displayed..");
			logger.info("********* Clicked using text -> Dashboard page exists**********");
			
			//Verification of all KPI cards
			
			Assert.assertTrue(dash.isTotalInvoiceKPIDisplayed(), "TotalInvoiceKPID is not displayed..");
			Assert.assertTrue(dash.isTotalIssuedInvoiceKPIDisplayed(), "TotalIssuedInvoiceKPID is not displayed..");
			Assert.assertTrue(dash.isTotalPendingInvoiceKPIDisplayed(), "TotalPendingInvoiceKPID is not displayed..");
			Assert.assertTrue(dash.isprimaryApprovalPendingKPIDisplayed(), "primaryApprovalPendingKPID is not displayed..");
			Assert.assertTrue(dash.issecondaryApprovalPendingKPIDisplayed(), "secondaryApprovalPendingKPID is not displayed..");
			
			//Printing all KPI Information
			logger.info("******* Successfully prints all KPI cards ********");
			dash.printKpiCards();
			logger.info("******* Successfully prints all KPI cards ********");
			
			
		} catch (Exception e) {
			Assert.fail("Dashboard test failed: " + e.getMessage());
		}

		logger.info("**** End of TC002_DashboardTest ****");
		
	}
	
					

}
