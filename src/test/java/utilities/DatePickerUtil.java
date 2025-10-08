package utilities;

import java.awt.AWTException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageObjects.BasePage;

public class DatePickerUtil {
	  WebDriver driver;
	  BasePage basePage;  

    public DatePickerUtil(WebDriver driver) throws AWTException {
        this.driver = driver;
        this.basePage = new BasePage(driver);
       
    }
	 
	    // Locators (adjust according to your HTML)
    
    @FindBy(xpath= "//input[@placeholder='MM/DD/YYYY']")
    WebElement datePicInputContainer;
   
    @FindBy (xpath= "//div[contains(@class,'react-datepicker__current-month')]")
    WebElement actualMonthAndYear;
    
	@FindBy(xpath="//div[@class='react-datepicker__header ']/div[contains(@class,'current-month react-datepicker')]")
	WebElement monthYearLabel;
	
	@FindBy(xpath="//button[@aria-label='Next Month']]")
	WebElement nextButton;   // -> button
	
	@FindBy(xpath="//button[@aria-label='Previous Month']")
	WebElement  previousButton;		  // <- button
	
	@FindBy(xpath="//span[@class='react-datepicker__year-read-view--selected-year']")
	WebElement currentYear;
	
	@FindBy(xpath="//div[@class='react-datepicker__week']//div[@role='option']")
	WebElement date;
	
	@FindBy(xpath = "//div[@class='react-datepicker__month']//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month'))]")
    private List<WebElement> dateCells;
	
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);

	public void clickOnDatePickerContainer() {
		
	//	basePage.waitForVisibility(datePicInputContainer);
		basePage.waitForClickability(datePicInputContainer);
		datePicInputContainer.click();
	}
	
	 public void clickNextMonth() {
	        nextButton.click();
	    }

	    // 🔹 Click Previous
	    public void clickPreviousMonth() {
	        previousButton.click();
	    }

	    // 🔹 Select Single Date
	    public void selectDate(String monthYear, String day) {
	        navigateToMonth(monthYear);
	        selectDay(day);
	        System.out.println("Selected single date: " + day + " " + monthYear);
	    }

	    // 🔹 Select Date Range (start → end)
	    public void selectDateRange(String startMonthYear, String startDay, 
	                                String endMonthYear, String endDay) {
	        // Select Start Date
	        navigateToMonth(startMonthYear);
	        selectDay(startDay);

	        // Select End Date
	        navigateToMonth(endMonthYear);
	        selectDay(endDay);

	        System.out.println("Selected range: " + startDay + " " + startMonthYear 
	                           + " → " + endDay + " " + endMonthYear);
	    }

	    // 🔹 Internal method: Navigate until correct month-year is displayed
	    private void navigateToMonth(String targetMonthYear) {
	        int maxTries = 24;
	        int attempts = 0;

	        while (true) {
	            String currentMonthYear = actualMonthAndYear.getText().trim();
	            YearMonth current = YearMonth.parse(currentMonthYear, formatter);
	            YearMonth target = YearMonth.parse(targetMonthYear, formatter);

	            if (current.equals(target)) break;

	            attempts++;
	            if (attempts > maxTries) {
	                throw new RuntimeException("Unable to reach target month: " + targetMonthYear);
	            }

	            if (current.isBefore(target)) {
	                clickNextMonth();
	            } else {
	                clickPreviousMonth();
	            }
	        }
	    }

	    // 🔹 Internal method: Select a single day from current visible month
	    private void selectDay(String day) {
	        for (WebElement dt : dateCells) {
	            if (dt.getText().equals(day)) {
	                dt.click();
	                return;
	            }
	        }
	        throw new RuntimeException("Day " + day + " not found in current month view");
	    }
	

}


