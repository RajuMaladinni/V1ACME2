package pageObjects;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ReRunInvoicesPage extends BasePage{
	
	public  ReRunInvoicesPage(WebDriver driver) throws AWTException {
		super(driver);
		
	}
	
	//Locators
	    
	    @FindBy (xpath="//span[contains(text(),'Re-Run Invoices')]")
		WebElement reRunInvoiceTab;
	    
	    @FindBy(xpath= "//h4[text()='Re-Run Invoices']")
	    WebElement reRunInvoicesHeaderText;
	    
	    // Radio buttons
	    @FindBy(xpath = "//input[@type='radio' and @value='duration']")
	    WebElement radioByDuration;

	    @FindBy(xpath = "//input[@type='radio' and @value='invoice']")
	    WebElement radioByInvoice;
	    
	    @FindBy(xpath= "//p[@class='text-primary text-center']")
	    WebElement descriptionOfRadioButtonText;
	    
	    @FindBy(xpath="//label[@class='mb-0 form-label']")
	    WebElement invoiceTypeText;
	    
	    @FindBy(xpath="//input[@type='checkbox' and @value='inbound']")
	    WebElement inboundInvoiceType;
	    
	    @FindBy(xpath="//input[@type='checkbox' and @value='outbound']")
	    WebElement outboundInvoiceType;
	    
	    @FindBy(xpath="//input[@type='checkbox' and @value='storage']")
	    WebElement storageInvoiceType;
	    
	    @FindBy(xpath= "//label[@id='fromDate_label']/following-sibling::div//div[@class='react-datepicker__input-container']")
		WebElement fromDatePickerContainer;
	    
	    @FindBy(xpath= "//label[@id='toDate_label']/following-sibling::div//div[@class='react-datepicker__input-container']")
		WebElement toDatePickerContainer;
	    
	    @FindBy (xpath="//label[contains(text(),'Location')]/following-sibling::div//input[@class='react-select__input']")
		WebElement rerunLocationFilter;
		
		@FindBy (xpath="//label[contains(text(),'Customer')]/following-sibling::div//input[@class='react-select__input']")
		WebElement rerunCustomerFilter;
		
		@FindBy(xpath="//button[@type='submit']")
		WebElement rerunSubmitButton;
		
		@FindBy(xpath="//button[@type='submit' and text()='Confirm']")
		WebElement confirmButton;
		
		@FindBy(xpath="//div[@class='border-0 pb-4 pt-2 justify-content-center modal-footer']//button[@type='submit']")
	    WebElement confirmationPupupSubmitButton;
		
		@FindBy(xpath="//button[@type='button' and text() ='Cancel']")
	    WebElement confirmationPupupCancelButton;
		
		@FindBy(xpath="//div[text()='Confirm Change']")
		WebElement confirmChangeText;
		
		@FindBy(xpath="//div[text()='Are you sure you want to submit the changes ?']")
		WebElement descriptionOfconfirmChangeText;

		@FindBy(xpath="//label[@for='invoice_number']")
		WebElement invoiceNumber;
		
		@FindBy(xpath="//input[@id='invoice_number']")
		WebElement inputInvoiceNumber;
		
		//WorkCenter Advance Filters fields
		
		@FindBy (xpath="//span[contains(text(),'Work Center')]")
		WebElement workCenterTab;
		
		@FindBy (xpath="//span[contains(text(),'Invoices Inquiry')]")
		WebElement invoiceInquiryTab;
		
		@FindBy(xpath = "//input[contains(@placeholder,'Search by Invoice')]") 
	    WebElement globalSearchBox;  
		
		@FindBy (xpath="//input[@placeholder='Search by Invoice, Type, Location, Amount, Item Number, PO Number, PO Suffix']")
		WebElement invoiceInquiryGlobalSearch;
		
	    @FindBy (xpath="//label[contains(text(),'Location')]/following-sibling::div//input[@class='react-select__input']")
		WebElement wcLocationFilter;
		
		@FindBy (xpath="//label[contains(text(),'Type')]/following-sibling::div//input[@class='react-select__input']")
		WebElement wcTypeFilter;
		
		@FindBy (xpath="//label[contains(text(),'Customer')]/following-sibling::div//input[@class='react-select__input']")
		WebElement wcCustomerFilter;
		
		@FindBy (xpath="//label[contains(text(),'Status')]/following-sibling::div//input[@class='react-select__input']")
		WebElement wcStatusFilter;
	    
		@FindBy(xpath = "//table//tbody/tr[1]/td[1]")  
		WebElement firstInvoiceNumber;
		
		@FindBy(xpath="//button[@type='submit' and text()='Apply']")
	    WebElement applyButton;
		
		@FindBy(xpath= "//div[@role='status' and contains(text(), 'Processing Rerun Invoices in background. You will receive notification once processed.')]")
	    WebElement popupRerunInvoiceProcessedSuccessfully;
		
		@FindBy(xpath= "//div[@role='status' and @aria-live='polite']")
	    WebElement rerunErrorPopup;
		
		@FindBy(xpath="//div[contains(@class,'dropdown')]/button[@type='button']")
		WebElement notificationBellIcon;
		
		@FindBy(xpath="//span[contains(@class,'notification-count')]")
		WebElement notificationCount;
		
		@FindBy(xpath="//div[contains(@class,'dropdown')]//span[contains(@class,'badge')]")
		List<WebElement> notificationCountBadge;
	    
	    // 🔹 All notification messages (truncated text)
	    @FindBy(xpath = "//span[@class='notification-text f-13']")
	    List<WebElement> notificationTexts;

	    // 🔹 Notification success header (clickable green text)
	    @FindBy(xpath = "//div[@class='cursor-pointer']//span[contains(@class,'fw-bolder')]")
	    List<WebElement> notificationHeaders;
	    
	    @FindBy(xpath="//span[text()='Rerun invoice completed successfully.']")
	    WebElement latestSuccessNotificationText;

	    // 🔹 "More" buttons for notifications
	    @FindBy(xpath = "//small[text()='More']")
	    List<WebElement> moreButtons;

	    // 🔹 Date/time of each notification
	    @FindBy(xpath = "//small[contains(@class,'notification-time')]")
	    List<WebElement> notificationTimes;

		@FindBy(xpath="//button[@type='button' and text()='Clear All']")
		WebElement clearAllNotificationButton;
		
		
		// Table rows (tbody > tr)
	    @FindBy(xpath = "//table[@data-testid='tableRecordTestId']//tbody//tr")
	    private List<WebElement> tableRows;
	    
	    @FindBy(xpath="//div[contains(@class,'dropdown')]//span[@class='cursor-pointer']")
	    WebElement actionRowButton;
	    
	    @FindBy(xpath="//div[contains(@class,'action-dropdown')]//button[@type='button']")
	    List<WebElement> listOfActions;
	    
	    @FindBy(xpath= "//div[@role='status' and contains(text(), 'Invoice Primary approved. Current status: Ready for secondary approval')]")
	    WebElement invoicePrimaryApprovedStatus;
	    
	    // Error popup container (generic, no hardcoded invoice number)
	 //   @FindBy(xpath = "//div[@role='status' and contains(text(),'has completed secondary approval and cannot be re-run.']")
	    
	    @FindBy(xpath="//div[@role='status' and contains(normalize-space(.), '%s')]")
	    private WebElement errorPopup;
	    
		
	    public void clickOnRerunInvoiceTab() {
	    	click(reRunInvoiceTab);
	    }
	    
	    //Click on Work Center tab
	    public void clickOnWorkCentertab() {
	    	click(workCenterTab);
	    }
	    
	    //Click on Invoice Inquiry tab
	    public void clickOninvoiceInquirytab() {
	    	click(invoiceInquiryTab);
	    }
	    
	    //Rerun text validation
	    public String validateReruninvoiceHeaderText() {
	    	return reRunInvoicesHeaderText.getText();
	    }
	    
		
	    

	    // Method to select radio based on option
	    public void selectOption(String option) {
	        if (option.equalsIgnoreCase("Duration") || option.equalsIgnoreCase("By Duration")) {
	        	if (!radioByDuration.isSelected()) {
	        		clickOnButton(radioByDuration);
	            }
	        } else if (option.equalsIgnoreCase("Invoice") || option.equalsIgnoreCase("Invoice Number")) {
	        	if (!radioByInvoice.isSelected()) {
	        		clickOnButton(radioByInvoice);
	            }
	        } else {
	            throw new IllegalArgumentException("Invalid option: " + option + 
	                ". Use 'Duration' or 'Invoice'.");
	        }
	    }

	    // Optional: check which is selected
	    public boolean isDurationSelected() {
	        return radioByDuration.isSelected();
	    }

	    public boolean isInvoiceSelected() {
	        return radioByInvoice.isSelected();
	    }
	    
	    //Rerun description text validation
	    public String validateDescriptionButtonText() {
	    	System.out.println("Description of Radio Button text: "+descriptionOfRadioButtonText.getText());
	    	return descriptionOfRadioButtonText.getText();
	    }
	 
	    
	    //Rerun description text validation
	    public String validateInvoiceTypeText() {
	    	System.out.println("Invoice Type text: "+ invoiceTypeText.getText());
	    	String rawText = invoiceTypeText.getText().trim();  // e.g. "Invoice Type*"
	        return rawText.replace("*", "").trim();    
	    	
	    	//return invoiceTypeText.getText();
	    }
	    
	    /**
	     * Select checkbox by name (Inbound, Outbound, Storage)
	     */
	    public void selectCheckbox(String name) {
	        WebElement[] checkboxes = {inboundInvoiceType, outboundInvoiceType, storageInvoiceType};
	        String[] names = {"Inbound", "Outbound", "Storage"};

	        for (int i = 0; i < names.length; i++) {
	            if (names[i].equalsIgnoreCase(name.trim())) {
	                if (buttonIsEnabled(checkboxes[i])) {
	                    click(checkboxes[i]);
	                    Assert.assertTrue(checkboxes[i].isSelected(),
	                            "Checkbox [" + names[i] + "] should be selected but isn't.");
	                    System.out.println("Checkbox [" + names[i] + "] selected successfully.");
	                } else {
	                    Assert.fail("Checkbox [" + names[i] + "] is disabled.");
	                }
	                break; // stop after finding the match
	            }
	        }
	    }
	    
	    
	    // Deselect checkbox by name
	    public void deselectCheckbox(String name) {
	        WebElement[] checkboxes = {inboundInvoiceType, outboundInvoiceType, storageInvoiceType};
	        String[] names = {"Inbound", "Outbound", "Storage"};

	        for (int i = 0; i < names.length; i++) {
	            if (names[i].equalsIgnoreCase(name.trim())) {
	                if (checkboxes[i].isSelected()) {
	                    checkboxes[i].click();
	                    System.out.println("✅ Checkbox [" + names[i] + "] deselected.");
	                }
	                break;
	            }
	        }
	    }

	    /**
	     * Check if checkbox is selected
	     */
	    public boolean isCheckboxSelected(String name) {
	        WebElement[] checkboxes = {inboundInvoiceType, outboundInvoiceType, storageInvoiceType};
	        String[] names = {"Inbound", "Outbound", "Storage"};

	        for (int i = 0; i < names.length; i++) {
	            if (names[i].equalsIgnoreCase(name.trim())) {
	                return checkboxes[i].isSelected();
	            }
	        }
	        throw new IllegalArgumentException("Invalid checkbox name: " + name);
	    }
	    
	    
	 // Selecting From Date
	    public void selectFromDate(String fromMonthYear, String fromDay) {
	        fromDatePickerContainer.click(); // open From Date calendar
	        datePicker.selectFromDate(fromMonthYear, fromDay); // select the date
	    }

	    // Selecting To Date
	    public void selectToDate(String toMonthYear, String toDay) {
	        toDatePickerContainer.click(); // open To Date calendar
	        datePicker.selectToDate(toMonthYear, toDay); // select the date
	    }
	    
	    public boolean validateRerunInboundTypeEnabled() {
	    	return fieldIsEnabled(inboundInvoiceType);
	    }
	    
	    public boolean validateRerunOutboundTypeEnabled() {
	    	return fieldIsEnabled(outboundInvoiceType);
	    }
	    
	    public boolean validateRerunStorageTypeEnabled() {
	    	return fieldIsEnabled(storageInvoiceType);
	    }
	    
	    
	    public boolean validateRerunLocationFilterEnabled() {
	    	return fieldIsEnabled(rerunLocationFilter);
	    }
	    
	    public boolean validateRerunCustomerFilterEnabled() {
	    	return fieldIsEnabled(rerunCustomerFilter);
	    }
	    
	    public boolean validateRerunLocationFilterDisabled() {
	    	return fieldIsDisabled(rerunLocationFilter);
	    }
	    
	    public boolean validateRerunCustomerFilterDisabled() {
	    	return fieldIsDisabled(rerunCustomerFilter);
	    }
	    
	    public boolean validateRerunSubmitButtonEnabled() {
	    	return fieldIsEnabled(rerunSubmitButton);
	    }
	    
	    public boolean validateRerunSubmitButtonDisabled() {
	    	return fieldIsDisabled(rerunSubmitButton);
	    }
	    
	    public void clickOnConfiramtionPopupSubmitButton() {
	    	click(confirmationPupupSubmitButton);
	    }
	    public void clickOnConfiramtionPopupCancelButton() {
	    	click(confirmationPupupCancelButton);
	    }
	    
	    public void clickOnRerunSubmitButton() {
	    	clickOnButton(rerunSubmitButton);
	    }
	    
	    public String validateConfimationTextOnPopup() {
	    	return confirmChangeText.getText();
	    }
	    
	    public String validatedescriptionOfConfimationTextOnPopup() {
	    	return descriptionOfconfirmChangeText.getText();
	    }
	    
	    public String validateInvoiceNumberText() {
	    	return inputInvoiceNumber.getText();
	    }
	    
	    public void enterInvoiceNumber(String invoiceNum) {
	    	inputInvoiceNumber.sendKeys(invoiceNum);
	    }
	    
	    public String geteEnteredInvoiceNumber() {
	    	return inputInvoiceNumber.getAttribute("value").trim();
	    }
	    
	  //Click On Apply button 
	    public void clickOnApplyButton() {
	    	clickOnButton(applyButton);
	    }
	    
	    //Invoices Inquiry advance filters
	    public void rerunInvoiceAdvanceFilters(String location, String customer 
	    		) throws InterruptedException {
    		WebElement[] fields = {
    				rerunLocationFilter, rerunCustomerFilter,};

    		String[] values = {location, customer };

    		for (int i = 0; i < fields.length; i++) {
    			selectDropdownValue(fields[i], values[i]);
    			}
			}
	    
	    
	  //Invoices Inquiry advance filters
	    public void invoiceInquiryAdvanceFilters(String type, String location, String customer, 
	    		String status) throws InterruptedException {
    		WebElement[] fields = {
    				wcTypeFilter, wcLocationFilter, wcCustomerFilter, wcStatusFilter };

    		String[] values = { type, location, customer, status};

    		for (int i = 0; i < fields.length; i++) {
    			selectDropdownValue(fields[i], values[i]);
    				}
				}
	    
	    
	    //Capture Generated Invoice Number
	    public String captureGeneratedInvoiceNumber() {
	        String invoiceNum = wait.until(ExpectedConditions.visibilityOf(firstInvoiceNumber)).getText().trim();
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
			        return globalSearchBox.getAttribute("value").trim();
			    }
			    
	    
			    
		//The Invoice has been created Successfully popup displayed
		public boolean validateRerunInvoiceHasBeenCreatedsuccessfullyPopupDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(popupRerunInvoiceProcessedSuccessfully));
		return popupRerunInvoiceProcessedSuccessfully.isDisplayed();
		}
		
		//The Invoice has been created Successfully popup displayed
		public String getRerunSuccessPopupMessage() {
		wait.until(ExpectedConditions.visibilityOf(popupRerunInvoiceProcessedSuccessfully));
		return popupRerunInvoiceProcessedSuccessfully.getText().trim();
		}
		
		
		public String getErrorPopupMessage() {
	        return rerunErrorPopup.getText().trim();
	    }
		
		public void clickOnBellIconToOpenNotifications() {
			click(notificationBellIcon);
		}
		
		
		// ✅ Get the invoice number currently entered in the Rerun Invoice input
		public String getRerunInvoiceNumber() {
		    return inputInvoiceNumber.getAttribute("value");
		}
		
		/*
		// Wait for specific invoice notification to appear
	    public void waitForInvoiceNotification(String invoiceNo) {
	        String xpath = "//span[contains(@class,'notification-text') and contains(text(),'" + invoiceNo + "')]";
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	    }
	    */
	    
	    // ✅ Wait for any notification to appear
	    public void waitForAnyNotification() {
	    	wait.until(ExpectedConditions.visibilityOf(latestSuccessNotificationText));
	      //  wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//span[contains(@class,'notification-text')]")));
			System.out.println("🔔 A notification appeared.");
	    }
	    
	    
	    // ✅ Wait for any notification to appear
	    public void waitForCountNotification() {
	    	wait.until(ExpectedConditions.visibilityOf(notificationCount));
	      //  wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//span[contains(@class,'notification-text')]")));
			System.out.println("🔔 A notification appeared.");
	    }




		    /**
		     * Click notification header for a given invoice number
		     * @param invoiceNo invoice number to match
		     */
		    public void clickNotificationForInvoice(String invoiceNo) {
		        for (int i = 0; i < notificationTexts.size(); i++) {
		            String notifText = notificationTexts.get(i).getText();
		            if (notifText.contains(invoiceNo)) {
		                notificationHeaders.get(i).click(); // click green header
		                break;
		            }
		        }
		    }

		    /**
		     * Get truncated notification text for a specific invoice
		     */
		    public String getTruncatedTextForInvoice(String invoiceNo) {
		        for (int i = 0; i < notificationTexts.size(); i++) {
		            String text = notificationTexts.get(i).getText();
		            if (text.contains(invoiceNo)) {
		                return text;
		            }
		        }
		        return null;
		    }

		    /**
		     * Click "More" button to expand full text for a specific invoice
		     */
		    public String getFullTextForInvoice(String invoiceNo) {
		        for (int i = 0; i < notificationTexts.size(); i++) {
		            String text = notificationTexts.get(i).getText();
		            if (text.contains(invoiceNo)) {
		                // click "More" button if present
		                if (moreButtons.size() > i) {
		                    moreButtons.get(i).click();
		                }
		                return notificationTexts.get(i).getText();
		            }
		        }
		        return null;
		    }

		    /**
		     * Get date/time for a specific invoice notification
		     */
		    public String getNotificationTimeForInvoice(String invoiceNo) {
		        for (int i = 0; i < notificationTexts.size(); i++) {
		            String text = notificationTexts.get(i).getText();
		            if (text.contains(invoiceNo)) {
		                if (notificationTimes.size() > i) {
		                    return notificationTimes.get(i).getText();
		                }
		            }
		        }
		        return null;
		    }
		

		 // 1️⃣ Get truncated text for given invoice number
		    public String getTruncatedText(String invoiceNo) {
		        String xpath = "//span[contains(@class,'notification-text') and contains(text(),'" + invoiceNo + "')]";
		        WebElement notif = driver.findElement(By.xpath(xpath));
		        return notif.getText().trim();
		    }

		    // 2️⃣ Get full text after clicking 'More'
		    public String getFullText(String invoiceNo) {
		        String moreXpath = "//span[contains(@class,'notification-text') and contains(text(),'" + invoiceNo + "')]/following::small[text()='More'][1]";
		        WebElement moreBtn = driver.findElement(By.xpath(moreXpath));
		        moreBtn.click();

		        String fullXpath = "//span[contains(@class,'notification-text') and contains(text(),'" + invoiceNo + "')]";
		        WebElement fullTextElem = driver.findElement(By.xpath(fullXpath));
		        return fullTextElem.getText().trim();
		    }

		    // 3️⃣ Get notification datetime for given invoice number
		    public String getNotificationTime(String invoiceNo) {
		        String timeXpath = "//span[contains(@class,'notification-text') and contains(text(),'" + invoiceNo + "')]/following::small[contains(@class,'notification-time')][1]";
		        WebElement timeElem = driver.findElement(By.xpath(timeXpath));
		        return timeElem.getText().trim();
		    }
		    
		 // ✅ Wait until latest notification timestamp updates
		    public void waitForLatestNotificationTime(String oldTime) {
		        wait.until(driver -> {
		            String newTime = notificationTimes.get(0).getText().trim();
		            return !newTime.equals(oldTime);  // wait until it changes
		        });
		    }
		    
		 // ✅ Get latest notification timestamp (topmost notification)
		    public String getLatestNotificationTime() {
		        return notificationTimes.get(0).getText().trim();
		    }

		    // ✅ Get latest notification text (short or full)
		    public String getLatestNotificationText() {
		        return notificationTexts.get(0).getText().trim();
		    }

		    // 4️⃣ Click notification header (success text) for given invoice number
		    public void clickNotificationHeader(String invoiceNo) {
		        String headerXpath = "//span[contains(@class,'notification-text') and contains(text(),'" + invoiceNo + "')]/preceding::span[contains(text(),'Rerun invoice completed successfully.')][1]";
		        WebElement header = driver.findElement(By.xpath(headerXpath));
		        header.click();
		    }
		    
		    public void clickOnClearAllNotificationButton() {
		        try {
		            if (clearAllNotificationButton.isDisplayed()) {
		                clickOnButton(clearAllNotificationButton);
		                System.out.println("✅ Clear All button clicked.");
		            } else {
		                System.out.println("ℹ️ Clear All button not visible, waiting for notification...");
		                waitForAnyNotification();
		            }
		        } catch (NoSuchElementException e) {
		            System.out.println("ℹ️ Clear All button not present, waiting for notification...");
		            waitForAnyNotification();
		        }
		    }
		    
		    
		 	//To get row count
		    public int getRowCount() {
		        return tableRows.size();
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
		    
		    public void clearGlobalSearchField() throws InterruptedException {
		    	clearOnElement(globalSearchBox);
		    	Thread.sleep(2000);  // wait for results
		    }
		    
		    public void clickOnActionRowButton() throws InterruptedException {
		    	clickOnButton(actionRowButton);
		    	Thread.sleep(2000);  // wait for results
		    }
		    
		 // Method to click an action by name (Approve, Details, Activity, etc.)
		    public void clickAction(String actionName) {
		        String actionXpath = "//div[contains(@class,'action-dropdown')]//button[normalize-space()='" + actionName + "']";
		        WebElement actionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(actionXpath)));
		         actions.moveToElement(actionButton);
		        clickOnButton(actionButton);
		        System.out.println("✅ Clicked on action: " + actionName);
		    }
		    
		  //The Invoice has been created Successfully popup displayed
			public String getRerunInvoicePrimaryApprovedSuccessPopupMessage() {
			wait.until(ExpectedConditions.visibilityOf(invoicePrimaryApprovedStatus));
			return invoicePrimaryApprovedStatus.getText().trim();
			}
		    
	    public void clickOnConfirmButton() {
	    	clickOnButton(confirmButton);
	    }
	    
	    
	    /**
	     * Clicks the bell only if count > 0, then clicks Clear All
	     */
	    public void clearNotificationsIfPresent() {
	        try {
	            if (!notificationCountBadge.isEmpty()) {
	                String count = notificationCountBadge.get(0).getText().trim();

	                if (!count.isEmpty() && Integer.parseInt(count) > 0) {
	                    clickOnButton(notificationBellIcon);
	                    System.out.println("🔔 Bell clicked. Notifications count: " + count);
	                    actions.moveToElement(clearAllNotificationButton).click().build().perform();
	                    System.out.println("✅ Clear All clicked using move to Element method");
	                    System.out.println("✅ Clear All clicked.");
	                } else {
	                    System.out.println("ℹ️ Bell shows no count → nothing to clear.");
	                }
	            } else {
	                System.out.println("ℹ️ No notification count badge found → nothing to clear.");
	            }
	        } catch (Exception e) {
	            System.out.println("⚠️ Error while clearing notifications: " + e.getMessage());
	        }
	    }
	    

	    //  Method: Wait for invoice error popup and return text
	    public String waitForInvoiceErrorPopup01(String invoiceNo) {
	        wait.until(ExpectedConditions.visibilityOf(errorPopup));
	        String popupText = errorPopup.getText().trim();

	        // Validate if it belongs to the correct invoice
	        if (!popupText.contains(invoiceNo)) {
	            throw new AssertionError("❌ Popup message does not match invoice number: " + invoiceNo  + " | Actual: " + popupText);
	        }
	        return popupText;
	    }
	    
	 
	    public void validateErrorPopupMessage(String expectedText) {
	        // This XPath will match any popup that has the given message text
	        String dynamicXpath = String.format(
	            "//div[@role='status' and contains(normalize-space(.), '%s')]",
	            expectedText
	        );

	        try {
	            // Wait for the element to appear and be visible
	            WebElement popupMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));
	            String actualMsg = popupMsg.getText();
	            System.out.println("Error Popup Message: " + actualMsg);
	            
	            Assert.assertTrue(actualMsg.contains(expectedText),
	                "Expected text not found!\nExpected: " + expectedText + "\nActual: " + actualMsg);
	            
	        } catch (Exception e) {
	            Assert.fail("Error popup not found or not visible after Submit: " + e.getMessage());
	        }
	    }
	    


	    
	    public String waitForInvoiceErrorPopup(String invoiceNo) {
	        String xpath = "//div[@role='status' and contains(text(),\"Invoice number '" + invoiceNo + "'\")]";
	        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	        return popup.getText();
	    }
	    
	    public String waitForInvoiceErrorPopupVoidedInvoice(String invoiceNo) {
	        String xpath = "//div[@role='status' and contains(text(),\"Invoice number '" + invoiceNo + "'\")]";
	        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	        return popup.getText();
	    }
			
}
