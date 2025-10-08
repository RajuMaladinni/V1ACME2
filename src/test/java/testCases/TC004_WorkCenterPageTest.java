package testCases;

import java.awt.AWTException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.InvoiceInquiryPage;
import pageObjects.WorkCenter;
import testBase.BaseClass;
import utilities.LoginUtility;

public class TC004_WorkCenterPageTest extends BaseClass{
	
    WorkCenter workCenter;
    InvoiceInquiryPage invoiceInquiry;
    LoginUtility loginUtil;

    @BeforeClass
    public void setupPageObjects() throws AWTException {
    	
        workCenter = new WorkCenter(BaseClass.driver);
        invoiceInquiry = new InvoiceInquiryPage(BaseClass.driver);
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
    public void verifyWorkCenterTab() {
        logger.info("******* Starting verifyWorkCenterTab ***********");
        try {
        	
        	Thread.sleep(10000);
        	
        	
        	workCenter.clickOnWorkCenterTab();
        	logger.info("Clicked on Work Center Tab");
        	
            Assert.assertEquals(workCenter.validateWorkCenterHeaderText(), "Work Center", "Work Center header text not diplayed");
            Assert.assertEquals(workCenter.validateWorkCenterListText(), "Work Center List", "Work Center List text not diplayed");
            logger.info("Validated Invoices Inquiry header and List text on the tab");
            
         
            
            logger.info("Work Center tab verified successfully");
        } catch (Exception e) {
            Assert.fail("Work Center tab test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 3)
    public void verifyInvoiceInquiryTabCollapseSectionToShowOrHideAdvanceFilters() {
        logger.info("******* Starting verifyInvoiceInquiryTabCollapseSectionToShowOrHideAdvanceFilters ***********");
        try {
        	
        	//by default collapse section shown
        	
        	logger.info("By default Collapse section in expanded state");
        	
        	invoiceInquiry.expandCollapseSection();
        	
        	Assert.assertTrue(invoiceInquiry.validateDatePickerContainerisEnabled(),  "Date Picker Container should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqLocationFilterEnabled(),  "Location Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqCustomerFilterEnabled(),  "Customer Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqStautsFilterEnabled(),  "Status Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqTypeFilterEnabled(),  "Type Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqRateByFilterEnabled(),  "Rate By Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateWcInvoiceChargeFilterEnabled(),  "Invoice Charge Advance Filter should be enabled");
        	logger.info("By default Collapse section expanded and shows the Advance filters");
        	
        	//Click on collapse button to hide and validate all advance filters hidden
        	invoiceInquiry.hideCollapseSection();
        	logger.info("Clicked on Collapse section to hide all Advance filters");
        	Thread.sleep(3000);
        	
        	Assert.assertTrue(invoiceInquiry.validateInvIqLocationFilterHidden(),  "Location Advance Filter should be Hidden");
        	Assert.assertTrue(invoiceInquiry.validateInvIqCustomerFilterHidden(),  "Customer Advance Filter should be Hidden");
        	Assert.assertTrue(invoiceInquiry.validateInvIqStautsFilterHidden(),  "Status Advance Filter should be Hidden");
        	Assert.assertTrue(invoiceInquiry.validateInvIqTypeFilterHidden(),  "Type Advance Filter should be Hidden");
        	Assert.assertTrue(invoiceInquiry.validateInvIqRateByFilterHidden(),  "Rate By Advance Filter should be Hidden");
        	Assert.assertTrue(invoiceInquiry.validateInvIqInvoiceChargeFilterHidden(),  "Invoice Charge Advance Filter should be Hidden");
        	Assert.assertTrue(invoiceInquiry.validateDatePickerContainerisHidden(),  "Date Picker Container should be Hidden");
        	logger.info("Advance filters are Hidden");
        	
        	//Again Click on collapse button to Show and validate all advance filters shown.
        	invoiceInquiry.expandCollapseSection();
        	logger.info("Clicked on Collapse section to show all Advance filters");
        	Thread.sleep(3000);
        	
        	Assert.assertTrue(invoiceInquiry.validateDatePickerContainerisEnabled(),  "Date Picker Container should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqLocationFilterEnabled(),  "Location Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqCustomerFilterEnabled(),  "Customer Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqStautsFilterEnabled(),  "Status Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqTypeFilterEnabled(),  "Type Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateInvIqRateByFilterEnabled(),  "Rate By Advance Filter should be enabled");
        	Assert.assertTrue(invoiceInquiry.validateWcInvoiceChargeFilterEnabled(),  "Invoice Charge Advance Filter should be enabled");
        	logger.info("Advance filters are Shown");
        	
        	
        	Assert.assertTrue(invoiceInquiry.validateInvInqClearButtonDisabled(), "Clear button should be Disabled");
        	Assert.assertTrue(invoiceInquiry.validateInvInqApplyButtonDisabled(), "Apply button should be Disabled");
        	logger.info("Apply and Clear buttons are Disabled");
        	
            logger.info("Invoice Inquiry tabs advance filters fields verified successfully");
        } catch (Exception e) {
            Assert.fail("Invoice Inquiry tab advance filters fields test failed: " + e.getMessage());
        }
    }
    
    
    
    @Test(priority = 4)
    public void verifyInvoicesInquiryGlobalSearchByDifferentFields() {
        logger.info("******* Starting verifyInvoicesInquiryGlobalSearchByDifferentFields***********");
        try {
        	
        	
        	 logger.info("===== Starting Invoice Inquiry Search Validation Test =====");

             // 🔹 Capture invoice number from first row
             String invoiceNum = invoiceInquiry.captureGeneratedInvoiceNumber();
             logger.info("Captured Invoice Number: " + invoiceNum);

             // Search by Invoice Number
             invoiceInquiry.searchInvoice(invoiceNum);
             String searchedInvoiceNum = invoiceInquiry.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Number: " + searchedInvoiceNum);

             Assert.assertEquals(searchedInvoiceNum, invoiceNum, "❌ Invoice Number search mismatch!");
             logger.info("✅ Invoice Number search verified successfully");

             // 🔹 Capture invoice type from first row
             String invoiceType = invoiceInquiry.captureGeneratedInvoiceType();
             logger.info("Captured Invoice Type: " + invoiceType);

             // Search by Invoice Type
             invoiceInquiry.searchInvoiceType(invoiceType);
             String searchedInvoiceType = invoiceInquiry.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Type: " + searchedInvoiceType);
             
             Assert.assertEquals(searchedInvoiceType, invoiceType, "❌ Invoice Type search mismatch!");
             logger.info("✅ Invoice Type search verified successfully");

             // 🔹 Capture invoice location from first row
             String invoiceLocation = invoiceInquiry.captureGeneratedInvoiceLocationName();
             logger.info("Captured Invoice Location: " + invoiceLocation);

             // Search by Invoice Location
             invoiceInquiry.searchInvoiceLocation(invoiceLocation);
             String searchedInvoiceLocation = invoiceInquiry.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Location: " + searchedInvoiceLocation);

             Assert.assertEquals(searchedInvoiceLocation, invoiceLocation, "❌ Invoice Location search mismatch!");
             logger.info("✅ Invoice Location search verified successfully");
             
             // 🔹 Capture invoice customer name from first row
             String invoiceCustomer = invoiceInquiry.captureGeneratedInvoiceCustomerName();
             logger.info("Captured Invoice Customer: " + invoiceCustomer);

             // Search by Invoice Customer name
             invoiceInquiry.searchInvoiceCustomer(invoiceCustomer);
             String searchedInvoiceCustomer = invoiceInquiry.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Customer: " + searchedInvoiceCustomer);

             Assert.assertEquals(searchedInvoiceCustomer, invoiceCustomer, "❌ Invoice Customer search mismatch!");
             logger.info("✅ Invoice Customer search verified successfully");

             
             // 🔹 Capture invoice Total Amount from first row
             String invoiceTotalAmount = invoiceInquiry.captureGeneratedInvoiceTotalAmount();
             logger.info("Captured Invoice Total Amount: " + invoiceTotalAmount);

             // Search by Invoice Customer
             invoiceInquiry.searchInvoiceTotalAmount(invoiceTotalAmount);
             String searchedInvoiceTotalAmount = invoiceInquiry.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Total Amount: " + searchedInvoiceTotalAmount);

             Assert.assertEquals(searchedInvoiceTotalAmount, invoiceTotalAmount, "❌ Invoice Total Amount search mismatch!");
             logger.info("✅ Invoice Total Amount search verified successfully");
             
             invoiceInquiry.clearGlobalSeacrhField();
             logger.info("===== Invoice Inquiry Global Search field cleared Successfully =====");
             
             logger.info("===== Invoice Inquiry Global Search Test Completed by Invoice, Type, Location, Customer and Amount Successfully =====");
             
        } catch (Exception e) {
            Assert.fail("Invoices Inquiry Global Search test failed: " + e.getMessage());
        }
    }
    
    
    
    @Test(priority = 5)
    public void verifyInvoicesInquiryAdvanceFiltes() {
        logger.info("******* Starting verifyInvoicesInquiryAdvanceFiltes***********");
        try {
        	
        	
        	//Advance filters values enters and Apply & Clear enabled
        	invoiceInquiry.invoiceInquiryAdvanceFilters("Inbound", "HL57", "CO14", "Ready for primary approval", "Pallet", "Acc.StretchWrap Processing");
        	invoiceInquiry.selectInvoiceDateRange("September 2025", "1", "September 2025", "25");
        	logger.info("Selected date range - 01/09/2024 to 25/09/2024");
        	
        	Assert.assertTrue(invoiceInquiry.validateInvInqClearButtonEnabled(),  "Clear button should be enabled");
            Assert.assertTrue(invoiceInquiry.validateInvInqApplyButtonEnabled(),  "Apply button should be enabled");
            
            invoiceInquiry.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            
            Thread.sleep(3000);
            invoiceInquiry.clickOnClearButton();
        	logger.info("Clicked on clear button values entered in the fields are cleared successfully");
        	
            
            logger.info("Invoices Inquiry Advance filter is applied for the AC01&FL51 - Emailed to Customer status verified successfully");
        } catch (Exception e) {
            Assert.fail("Invoices Inquiry Advance filter test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 6)
    public void validateInvoiceInquiryTableHeadersAndData() {
        logger.info("******* Starting validateInvoiceInquiryTableHeadersAndData***********");
        try {
        	
        	
        	 // Expected headers
        	
            List<String> expectedHeaders = Arrays.asList("Invoice", "Type", "Location", "Customer", "Created Date", "Total", "Method", "Status","Action");

            // Validate headers
            List<String> actualHeaders = invoiceInquiry.getTableHeaders();
            
            Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers mismatch!");
            System.out.println("✅ Headers verified: " + actualHeaders);
            logger.info("Header fields verified successfully");
            
            logger.info("Expected Headers: " + expectedHeaders);
            logger.info("Actual Headers: " + actualHeaders);

            // Validate row data on first page
            int rowCount = invoiceInquiry.getRowCount();
            System.out.println("Rows on first page: " + rowCount);
            Assert.assertTrue(rowCount > 0, "No rows found in table!");
            logger.info("✅ Table rows verified successfully");
            
            // ✅ Validate first row data
            List<String> firstRow = invoiceInquiry.getTableRowData(0);
            System.out.println("✅ First row data: " + firstRow);
        	Thread.sleep(10000);
        	
            logger.info("Invoices Inquiry tab verified successfully");
        } catch (Exception e) {
            Assert.fail("Invoices Inquiry tab test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 7)
    public void validatePaginationControls() throws InterruptedException {
    	logger.info("******* Starting validatePaginationControls***********");
        try {
        	Thread.sleep(10000);

        // Validate total records
        int totalRecords = invoiceInquiry.getTotalRecords();
        System.out.println("Total Records: " + totalRecords);
        Assert.assertTrue(totalRecords > 0, "Total records should be greater than 0!");
        
        // ✅ Validate pagination buttons default state
        logger.info("Checking pagination button states on first load...");
        Assert.assertFalse(invoiceInquiry.isFirstPageButtonEnabled(), "❌ First Page button should be disabled initially");
        Assert.assertFalse(invoiceInquiry.isPrevPageButtonEnabled(), "❌ Previous Page button should be disabled initially");
        Assert.assertTrue(invoiceInquiry.isNextPageButtonEnabled(), "❌ Next Page button should be enabled initially");
        Assert.assertTrue(invoiceInquiry.isLastPageButtonEnabled(), "❌ Last Page button should be enabled initially");
        logger.info("✅ Pagination button states verified successfully on first page");

        // ✅ Navigate to next page
        if (invoiceInquiry.isNextPageButtonEnabled()) {
            logger.info("Clicking Next Page button...");
            invoiceInquiry.goToNextPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated to next page");
        }
        
        Thread.sleep(1000);
        Assert.assertTrue(invoiceInquiry.isFirstPageButtonEnabled(), "'<<' should be enabled on middle page");
        Assert.assertTrue(invoiceInquiry.isPrevPageButtonEnabled(), "'<' should be enabled on middle page");
        Assert.assertTrue(invoiceInquiry.isNextPageButtonEnabled(), "'>' should be enabled on first page");
        Assert.assertTrue(invoiceInquiry.isLastPageButtonEnabled(), "'>>' should be enabled on first page");
        System.out.println("✅ Next page buttons validated");

        // ✅ Navigate to last page
        if (invoiceInquiry.isLastPageButtonEnabled()) {
            logger.info("Clicking Last Page button...");
            invoiceInquiry.goToLastPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated to last page");
        }

        // ✅ Validate pagination button states on last page
        logger.info("Checking pagination button states on last page...");
        Assert.assertTrue(invoiceInquiry.isFirstPageButtonEnabled(), " '<<' should be enabled on last page");
        Assert.assertTrue(invoiceInquiry.isPrevPageButtonEnabled(), " '<' should be enabled on last page");
        Assert.assertTrue(invoiceInquiry.isNextPageButtonDisabled(), " '>' should be disabled on last page");
        Assert.assertTrue(invoiceInquiry.isLastPageButtonDisabled(), " '>>' should be disabled on last page");
        logger.info("✅ Pagination button states verified successfully on last page");
        
        // ✅ Navigate to last page
        if (invoiceInquiry.isPrevPageButtonEnabled()) {
            logger.info("Clicking Prev Page button...");
            invoiceInquiry.goToPrevPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated to Prev page");
        }

        // ✅ Validate pagination button states on last page
        logger.info("Checking pagination button states on last page...");
        Assert.assertTrue(invoiceInquiry.isFirstPageButtonEnabled(), "'<<' should be enabled on middle page");
        Assert.assertTrue(invoiceInquiry.isPrevPageButtonEnabled(), "'<' should be enabled on middle page");
        Assert.assertTrue(invoiceInquiry.isNextPageButtonEnabled(), "'>' should be enabled on middle page");
        Assert.assertTrue(invoiceInquiry.isLastPageButtonEnabled(), "'>>' should be enabled on middle page");
        logger.info("✅ Pagination button states verified successfully on last page");

        // ✅ Navigate back to first page
        if (invoiceInquiry.isFirstPageButtonEnabled()) {
            logger.info("Clicking First Page button...");
            invoiceInquiry.goToFirstPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated back to first page");
        }
        
        Assert.assertTrue(invoiceInquiry.isFirstPageButtonDisabled(), "'<<' should be disabled on first page");
        Assert.assertTrue(invoiceInquiry.isPrevPageButtonDisabled(), "'<' should be disabled on first page");
        Assert.assertTrue(invoiceInquiry.isNextPageButtonEnabled(), "'>' should be enabled on first page");
        Assert.assertTrue(invoiceInquiry.isLastPageButtonEnabled(), "'>>' should be enabled on first page");
        System.out.println("✅ First page buttons validated");
        logger.info("===== Invoice Inquiry Table & Pagination Test Completed Successfully =====");
        
        // ✅ Validate Records per Page selection
        invoiceInquiry.selectRecordsPerPage("20");
        Thread.sleep(2000);
        Assert.assertTrue(invoiceInquiry.getRowCount() <= 20, "Row count exceeds 20 per page!");
        System.out.println("✅ Records per page (20) verified");

        invoiceInquiry.selectRecordsPerPage("50");
        Thread.sleep(2000);
        Assert.assertTrue(invoiceInquiry.getRowCount() <= 50, "Row count exceeds 50 per page!");
        System.out.println("✅ Records per page (50) verified");
        
        invoiceInquiry.selectRecordsPerPage("100");
        Thread.sleep(2000);
        Assert.assertTrue(invoiceInquiry.getRowCount() <= 100, "Row count exceeds 100 per page!");
        System.out.println("✅ Records per page (100) verified");
        
        // ✅ Validate Records per Page selection
        invoiceInquiry.selectRecordsPerPage("20");
        Thread.sleep(2000);
        Assert.assertTrue(invoiceInquiry.getRowCount() <= 20, "Row count exceeds 20 per page!");
        System.out.println("✅ Records per page (20) verified");
        
        logger.info("Invoices Inquiry Pagination Controls verified successfully");
        } catch (Exception e) {
        	Assert.fail("Invoices Inquiry Pagination Controls test failed: " + e.getMessage());
        }
        
    }
    
    
    
   /* 
    @Test(priority = 3)
    public void verifyWorkCenterTabCollapseSectionToShowOrHideAdvanceFilters() {
        logger.info("******* Starting verifyWorkCenterTabCollapseSectionToShowOrHideAdvanceFilters ***********");
        try {
        	
        	//by default collapse section shown
        	
        	logger.info("By default Collapse section in expanded state");
        	
        	workCenter.expandCollapseSection();
        	
        	Assert.assertTrue(workCenter.validateDatePickerContainerisEnabled(),  "Date Picker Container should be enabled");
        	Assert.assertTrue(workCenter.validateWcLocationFilterEnabled(),  "Location Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcCustomerFilterEnabled(),  "Customer Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcStautsFilterEnabled(),  "Status Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcTypeFilterEnabled(),  "Type Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcRateByFilterEnabled(),  "Rate By Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcInvoiceChargeFilterEnabled(),  "Invoice Charge Advance Filter should be enabled");
        	logger.info("By default Collapse section expanded and shows the Advance filters");
        	
        	Assert.assertTrue(workCenter.validateWcClearButtonDisabled(), "Clear button should be disabled");
        	Assert.assertTrue(workCenter.validateWcApplyButtonDisabled(), "Apply button should be disabled");
        	logger.info("Apply and Clear buttons are disabled");
        	
        	//Click on collapse button to hide and validate all advance filters hidden
        	workCenter.hideCollapseSection();
        	logger.info("Clicked on Collapse section to hide all Advance filters");
        	Thread.sleep(10000);
        	
        	Assert.assertTrue(workCenter.validateDatePickerContainerisHidden(),  "Date Picker Container should be Hidden");
        	Assert.assertTrue(workCenter.validateWcLocationFilterHidden(),  "Location Advance Filter should be Hidden");
        	Assert.assertTrue(workCenter.validateWcCustomerFilterHidden(),  "Customer Advance Filter should be Hidden");
        	Assert.assertTrue(workCenter.validateWcStautsFilterHidden(),  "Status Advance Filter should be Hidden");
        	Assert.assertTrue(workCenter.validateWcTypeFilterHidden(),  "Type Advance Filter should be Hidden");
        	Assert.assertTrue(workCenter.validateWcRateByFilterHidden(),  "Rate By Advance Filter should be Hidden");
        	Assert.assertTrue(workCenter.validateWcInvoiceChargeFilterHidden(),  "Invoice Charge Advance Filter should be Hidden");
        	Assert.assertTrue(workCenter.validateWcClearButtonHidden(),  "Clear button  should be Hidden");
        	Assert.assertTrue(workCenter.validateWcApplyButtonHidden(),  "Apply Button  should be Hidden");
        	logger.info("Advance filters, Clear and Apply buttons are Hidden");
        	
        	//Again Click on collapse button to Show and validate all advance filters shown.
        	workCenter.expandCollapseSection();
        	logger.info("Clicked on Collapse section to show all Advance filters");
        	Thread.sleep(10000);
        	
        	Assert.assertTrue(workCenter.validateDatePickerContainerisEnabled(),  "Date Picker Container should be enabled");
        	Assert.assertTrue(workCenter.validateWcLocationFilterEnabled(),  "Location Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcCustomerFilterEnabled(),  "Customer Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcStautsFilterEnabled(),  "Status Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcTypeFilterEnabled(),  "Type Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcRateByFilterEnabled(),  "Rate By Advance Filter should be enabled");
        	Assert.assertTrue(workCenter.validateWcInvoiceChargeFilterEnabled(),  "Invoice Charge Advance Filter should be enabled");
        	logger.info("Advance filters are Shown");
        	
        	Assert.assertTrue(workCenter.validateWcClearButtonDisabled(), "Clear button should be disabled");
        	Assert.assertTrue(workCenter.validateWcApplyButtonDisabled(), "Apply button should be disabled");
        	logger.info("Apply and Clear buttons are disabled");
        	
            
            logger.info("Work Center tabs advance filters fields verified successfully");
        } catch (Exception e) {
            Assert.fail("Work Center tab advance filters fields test failed: " + e.getMessage());
        }
    }
    
    @Test(priority = 4)
    public void verifyWorkCenterTabAdvanceFilters() {
        logger.info("******* Starting verifyWorkCenterTabAdvanceFilters ***********");
        try {
            
        	   
            //Advance filters values enters and Apply & Clear enabled
        	workCenter.workCenterAdvanceFilters("Accessorial", "AC01", "FL50", "Ready for primary approval", "1/2", "Lease for Trucks/Trailers");
        	workCenter.selectInvoiceDateRange("August 2025", "28", "September 2025", "25");
        	logger.info("Selected date range - 28/08/2024 to 25/09/2024");
        	Assert.assertTrue(workCenter.validateWcClearButtonEnabled(),  "Clear button should be enabled");
            Assert.assertTrue(workCenter.validateWcApplyButtonEnabled(),  "Apply button should be enabled");
            workCenter.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            Thread.sleep(3000);
            workCenter.clickOnClearButton();
            Thread.sleep(3000);
        	logger.info("Clicked on clear button values entered in the fields are cleared successfully");
        	//Assert.assertTrue(workCenter.validateInvInqClearButtonDisabled(),  "Clear button should be enabled");
            //Assert.assertTrue(workCenter.validateInvInqApplyButtonDisabled(),  "Apply button should be enabled");
        	        
            logger.info("Work Center tabs advance filters fields verified successfully");
        } catch (Exception e) {
            Assert.fail("Work Center tab advance filters fields test failed: " + e.getMessage());
        }
    }
    
    /*
    @Test(priority = 5)
    public void verifyInvoicesInquiryGlobalSearchByDifferentFields() {
        logger.info("******* Starting verifyInvoicesInquiryGlobalSearchByDifferentFields***********");
        try {
        	
        	
        	 logger.info("===== Starting Invoice Inquiry Search Validation Test =====");

             // 🔹 Capture invoice number from first row
             String invoiceNum = workCenter.captureGeneratedInvoiceNumber();
             logger.info("Captured Invoice Number: " + invoiceNum);

             // Search by Invoice Number
             workCenter.searchInvoice(invoiceNum);
             String searchedInvoiceNum = workCenter.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Number: " + searchedInvoiceNum);

             Assert.assertEquals(searchedInvoiceNum, invoiceNum, "❌ Invoice Number search mismatch!");
             logger.info("✅ Invoice Number search verified successfully");

             // 🔹 Capture invoice type from first row
             String invoiceType = workCenter.captureGeneratedInvoiceType();
             logger.info("Captured Invoice Type: " + invoiceType);

             // Search by Invoice Type
             workCenter.searchInvoiceType(invoiceType);
             String searchedInvoiceType = workCenter.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Type: " + searchedInvoiceType);
             
             Assert.assertEquals(searchedInvoiceType, invoiceType, "❌ Invoice Type search mismatch!");
             logger.info("✅ Invoice Type search verified successfully");

             // 🔹 Capture invoice location from first row
             String invoiceLocation = workCenter.captureGeneratedInvoiceLocationName();
             logger.info("Captured Invoice Location: " + invoiceLocation);

             // Search by Invoice Location
             workCenter.searchInvoiceLocation(invoiceLocation);
             String searchedInvoiceLocation = workCenter.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Location: " + searchedInvoiceLocation);

             Assert.assertEquals(searchedInvoiceLocation, invoiceLocation, "❌ Invoice Location search mismatch!");
             logger.info("✅ Invoice Location search verified successfully");
             
             // 🔹 Capture invoice customer name from first row
             String invoiceCustomer = workCenter.captureGeneratedInvoiceCustomerName();
             logger.info("Captured Invoice Customer: " + invoiceCustomer);

             // Search by Invoice Customer name
             workCenter.searchInvoiceCustomer(invoiceCustomer);
             String searchedInvoiceCustomer = workCenter.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Customer: " + searchedInvoiceCustomer);

             Assert.assertEquals(searchedInvoiceCustomer, invoiceCustomer, "❌ Invoice Customer search mismatch!");
             logger.info("✅ Invoice Customer search verified successfully");

             
             // 🔹 Capture invoice Total Amount from first row
             String invoiceTotalAmount = workCenter.captureGeneratedInvoiceTotalAmount();
             logger.info("Captured Invoice Total Amount: " + invoiceTotalAmount);

             // Search by Invoice Customer
             workCenter.searchInvoiceTotalAmount(invoiceTotalAmount);
             String searchedInvoiceTotalAmount = workCenter.getGlobalSearchedAttributeValue();
             logger.info("Searched Invoice Total Amount: " + searchedInvoiceTotalAmount);

             Assert.assertEquals(searchedInvoiceTotalAmount, invoiceTotalAmount, "❌ Invoice Total Amount search mismatch!");
             logger.info("✅ Invoice Total Amount search verified successfully");
             
             workCenter.clearGlobalSeacrhField();
             logger.info("===== Invoice Inquiry Global Search field cleared Successfully =====");
             
             logger.info("===== Invoice Inquiry Global Search Test Completed by Invoice, Type, Location, Customer and Amount Successfully =====");
             
        } catch (Exception e) {
            Assert.fail("Invoices Inquiry Global Search test failed: " + e.getMessage());
        }
    }
    
    
    
    @Test(priority = 6)
    public void verifyInvoicesInquiryAdvanceFiltes() {
        logger.info("******* Starting verifyInvoicesInquiryAdvanceFiltes***********");
        try {
        	workCenter.clearGlobalSeacrhField();
            logger.info("===== Invoice Inquiry Global Search field cleared Successfully =====");
        	
        	//Advance filters values enters and Apply & Clear enabled
        	workCenter.workCenterAdvanceFilters("Outbound", "AC11", "DA40", "Ready for primary approval", "One", "Acc.Administrative");
        	workCenter.selectInvoiceDateRange("August 2025", "25", "September 2025", "25");
        	logger.info("Selected date range - 29/07/2024 to 25/09/2024");
        	
        	Assert.assertTrue(workCenter.validateWcClearButtonEnabled(),  "Clear button should be enabled");
            Assert.assertTrue(workCenter.validateWcApplyButtonEnabled(),  "Apply button should be enabled");
            
            workCenter.clickOnApplyButton();
            logger.info("Clicked on Apply button and values entered in the advance filters fields are applied Successfully");
            
            Thread.sleep(3000);
            workCenter.clickOnClearButton();
            Thread.sleep(3000);
        	logger.info("Clicked on clear button values entered in the fields are cleared successfully");
        	
        	Assert.assertTrue(workCenter.validateWcClearButtonEnabled(),  "Clear button should be enabled");
            Assert.assertTrue(workCenter.validateWcApplyButtonEnabled(),  "Apply button should be enabled");
            
            
            logger.info("Invoices Inquiry Advance filter is applied for the AC01&FL51 - Ready for primary approval status verified successfully");
        } catch (Exception e) {
            Assert.fail("Invoices Inquiry Advance filter test failed: " + e.getMessage());
        }
    }
    
   
    
    @Test(priority = 7)
    public void validateInvoiceInquiryTableHeadersAndData() {
        logger.info("******* Starting validateInvoiceInquiryTableHeadersAndData***********");
        try {
        	
        	
        	 // Expected headers
        	
            List<String> expectedHeaders = Arrays.asList("Invoice", "Type", "Location", "Customer", "Created Date", "Total", "Method", "Status","Action");

            // Validate headers
            List<String> actualHeaders = workCenter.getTableHeaders();
            
            Assert.assertEquals(actualHeaders, expectedHeaders, "Table headers mismatch!");
            System.out.println("✅ Headers verified: " + actualHeaders);
            logger.info("Header fields verified successfully");
            
            logger.info("Expected Headers: " + expectedHeaders);
            logger.info("Actual Headers: " + actualHeaders);

            // Validate row data on first page
            int rowCount = workCenter.getRowCount();
            System.out.println("Rows on first page: " + rowCount);
            Assert.assertTrue(rowCount > 0, "No rows found in table!");
            logger.info("✅ Table rows verified successfully");
            
            // ✅ Validate first row data
            List<String> firstRow = workCenter.getTableRowData(0);
            System.out.println("✅ First row data: " + firstRow);
        	
        	
            logger.info("Invoices Inquiry tab verified successfully");
        } catch (Exception e) {
            Assert.fail("Invoices Inquiry tab test failed: " + e.getMessage());
        }
    }
    
    
    @Test(priority = 8)
    public void validatePaginationControls() throws InterruptedException {
    	logger.info("******* Starting validatePaginationControls***********");
        try {

        // Validate total records
        int totalRecords = workCenter.getTotalRecords();
        System.out.println("Total Records: " + totalRecords);
        Assert.assertTrue(totalRecords > 0, "Total records should be greater than 0!");
        
        // ✅ Validate pagination buttons default state
        logger.info("Checking pagination button states on first load...");
        Assert.assertFalse(workCenter.isFirstPageButtonEnabled(), "❌ First Page button should be disabled initially");
        Assert.assertFalse(workCenter.isPrevPageButtonEnabled(), "❌ Previous Page button should be disabled initially");
        Assert.assertTrue(workCenter.isNextPageButtonEnabled(), "❌ Next Page button should be enabled initially");
        Assert.assertTrue(workCenter.isLastPageButtonEnabled(), "❌ Last Page button should be enabled initially");
        logger.info("✅ Pagination button states verified successfully on first page");

        // ✅ Navigate to next page
        if (workCenter.isNextPageButtonEnabled()) {
            logger.info("Clicking Next Page button...");
            workCenter.goToNextPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated to next page");
        }
        
        Thread.sleep(1000);
        Assert.assertTrue(workCenter.isFirstPageButtonEnabled(), "'<<' should be enabled on middle page");
        Assert.assertTrue(workCenter.isPrevPageButtonEnabled(), "'<' should be enabled on middle page");
        Assert.assertTrue(workCenter.isNextPageButtonEnabled(), "'>' should be enabled on first page");
        Assert.assertTrue(workCenter.isLastPageButtonEnabled(), "'>>' should be enabled on first page");
        System.out.println("✅ Next page buttons validated");

        // ✅ Navigate to last page
        if (workCenter.isLastPageButtonEnabled()) {
            logger.info("Clicking Last Page button...");
            workCenter.goToLastPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated to last page");
        }

        // ✅ Validate pagination button states on last page
        logger.info("Checking pagination button states on last page...");
        Assert.assertTrue(workCenter.isFirstPageButtonEnabled(), " '<<' should be enabled on last page");
        Assert.assertTrue(workCenter.isPrevPageButtonEnabled(), " '<' should be enabled on last page");
        Assert.assertTrue(workCenter.isNextPageButtonDisabled(), " '>' should be disabled on last page");
        Assert.assertTrue(workCenter.isLastPageButtonDisabled(), " '>>' should be disabled on last page");
        logger.info("✅ Pagination button states verified successfully on last page");
        
        // ✅ Navigate to last page
        if (workCenter.isPrevPageButtonEnabled()) {
            logger.info("Clicking Prev Page button...");
            workCenter.goToPrevPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated to Prev page");
        }

        // ✅ Validate pagination button states on last page
        logger.info("Checking pagination button states on last page...");
        Assert.assertTrue(workCenter.isFirstPageButtonEnabled(), "'<<' should be enabled on middle page");
        Assert.assertTrue(workCenter.isPrevPageButtonEnabled(), "'<' should be enabled on middle page");
        Assert.assertTrue(workCenter.isNextPageButtonEnabled(), "'>' should be enabled on middle page");
        Assert.assertTrue(workCenter.isLastPageButtonEnabled(), "'>>' should be enabled on middle page");
        logger.info("✅ Pagination button states verified successfully on last page");

        // ✅ Navigate back to first page
        if (workCenter.isFirstPageButtonEnabled()) {
            logger.info("Clicking First Page button...");
            workCenter.goToFirstPage();
            Thread.sleep(2000);
            logger.info("✅ Navigated back to first page");
        }
        
        Assert.assertTrue(workCenter.isFirstPageButtonDisabled(), "'<<' should be disabled on first page");
        Assert.assertTrue(workCenter.isPrevPageButtonDisabled(), "'<' should be disabled on first page");
        Assert.assertTrue(workCenter.isNextPageButtonEnabled(), "'>' should be enabled on first page");
        Assert.assertTrue(workCenter.isLastPageButtonEnabled(), "'>>' should be enabled on first page");
        System.out.println("✅ First page buttons validated");
        logger.info("===== Invoice Inquiry Table & Pagination Test Completed Successfully =====");
        
        // ✅ Validate Records per Page selection
        workCenter.selectRecordsPerPage("20");
        Thread.sleep(2000);
        Assert.assertTrue(workCenter.getRowCount() <= 20, "Row count exceeds 20 per page!");
        System.out.println("✅ Records per page (20) verified");

        workCenter.selectRecordsPerPage("50");
        Thread.sleep(2000);
        Assert.assertTrue(workCenter.getRowCount() <= 50, "Row count exceeds 50 per page!");
        System.out.println("✅ Records per page (50) verified");
        
        workCenter.selectRecordsPerPage("100");
        Thread.sleep(2000);
        Assert.assertTrue(workCenter.getRowCount() <= 100, "Row count exceeds 100 per page!");
        System.out.println("✅ Records per page (100) verified");
        
        // ✅ Validate Records per Page selection
        workCenter.selectRecordsPerPage("20");
        Thread.sleep(2000);
        Assert.assertTrue(workCenter.getRowCount() <= 20, "Row count exceeds 20 per page!");
        System.out.println("✅ Records per page (20) verified");
        
        logger.info("Invoices Inquiry Pagination Controls verified successfully");
        } catch (Exception e) {
        	Assert.fail("Invoices Inquiry Pagination Controls test failed: " + e.getMessage());
        }
        
    }
	
     */
}
