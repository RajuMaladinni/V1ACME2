package pageObjects;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class WorkCenter extends BasePage{
	public  WorkCenter(WebDriver driver) throws AWTException {
		super(driver);
	}
	
	//Locators
	@FindBy (xpath="//input[@placeholder='Search by Invoice, Type, Location, Amount, Item Number, PO Number, PO Suffix']")
	WebElement workCenterGlobalSearch;
	
	@FindBy (xpath="//label[contains(text(),'Location')]/following-sibling::div//div[contains(@class,'select__control')]")
	WebElement wcLocationFilter;
	
	@FindBy (xpath="//label[contains(text(),'Type')]/following-sibling::div//div[contains(@class,'select__control')]")
	WebElement wcTypeFilter;
	
	@FindBy (xpath="//label[contains(text(),'Customer')]/following-sibling::div//div[contains(@class,'select__control')]")
	WebElement wcCustomerFilter;
	
	@FindBy (xpath="//label[contains(text(),'Status')]/following-sibling::div//div[contains(@class,'select__control')]")
	WebElement wcStatusFilter;
	
	@FindBy (xpath="//label[contains(text(),'Rate By')]/following-sibling::div//div[contains(@class,'select__control')]")
	WebElement wcRateByFilter;
	
	@FindBy (xpath="//label[contains(text(),'Invoice Charge')]/following-sibling::div//div[contains(@class,'select__control')]")
	WebElement wcInvoiceChargeFilter;
	
	@FindBy(xpath= "//div[@class='react-datepicker__input-container']")
	WebElement datePicInputContainer;
	   
    @FindBy (xpath ="//input[@placeholder='MM/DD/YYYY - MM/DD/YYYY']")
    WebElement genInvdatePicker;	    

	@FindBy(xpath="//button[@aria-label='Previous Month']")
	WebElement previousMonthButton;
	
	@FindBy(xpath="//button[@aria-label='Next Month']")
	WebElement nextMonthButton;
    
    @FindBy (xpath="//div[@class='react-datepicker__current-month react-datepicker__current-month--hasYearDropdown']")
    WebElement currentMonthYear;

    @FindBy (xpath= "//span[@class='react-datepicker__year-read-view--selected-year']")
    WebElement currentYear;
    
    @FindBy (xpath="//span[contains(text(),'Work Center')]")
	WebElement workCenterTab;
    
    @FindBy(xpath= "//h4[text()='Work Center']")
    WebElement workCenterHeaderText;
    
    @FindBy(xpath="//h4[@class='card-title' and text()='Work Center List']")
    WebElement workCenterListText;
    
    @FindBy(xpath="//button[.//span[contains(text(),'Filters')]]")
    WebElement filtersText;
    
    @FindBy(xpath="//button[@data-testid='filterbtnTestId']")
    WebElement collapseButton;
    
    @FindBy(xpath="//div[contains(@class,'collapse')]")
    WebElement collapseDiv;
    
    @FindBy(xpath= "//button[@type='button' and text()='Clear']")
    WebElement clearButton;
    
    @FindBy(xpath="//button[@type='submit' and text()='Apply']")
    WebElement applyButton;
    
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[1]")  
    WebElement firstInvoiceNumber;
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[2]/span")  
    WebElement firstInvoiceType;
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[3]/div")  
    WebElement firstInvoiceLocationName;
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[4]/div")  
    WebElement firstInvoiceCustomerName;
    
    @FindBy(xpath ="//table//tbody/tr[1]/td[6]/span")
    WebElement firstInvoiceTotalAmount;
    
    @FindBy(xpath = "//input[contains(@placeholder,'Search by Invoice')]") 
    WebElement globalSearchBox;
    
    
    // First search result row invoice number
    @FindBy(xpath = "//div[@class='table-responsive']//table//tbody/tr[1]/td[1]")  
    WebElement searchResultInvoiceNumber;
    
    //Table records validating
    // --- Table Headers ---
    @FindBy(xpath = "//table[@data-testid='tableRecordTestId']//thead//th")
    private List<WebElement> thHeaders;

    // Special case header (like "Invoice") inside <div>
    @FindBy(xpath = "//table[@data-testid='tableRecordTestId']//thead//div[contains(@class,'align-items-center')]")
    private List<WebElement> divHeaders;
    
    // Table rows (tbody > tr)
    @FindBy(xpath = "//table[@data-testid='tableRecordTestId']//tbody//tr")
    private List<WebElement> tableRows;

    // --- Total Records ---
    @FindBy(xpath = "//div[@data-testid='totalNoRecordsMsgTestId']")
    WebElement totalRecordsElement;

    // --- Records per Page Dropdown ---
    @FindBy(xpath = "//select[@aria-labelledby='items-per-page-label']")
    WebElement recordsPerPageDropdown;

    // --- Pagination buttons ---
    @FindBy(xpath = "//button[@data-testid='paginationStartTestId']")
    WebElement firstPageButton;

    @FindBy(xpath = "//button[@data-testid='paginationPrevPageTestId']")
    WebElement prevPageButton;

    @FindBy(xpath = "//button[@data-testid='paginationNextPageTestId']")
    WebElement nextPageButton;

    @FindBy(xpath = "//button[@data-testid='paginationEndTestId']")
    WebElement lastPageButton;
    
    
    
    
    //Verification of General Invoice fields 
    
    //Click on Work Center Tab
    public void clickOnWorkCenterTab() {
    	wait.until(ExpectedConditions.elementToBeClickable(workCenterTab));
    	workCenterTab.click();
    }
    
    //Work Center text validation
    public String validateWorkCenterHeaderText() {
   	 return workCenterHeaderText.getText();
   
   }
    
  //Work Center text validation
    public String validateWorkCenterListText() {
   	 return workCenterListText.getText();
   
   }
    
    //Filters text validation
    public String validateFiltersText() {
    	return filtersText.getText();
    }
    
    
   /* 
    public void clearGlobalSeacrhField() {
    	clearOnElement(workCenterGlobalSearch);
    }
    
    // Expand collapse section only if hidden
    public void expandCollapseSection() {
    	
        String collapseClass = collapseDiv.getAttribute("class");

        if (!collapseClass.contains("show")) {
            collapseButton.click();
            System.out.println("Collapse was hidden, clicked to expand.");
        } else {
            System.out.println("Collapse already expanded, no action needed.");
        }

        // ✅ Validation
        collapseClass = collapseDiv.getAttribute("class");
        if (collapseClass.contains("show")) {
            System.out.println("Collapse is now shown successfully.");
        } else {
            System.out.println("Collapse did not expand as expected.");
        }
    }

    // Collapse section only if shown
    public void hideCollapseSection() {
        String collapseClass = collapseDiv.getAttribute("class");

        if (collapseClass.contains("show")) {
        	click(collapseButton);
           // collapseButton.click();
            System.out.println("Collapse was visible, clicked to hide.");
        } else {
            System.out.println("Collapse already hidden, no action needed.");
        }

        // ✅ Validation
        collapseClass = collapseDiv.getAttribute("class");
        if (!collapseClass.contains("show")) {
            System.out.println("Collapse is now hidden successfully.");
        } else {
            System.out.println("Collapse did not collapse as expected.");
        }
    }

    
    //Validate all Advance filters are Enabled and displayed
    public boolean validateWcTypeFilterEnabled() {
    	 return fieldIsEnabled(wcTypeFilter);
    }
    
    public boolean validateWcLocationFilterEnabled() {
    	return fieldIsEnabled(wcLocationFilter);
    }
    
    public boolean validateWcCustomerFilterEnabled() {
    	return fieldIsEnabled(wcCustomerFilter);
    }
    
    public boolean validateWcStautsFilterEnabled() {
    	return fieldIsEnabled(wcStatusFilter);
    }
    
    public boolean validateWcDateFilterEnabled() {
    	return fieldIsEnabled(datePicInputContainer);
    }
    
    public boolean validateWcRateByFilterEnabled() {
    	return fieldIsEnabled(wcRateByFilter);
    }
    
    public boolean validateWcInvoiceChargeFilterEnabled() {
    	return fieldIsEnabled(wcInvoiceChargeFilter);
    }
    
    
    public boolean validateWcClearButtonShown() {
    	return fieldIsEnabled(clearButton);
    }
    
    public boolean validateWcApplyButtonShown() {
    	return fieldIsEnabled(applyButton);
    }
    
    //Validate all Advance Filters are Hidden
    public boolean validateWcTypeFilterHidden() {
    	return fieldIsHidden(wcTypeFilter);
    }
    
    public boolean validateWcLocationFilterHidden() {
    	return fieldIsHidden(wcLocationFilter);
    }
    
    public boolean validateWcCustomerFilterHidden() {
    	return fieldIsHidden(wcCustomerFilter);
    }
    
    public boolean validateWcStautsFilterHidden() {
    	return fieldIsHidden(wcStatusFilter);
    }
    
    public boolean validateWcDateFilterHidden() {
    	return fieldIsHidden(datePicInputContainer);
    }
    
    public boolean validateWcRateByFilterHidden() {
    	return fieldIsHidden(wcRateByFilter);
    }
    
    public boolean validateWcInvoiceChargeFilterHidden() {
    	return fieldIsHidden(wcInvoiceChargeFilter);
    }
    
    public boolean validateWcClearButtonHidden() {
    	return fieldIsEnabled(clearButton);
    }
    
    public boolean validateWcApplyButtonHidden() {
    	return fieldIsEnabled(applyButton);
    }
    
    //Validate Clear button disabled
    public boolean validateWcClearButtonDisabled() {
    	return buttonIsDisabled(clearButton);
    }
    
    //Validate Apply button disabled
    public boolean validateWcApplyButtonDisabled() {
    	return buttonIsDisabled(applyButton);
    }
    
    //Validate Clear button disabled
    public boolean validateWcClearButtonEnabled() {
    	return buttonIsEnabled(clearButton);
    }
    
    //Validate Apply button disabled
    public boolean validateWcApplyButtonEnabled() {
    	return buttonIsEnabled(applyButton);
    }
    
    //Click on clear button
    public void clickOnClearButton() {
    	clickOnButton(clearButton);
    }
    
    //Click On Apply button 
    public void clickOnApplyButton() {
    	clickOnButton(applyButton);
    }
    
    public void searchByInvoiceNumber() {
    	wait.until(ExpectedConditions.elementToBeClickable(workCenterGlobalSearch));
    	workCenterGlobalSearch.sendKeys("");
    }
    
    //Invoices Inquiry advance filters
    public void workCenterAdvanceFilters(String type, String location, String customer, 
    		String status, String rateBy, String invoiceCharge) throws InterruptedException {
		WebElement[] fields = {
				wcTypeFilter, wcLocationFilter, wcCustomerFilter,
				wcStatusFilter, wcRateByFilter, wcInvoiceChargeFilter
					};

		String[] values = { type, location, customer, status, rateBy, invoiceCharge };

		for (int i = 0; i < fields.length; i++) {
			selectDropdownValue(fields[i], values[i]);
				}
			}
    
    
    //Date Picker Container enabled
    public boolean validateDatePickerContainerisEnabled() {
    	return fieldIsEnabled(datePicInputContainer);
	}
    
    //Valdiate date picked container is hidden
    public boolean validateDatePickerContainerisHidden() {
    	return fieldIsHidden(datePicInputContainer);
	}
    
    //Capture Generated Invoice Number
    public String captureGeneratedInvoiceNumber() {
        String invoiceNum = firstInvoiceNumber.getText().trim();
        System.out.println("Generated Invoice Number: " + invoiceNum);
        return invoiceNum;
    }
    
		    public void searchInvoice(String invoiceNum) throws InterruptedException {
		        globalSearchBox.clear();
		        globalSearchBox.sendKeys(invoiceNum);
		        globalSearchBox.sendKeys(Keys.ENTER);
		        Thread.sleep(2000);  // wait for results
		    }
	
		    public String getGlobalSearchedAttributeValue() {
		        return workCenterGlobalSearch.getAttribute("value").trim();
		    }
    
    //Capture First Invoice Type
    public String captureGeneratedInvoiceType() {
        String invoiceType = firstInvoiceType.getText().trim();
        System.out.println("Generated Invoice Type: " + invoiceType);
        return invoiceType;
    }
    
		    public void searchInvoiceType(String invoiceType) throws InterruptedException {
		        globalSearchBox.clear();
		        globalSearchBox.sendKeys(invoiceType);
		        globalSearchBox.sendKeys(Keys.ENTER);
		        Thread.sleep(2000);  // wait for results
		    }
	
		    public String getGlobalSearchedTextValue() {
		        return workCenterGlobalSearch.getText().trim();
		    }
    
  //Capture First Invoice Location Name
    public String captureGeneratedInvoiceLocationName() {
        String invoiceLocation = firstInvoiceLocationName.getText().trim();
        System.out.println("Generated Invoice Location: " + invoiceLocation);
        return invoiceLocation;
    }
    
		    public void searchInvoiceLocation(String invoiceLocation) throws InterruptedException {
		        globalSearchBox.clear();
		        globalSearchBox.sendKeys(invoiceLocation);
		        globalSearchBox.sendKeys(Keys.ENTER);
		        Thread.sleep(2000);  // wait for results
		    }
	
		    public String getSearchedInvoiceLocationName() {
		    	 return workCenterGlobalSearch.getText().trim();
		    }
    
    
  	//Capture First Invoice Customer Name
    public String captureGeneratedInvoiceCustomerName() {
        String invoiceCustomer = firstInvoiceCustomerName.getText().trim();
        System.out.println("Generated Invoice Location: " + invoiceCustomer);
        
        // ✅ Extract only the part before '(' e.g. FL50 from "FL50(Brook field)"
        if (invoiceCustomer.contains("(")) {
            invoiceCustomer = invoiceCustomer.split("\\(")[0].trim();
        }
        
        return invoiceCustomer;
    }
    
		    public void searchInvoiceCustomer(String invoiceCustomer) throws InterruptedException {
		        globalSearchBox.clear();
		        globalSearchBox.sendKeys(invoiceCustomer);
		        globalSearchBox.sendKeys(Keys.ENTER);
		        Thread.sleep(2000);  // wait for results
		    }
	
		    public String getSearchedInvoiceCustomerName() {
		    	 return workCenterGlobalSearch.getText().trim();
		    }
    
    //Capture First Invoice Total Amount
    public String captureGeneratedInvoiceTotalAmount() {
        String invoiceTotalAmount = firstInvoiceTotalAmount.getText().trim();
        System.out.println("Generated Invoice Total Amount (raw): " + invoiceTotalAmount);

        // Remove everything except digits and dot
        String numericAmount = invoiceTotalAmount.replaceAll("[^0-9.]", "").trim();
        System.out.println("Generated Invoice Total Amount (numeric only): " + numericAmount);

        return numericAmount;  // ✅ returns "0.00"
    }
    
    public void searchInvoiceTotalAmount(String invoiceTotalAmount) throws InterruptedException {
        globalSearchBox.clear();
        globalSearchBox.sendKeys(invoiceTotalAmount);
        globalSearchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);  // wait for results
    }	   
    
    //Selecting date with range
    public void selectInvoiceDateRange(String fromMonthYear, String fromDay,
            String toMonthYear, String toDay) {
    		datePicInputContainer.click(); // open calendar
    		datePicker.selectDateRange(fromMonthYear, fromDay, toMonthYear, toDay);
    	}
    
    //selecting todays or current date
    public void selectTodayInvoiceDate() {
    	datePicInputContainer.click(); // open calendar
        datePicker.selectTodayDate();
    }	   
 
 // --- Methods for Table ---
    // To get headers name
    public List<String> getTableHeaders() { 
    		List<String> actualHeaders = new ArrayList<>();

        for(WebElement th : thHeaders) {
            actualHeaders.add(th.getText().trim());
        }
      
        return actualHeaders;
    }
    
    
    	//to get row data
    public List<String> getTableRowData(int rowIndex) {
        if (rowIndex >= getRowCount()) {
            throw new IllegalArgumentException("Invalid row index: " + rowIndex);
        }
        List<WebElement> cells = tableRows.get(rowIndex).findElements(By.tagName("td"));
        List<String> rowData = new ArrayList<>();
        for (WebElement cell : cells) {
            rowData.add(cell.getText().trim());
        }
        return rowData;
    }
    
    	//To get row count
    public int getRowCount() {
        return tableRows.size();
    }

    // --- Methods for Pagination ---
    public int getTotalRecords() {
        String text = totalRecordsElement.getText().trim(); 
        return Integer.parseInt(text.split(" ")[0]);
    }
    
    //Select record perpage 
    public void selectRecordsPerPage(String value) {
        new Select(recordsPerPageDropdown).selectByVisibleText(value);
    }
    
 // ---------- Methods for Pagination ----------
    
    	//Click on FirstPage button
    public void goToFirstPage() {
        if (firstPageButton.isEnabled()) {
        	clickOnButton(firstPageButton);
        }
    }
    	
    	//Click on PreviousPage button
    public void goToPrevPage() {
        if (prevPageButton.isEnabled()) {
        	clickOnButton(prevPageButton);
        }
    }
    	//Click on NextPage button
    public void goToNextPage() {
        if (nextPageButton.isEnabled()) {
        	clickOnButton(nextPageButton);
        }
    }
 		//Click on LastPage button
    public void goToLastPage() {
        if (lastPageButton.isEnabled()) {
        	clickOnButton(lastPageButton);
        }
    }

    public boolean isFirstPageButtonEnabled() { 
    	return buttonIsEnabled(firstPageButton);
    	}
    
    public boolean isFirstPageButtonDisabled() { 
    	return buttonIsDisabled(firstPageButton);
    	}
    
    public boolean isPrevPageButtonEnabled() { 
    	return buttonIsEnabled(prevPageButton);
    	}
    
    public boolean isPrevPageButtonDisabled() { 
    	return buttonIsDisabled(prevPageButton);
    	}
    
    public boolean isNextPageButtonEnabled() { 
    	return buttonIsEnabled(nextPageButton);
    	}
    
    public boolean isNextPageButtonDisabled() { 
    	return buttonIsDisabled(nextPageButton);
    	}
    
    public boolean isLastPageButtonEnabled() { 
    	return buttonIsEnabled(lastPageButton);
    	}
    
    public boolean isLastPageButtonDisabled() { 
    	return buttonIsDisabled(lastPageButton);
    	}

    
    
    
    */
    
    
}
