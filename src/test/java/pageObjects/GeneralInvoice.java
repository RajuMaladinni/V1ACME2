package pageObjects;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.DatePickerUtil;

public class GeneralInvoice extends BasePage {
	
	DatePickerUtil datePicker;
	
	public GeneralInvoice(WebDriver driver) throws AWTException {
		super(driver);
		datePicker = new DatePickerUtil(driver);
		
	}
	
			// === Locators ===
			@FindBy (xpath="//span[contains(text(),'General Invoice')]")
			WebElement generalInvoiceTab;
			
			@FindBy (xpath = "//div[@class='d-flex flex-column']/h4[text()='General Invoice']")
			WebElement generalInvoiceHeader;
			
			@FindBy(xpath= "//div[@class='react-datepicker__input-container']")
			WebElement datePicInputContainer;
			   
		    @FindBy (xpath ="//input[@placeholder='MM/DD/YYYY']")
		    WebElement genInvdatePicker;	    

			@FindBy(xpath="//button[@aria-label='Previous Month']")
			WebElement previousMonthButton;
			
			@FindBy(xpath="//button[@aria-label='Next Month']")
			WebElement nextMonthButton;
		    
		    @FindBy (xpath="//div[@class='react-datepicker__current-month react-datepicker__current-month--hasYearDropdown']")
		    WebElement currentMonthYear;
		
		    @FindBy (xpath= "//span[@class='react-datepicker__year-read-view--selected-year']")
		    WebElement currentYear;
			
		    @FindBy (xpath="//label[contains(text(),'Location')]/following-sibling::div//div[contains(@class,'select__control')]")
		    WebElement locationDropdown ;
		    
		    @FindBy(xpath ="//label[contains(text(),'Location')]/following-sibling::div//div[contains(@class,'select__control')]//input[@class='react-select__input']")
		    WebElement locationField;
		   
		    @FindBy (xpath="//label[contains(text(),'Customer')]/following-sibling::div//div[contains(@class,'select__control')]")
		    WebElement customerDropdown;
		    
		    @FindBy(xpath ="//label[contains(text(),'Customer')]/following-sibling::div")
		    WebElement customerField;
		    
		    @FindBy(xpath="//label[contains(text(),'Category')]/following-sibling::div//div[contains(@class,'select__control')]")
		    WebElement categoryDropdown;
		    
		    @FindBy(xpath ="//label[contains(text(),'Category')]/following-sibling::div//div[contains(@class,'select__control')]//input[@class='react-select__input']")
		    WebElement categoryField;
		    
		    @FindBy (xpath="//label[contains(text(),'Sub Category')]/following-sibling::div//div[contains(@class,'select__control')]")
		    WebElement subCategoryDropdown;
		    
		    @FindBy(xpath ="//label[contains(text(),'Sub Category')]/following-sibling::div")
		    WebElement subCategoryField;
		    
		    @FindBy (xpath="//label[contains(text(),'Rate By')]/following-sibling::div//div[contains(@class,'select__control')]")
		    WebElement rateByDropdown;
		    
		    @FindBy(xpath ="//label[contains(text(),'Rate By')]/following-sibling::div//div[contains(@class,'select__control')]//input[@class='react-select__input']")
		    WebElement rateByField;
		    
		    @FindBy (xpath= "//input[@placeholder='UoM']")
		    WebElement uomField;
		    
		    @FindBy (xpath ="//input[@id='quantity_id']")
		    WebElement quantityField;
		    
		    @FindBy (xpath = "//input[@id='rate_label']")
		    WebElement rateField;
		    
		    @FindBy (xpath= "//input[@name='charge']")
		    WebElement chargeField;
		    
		    @FindBy (xpath ="//textarea[@name='comment']")
		    WebElement commentField;
		    
		    @FindBy (xpath = "//button[contains(text(),'Clear')]")
		    WebElement clearButton;

		    @FindBy (xpath ="//button[@type='button' and contains(text(),'+ Add Line Item')]")
		    WebElement addLineItemButton;
		    
