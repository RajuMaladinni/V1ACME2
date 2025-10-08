package testBase;

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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	// it contains reusable test methods
	
	public static WebDriver driver;
	public static Logger logger;   //log4j
	public static Properties prop;
	public WebDriverWait wait;
	
	
	//launching Web Browser
	
		@Parameters({"os","browser"})
		@BeforeSuite(alwaysRun = true)  
		
		public void setup(String os, String br) throws IOException {
		
		//Loading config.properties file  
		FileReader fileReader=new FileReader(".//src//test//resources//config.properties");
		prop=new Properties();
		prop.load(fileReader);
		
		 // Initialize logger
        logger = LogManager.getLogger(this.getClass());
		
		//Initialize driver first
		switch(br.toLowerCase()) {
		
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		
		default : System.out.println("Invalid browser name.."+ br); 
			return;
		}
		
		//Configure driver
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("appURL")); //reading url from properties file
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));  //Initialize wait only after driver is ready
		
		
		}
		
		
	// Close browser after suite (only once)
		@AfterSuite(alwaysRun = true)
		public void tearDown()  {
			try {
				if (driver != null) {
					driver.quit();
				}
			} catch (Exception e) {
				System.out.println("Browser was already closed: " + e.getMessage());
			}
		}
		
		

	
		
		// ======================
	    // Capture Screenshot Utility for Extent report
	    // ======================
	    	
		public static String captureScreen(String tname) throws IOException {
		
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot= (TakesScreenshot) driver;
		File sourceFile=takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" +tname +"_"+ timeStamp + ".png";
		// Better than renameTo (more reliable)
		File targetFile= new File(targetFilePath);
		
		//sourceFile.renameTo(tagetFile);
		
		// Better than renameTo (more reliable)
				Files.copy(sourceFile.toPath(), targetFile.toPath());
		
		return targetFilePath;
		}


}
