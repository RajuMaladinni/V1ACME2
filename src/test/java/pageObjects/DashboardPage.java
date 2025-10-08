package pageObjects;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.DatePickerUtil;
import utilities.ExcelUtility;
	
public class DashboardPage extends BasePage {
	
	DatePickerUtil datePicker;
	ExcelUtility excelUtil;

	public DashboardPage(WebDriver driver) throws AWTException {
		super(driver);		
		datePicker = new DatePickerUtil(driver);
		excelUtil = new ExcelUtility(System.getProperty("user.dir")+"/testData/NewGenInv.xlsx");
	}
	
	//Dashboard Page locators
	@FindBy(xpath= "//div[@class='text-center logo-sidebar']/img")
	WebElement companyACMELogo;
	
	@FindBy(xpath="//i[@class='bi bi-list d-none d-lg-block sidebar-collapse-icon-btn cursor-pointer fs-3']")
	WebElement collapseButton;
	
	@FindBy(xpath = "//main/div[contains(@class, 'flex-grow-1 d-md-block d-lg-flex')]")
	WebElement mainContainer;
	
	@FindBy(xpath="//ul[@class='lightText nav flex-column']/li//a")
	List<WebElement> listOfSideBarTabs;
	
	
	//notifications
	@FindBy(xpath= "//span[@class='notification-count bg-danger badge bg-secondary']")
	WebElement notificationIcon;
	
	@FindBy(xpath= "//div[@class='notification-Width dropdown-menu show']")
	WebElement notificationPage;
	
	@FindBy(xpath="//span[@class='mb-0 fs-4 text-dark']")
	WebElement notificatoinText;
	
	@FindBy(xpath= "//div[@id='toggle-message-view']")
	WebElement showReadUnreadMessageIcon;
	
	@FindBy(xpath= "//button[text()='Clear All']")
	WebElement clearAllButton;
	
	//Login user name
	@FindBy(xpath="//span[@class='fw-bold']")
	WebElement loginUserName;
	
	@FindBy(xpath="//p[@class='fs-6 text-muted m-0 mt-1']")
	WebElement roleName;
	
	//dash board title
	@FindBy(xpath="//h4[@class='card-title' and text() = 'Dashboard']")  //Dashboard page title
	WebElement dashBoardTitle;
	
	//Date Range Types
	@FindBy(xpath= "//button[contains(@class,'custom-white-btn')]")
	WebElement dateRangeTypeDropdown;
	
	@FindBy(xpath= "//div[@class='custom-dropdown-menu dropdown-menu show']")
	WebElement listOfDateRangeTypes;
	
	//Date Selection
	@FindBy(xpath="//button[@class='react-datepicker__close-icon' and @aria-label='Close']")
	WebElement calanderCloseButton;
	
	@FindBy(xpath= "//div[@class='react-datepicker__input-container']")
	WebElement datePicInputContainer;

	@FindBy(xpath="//button[@aria-label='Previous Month']")
	WebElement previousMonthButton;
	
	@FindBy(xpath="//button[@aria-label='Next Month']")
	WebElement nextMonthButton;
	
	
	@FindBy(xpath="//div[@class='react-datepicker__week']//div[@role='option']")
	WebElement dates;
	
	@FindBy(xpath= "//div[contains(@class,'kpi-card')]")
	List<WebElement> kpiCards;
	
	@FindBy(xpath="//div[contains(@class,'kpi-card')]//h5[text()='Total Invoices']")
	WebElement totalInvoicesText;
	
	@FindBy(xpath="//div[contains(@class,'kpi-card')]//h5[text()='Total Issued Invoices']")
	WebElement totalIssuedInvoicesText;
	
	@FindBy(xpath="//div[contains(@class,'kpi-card')]//h5[text()='Total Pending Invoices']")
	WebElement totalPendingInvoicesText;
	
	@FindBy(xpath="//div[contains(@class,'kpi-card')]//h5[text()='Primary Approval Pending']")
	WebElement primaryApprovalPendingText;
	
	@FindBy(xpath="//div[contains(@class,'kpi-card')]//h5[text()='Secondary Approval Pending']")
	WebElement secondaryApprovalPendingText;
	