		    @FindBy(xpath= "//button[@type='button' and contains(text(),'Update Line Item')]")
		    WebElement updateLineItemButton;
		    
		    @FindBy(xpath ="//button[@type='submit' and contains(text(),'Update')]")
		    WebElement updatePopupButton;
		    
		    @FindBy (xpath ="//button[@type='button' and contains(text(),'Save Invoice Header')]")
		    WebElement saveInvoiceHeaderButton;
		    
		    @FindBy (xpath ="//button[@class='ms-2 border-0 text-white bg-primary btn btn-secondary']")
		    WebElement saveInvoiceHeaderBtn;
		    
		    @FindBy(xpath= "//div[@class='modal-content']")
		    WebElement popUp;
		    
		    @FindBy(xpath= "//div[contains(text(), 'Confirm Clear Form')]")
		    WebElement confirmClearFormPopUpText;
		    
		    @FindBy(xpath= "//div[contains(text(), 'Are you sure you want to clear form?')]")
		    WebElement descriptionOfConfirmClearFormPopUpText;
		    
		    @FindBy(xpath="//button[@type='submit' and contains(text(), 'Clear')]")
		    WebElement clearPopupButton;
		    
		    @FindBy(xpath= "//div[contains(text(), 'Confirm Invoice Line Item Save')]")
		    WebElement confirmInvoiceLineItemSavePopupText;
		    
		    @FindBy(xpath= "//div[contains(text(), 'Are you sure you want to save the invoice line item with new line item?')]")
		    WebElement confirmInvoiceLineItemSaveDescriptionPopuText;
		    
		    @FindBy(xpath = "//div[@class='text-center px-4 pt-4 modal-body']//div[contains(text(),'Confirm Invoice Header Save')]")
		    WebElement textAreaConfirmationInvoiceHeaderSave;
		    
		    @FindBy (xpath= "//div[@class='text-center px-4 pt-4 modal-body']//div[contains(text(),'Are you sure you want to save the invoice header with new line items?')]")
		    WebElement textAreaConfirmationPopUp;
		    
		    @FindBy(xpath= "//div[@class='border-0 pb-4 pt-2 justify-content-center modal-footer']/button[(contains(text(), 'Cancel'))]")
		    WebElement cancelButton;
		    
		    @FindBy(xpath= "//div[@class='border-0 pb-4 pt-2 justify-content-center modal-footer']/button[(contains(text(), 'Save'))]")
		    WebElement saveButton;
		    
		    @FindBy(xpath= "//div[@role='status' and contains(text(), 'The invoice has been created successfully.')]")
		    WebElement theInvoiceHasbeenCreatedSuccessfullyPopup;
		    
		    @FindBy(xpath = "//button[@type='button']/*[name()='svg' and @class='text-warning cursor-pointer']")
		    WebElement editCursorPointer;
		    
		    @FindBy(xpath = "//div[@class='d-flex']//*[name()='svg' and @class='text-danger']")
		    WebElement deleteCursorPointer;
		    
		    @FindBy(xpath= "//div[@class='modal-content']//i[@class='bi fs-3 text-danger bi-exclamation-triangle-fill']")
		    WebElement warningIconOnConfimationPopup;
		    
		    @FindBy(xpath = "//div[contains(text(),'Confirm Delete')]")
		    WebElement confirmDeleteTextOnPopup;
		    
		    @FindBy(xpath="//div[contains(text(),'Are you sure you want to delete this item?')]")
		    WebElement desciptionOfconfirmDeleteTextOnPopup;
		    
		    @FindBy(xpath= "//button[@type='button' and text()='Cancel']")
		    WebElement confirmationPopUpCancelButton;
		    
		    @FindBy(xpath="//button[@type='submit' and text()='Delete']")
		    WebElement confirmationPopUpDeleteButton;
		    
		    @FindBy(xpath = "//div[@role='status' and contains(text(), 'All items cleared successfully')]")
		    WebElement allItemsClearedSuccessfullyPopup;
		    
		    @FindBy(xpath = "//div[@role='status' and contains(text(), 'All items cleared successfully')]")
		    WebElement AllitemsclearedsuccessfullyPopup;
		    
