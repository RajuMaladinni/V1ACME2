package pageObjects;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.DatePickerUtil;
import utilities.ExcelUtility;
	
public class CustomerSetupPage extends BasePage {
	
	DatePickerUtil datePicker;
	ExcelUtility excelUtil;

	public CustomerSetupPage(WebDriver driver) throws AWTException {
		super(driver);		
		datePicker = new DatePickerUtil(driver);
		excelUtil = new ExcelUtility(System.getProperty("user.dir")+"/testData/NewGenInv.xlsx");
	}
	
	//Cusomter setup Page locators
	//Locators
    
    @FindBy (xpath="//span[contains(text(),'Customer Setup')]")
	WebElement customerSetupTab;
    
    @FindBy(xpath= "//h4[text()='Customer Setup List']")
    WebElement customerSetupHeaderText;
    
    @FindBy(xpath="//div[@class='card-subtitle' and text()='Overview of Customer Setup']")
    WebElement overviewOfWebElementText;
    
    @FindBy (xpath="//input[@placeholder='Search by Location, Customer']")
	WebElement globalSearchByLocationAndCustomer;
	
	@FindBy (xpath="//label[contains(text(),'Location')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupLocationFilter;
	
	@FindBy (xpath="//label[contains(text(),'Customer')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupCustomerFilter;
	
	@FindBy (xpath="//label[contains(text(),'Status')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupStatusFilter;
	
	@FindBy (xpath="//label[contains(text(),'Description')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupDescriptionFilter;
	
	@FindBy (xpath="//label[contains(text(),'Rate By')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupRateByFilter;
	
	@FindBy (xpath="//label[contains(text(),'Charge Name')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupChargeNameFilter;
	
	@FindBy (xpath="//label[contains(text(),'Charge Name')]/following-sibling::div//input[@class='react-select__input']")
	WebElement customerSetupChangeUoMFilter;
	
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
    WebElement firstCustomerSetupLocation;
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[2]/div")  
    WebElement firstCustomerSetupCustomer;
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[3]/div")  
    WebElement firstCustomerSetupStatus;
    
    @FindBy(xpath = "//table//tbody/tr[1]/td[4]/button")  
    WebElement firstCustomerSetupButton;
    
    @FindBy(xpath="//div[@class='d-flex']//a")
    List<WebElement> listOfInvoiceTypesCustomerSetup;
    
    @FindBy(xpath="//div[@class='d-flex']//a[text()='Inbound']")
    WebElement customerSetupTypeInbound;
    
    @FindBy(xpath="//div[@class='d-flex']//a[text()='Outbound']")
    WebElement customerSetupTypeOutbound;
    
    @FindBy(xpath="//div[@class='d-flex']//a[text()='Storage']")
    WebElement customerSetupTypeStorage;
    
    @FindBy(xpath="//div[@class='d-flex']//a[text()='Accessorial']")
    WebElement customerSetupTypeAccessorial;
    
    @FindBy(xpath="//div[@class='d-flex']//a[text()='Increase Rate']")
    WebElement customerSetupTypeincreaseRate;
    
    @FindBy(xpath="//div[@class='d-flex']//a[text()='Settings']")
    WebElement customerSetupTypeSettings;
    
    
    @FindBy (xpath="//input[@placeholder='Search by Location, Customer']")
	WebElement globalSearchByDescription;
    
    @FindBy(xpath="//h4[@class='mb-0']")
    WebElement invoiceTypesHeaderText;
    
    @FindBy(xpath="//div[@class='card-subtitle']")
    WebElement invoiceTypesSubTitle;
    
    @FindBy(xpath="//h4[text()='Increase Rate']")
    WebElement inceaseRateHeaderText;
    
    @FindBy(xpath="//h4[text()='Customer Setting']")
    WebElement customerSettingHeaderText;
    
    @FindBy(xpath="//span[@title='View Audit Log']")
    WebElement viewAuditLogAction;
    
    @FindBy (xpath="//input[@placeholder='Search by Invoice Type']")
	WebElement globalSearchByInvoiceType;
    
    
    
    
    
    
    
    
    
	
	
	
	
	
	
	
	
	
	
	
	
}
