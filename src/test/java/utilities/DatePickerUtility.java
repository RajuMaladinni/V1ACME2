package utilities;

import java.awt.AWTException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatePickerUtility{
	
		private WebDriver driver;
	   
	 
	 // 🔹 Locators using @FindBy
	    @FindBy(xpath = "//div[@class='react-datepicker__current-month react-datepicker__current-month--hasYearDropdown']")
	    WebElement currentMonthYear;

	    @FindBy(xpath = "//button[@aria-label='Next Month']")
	    WebElement nextMonthButton;

	    @FindBy(xpath = "//button[@aria-label='Previous Month']")
	    WebElement previousMonthButton;
	    
	    @FindBy(xpath= "//label[@id='fromDate_label']/following-sibling::div//div[@class='react-datepicker__input-container']")
		WebElement fromDateInput;
		    
	    @FindBy(xpath= "//label[@id='toDate_label']/following-sibling::div//div[@class='react-datepicker__input-container']")
		WebElement toDateInput;

	    // Constructor
	    public DatePickerUtility(WebDriver driver) throws AWTException  {
	    	this.driver = driver;
	    	PageFactory.initElements(driver, this);
	    }
	    
	    
	    // Utility Map for month names to index
	    private static final Map<String, Integer> monthMap = new HashMap<>();
	    static {
	        monthMap.put("January", 1);
	        monthMap.put("February", 2);
	        monthMap.put("March", 3);
	        monthMap.put("April", 4);
	        monthMap.put("May", 5);
	        monthMap.put("June", 6);
	        monthMap.put("July", 7);
	        monthMap.put("August", 8);
	        monthMap.put("September", 9);
	        monthMap.put("October", 10);
	        monthMap.put("November", 11);
	        monthMap.put("December", 12);
	    }

	    // Convert month name to index
	    private int getMonthIndex(String month) {
	        Integer index = monthMap.get(month);
	        if (index == null) {
	            throw new IllegalArgumentException("Invalid month: " + month);
	        }
	        return index;
	    }

	    // ✅ Select a date range
	    public void selectDateRange(String startMonthYear, String startDay,
	                                String endMonthYear, String endDay) {
	        selectDate(startMonthYear, startDay);
	        selectDate(endMonthYear, endDay);
	        System.out.println("Date range selection done: "
	                + startDay + " " + startMonthYear + " to "
	                + endDay + " " + endMonthYear);
	    }
	    
	    
	    // ✅ Select From Date
	    public void selectFromDate(String monthYear, String day) {
	        fromDateInput.click();  // Open picker
	        selectDate(monthYear, day);
	        System.out.println("From Date selected: " + day + " " + monthYear);
	    }

	    // ✅ Select To Date
	    public void selectToDate(String monthYear, String day) {
	        toDateInput.click();  // Open picker
	        selectDate(monthYear, day);
	        System.out.println("To Date selected: " + day + " " + monthYear);
	    }

	    // ✅ Select Today’s Date in FromDate
	    public void selectTodayFromDate() {
	        LocalDate today = LocalDate.now();
	        String monthName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        String monthYear = monthName + " " + today.getYear();
	        selectFromDate(monthYear, String.valueOf(today.getDayOfMonth()));
	    }

	    // ✅ Select Today’s Date in ToDate
	    public void selectTodayToDate() {
	        LocalDate today = LocalDate.now();
	        String monthName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        String monthYear = monthName + " " + today.getYear();
	        selectToDate(monthYear, String.valueOf(today.getDayOfMonth()));
	    }
	    
	    
	    // ✅ Select Today’s Date
	    public void selectTodayDate() {
	        LocalDate today = LocalDate.now();
	        String monthName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	        String monthYear = monthName + " " + today.getYear();
	        String day = String.valueOf(today.getDayOfMonth());

	        selectDate(monthYear, day);
	        System.out.println("Today’s date selected: " + day + " " + monthYear);
	    }


	    // ✅ Select a single date

	    // 🔹 Core: Select single date
	    private void selectDate(String targetMonthYear, String targetDay) {
	        while (true) {
	            String actualMonthYear = currentMonthYear.getText().trim();

	            if (actualMonthYear.equals(targetMonthYear)) {
	                break;
	            }

	            // Split current & target
	            String[] actualParts = actualMonthYear.split(" ");
	            String actualMonth = actualParts[0];
	            int actualYear = Integer.parseInt(actualParts[1]);

	            String[] targetParts = targetMonthYear.split(" ");
	            String targetMonth = targetParts[0];
	            int targetYear = Integer.parseInt(targetParts[1]);

	            int actualMonthIndex = getMonthIndex(actualMonth);
	            int targetMonthIndex = getMonthIndex(targetMonth);

	            // Navigate
	            if (targetYear < actualYear ||
	               (targetYear == actualYear && targetMonthIndex < actualMonthIndex)) {
	                previousMonthButton.click();
	            } else {
	                nextMonthButton.click();
	            }
	        }

	        // Pick the day
	        List<WebElement> dateList = driver.findElements(
	                By.xpath("//div[@class='react-datepicker__month']" +  "/div[@class='react-datepicker__week']" +
	                        "/div[contains(@class,'react-datepicker__day') " +  "and not(contains(@class,'outside-month'))]"));
	        

	        for (WebElement dt : dateList) {
	            if (dt.getText().equals(targetDay)) {
	                dt.click();
	                break;
	            }
	        }
	    }

}