		    @FindBy(xpath = "//table//tbody/tr")
		    List<WebElement> tableRows;

		    @FindBy(xpath = "//button[@aria-label='single-next-page']")
		    WebElement nextBtn;
		    
		    @FindBy(xpath="//h6[contains(text(),'No Records Available')]")
		    WebElement noRecordsAvailable;
		    
		    @FindBy(xpath = "//div[@role='status' and contains(text(), 'The invoice has been created successfully')]")
		    WebElement theInvoiceHasBeenCreatedSuccessfullyPopup;
		    
		    @FindBy(xpath = "//div[@class='dropdown show']")
		    WebElement actionButton;
		    
		    @FindBy(xpath= "//div[@class='invoice-dropdown border action-dropdown dropdown-menu show']//button[@type='button']")
		    List<WebElement> actionsLists;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement exportOption;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement exportOptio;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement exportOpti;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement exportOtion;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement expotOption;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement exortOption;
		    
		    @FindBy(xpath="//div[@class=\"invoice-dropdown border action-dropdown dropdown-menu show\"]//span[text()='Export']")
		    WebElement exporOption;
		    
		    
		    
		    
		    //Click on General Invoice Tab
		    public void clickOnGeneralInvoiceTab() {
		    	wait.until(ExpectedConditions.elementToBeClickable(generalInvoiceTab));
		    	 generalInvoiceTab.click();
		    }
		    
		    //Validate General Invoice Header text
		    public String validateGeneralInvoiceHeader() {
		    	 return generalInvoiceHeader.getText();
		    
		    }
		    
