package testCases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.GeneralInvoice;
import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.LoginUtility;

public class TC003_GeneralInvoiceTest extends BaseClass {

    GeneralInvoice genInv;
    ExcelUtility excel;
    LoginUtility loginUtil;

    @BeforeClass
    public void setupPageObjects() throws AWTException {
        genInv = new GeneralInvoice(BaseClass.driver);
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
    public void verifyGeneralInvoiceTab() {
        logger.info("******* Starting verifyGeneralInvoiceTab ***********");
        try {
        	
        	Thread.sleep(10000);
        	
        	
            genInv.clickOnGeneralInvoiceTab();
            
            Assert.assertEquals(genInv.validateGeneralInvoiceHeader(), "General Invoice",
                    "General Invoice header not displayed");
            
            
            logger.info("General Invoice tab verified successfully");
        } catch (Exception e) {
            Assert.fail("General Invoice tab test failed: " + e.getMessage());
        }
    }
    
   
    
    @Test(priority = 3)
    public void verifyGeneralInvoiceTabFields() {
        logger.info("******* Starting verifyGeneralInvoiceTabFields ***********");
        try {
        	    
        	Assert.assertTrue(genInv.locationFieldEnabled(), "lcoation field should be enabled");
        	Assert.assertTrue(genInv.categoryFieldEnabeld(), "Category field should be enabled");
        	Assert.assertTrue(genInv.dateFieldEnabled(), "Date field should be enabled");
        	Assert.assertTrue(genInv.rateByFieldEnabeld(), "RateBy field should be enabled");
        	Assert.assertTrue(genInv.quantityFieldEnabled(), "Quantityfield should be enabled");
        	Assert.assertTrue(genInv.rateFieldEnabled(), "Rate field should be enabled");
        	
        	Assert.assertTrue(genInv.customerFieldDisabled(), "Customer field should be disabled");
        	Assert.assertTrue(genInv.subCategoryFieldDisabled(), "Sub Category field should be disabled ");
        	Assert.assertTrue(genInv.uomFieldDisabled(), "UOM field should be disabled");
        	Assert.assertTrue(genInv.chargeFieldDisabled(), "Charge field should be Disabled");
        	Assert.assertTrue(genInv.commentFieldEnabled(), "Charge field should be Disabled");
        	
            
            logger.info("General Invoice tab fields are verified successfully");
        } catch (Exception e) {
            Assert.fail("General Invoice tab test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 4)
    public void verifyAddingSingleGeneralInvoiceLineItemCreation() {
        logger.info("******* Starting verifyAddingSingleGeneralInvoiceLineItemCreation ***********");
        try {
        	
        	Assert.assertTrue(genInv.validateAddLineItemButtonDisabled(), "Add line Item button should be Disabled");
        	Assert.assertTrue(genInv.validateSaveInvoiceHeaderButtonDisabled(), "Save Invoice Header button should be disabled");
        	
        	
        	logger.info("******* Validated Add line Item and Save Invoice Header button is enabled ***********");
        	
            genInv.fillInvoiceForm("AC04", "NA32", "Operations", "Disposal", "Box", "11", "11",
                    "Included 1st line");
            
            genInv.clickOnDatePickerContainer();
            genInv.selectDateFromGenInv("September 2025", "30");
            
            Assert.assertTrue(genInv.validateAddLineItemButtonEnabled(), "Add line Item button should be enabled");
            Assert.assertTrue(genInv.validateSaveInvoiceHeaderButtonDisabled(), "Save Invoice Header button should be disabled");
            Assert.assertTrue(genInv.validateClearButtonEnabled(), "Clear button should be enabled");
            
            genInv.clickOnClearButton();
          
            Assert.assertTrue(genInv.validateConfirmationPopupDisplayed(), "Clear Confirmation Popup should be enabled");
            
            Assert.assertEquals(genInv.validateConfirmClearFormHeaderTextOnClearPopup(),
               		"Confirm Clear Form", "Confirm Clear Form - popup message validation failed!");
            Assert.assertEquals(genInv.validateConfirmClearFormDescriptionTextOnClearPopup(),
               		"Are you sure you want to clear form?", "Are you sure you want to clear form? - popup message validation failed!");
            
            Assert.assertTrue(genInv.validateCanclePopupButtonDisplayed(), "Cancle Popup button should be enabled");
            Assert.assertTrue(genInv.validateClearPopupButtonEnabled(), "Clear popup button should be enabled");
            
            genInv.clickOnClearPopupButton();
            logger.info("Clicked on Clear button and all fields are cleared successfully");
            
            
            logger.info("Again creating Manual Invoice using different data input");
            genInv.fillInvoiceForm("AC11", "CE70", "Accounting", "Deposit", "Line", "111", "11",
                    "Included 1st line");
            genInv.clickOnDatePickerContainer();
            genInv.selectDateFromGenInv("September 2025", "30");
            
            genInv.clickOnAddLineItem();
            
            Assert.assertEquals(genInv.validateConfirmInvoiceLineItemSavePopupText(),
               		"Confirm Invoice Line Item Save", "Confirm Invoice Line Item Save - popup message validation failed!");
            Assert.assertEquals(genInv.validateConfirmInvoiceLineItemSaveDescriptionPopupText(),
               		"Are you sure you want to save the invoice line item with new line item?",
               		"Are you sure you want to save the invoice line item with new line item? - popup message validation failed!");
            
            genInv.clickOnSaveButton();
            
            Assert.assertTrue(genInv.validateAddLineItemButtonDisabled(), "Add line Item button should be Disabled");
            Assert.assertTrue(genInv.validateSaveInvoiceHeaderButtonEnabled(), "Save Invoice Header button should be enabled");
			 
            logger.info("Manual invoice creation verified successfully");
            
        } catch (Exception e) {
            Assert.fail("Single General invoice line item test failed: " + e.getMessage());
        }
        
    }
    
  
    @Test(priority = 5)
    public void verifyEditAndDeleteCursorAction() {
        logger.info("******* Starting verifyEditAndDeleteCusrsorsActionForLineItem ***********");
        
        try {
        	
        	
        	Thread.sleep(3000);
        	
        	 Assert.assertTrue(genInv.validateEditCursorPointerEnabled(), "Edit Cursor pointer should be Disabled");
        	 logger.info("Edit cursor Pointer Enabled");
            
        	 Assert.assertTrue(genInv.validateDeleteCursorPointerEnabled(), "Delete Cursor pointer should be Disabled");
        	 genInv.validateDeleteCursorPointerEnabled();
        	 logger.info("Delete cursor Pointer Enabled ");
            
        	 
            genInv.clickOnEditCursorPointer();
            logger.info(" clicked on Edit cursor Pointer ");
            Thread.sleep(3000);
            
            Assert.assertTrue(genInv.validateUpdateLineButtonDisplayed(), "Update line button should be Disabled");
            logger.info(" Update Line Item Button displyed");
            
            genInv.clickOnUpdateLineItemButton();
            logger.info(" clicked on Update Line Item Button ");
            Thread.sleep(3000);
            
            Assert.assertTrue(genInv.validateUpdatePopupButtonEnabled(), "Update popup button should be Disabled");
            
            genInv.clickOnUdpatePopButton();
            
            genInv.clickOnDeleteCursorPointer();
            logger.info(" clicked on Delete Cursor Pointer ");
            Thread.sleep(2000);
            
            Assert.assertTrue(genInv.validateConfirmationPopupDisplayed(), "Confirmation popup should be Disabled");
           
            genInv.clickOnWarningPopupCancelButton();
            
            System.out.println("Verified all actions Delete and Edit Cursor Pointer");
            
            Assert.assertTrue(genInv.validateAddLineItemButtonDisabled(), "Add line Item button should be Disabled");
            Assert.assertTrue(genInv.validateSaveInvoiceHeaderButtonEnabled(), "Save Invoice Header button should be enabled");
            genInv.clickOnCancelButton();
            
            logger.info("Edit and delete cursors for an line item verified successfully");
            
        } catch (Exception e) {
            Assert.fail("Edit/Delete cursor action on Line Item test failed: " + e.getMessage());
        }
        
        
    }
    
   
    @Test (priority = 6)
	public void validateDataAndRowForGeneralInvoiceSingleLineItem() {
		logger.info("******** Starting validateGeneralManualInvoiceRow *******");
		
		try {
			
			 // ✅ Validate row count
    	    int rows = genInv.getRowCount();
    	    Assert.assertEquals(rows, 1, " Expected 1 row after adding manually!");
    	    
    	    // Expected values (from manual input)
    	    String expCategory = "Accounting";
    	    String expSubCategory = "Deposit";
    	    String expUOM = "LIN";
    	    String expQty = "111.00";
    	    String expRate = "$11.00";
    	    String expCharge = "$1,221.00";

    	    genInv.printAndValidateRowData(expCategory, expSubCategory, expUOM, expQty, expRate, expCharge);

    	    logger.info("General Invoice single line table row verified successfully");
		 } catch (Exception e) {
            Assert.fail("Single line General Invoice validation test failed: " + e.getMessage());
        }
	}
    
   
    
    @Test (priority = 7)
   	public void validateCreationGeneralInvoice() {
   		logger.info("******** Starting validateCreationGeneralInvoice *******");
   		
   		try {
   			
   			genInv.clickOnSaveInvoiceHeader();
   			Thread.sleep(3000);
   			
   			Assert.assertTrue(genInv.validateConfirmationPopupDisplayed(), "Confirmation popup should be Displayed");
   			
   			Assert.assertEquals(genInv.validateConfirmInvoiceHeaderTextOnConfirmPopup(),
               		"Confirm Invoice Header Save", "Confirm Invoice Header Save - popup message validation failed!");
   			
   			Assert.assertEquals(genInv.validateConfirmInvoiceHeaderDescriptionTextOnConfirmationPopup(),
               		"Are you sure you want to save the invoice header with new line items?",
               		"Are you sure you want to save the invoice header with new line items? - popup message validation failed!");
            
   			Assert.assertTrue(genInv.validateSaveButtonDisplayed(), "Save button Popup should be Displayed");
   			Assert.assertTrue(genInv.validateCancelButtonDisplayed(), "Cancel button Popup should be Displayed");
   			Thread.sleep(3000);
   			
   			genInv.clickOnSaveButton();
   			
   			
   			Assert.assertTrue(genInv.validateTheInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), 
               		"The invoice has been created successfully popup should be displayed");
   			
   			Assert.assertEquals(genInv.validateTheInvoiceHasBeenCreatedsuccessfullyPopupText(),
               		"The invoice has been created successfully.", "The invoice has been created successfully - popup message validation failed!");
   				
   			
       	    logger.info("General Invoice has been created successfully");
   		 } catch (Exception e) {
               Assert.fail("General Invoice validation test failed: " + e.getMessage());
           }
   	}
    
   
    
    @Test (priority = 8)
	public void validateAfterDeletingSingleLineItemNoRecordsAvaialable() {
		logger.info("******** Starting validateAfterDeletingSingleLineItemNoRecordsAvaialable *******");
		
		try {
			
			genInv.clickOnGeneralInvoiceTab();
        	
            genInv.fillInvoiceForm("AC11", "CE70", "Accounting", "Deposit", "Line", "111", "11",
                    "Included 1st line");
            genInv.clickOnDatePickerContainer();
            genInv.selectDateFromGenInv("September 2025", "30");
            Thread.sleep(3000);
            
            genInv.clickOnAddLineItem();
            Thread.sleep(3000);
            genInv.clickOnSaveButton();
			
			genInv.clickOnDeleteCursorPointer();
            logger.info("******* clicked on Delete Cursor Pointer ***********");
            Thread.sleep(3000);
            
            Assert.assertTrue(genInv.validateConfirmationPopupDisplayed(), 
               		"The Confimation popup should be displayed");
            
            
            genInv.clickOnWarningPopupDeleteButton();
            
            Assert.assertEquals(genInv.validateNoRecordsAvailableAfterDeletingLineItems(),
            		"No Records Available", "No Records Available validation failed!");
			

    	    logger.info("Deleting single line item in General Invoice table row verified successfully");
		 } catch (Exception e) {
            Assert.fail("Deleting single line item in General Invoice table row validation test failed: " + e.getMessage());
        }
	}
    
    
	@Test (priority = 9)
	public void validateSingleLineItemDeletedSuccessfullyForGeneralInvoice() {
		logger.info("******** Starting validateGeneralInvoiceDataForDeletedSingleLineItemInvoice *******");
		
		try {
    	    Assert.assertTrue(genInv.validateAllItemsClearedSuccessfullyDisplayed(), 
            		"All Items cleared successfully popup should be displayed");
            logger.info("******* All Items cleared successfully popup displayed ***********");
            
            Assert.assertEquals(genInv.validateAllitemsclearedsuccessfullyPopupText(),
            		"All items cleared successfully", "All items cleared successfully - popup message validation failed!");
            
            genInv.clickOnGeneralInvoiceTab();
            logger.info("General Invoice clicked on General Invoice Tab");
            
            // ✅ Validate row count
    	    int rows = genInv.getRowCount();
    	    Assert.assertEquals(rows, 0, "❌ Expected 0 row after adding manually!");

    	    logger.info("General Invoice delete single line item table row verified successfully");
		 } catch (Exception e) {
            Assert.fail("General Invoice delete single line table row validation test failed: " + e.getMessage());
        }
	}

	/*
	
	@Test(priority = 10)
    public void verifyInvoiceDeitalsDataFetchingFromExcel() {
        logger.info("******* Starting verifyInvoiceDetailsDataFetchingFromExcel ***********");
        try {
        	
        	genInv.clickOnDatePickerContainer();
            genInv.selectDateFromGenInv("September 2025", "30");
            logger.info("Selected date");
            
            String filePath = System.getProperty("user.dir") + "/testDataE2/GenInvoiceData.xlsx";
            ExcelUtility excelUtil = new ExcelUtility(filePath);
            int totalRows = excelUtil.getRowCount("Sheet1");

            for (int i = 1; i <= totalRows; i++) {
                String location = excelUtil.getCellData("Sheet1", i, 0);
                String customer = excelUtil.getCellData("Sheet1", i, 1);
                String category = excelUtil.getCellData("Sheet1", i, 2);
                String subCategory = excelUtil.getCellData("Sheet1", i, 3);
                String rateBy = excelUtil.getCellData("Sheet1", i, 4);
                String quantity = excelUtil.getCellData("Sheet1", i, 5);
                String rate = excelUtil.getCellData("Sheet1", i, 6);
                String comment = excelUtil.getCellData("Sheet1", i, 7);

                genInv.fillInvoiceForm(location, customer, category, subCategory, rateBy, quantity, rate, comment);
                genInv.clickOnAddLineItem();
                genInv.clickOnSaveButton();
                logger.info("Invoice created for row: " + i + " with Customer: " + customer);
            }
            logger.info("Invoice Deitals Data aFetching From Excel Successfully verified");
            
            
        } catch (Exception e) {
            Assert.fail("Invoice Deitals Data aFetching From Excel test failed: " + e.getMessage());
        }
    }
	
	
	@Test(priority = 11)
	public void validateInvoiceTableRowsAgainstExcel() throws Exception {
		logger.info("******** Starting validateInvoiceTableRowsAgainstExcel *******");
		
	    try {
	    	
	    	// Count rows from UI table
		    int uiRowCount = genInv.getTableRowCount();
		
		    // Count rows from Excel
		    String filePath = System.getProperty("user.dir") + "/testData/NewGenInv.xlsx";
		    ExcelUtility excelUtil = new ExcelUtility(filePath);
		    int excelRowCount = excelUtil.getRowCount("Sheet1");
		
		    System.out.println("UI Table Rows: " + uiRowCount);
		    System.out.println("Excel Rows: " + excelRowCount);
		
		    // Assert
		    Assert.assertEquals(uiRowCount, excelRowCount,  "Mismatch between UI Table rows and Excel rows!");
		  
		    logger.info("Invoice Table Rows Against Excel rows verified successfully");
	    }catch (Exception e) {
            Assert.fail("Invoice Table Rows Against Excel rows test failed: " + e.getMessage());
        }
	    
	}
	
	
	@Test (priority = 12)
	public void validateGeneralInvoiceCreationFromExcelData() {
		logger.info("******** Starting validateGeneralInvoiceCreationFromExcelData *******");
		
		try {
			
            genInv.clickOnSaveInvoiceHeader();

            Assert.assertTrue(genInv.validateSaveButtonDisplayed(), "Save button should be displayed");
            Assert.assertTrue(genInv.validateCancelButtonDisplayed(), "Cancel button should be displayed");

            genInv.clickOnSaveButton();
            

            logger.info("Save invoice header and General Invoice got created from Excel data - verified successfully");
		 } catch (Exception e) {
            Assert.fail("Save invoice header and General Invoice got created from Excel data validation test failed: " + e.getMessage());
        }
	}

	
	/*
	 @Test (priority = 12)
	   	public void validateCreationGeneralInvoiceFromExcel() {
	   		logger.info("******** Starting validateCreationGeneralInvoice *******");
	   		
	   		try {
	   			
	   			genInv.clickOnSaveInvoiceHeader();
	   			
	   			Assert.assertTrue(genInv.validateConfirmationPopupDisplayed(), "Confiamtion Popup should be displayed");
	   			Assert.assertEquals(genInv.validateConfirmInvoiceHeaderTextOnConfirmPopup(), 
	   					"Confirm Invoice Header Save","Confirm Invoice Header Save - popup message validation failed!" );
	   			
	   			Assert.assertEquals(genInv.validateConfirmInvoiceHeaderTextOnConfirmPopup(), 
	   					"Are you sure you want to save the invoice header with new line items?","Are you sure you want to save the invoice header with new line items? - popup message validation failed!" );
	   			
	   			Assert.assertTrue(genInv.validateSaveButtonDisplayed(), "Confiamtion Save button should be displayed");
	   			Assert.assertTrue(genInv.validateCancelButtonDisplayed(), "Confiamtion Cancel button should be displayed");
	   			
	   			
	   			genInv.clickOnSaveButton();
	   			Thread.sleep(3000);
	   			
	   			Assert.assertTrue(genInv.validateTheInvoiceHasBeenCreatedsuccessfullyPopupDisplayed(), 
	               		"The invoice has been created successfully popup should be displayed");
	   			
	   			Assert.assertEquals(genInv.validateTheInvoiceHasBeenCreatedsuccessfullyPopupText(),
	               		"The invoice has been created successfully.", "The invoice has been created successfully - popup message validation failed!");
	   				
	   			
	       	    logger.info("General Invoice has been created successfully");
	   		 } catch (Exception e) {
	               Assert.fail("General Invoice validation test failed: " + e.getMessage());
	           }
	   	}
	
	 
	 /* 
	
    
    @Test(priority = 13)
    public void verifyCreationOfSingleLinelInvoice() {
        logger.info("******* Starting verifySaveInvoice ***********");
        try {
        	
        	 genInv.scrollToDeleteCursorButton();
             logger.info("Scrolled to  Manual invoice included Line Item");
             Thread.sleep(5000);

             Assert.assertTrue(genInv.validateDeleteCursorPointerEnabled(), "Delete button should be enabled");
             Assert.assertTrue(genInv.validateEditCursorPointerEnabled(), "Edit cursor pointer should be enabled");
             
             genInv.clickOnEditCursorPointer();
        
             genInv.validateWarningIconOnConfirmationPopupDisplayed();
             logger.info("*******Warnining icon displyaed ***********");
            
            Assert.assertTrue(genInv.validateWarningIconOnConfirmationPopupDisplayed(),
                    "Warning Icon on Confirmation popup should be displayed");
            
           
            Assert.assertEquals(genInv.validateConfirmDeleteWarningTextOnConfirmationPopup(),
            		"Confirm Delete", "Warning text on confirmation popup is incorrect!");
            
            Assert.assertEquals(genInv.validateConfirmDeleteDescriptionWarningTextOnConfirmationPopup(),
                    "Are you sure you want to delete this item?", "Description text on confirmation popup is incorrect!");
            
            Assert.assertTrue( genInv.validateCanclePopupButtonDisplayed(),
                    "Warning popup Cancel button should be displayed");
            
            Assert.assertTrue( genInv.validateDeletePopupButtonDisplayed(),
            		"Warning popup Delete button should be displayed");
            
            genInv.clickOnWarningPopupCancelButton();
            logger.info("******* clicked on Delete warning popup button ***********");
            Thread.sleep(3000);
            
            genInv.clickOnDeleteCursorPointer();
            Thread.sleep(3000);
            
            genInv.validateWarningIconOnConfirmationPopupDisplayed();
            logger.info("*******Warnining icon displyaed ***********");
            
            genInv.clickOnWarningPopupDeleteButton();
            logger.info("******* clicked on Delete warning popup button ***********");
            
            
            Assert.assertTrue(genInv.validateAllitemsclearedsuccessfullyDisplayed(), 
            		"All Items cleared successfully popup should be displayed");
            logger.info("******* All Items cleared successfully popup displayed ***********");
            
            Assert.assertEquals(genInv.validateAllitemsclearedsuccessfullyPopupText(),
            		"All items cleared successfully", "Delete popup message validation failed!");
            
            Assert.assertEquals(genInv.validateNoRecordsAvailableAfterDeletingLineItems(),
            		"No Records Available", "No Records Available validation failed!");
            
            logger.info("********* All Items cleared successfully popup verified ********");
             
            
            logger.info("Save invoice verified successfully");
        } catch (Exception e) {
            Assert.fail("Save Invoice test failed: " + e.getMessage());
        }
    }
    
    
	
     */ 
    
    	

   
  
    
}
