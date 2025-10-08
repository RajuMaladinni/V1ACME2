import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger;	 //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


public class NewBasePage {
	
	 // Use ThreadLocal for parallel execution safety
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger;
    public Properties prop;
    public WebDriverWait wait;
    
    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() throws IOException {
        // Load properties only once per suite
        FileReader fileReader = new FileReader(".//src//test//resources//config.properties");
        prop = new Properties();
        prop.load(fileReader);
        
        logger = LogManager.getLogger(this.getClass());
        System.out.println("=== Test Suite Started ===");
    }	
    
    @Parameters({"os", "browser"})
    @BeforeClass(alwaysRun = true)
    public void classSetup(String os, String br) {
        System.out.println("Setting up browser for class: " + this.getClass().getSimpleName());
        
        // Initialize WebDriver if not already done for this thread
        if (driver.get() == null) {
            initializeDriver(br);
        }
    }
    
    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        // Ensure driver is ready for each test method
        if (driver.get() == null) {
            initializeDriver("chrome"); // default or get from parameters
        }
        
        // Navigate to application URL before each test method if needed
        if (!driver.get().getCurrentUrl().contains(prop.getProperty("appURL"))) {
            driver.get().get(prop.getProperty("appURL"));
        }
    }
    
    private void initializeDriver(String browser) {
        System.out.println("Initializing WebDriver for browser: " + browser);
        
        switch(browser.toLowerCase()) {
            case "chrome": 
                driver.set(new ChromeDriver()); 
                break;
            case "edge": 
                driver.set(new EdgeDriver()); 
                break;
            case "firefox": 
                driver.set(new FirefoxDriver()); 
                break;
            default: 
                System.out.println("Invalid browser name, using Chrome as default");
                driver.set(new ChromeDriver());
                return;
        }
        
        // Configure driver
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("appURL"));
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        
        System.out.println("Browser initialized successfully: " + browser);
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }
    
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        // Don't close browser after each method, just cleanup if needed
        System.out.println("Test method completed: " + this.getClass().getSimpleName());
        
        // Optional: Clear cookies or navigate to home page between tests
        try {
            getDriver().manage().deleteAllCookies();
            // If you want to start fresh for each test, uncomment below:
            // getDriver().get(prop.getProperty("appURL"));
        } catch (Exception e) {
            System.out.println("Cleanup after method failed: " + e.getMessage());
        }
    }
    
    @AfterClass(alwaysRun = true)
    public void classTearDown() {
        System.out.println("Class teardown for: " + this.getClass().getSimpleName());
        // Don't close driver here - let suite handle it
    }
    
    @AfterSuite(alwaysRun = true)
    public void suiteTearDown() {
        System.out.println("=== Test Suite Completed - Closing Browser ===");
        try {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
                System.out.println("Browser closed successfully");
            }
        } catch (Exception e) {
            System.out.println("Browser was already closed: " + e.getMessage());
        }
    }
    
    // Screenshot utility (keep your existing method)
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        
        TakesScreenshot takeScreenshot = (TakesScreenshot) getDriver();
        File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
        
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        
        Files.copy(sourceFile.toPath(), targetFile.toPath());
        
        return targetFilePath;
    }

}