		    //Verification of General Invoice fields 
		    public boolean locationFieldEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(locationField));
		    	return locationField.isEnabled();
		    }
		    
		    public boolean customerFieldDisabled() {
		    	wait.until(ExpectedConditions.visibilityOf(customerField));
		    	return customerField.getAttribute("class").contains("disabled");
		    }
		    
		    
		    public boolean categoryFieldEnabeld() {
		    	wait.until(ExpectedConditions.elementToBeClickable(categoryField));
		    	return categoryField.isEnabled();
		    }
		    
		    public boolean subCategoryFieldDisabled() {
		    	wait.until(ExpectedConditions.visibilityOf(subCategoryField));
		    	return subCategoryField.getAttribute("class").contains("react-select--is-disabled");
		    }
		    
		    public boolean dateFieldEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(datePicInputContainer));
		    	return datePicInputContainer.isEnabled();
		    }
		    
		    
		    public boolean rateByFieldEnabeld() {
		    	wait.until(ExpectedConditions.elementToBeClickable(rateByField));
		    	return rateByField.isEnabled();
		    }
		    
		    public boolean uomFieldDisabled() {
		    	wait.until(ExpectedConditions.visibilityOf(uomField));
		    	return !uomField.isEnabled();
		    }
		    
		    public boolean quantityFieldEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(quantityField));
		    	return quantityField.isEnabled();
		    }
		    
		    public boolean rateFieldEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(rateField));
		    	return rateField.isEnabled();
		    }
		    
		    public boolean chargeFieldDisabled() {
		    	wait.until(ExpectedConditions.visibilityOf(chargeField));
		    	return !chargeField.isEnabled();
		    }
		    
		    public boolean commentFieldEnabled() {
		    	wait.until(ExpectedConditions.visibilityOf(commentField));
		    	return commentField.isEnabled();
		    }
		    
		    //click on Date Picker Container
		    public void clickOnDatePickerContainer() {
				datePicInputContainer.click();
			}
		    
		    //Select date 
		    public void selectDateFromGenInv(String monthYear, String day) {
		    	while(true) {
					
					String actualMonthandYear = currentMonthYear.getText();
					
					if(actualMonthandYear.equals(monthYear)) {
						break;	
					}
					
					//driver.findElement(By.xpath("//button[@aria-label='Next Month']")).click(); //Front click on next either one will work
					previousMonthButton.click(); //back click for previous 

				}
				
				List<WebElement> dateList = driver.findElements
						(By.xpath("//div[@class='react-datepicker__month']/div[@class='react-datepicker__week']/div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month'))]"));
	 			//List<WebElement> dateList = driver.findElements(By.xpath("//div[@class='react-datepicker__month']/div[@class='react-datepicker__week']/div[@role='option']"));
				
					for (WebElement dt : dateList) {
					
						if(dt.getText().equals(day)) {
						
							dt.click();
							break;
						}
					}
				
				System.out.println("Date selection done");
				
			}
		    
		    /*
		    //fill Invoice form for single line item - Manual
		    public void fillInvoiceFormOnce(String location, String customer, String category, String subCategory,
		            String rateBy, String quantity, String rate, String comment) throws InterruptedException {

					selectDropdownAction(locationDropdown, location);
					selectDropdownAction(customerDropdown, customer);
					selectDropdownAction(categoryDropdown, category);
					selectDropdownAction(subCategoryDropdown, subCategory);
					selectDropdownAction(rateByDropdown, rateBy);
					
					
					quantityField.sendKeys(quantity);
					rateField.sendKeys(rate);
					commentField.sendKeys(comment);
					}
		  */
				
			         
		    //Filling invoice form using Excel Data and Manual Invoice creation 
		    public void fillInvoiceForm(String location, String customer, String category, String subCategory,
		            String rateBy, String quantity, String rate, String comment) throws InterruptedException {

		    	selectDropdownValuesFromExcelSheet(locationDropdown, location);
		    	selectDropdownValuesFromExcelSheet(customerDropdown, customer);
		    	selectDropdownValuesFromExcelSheet(categoryDropdown, category);
		    	selectDropdownValuesFromExcelSheet(subCategoryDropdown, subCategory);
		    	selectDropdownValuesFromExcelSheet(rateByDropdown, rateBy);
					
					
					quantityField.sendKeys(quantity);
					rateField.sendKeys(rate);
					commentField.sendKeys(comment);
					}
		    
		    
		    //Add Line Item Button Enabled
		    public boolean validateAddLineItemButtonEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(addLineItemButton));
		    	return addLineItemButton.isEnabled();
		    }
		    
		    //Add Line Item Button Disabled
		    public boolean validateAddLineItemButtonDisabled() {
		    	wait.until(ExpectedConditions.visibilityOf(addLineItemButton));
		    	return addLineItemButton.getAttribute("class").contains("disabled");
		    }
		    	
		    //click on Add line item button
			public void clickOnAddLineItem() {
			wait.until(ExpectedConditions.elementToBeClickable(addLineItemButton));
			addLineItemButton.click();
			}
			
			//Confirm Invoice Line Item Save popu Text
			public String validateConfirmInvoiceLineItemSavePopupText() {
				wait.until(ExpectedConditions.visibilityOf(confirmInvoiceLineItemSavePopupText));
				return confirmInvoiceLineItemSavePopupText.getText().trim();
			}
			
			//Confirm InvoiceLine Item Save description popup
			public String validateConfirmInvoiceLineItemSaveDescriptionPopupText() {
				wait.until(ExpectedConditions.visibilityOf(confirmInvoiceLineItemSaveDescriptionPopuText));
				return confirmInvoiceLineItemSaveDescriptionPopuText.getText().trim();
			}
			
			
			
			//Update Line Item Button Enabled		
			public boolean validateUpdateLineItemButtonEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(updateLineItemButton));
		    	return updateLineItemButton.isEnabled();
		    }
			
			//click on Update Line Item Button
			public void clickOnUpdateLineItemButton() {
				wait.until(ExpectedConditions.elementToBeClickable(updateLineItemButton));
				updateLineItemButton.click();
			}
				
			//Save Invoice Header Button Enabled
			 public boolean validateSaveInvoiceHeaderButtonEnabled() {
			    	wait.until(ExpectedConditions.elementToBeClickable(saveInvoiceHeaderButton));
			    	return saveInvoiceHeaderButton.isEnabled();
			    }
			 
			//Save Invoice Header Button Disabled
			 public boolean validateSaveInvoiceHeaderButtonDisabled() {
			    	wait.until(ExpectedConditions.visibilityOf(saveInvoiceHeaderButton));
			    	return saveInvoiceHeaderButton.getAttribute("class").contains("disabled");
			    }
					
			//click on saveInvoice Header button
			 public void clickOnSaveInvoiceHeader() {
				 wait.until(ExpectedConditions.visibilityOf(saveInvoiceHeaderButton));
					saveInvoiceHeaderButton.click();
					}	
			 
			 //Clear Button Enabled		
			public boolean validateClearButtonEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(clearButton));
		    	return clearButton.isEnabled();
		    }		
					
			//click on clear button
			public void clickOnClearButton() {
				clearButton.click();;
			}
			
			//Confirm Clear Form header Popup Text
		    public  String validateConfirmClearFormHeaderTextOnClearPopup() {
		    	 return confirmClearFormPopUpText.getText().trim();	    	
		    }
		    
		    //Confirm Confirm Clear Form description Popup Text
		    public  String validateConfirmClearFormDescriptionTextOnClearPopup() {
		    	return descriptionOfConfirmClearFormPopUpText.getText().trim();
		    }
		    
		    //Clear Popup button enabled
		    public boolean validateClearPopupButtonEnabled() {
		    	wait.until(ExpectedConditions.visibilityOf(clearPopupButton));
		    	return clearPopupButton.isEnabled();
		    }
		    
		    //click on Clear Popup Button
		    public void clickOnClearPopupButton() {
		    	wait.until(ExpectedConditions.elementToBeClickable(clearPopupButton));
		    	clearPopupButton.click();
		    }
		    
		    
			//Confirmation popUp displayed
			public boolean validateConfirmationPopupDisplayed() {
				wait.until(ExpectedConditions.visibilityOf(popUp));
				return popUp.isDisplayed();
			}
			
			//Confirm Invoice header Popup Text
		    public  String validateConfirmInvoiceHeaderTextOnConfirmPopup() {
		    	 return textAreaConfirmationInvoiceHeaderSave.getText().trim();	    	
		    }
		    
		    //Confirm Invoice header description Popup Text
		    public  String validateConfirmInvoiceHeaderDescriptionTextOnConfirmationPopup() {
		    	return textAreaConfirmationPopUp.getText().trim();
		    	
		    }
		    
		  //Save button Displayed
			public boolean validateSaveButtonDisplayed() {
			    wait.until(ExpectedConditions.elementToBeClickable(saveButton));
			    return saveButton.isDisplayed();
			}
			
			//Cancel button Displayed
			public boolean validateCancelButtonDisplayed() {
			    wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
			    return cancelButton.isDisplayed();
			}
		   	
			 // Confirm Save button click
				public void clickOnSaveButton() {
				    try {
				        wait.until(ExpectedConditions.elementToBeClickable(saveButton)); // Wait until popup is visible
				        if (saveButton.isDisplayed()) {
				            saveButton.click();
				            System.out.println("Save button clicked on confirmation popup.");
				        }
				    } catch (Exception e) {
				        System.out.println("Save popup not displayed: " + e.getMessage());
				    }
				}
			    
			    
				// Cancel button click
				public void clickOnCancelButton() {
				    try {
				        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)); // Wait until popup is visible
				        if (cancelButton.isDisplayed()) {
				            cancelButton.click();
				            System.out.println("Cancel button clicked on confirmation popup.");
				        }
				    } catch (Exception e) {
				        System.out.println("Cancel popup not displayed: " + e.getMessage());
				    }
				}
				
			//The Invoice has been created Successfully popup displayed
			public boolean validateTheInvoiceHasBeenCreatedsuccessfullyPopupDisplayed() {
				wait.until(ExpectedConditions.visibilityOf(theInvoiceHasbeenCreatedSuccessfullyPopup));
				return theInvoiceHasbeenCreatedSuccessfullyPopup.isDisplayed();
			}
			
			//The Invoice has been created Successfully popup Text
			public String validateTheInvoiceHasBeenCreatedsuccessfullyPopupText() {
				wait.until(ExpectedConditions.visibilityOf(theInvoiceHasbeenCreatedSuccessfullyPopup));
				return theInvoiceHasbeenCreatedSuccessfullyPopup.getText();
			}
		    
			
			//scroll to Delete Cursor point
			public void scrollToDeleteCursorButton() {
				scrollToElement(deleteCursorPointer);
			}
	
		    //Edit Cursor Pointer Enabled
		    public boolean validateEditCursorPointerEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(editCursorPointer));
		    	return editCursorPointer.isEnabled();
		    }
		    
		    //Click on Edit Cursor Pointer
		    public void clickOnEditCursorPointer() {
		    	wait.until(ExpectedConditions.elementToBeClickable(editCursorPointer));
		    	editCursorPointer.click();
		    }
		    
		    //Delete Cursor Pointer Enabled
		    public boolean validateDeleteCursorPointerEnabled() {
		    	wait.until(ExpectedConditions.elementToBeClickable(deleteCursorPointer));
		    	return deleteCursorPointer.isEnabled();
		    }
		    
		    //Click on Edit Cursor Pointer
		    public void clickOnDeleteCursorPointer() {
		    	wait.until(ExpectedConditions.elementToBeClickable(deleteCursorPointer));
		    	deleteCursorPointer.click();
		    }
		    
		    
		    //Warning popup displayed
		    public boolean validateWarningIconOnConfirmationPopupDisplayed() {
		    	wait.until(ExpectedConditions.visibilityOf(warningIconOnConfimationPopup));
		    	return warningIconOnConfimationPopup.isDisplayed();
		    	
		    }
		    
		    //Warning Popup Text
		    public  String validateConfirmDeleteWarningTextOnConfirmationPopup() {
		    	 return confirmDeleteTextOnPopup.getText().trim();	    	
		    }
		    
		    //Warning Popup Confirmation Text
		    public  String validateConfirmDeleteDescriptionWarningTextOnConfirmationPopup() {
		    	return desciptionOfconfirmDeleteTextOnPopup.getText().trim();
		    	
		    }
		    
		    //All item cleared successfully popup displayed
		    public boolean validateAllItemsClearedSuccessfullyDisplayed() {
		    	wait.until(ExpectedConditions.visibilityOf(AllitemsclearedsuccessfullyPopup));
		    	return AllitemsclearedsuccessfullyPopup.isDisplayed();
		    }
		    
		    //All item cleared successfully popup text verified
		    public String validateAllitemsclearedsuccessfullyPopupText() {
		    	wait.until(ExpectedConditions.visibilityOf(AllitemsclearedsuccessfullyPopup));
		    	return AllitemsclearedsuccessfullyPopup.getText();
		    }
		    
		    //No Records Available After deleting line items 
		    public String validateNoRecordsAvailableAfterDeletingLineItems() {
		    	wait.until(ExpectedConditions.visibilityOf(noRecordsAvailable));
		    	return noRecordsAvailable.getText();
		    }
			
		
		    //Update Line Button
		    public boolean validateUpdateLineButtonDisplayed() {
		    	wait.until(ExpectedConditions.visibilityOf(updateLineItemButton));
		    	return updateLineItemButton.isDisplayed();
		    }
		    
		    public boolean validateUpdatePopupButtonEnabled() {
		    	wait.until(ExpectedConditions.visibilityOf(updatePopupButton));
		    	return updatePopupButton.isEnabled();
		    }
		    
		    public void clickOnUdpatePopButton() {
		    	wait.until(ExpectedConditions.elementToBeClickable(updatePopupButton));
		    	updatePopupButton.click();
		    }
		    
			//Cancel popup button displayed
		    public boolean validateCanclePopupButtonDisplayed() {
		    	wait.until(ExpectedConditions.visibilityOf(confirmationPopUpCancelButton));
		    	return confirmationPopUpCancelButton.isDisplayed();
		    }
		    
		 // Cancel popup button click
		    public void clickOnWarningPopupCancelButton() {
 			    try {
 			        wait.until(ExpectedConditions.visibilityOf(popUp)); // Wait until popup is visible
 			        if (popUp.isDisplayed()) {
 			        	confirmationPopUpCancelButton.click();
 			            System.out.println("Cancel button clicked on confirmation popup.");
 			        }
 			    } catch (Exception e) {
 			        System.out.println("Cancel popup not displayed: " + e.getMessage());
 			    }
 			}
		    
		    //Delete popup button displayed
		    public boolean validateDeletePopupButtonDisplayed() {
		    	wait.until(ExpectedConditions.visibilityOf(confirmationPopUpDeleteButton));
		    	return confirmationPopUpDeleteButton.isDisplayed();
		    }
		    
		    //Delete popup button click
		    public void clickOnWarningPopupDeleteButton() {
			    try {
			        wait.until(ExpectedConditions.visibilityOf(popUp)); // Wait until popup is visible
			        if (popUp.isDisplayed()) {
			            
			        	confirmationPopUpDeleteButton.click();
			            System.out.println(" clicked on warning popup delete button.");
			        }
			    } catch (Exception e) {
			        System.out.println("Save popup not displayed: " + e.getMessage());
			    }
			}
		    
		    //All Items Cleared Successfully Popup displayed
		    public boolean valdiateAllItemsClearedSuccessfullyPopupDisplayed() {
		    	wait.until(ExpectedConditions.visibilityOf(allItemsClearedSuccessfullyPopup));
		    	return allItemsClearedSuccessfullyPopup.isDisplayed();
		    }
		    
		  //All Items Cleared Successfully Popup Text
		    public String valdiateAllItemsClearedSuccessfullyPopupText() {
		    	wait.until(ExpectedConditions.visibilityOf(allItemsClearedSuccessfullyPopup));
		    	return allItemsClearedSuccessfullyPopup.getText();
		    }
		    
			
		    //generalInvoice row count on tableRows
		    public int getRowCount() {
		    	System.out.println("Total row sixze avaialable in Excel sheet: "+ tableRows.size());
		        return tableRows.size();
		    }
		    
		    // ✅ Print and validate all Table rows
		    public void printAndValidateRowData(String expectedCategory, String expectedSubCategory, String expectedUOM, 
		    			String expectedQty,  String expectedRate,  String expectedCharge) {

				
				if (tableRows.size() == 0) {
				System.out.println("❌ No rows found in invoice table!");
				return;
				}
				
				int rowNum = 1;
				for (WebElement row : tableRows) {
					List<WebElement> cells = row.findElements(By.tagName("td"));
					
					// Print row data
					System.out.print("Row " + rowNum + " -> ");
					for (WebElement cell : cells) {
					System.out.print(cell.getText().trim() + " | ");
					}
					System.out.println();
					
					// ✅ Validation
					if (cells.get(0).getText().trim().equalsIgnoreCase(expectedCategory) &&
					cells.get(1).getText().trim().equalsIgnoreCase(expectedSubCategory) &&
					cells.get(2).getText().trim().equalsIgnoreCase(expectedUOM) &&
					cells.get(3).getText().trim().equalsIgnoreCase(expectedQty) &&
					cells.get(4).getText().trim().equalsIgnoreCase(expectedRate) &&
					cells.get(5).getText().trim().equalsIgnoreCase(expectedCharge)) {
					System.out.println("✅ Row " + rowNum + " data is correct");
					} else {
					System.out.println("❌ Row " + rowNum + " data mismatch");
					}
				
				rowNum++;
				}
			}
				
		    
		  
		 // Method: count total rows across all pages
		    public int getTableRowCount() {
		        int totalRecords = 0;

		        while (true) {
		            List<WebElement> rows = wait.until(
		                    ExpectedConditions.visibilityOfAllElements(tableRows)
		            );
		            totalRecords += rows.size();

		            // If no more pages, exit
		            if (nextBtn.getAttribute("disabled") != null) {
		                break;
		            }
		            nextBtn.click();
		            wait.until(ExpectedConditions.stalenessOf(rows.get(0))); // wait until next page loads
		        }
		        return totalRecords;
		    }
		 
					
					
					
					
					
					
					

					
   
}