	@FindBy(xpath="//span[text()='Logout']")  //Dashboard page title
	WebElement logoutButton;
	
	
	public boolean isSideBarColapseButtonDisplayed() {
		return collapseButton.isDisplayed();
	}
	
	public void clickOnSideBarButton() {
		collapseButton.click();
	}
	
	public boolean isSidebarCollapsed() {
        return mainContainer.getAttribute("class").contains("isMiniSidebar");
    }
	
	public void waitForCollapsed() {
	    wait.until(ExpectedConditions.attributeContains(mainContainer, "class", "isMiniSidebar"));
	}

	public void waitForExpanded() {
	    wait.until(ExpectedConditions.not(
	        ExpectedConditions.attributeContains(mainContainer, "class", "isMiniSidebar")));
	}
	
	// Method to get tab count
	public int getSidebarTabCount() {
	    return listOfSideBarTabs.size();
	}

	// Method to click tab by index (0 to 10 for 11 tabs)
	public void clickTabByIndex(int index) {
		listOfSideBarTabs.get(index).click();
	}
	
	// Method to print all tab names
	public void printAllTabs() {
	    System.out.println("Total Tabs: " + listOfSideBarTabs.size());
	    for (WebElement tab : listOfSideBarTabs) {
	        System.out.println("Tabs present in the side bar -> " + tab.getText().trim());
	    }
	}

	// Method to click tab by visible text
	public void clickTabByName(String tabName) {
	    for (WebElement tab : listOfSideBarTabs) {
	        if (tab.getText().trim().equalsIgnoreCase(tabName)) {
	            tab.click();
	            break;
	        }
	    }
	}
	
	//Print all KPI cards
	public void printKpiCards() {
	    for (WebElement card : kpiCards) {
	        String title = card.findElement(By.xpath(".//h5[contains(@class,'card-title')]")).getText();
	        String percentage = card.findElement(By.xpath(".//h5[contains(@class,'card-text')]")).getText();
	        String amount = card.findElement(By.xpath(".//h4[contains(@class,'card-text')]")).getText();

	        System.out.println("KPI Title: " + title);
	        System.out.println("KPI Percentage: " + percentage);
	        System.out.println("KPI Amount: " + amount);
	        System.out.println("-------------------------");
	    }
	}
	
	//KPI's is displayed and click on methods
	public boolean isTotalInvoiceKPIDisplayed() {
		return totalInvoicesText.isDisplayed();
	}
	
	public void clickOnTotalInvoiceKPI() {
		totalInvoicesText.click();
	}
	
	public boolean isTotalIssuedInvoiceKPIDisplayed() {
		return totalIssuedInvoicesText.isDisplayed();
	}
	
	public void clickOnTotalIssuedInvoiceKPI() {
		totalIssuedInvoicesText.click();
	}
	
	public boolean isTotalPendingInvoiceKPIDisplayed() {
		return totalPendingInvoicesText.isDisplayed();
	}
	
	public void clickOnTotalPendingInvoiceKPI() {
		totalPendingInvoicesText.click();
	}
	
	public boolean isprimaryApprovalPendingKPIDisplayed() {
		return primaryApprovalPendingText.isDisplayed();
	}
	
	public void clickOnPrimaryApprovalPendingKPI() {
		primaryApprovalPendingText.click();
	}
	
	public boolean issecondaryApprovalPendingKPIDisplayed() {
		return secondaryApprovalPendingText.isDisplayed();
	}
	
	public void clickOnSecondaryApprovalPendingKPI() {
		secondaryApprovalPendingText.click();
	}
	
	
	//Calendar actions
	public void clickOnCalanderCloseButton() {
		calanderCloseButton.click();
	}
	
	public void clickOnDatePickerContainer() {
		datePicInputContainer.click();
	}
	
	
	
	public boolean isdashbodardPageExitsts() {
		
		try {
		return (dashBoardTitle.isDisplayed());
		}
		catch(Exception e){
			return false;	
		}	
	}
	
	public boolean isLogoutButtonDisplayed() {
		return logoutButton.isDisplayed();
		
	}
	
	public void clickLogoutbutton() {
		logoutButton.click();
	}

}
