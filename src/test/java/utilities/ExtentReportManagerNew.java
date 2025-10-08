package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;



public class ExtentReportManagerNew implements ITestListener {
	
	 private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<>();
	    private static Map<String, ExtentTest> classLevelTests = new HashMap<>();
	    private static String repName;

	    public void onStart(ITestContext testContext) {
	        String testStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        repName = "Test-Report-" + testStamp + ".html";

	        ExtentSparkReporter sparkreporter = new ExtentSparkReporter(".\\reports\\" + repName);
	        sparkreporter.config().setDocumentTitle("ACME Automation Report");
	        sparkreporter.config().setReportName("ACME Functional Testing");
	        sparkreporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkreporter);

	        extent.setSystemInfo("Application", "ACME");
	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("Sub-Module", "Dashboard");
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "QA");

	        String os = testContext.getCurrentXmlTest().getParameter("os");
	        extent.setSystemInfo("Operating System", os);

	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Browser", browser);

	        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	        if (!includedGroups.isEmpty()) {
	            extent.setSystemInfo("Groups", includedGroups.toString());
	        }
	    }

	    public void onTestStart(ITestResult result) {
	        String className = result.getTestClass().getName();
	        String methodName = result.getMethod().getMethodName();

	        // Create parent test if not already present
	        ExtentTest parent = classLevelTests.get(className);
	        if (parent == null) {
	            parent = extent.createTest(className); // Class name as parent
	            classLevelTests.put(className, parent);
	        }

	        // Create child test under parent class
	        ExtentTest child = parent.createNode(methodName);
	        child.assignCategory(result.getMethod().getGroups());
	        childTest.set(child);
	    }

	    public void onTestSuccess(ITestResult result) {
	        childTest.get().log(Status.PASS, "✅ " + result.getMethod().getMethodName() + " passed successfully");
	    }

	    public void onTestFailure(ITestResult result) {
	        childTest.get().log(Status.FAIL, "❌ " + result.getMethod().getMethodName() + " failed");
	        childTest.get().log(Status.INFO, result.getThrowable().getMessage());

	        try {
	            String impPath = BaseClass.captureScreen(result.getMethod().getMethodName());
	            childTest.get().addScreenCaptureFromPath(impPath);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	    }

	    public void onTestSkipped(ITestResult result) {
	        childTest.get().log(Status.SKIP, "⚠️ " + result.getMethod().getMethodName() + " was skipped");
	        if (result.getThrowable() != null) {
	            childTest.get().log(Status.INFO, result.getThrowable().getMessage());
	        }
	    }

	    public void onFinish(ITestContext testContext) {
	        extent.flush();

	        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	        File extentReport = new File(pathOfExtentReport);

	        try {
	            Desktop.getDesktop().browse(extentReport.toURI());
	            System.out.println("Created HTML report successfully!");
	        } catch (IOException e) {
	        	System.out.println("Failed to Create HTML report: " + e.getMessage());
	            e.printStackTrace();
	        }
	        
	        
	  	   //email report 
	        try {
	        	
	 	  	   
	        	ImageHtmlEmail email =new ImageHtmlEmail();
	        	email.setHostName("smtp.gmail.com");
	        	email.setSmtpPort(587);
	        	email.setAuthenticator(new DefaultAuthenticator("rajumaladinni@gmail.com", "beagioakrwbiufkn")); // Use App Password
	        	email.setStartTLSEnabled(true);
	        	email.setSSLOnConnect(true);
	  	   
	        	email.setFrom("rajumaladinni007@gmail.com", "Automation QA");
	        	email.setSubject("Automation Test Execution Report - " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
	        	email.setMsg("Hello Please find attached the latest automation test execution report....");
	        	
	        	// Path of extent report
	            String extentReportPath = System.getProperty("user.dir") + "\\reports\\" + repName;
	            File reportFile = new File(extentReportPath);

	            // Email body with link
	            email.setHtmlMsg("<html><body>"
	                    + "<h2>Hi Team,</h2>"
	                    + "<p>The latest <b>Extent Automation Test Report</b> has been generated.</p>"
	                    + "<p>You can open the attached report or click the link below:</p>"
	                    + "<p><a href='file://" + extentReportPath + "'>Open Extent Report</a></p>"
	                    + "<br><p>Best Regards,<br><b>Automation QA</b></p>"
	                    + "</body></html>");
	        	
	            email.attach(reportFile);
	         	email.send();
	         	System.out.println("Email sent successfully!");
	  	   
	        }catch(Exception e) {
	        	System.out.println("Failed to send email: " + e.getMessage());
	        	e.printStackTrace();
	        }
	     
	    }
	    
	



}
