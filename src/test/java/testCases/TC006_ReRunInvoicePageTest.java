package testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.ReRunInvoicesPage;
import testBase.BaseClass;
import utilities.LoginUtility;

public class TC006_ReRunInvoicePageTest extends BaseClass {
	
	
	ReRunInvoicesPage reRun;
	LoginUtility loginUtil;

    @BeforeClass
    public void setupPageObjects() throws AWTException {
        reRun = new ReRunInvoicesPage(BaseClass.driver);
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

    @Test(priority = 2)
    public void verifyRerunInvoicesTab() {
        logger.info("******* Starting verifyRerunInvoicesTab***********");
        try {
        	
        	
        	reRun.clickOnRerunInvoiceTab();
        	logger.info("Clicked on Rerun Invoice Tab");
        	
            Assert.assertEquals(reRun.validateReruninvoiceHeaderText(), "Re-Run Invoices", "Rerun Invoices header text not diplayed");
            logger.info("Validated Rerun Invoices header text on the tab");
            
            reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
            logger.info("Rerun Invoices tab verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices tab test failed: " + e.getMessage());
        }
    }
    
   
    
    @Test(priority = 3)
    public void validateRerunInvoiceFunction() {
        logger.info("******* Starting validateRerunInvoiceFunction***********");
        try {
        	
        	reRun.selectOption("Duration");
        	Assert.assertTrue(reRun.isDurationSelected(), "By Duration should be selected");
        	logger.info("Selected By Duration radio Button");
        	Thread.sleep(5000);
        	
        	Assert.assertEquals(reRun.validateDescriptionButtonText(), "Run an invoice that not exist: Make sure there is no data in another invoice that overlaps with range selected. If data does exist that invoice must be voided before a new invoice can be run.", 
        			"Description of By duration radio button should be displayed");
        			   	logger.info("Discription of By Duration text verified sucessfully");
        	
        	reRun.selectOption("Invoice");
        	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
        	logger.info("Selected By Invoice Number radio Button");
        	
        	Assert.assertEquals(reRun.validateDescriptionButtonText(), "Run an individual invoice: Make sure the invoice has not gone through secondary approval and that is not a void status. If customer settings were changed to create 1 invoice per PO or the opposite make sure the original invoice(s) are voided first.", 
        			"Description of Invoice Number radio button should be displayed");
        	logger.info("Discription of Invoice Number text verified sucessfully");
        	
            reRun.selectOption("Duration");
        	Assert.assertTrue(reRun.isDurationSelected(), "By Duration should be selected");
        	logger.info("Selected By Duration radio Button");
        	
            logger.info("Rerun Invoices function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Function test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 4)
    public void validateRerunInvoiceFields() {
        logger.info("******* Starting validateRerunInvoiceFields***********");
        try {
        	
        	Assert.assertEquals(reRun.validateInvoiceTypeText(), "Invoice Type", 
        			"Invoice Type required field should be display");
        			   	logger.info("Invoice Type: required field verified successfully");
        			   	
        			   	Assert.assertTrue(reRun.validateRerunInboundTypeEnabled(), "Inbound Type should be enabled");
        			   	Assert.assertTrue(reRun.validateRerunOutboundTypeEnabled(), "Outbound Type should be enabled");
        			   	Assert.assertTrue(reRun.validateRerunStorageTypeEnabled(), "Storage Type should be enabled");
        			   	
        			   	// Select and assert Inbound
        			   	reRun.selectCheckbox("Inbound");
        			    Assert.assertTrue(reRun.isCheckboxSelected("Inbound"), "Checkbox Inbound should be selected");
        			    
        			    // Select and assert outbound
        			    reRun.selectCheckbox("Outbound");
        			    Assert.assertTrue(reRun.isCheckboxSelected("Outbound"), "Checkbox Outbound should be selected");
        			    
        			    // Select and assert Storage
        			    reRun.selectCheckbox("Storage");
        			    Assert.assertTrue(reRun.isCheckboxSelected("Storage"), "Checkbox Storage should be selected");

        			    logger.info("All checkboxes are selected successfully.");	   	
        			    
        				// Deselect and assert Inbound
        			    reRun.deselectCheckbox("Inbound");
        			    Assert.assertFalse(reRun.isCheckboxSelected("Inbound"), "❌ Inbound checkbox should be deselected.");
        			    
        			    // Deselect and assert Outbound
        			    reRun.deselectCheckbox("Outbound");
        			    Assert.assertFalse(reRun.isCheckboxSelected("Outbound"), "❌ Outbound checkbox should be deselected.");
        			    
        			    // Deselect and assert Storage
        			    reRun.deselectCheckbox("Storage");
        			    Assert.assertFalse(reRun.isCheckboxSelected("Storage"), "❌ Storage checkbox should be deselected.");
        			    
        			    logger.info("All checkboxes are Deselected successfully.");	   	
        			    
        			    Assert.assertTrue(reRun.validateRerunLocationFilterEnabled(), "Rerun lcoation fiedl should be Enabled");
        			    Assert.assertTrue(reRun.validateRerunSubmitButtonDisabled(), "Rerun Submit button should be disabled");
        			    
            logger.info("Rerun Invoices function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Function test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 7)
    public void verifyVoidedInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyVoidedInvoice Method= Manual and Status= Voided, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Voided");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Voided status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info("Voided Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Voided Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // Validate using POM method
            reRun.validateErrorPopupMessage("cannot be re-run");
            logger.info("Error message validated successfully");
            
            
            
            logger.info("Rerun Invoice with Invoice Status = Voided function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Voided test failed: " + e.getMessage());
        }
    
    }
    
    
    
    @Test(priority = 8)
    public void verifyEmailedToCustomerInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyEmailedToCustomerInvoice Method= Manual and Status= Emailed To Customer, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Emailed To Customer");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Emailed To Customer status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info("Emailed To Customer Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Emailed To Customer Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // Validate using POM method
            reRun.validateErrorPopupMessage("cannot be re-run");
            logger.info("Error message validated successfully");
            
            
            
            logger.info("Rerun Invoice with Invoice Status = Emailed To Customer function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Emailed To Customer test failed: " + e.getMessage());
        }
    
    }
    
    @Test(priority = 9)
    public void verifyPartialCreditInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyPartialCreditInvoice Method= Manual and Status= Partial Credit, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Partial Credit");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Partial Credit status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info("Partial Credit Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Partial Credit Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // Validate using POM method
            reRun.validateErrorPopupMessage("cannot be re-run");
            logger.info("Error message validated successfully");
            
            
            
            logger.info("Rerun Invoice with Invoice Status = Partial Credit function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Partial Credit test failed: " + e.getMessage());
        }
    
    }
    
    @Test(priority = 10)
    public void verifyCreditInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyPartialCreditInvoice Method= Manual and Status= Credit, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Credit");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Credit status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info("Credit Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Credit Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // Validate using POM method
            reRun.validateErrorPopupMessage("cannot be re-run");
            logger.info("Error message validated successfully");
            
            
            
            logger.info("Rerun Invoice with Invoice Status = Credit function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Credit test failed: " + e.getMessage());
        }
    
    }
    
    /*
    
    
    @Test(priority = 8)
    public void verifyPartialCreditInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyPartialCreditInvoice, Status= Partical Credit, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Partial Credit");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Partial Credit status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info(" Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // this comes from your rerun flow input
            String popupMessage = reRun.waitForInvoiceErrorPopup(invoiceNo);

            System.out.println("📌 Popup message: " + popupMessage);

            Assert.assertTrue(popupMessage.contains("Invoice number '" + invoiceNo + "' has completed secondary approval and cannot be re-run."),
                "❌ Error popup message did not match for invoice " + invoiceNo);
            
            logger.info("Successfully verified rerun invoice with Partial Credit status error popup message");
            
            
            logger.info("Rerun Invoice with Invoice Status = Partial Credit function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Partial Credit test failed: " + e.getMessage());
        }
    
    }
    
    @Test(priority = 9)
    public void verifyCreditInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyCreditInvoice, Status= Partical Credit, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Credit");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Credit status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info(" Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // this comes from your rerun flow input
            String popupMessage = reRun.waitForInvoiceErrorPopup(invoiceNo);

            System.out.println("📌 Popup message: " + popupMessage);

            Assert.assertTrue(popupMessage.contains("Invoice number '" + invoiceNo + "' has completed secondary approval and cannot be re-run."),
                "❌ Error popup message did not match for invoice " + invoiceNo);
            
            logger.info("Successfully verified rerun invoice with Credit status error popup message");
            
            
            logger.info("Rerun Invoice with Invoice Status = Credit function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Credit test failed: " + e.getMessage());
        }
    
    }
    
    @Test(priority = 9)
    public void verifyVoidedInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyVoidedInvoice, Status= Partical Credit, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Inquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Voided");
        	logger.info("Applied advance filters in Invoice Inquiry tab for Accessorial, AC01, FL51 and Voided status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info(" Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
            
            // this comes from your rerun flow input
            String popupMessage = reRun.waitForInvoiceErrorPopup(invoiceNo);

            System.out.println("📌 Popup message: " + popupMessage);

            Assert.assertTrue(popupMessage.contains("Invoice number '" + invoiceNo + "' has completed secondary approval and cannot be re-run."),
                "❌ Error popup message did not match for invoice " + invoiceNo);
            
            logger.info("Successfully verified rerun invoice with Credit status error popup message");
            
            
            logger.info("Rerun Invoice with Invoice Status = Voided function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Invoice Status = Voided test failed: " + e.getMessage());
        }
    
    }
    
    /*
    
    @Test(priority = 5)
    public void verifyReadyForPrimaryApprovalInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyReadyForPrimaryApprovalInvoice Method= Manual and Status= Ready for primary approval, WithInvoiceNumber***********");
        try {
        	
        	//reRun.clearNotificationsIfPresent();
        	//logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOnWorkCentertab();
        	logger.info("Clicked on Work center tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Ready for primary approval");
        	logger.info("Applied advance filters in WC tab for Accessorial, AC01, FL51 and Ready for primary approval status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for primary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info(" Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Rerun Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
             
            Assert.assertTrue(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), "The Processing Rerun Invoices in background popup should be displayed");
            Assert.assertEquals(reRun.getRerunSuccessPopupMessage(), "Processing Rerun Invoices in background. You will receive notification once processed.", 
            		 "The Processing Rerun Invoices in background message popup verified");
            logger.info("Successfully verified rerun invoice processing popup message");
            
            //Step 1: Wait for the Notification count
            reRun.waitForCountNotification();
            logger.info("Waited until count of notification appears on the bell icon Notifications");
            
            // Step 2: Open notifications
            reRun.clickOnBellIconToOpenNotifications();
            logger.info("Clicked on Bell Icon to open the Notifications");
            
            //Step 3: Validate notification contains invoice number
            String notifText = reRun.getLatestNotificationText();
            Assert.assertTrue(notifText.contains(invoiceNo), "❌ Notification does not contain expected invoice number!");

            // Step 4: Validate truncated notification
            String truncatedText = reRun.getTruncatedText(invoiceNo);
            logger.info("Truncated: " + truncatedText);
            System.out.println("Truncated: " + truncatedText);

            // Step 5: Click 'More' to get full text
            String fullText = reRun.getFullText(invoiceNo);
            logger.info(invoiceNo + "- Full Text: " + fullText);
            System.out.println(invoiceNo+ "Full Text: " + fullText);

            //Step 6: Validate notification timestamp
            String notifTime = reRun.getNotificationTime(invoiceNo);
            logger.info(invoiceNo+ "- Notification Time: " + notifTime);
            System.out.println("Notification Time: " + notifTime);
            
            // Step 7: Navigate to WorkCenter by clicking notification
            reRun.clickNotificationHeader(invoiceNo);
            logger.info("Clicked on Successfull notification text");
            
            // Validate row data on Work Center Page for the Rerun Invoice Number
            int rowCount = reRun.getRowCount();
            System.out.println("Rows on Work Center page: " + rowCount);
            Assert.assertTrue(rowCount > 0, "No rows found in table!");
            logger.info("✅ Rerun Invoice Number Work Center Table row verified successfully");
            
            // ✅ Validate Rerun Invoice row data
            List<String> rerRunInvoiceRow = reRun.getTableRowData(0);
            System.out.println("✅ Rerun Invoice number row data: " + rerRunInvoiceRow);
			
            reRun.clickOnActionRowButton();
            logger.info("clicked on action row button successfully"); 
            
            reRun.clickAction("Approve");
            logger.info("clicked on Approve action successfully"); 
            
            reRun.clickOnConfirmButton();
            logger.info("In confirmation popup clicked on Confirm button successfully"); 
            
            Assert.assertEquals(reRun.getRerunInvoicePrimaryApprovedSuccessPopupMessage(), "Invoice Primary approved. Current status: Ready for secondary approval", 
            		"Rerun Invoice status changed from Ready for primary approval to Ready for secondary approval successfully");
            
            // ✅ Validate first row data
            List<String> rerRunInvoicePrimaryApprovedNumber = reRun.getTableRowData(0);
            System.out.println("✅ Rerun Invoice Number approved status from Primary status to Secondary approved row data: " + rerRunInvoicePrimaryApprovedNumber);
            
            reRun.clearGlobalSearchField();
            logger.info("Cleared the global filter value"); 
            
            
            logger.info("Rerun Invoice with Method= Rerun and Invoice Status = Ready for Primary approval function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Method= Manual and Invoice Status = Ready for Primary approvaltest failed: " + e.getMessage());
        }
    
    }
   
    
     
    @Test(priority = 6)
    public void verifyReadyForSecondaryApprovalInvoiceRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyReadyForPrimaryApprovalInvoice Method= Manual and Status= Ready for secondary approval, WithInvoiceNumber***********");
        try {
        	
        	reRun.clearNotificationsIfPresent();
        	logger.info("Cleared the existing notifications if present otherwise Skipped this notificaiton");
        	
        	reRun.clickOnWorkCentertab();
        	logger.info("Clicked on Work center tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Ready for secondary approval");
        	logger.info("Applied advance filters in WC tab for Accessorial, AC01, FL51 and Ready for primary approval status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for secondary approval status for Accessorial type: " + invoiceNumber);
            
            //Capture the invoice number from input field after entering
            String invoiceNo = reRun.geteEnteredInvoiceNumber();
            logger.info(" Rerun Invoice Number used: " + invoiceNo);
            System.out.println("Rerun Invoice Number used: " + invoiceNo);
           
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Submit button");
             
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Rerun Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
             
            Assert.assertTrue(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), "The Processing Rerun Invoices in background popup should be displayed");
            Assert.assertEquals(reRun.getRerunSuccessPopupMessage(), "Processing Rerun Invoices in background. You will receive notification once processed.", 
            		 "The Processing Rerun Invoices in background message popup verified");
            logger.info("Successfully verified rerun invoice processing popup message");
            
            //Step 1: Wait for the Notification count
            reRun.waitForCountNotification();
            logger.info("Waited until count of notification appears on the bell icon Notifications");
            
            // Step 2: Open notifications
            reRun.clickOnBellIconToOpenNotifications();
            logger.info("Clicked on Bell Icon to open the Notifications");
            
            //Step 3: Validate notification contains invoice number
            String notifText = reRun.getLatestNotificationText();
            Assert.assertTrue(notifText.contains(invoiceNo), "❌ Notification does not contain expected invoice number!");

            // Step 4: Validate truncated notification
            String truncatedText = reRun.getTruncatedText(invoiceNo);
            logger.info("Truncated: " + truncatedText);
            System.out.println("Truncated: " + truncatedText);

            // Step 5: Click 'More' to get full text
            String fullText = reRun.getFullText(invoiceNo);
            logger.info(invoiceNo + "- Full Text: " + fullText);
            System.out.println(invoiceNo+ "Full Text: " + fullText);

            //Step 6: Validate notification timestamp
            String notifTime = reRun.getNotificationTime(invoiceNo);
            logger.info(invoiceNo+ "- Notification Time: " + notifTime);
            System.out.println("Notification Time: " + notifTime);
            
            // Step 7: Navigate to WorkCenter by clicking notification
            reRun.clickNotificationHeader(invoiceNo);
            logger.info("Clicked on Successfull notification text");
            
            // Validate row data on Work Center Page for the Rerun Invoice Number
            int rowCount = reRun.getRowCount();
            System.out.println("Rows on Work Center page: " + rowCount);
            Assert.assertTrue(rowCount > 0, "No rows found in table!");
            logger.info("✅ Rerun Invoice Number Work Center Table row verified successfully");
            
            // ✅ Validate Rerun Invoice row data
            List<String> rerRunInvoiceRow = reRun.getTableRowData(0);
            System.out.println("✅ Rerun Invoice number row data: " + rerRunInvoiceRow);
			
            // ✅ Validate first row data
            List<String> rerRunInvoiceSecondaryApprovedNumber = reRun.getTableRowData(0);
            System.out.println("✅ Rerun Invoice Number approved status from Primary status to Secondary approved row data: " + rerRunInvoiceSecondaryApprovedNumber);
            
            reRun.clearGlobalSearchField();
            logger.info("Cleared the global filter value"); 
            
            
            logger.info("Rerun Invoice with Method= Rerun and Invoice Status = Ready for Secondary approval function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Method= Manual and Invoice Status = Ready for Secondary approvaltest failed: " + e.getMessage());
        }
    
    }










  /*
    @Test(priority = )
    public void verifyReadyForPrimaryApprovalInvoiceMethodRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyReadyForPrimaryApprovalInvoice Method= Rerun and Status= Ready for primary approval, WithInvoiceNumber***********");
        try {
        	
        	
        	reRun.clickOnWorkCentertab();
        	logger.info("Clicked on Work center tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Ready for primary approval");
        	logger.info("Applied advance filters in WC tab for Accessorial, AC01, FL51 and Ready for primary approval status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for primary approval status for Accessorial type: " + invoiceNumber);
             
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Rerun Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
             
            Assert.assertTrue(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), 
                		"The Processing Rerun Invoices in background popup should be displayed");
             
            Assert.assertEquals(reRun.getRerunSuccessPopupMessage(), "Processing Rerun Invoices in background. You will receive notification once processed.", 
            		 "The Processing Rerun Invoices in background message popup verified");
             
            logger.info("Rerun Invoice with Method= Rerun and Invoice Status = Ready for primary approval function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Method= Rerun and Invoice Status = Ready for primary approval test failed: " + e.getMessage());
        }
    
    }

    
    @Test(priority = 7)
    public void verifyReadyForSecondaryApprovalInvoicewithMethodRerunWithInvoiceNumber() {
        logger.info("******* Starting verifyReadyForPrimaryApprovalInvoice Method= Rerun and Status= Ready for secondary approval, WithInvoiceNumber***********");
        try {
        	
        	reRun.clickOnWorkCentertab();
        	logger.info("Clicked on Work center tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Ready for secandary approval");
        	logger.info("Applied advance filters in WC tab for Accessorial, AC01, FL51 and Ready for primary approval status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for primary approval status for Accessorial type: " + invoiceNumber);
             
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Rerun Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
             
            Assert.assertTrue(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(),"The Processing Rerun Invoices in background popup should be displayed");
            Assert.assertEquals(reRun.getRerunSuccessPopupMessage(), "Processing Rerun Invoices in background. You will receive notification once processed.", 
            		 "The Processing Rerun Invoices in background message popup verified");
             
            logger.info("Rerun Invoice with Method= Rerun and Invoice Status = Ready for secondary approval function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Method= Rerun and Invoice Status = Ready for secondary approval function test failed: " + e.getMessage());
        }
    
    }

    @Test(priority = 7)
    public void verifyRerunEmailToCustomerInvoiceWithInvoiceNumber() {
        logger.info("******* Starting verifyRerunEmailToCustomerInvoiceWithInvoiceNumber***********");
        try {
        	
        	reRun.clickOninvoiceInquirytab();
        	logger.info("Clicked on Invoice Iquiry tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Emailed To Cusomter");
        	logger.info("Applied advance filters in WC tab for Accessorial, AC01, FL51 and Emailed To Cusomter status invocie");
        	
        	reRun.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(10000); 
            
            // 🔹 Capture invoice number from first row
            String invoiceNumber = reRun.captureGeneratedInvoiceNumber();
            logger.info("Captured Invoice Number: " + invoiceNumber);

            // Search by Invoice Number
            reRun.searchInvoice(invoiceNumber);
            String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
            logger.info("Searched Invoice Number: " + searchedInvoiceNum);

            Assert.assertEquals(searchedInvoiceNum, invoiceNumber, "❌ Invoice Number search mismatch!");
            logger.info("✅ Invoice Number search verified successfully");
             
            reRun.clickOnRerunInvoiceTab();
            logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
            reRun.enterInvoiceNumber(invoiceNumber);
            logger.info("Entered the Invoice number with Ready for primary approval status for Accessorial type: " + invoiceNumber);
             
            reRun.clickOnRerunSubmitButton();
            logger.info("Clicked on Rerun Submit button");
             
            reRun.clickOnConfiramtionPopupSubmitButton();
            logger.info("Clicked on Confirmation popup Submit button");
             
            Assert.assertTrue(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(),"The Processing Rerun Invoices in background popup should be displayed");
            Assert.assertEquals(reRun.getRerunSuccessPopupMessage(), "Processing Rerun Invoices in background. You will receive notification once processed.", 
            		 "The Processing Rerun Invoices in background message popup verified");
             
            logger.info("Rerun Invoice with Method= Rerun and Invoice Status = Ready for secondary approval function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Method= Rerun and Invoice Status = Ready for secondary approval function test failed: " + e.getMessage());
        }
    
    }
    
    
    
   /* 
    @Test(priority = 6)
    public void verifyRerunInvoiceInboundTypeByDuration() {
        logger.info("******* Starting verifyRerunInvoiceInboundTypeByDuration***********");
        try {
        	
        	
        	// Select and assert Inbound
		   	reRun.selectCheckbox("Inbound");
		    Assert.assertTrue(reRun.isCheckboxSelected("Inbound"), "Checkbox Inbound should be selected");
        	
            Assert.assertEquals(reRun.validateReruninvoiceHeaderText(), "Re-Run Invoices", "Rerun Invoices header text not diplayed");
            logger.info("Validated Rerun Invoices header text on the tab");
            
            reRun.selectFromDate("August 2025", "25");
		    logger.info("From date  August/2025/25 selected successfully.");	 
		    
		    reRun.selectToDate("September 2025", "11");
		    logger.info("To date  September/2025/11 selected successfully.");
		   
		    Assert.assertTrue(reRun.validateRerunSubmitButtonDisabled(), "Checkbox Inbound should be selected");
		    
		    reRun.rerunInvoiceAdvanceFilters("AC01", "FL51");
		    
		    Assert.assertTrue(reRun.validateRerunSubmitButtonEnabled(), "Checkbox Inbound should be selected");
        	
		    reRun.clickOnRerunSubmitButton();
		    logger.info("Click on Submit button successfully");
		    
		    
		    Assert.assertEquals(reRun.validateConfimationTextOnPopup(), "Confirm Change","On popup Confirm change text should be displayed");
		    Assert.assertEquals(reRun.validatedescriptionOfConfimationTextOnPopup(), "Are you sure you want to submit the changes ?","on popup Description of Confirm change text should be displayed");
		    reRun.clickOnConfiramtionPopupCancelButton();
		    logger.info("Click on confimation popup Submit button successfully");
		    
		    reRun.clickOnConfiramtionPopupSubmitButton();
		    Assert.assertEquals(reRun.validateConfimationTextOnPopup(), "Confirm Change","On popup Confirm change text should be displayed");
		    Assert.assertEquals(reRun.validatedescriptionOfConfimationTextOnPopup(), "Are you sure you want to submit the changes ?","on popup Description of Confirm change text should be displayed");
		    logger.info("Click on confimation cancel button successfully");
		    
		    
            logger.info("Rerun Invoice for Inbound type by duration verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoice for Inbound Type By Duration test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 6)
    public void validateRerunWithInvoiceNumber() {
        logger.info("******* Starting validateRerunInvoiceFunction***********");
        try {
        	
        	
        	
        	reRun.selectOption("Invoice");
        	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
        	logger.info("Selected By Invoice Number radio Button");
        	
        	
        	Assert.assertEquals(reRun.validateDescriptionButtonText(), "Run an individual invoice: Make sure the invoice has not gone through secondary approval and that is not a void status. If customer settings were changed to create 1 invoice per PO or the opposite make sure the original invoice(s) are voided first.", 
        			"Description of Invoice Number radio button should be displayed");
        	logger.info("Discription of Invoice Number text verified sucessfully");
        	
        	Assert.assertEquals(reRun.validateInvoiceNumberText(),"Invoice Number", "Invoice number text should be displayed");
        	
        	reRun.clickOnWorkCentertab();
        	logger.info("Clicked on Work center tab sucessfully");
        	
        	reRun.invoiceInquiryAdvanceFilters("Accessorial", "AC01", "FL50", "Ready for primary approval");
        	logger.info("Applied advance filters in WC tab for Accessorial, AC01, FL51 and Ready for primary approval status invocie");
        	
        	 reRun.clickOnApplyButton();
             logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
             
             
             
             logger.info("===== Starting Invoice Inquiry Search Validation Test =====");

             // 🔹 Capture invoice number from first row
             String invoiceNum = reRun.captureGeneratedInvoiceNumber();
             logger.info("Captured Invoice Number: " + invoiceNum);

             // Search by Invoice Number
             reRun.searchInvoice(invoiceNum);
             String searchedInvoiceNum = reRun.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Number: " + searchedInvoiceNum);

             Assert.assertEquals(searchedInvoiceNum, invoiceNum, "❌ Invoice Number search mismatch!");
             logger.info("✅ Invoice Number search verified successfully");
             
             reRun.clickOnRerunInvoiceTab();
             logger.info("Clicked on Rerun Invoice Tab successfully");
             
            reRun.selectOption("Invoice");
         	Assert.assertTrue(reRun.isInvoiceSelected(), "Invoice Number should be selected");
         	logger.info("Selected By Invoice Number radio Button");
             
             reRun.enterInvoiceNumber(invoiceNum);
             logger.info("Entered the Invoice number with Ready for primary approval status for Accessorial type: " + invoiceNum);
             
             reRun.clickOnRerunSubmitButton();
             logger.info("Clicked on Rerun Submit button");
             
             reRun.clickOnConfiramtionPopupSubmitButton();
             logger.info("Clicked on Confirmation popup Submit button");
             
             Assert.assertTrue(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), 
                		"The Processing Rerun Invoices in background popup should be displayed");
             
             Assert.assertEquals(reRun.validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), "Processing Rerun Invoices in background. You will receive notification once processed.", 
            		 "The Processing Rerun Invoices in background message popup verified");
             
             // Case A: If rerun is NOT allowed → popup must appear
             try {
                 String popupMsg = reRun.getErrorPopupMessage();
                 Assert.assertTrue(
                     popupMsg.contains("Invoice number '" + invoiceNum + "'"),
                     "Popup did not contain the correct invoice number!"
                 );
                 System.out.println("❌ Rerun not allowed. Popup validated: " + popupMsg);

             } catch (Exception e) {
                 // Case B: If no popup, assume rerun succeeded
                 System.out.println("✅ Rerun successful for Invoice No: " + invoiceNum);
                 // Optional: Add assertion for rerun success message here
             }
        	
            logger.info("Rerun Invoices function verified successfully");
        } catch (Exception e) {
            Assert.fail("Rerun Invoices Function test failed: " + e.getMessage());
        }
    }
    
    */
    
}
