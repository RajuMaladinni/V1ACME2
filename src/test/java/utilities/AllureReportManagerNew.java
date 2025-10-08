package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import testBase.BaseClass;

public class AllureReportManagerNew extends AllureTestNg implements ITestListener, ISuiteListener{

	private static final String RESULTS_DIR = System.getProperty("user.dir") + "/testData/allure-results";
	private static final String REPORT_DIR = System.getProperty("user.dir") + "/testData/allure-report";
	 private static final String MAVEN_REPORT_DIR = "target/site/allure-maven-plugin";

	    @Attachment(value = "Screenshot", type = "image/png")
	    public static byte[] captureScreenshot(WebDriver driver) {
	        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	    }

	    @Attachment(value = "{0}", type = "text/plain")
	    public static String logStep(String message) {
	        return message;
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        logStep("Test Suite Started: " + context.getName());
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        logStep("Test Started: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        logStep("Test Passed: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        logStep("Test Failed: " + result.getMethod().getMethodName());
	        if (result.getThrowable() != null) {
	            logStep("Error: " + result.getThrowable().getMessage());
	        }
	        try {
	            if (BaseClass.driver != null) {
	                captureScreenshot(BaseClass.driver);
	            }
	        } catch (Exception e) {
	            System.out.println("Screenshot failed: " + e.getMessage());
	        }
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        logStep("Test Skipped: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        logStep("Test Suite Finished: " + context.getName());
	        
	        generateReportWithMaven();
	        openReport();
	        //sendEmailReport();
	    }

	    private void generateReportWithMaven() {
	        try {
	            Process process = new ProcessBuilder("mvn", "allure:report")
	                    .directory(new File(System.getProperty("user.dir")))
	                    .start();
	            
	            int exitCode = process.waitFor();
	            if (exitCode == 0) {
	                System.out.println("Allure report generated");
	            } else {
	                System.out.println("Report generation failed");
	            }
	        } catch (Exception e) {
	            System.out.println("Maven execution failed: " + e.getMessage());
	        }
	    }

	    private void openReport() {
	        try {
	            File reportFile = new File(MAVEN_REPORT_DIR + "/index.html");
	            if (reportFile.exists()) {
	                Desktop.getDesktop().browse(reportFile.toURI());
	                System.out.println("Report opened in browser");
	            } else {
	                System.out.println("Report not found: " + reportFile.getPath());
	            }
	        } catch (Exception e) {
	            System.out.println("Failed to open report: " + e.getMessage());
	        }
	    }

	    
	    private void sendEmailReport() {
	    	File reportFile = new File(MAVEN_REPORT_DIR + "/index.html");
	        if (!reportFile.exists()) {
	            System.out.println("No report file for email: " + reportFile.getPath());
	            return;
	        }

	        try {
	            String htmlContent = new String(java.nio.file.Files.readAllBytes(reportFile.toPath()), java.nio.charset.StandardCharsets.UTF_8);

	            MultiPartEmail email = new MultiPartEmail();
	            email.setHostName("smtp.gmail.com");
	            email.setSmtpPort(587);
	            email.setAuthenticator(new DefaultAuthenticator("rajumaladinni@gmail.com", "beagioakrwbiufkn"));
	            email.setStartTLSEnabled(true);

	            email.setFrom("rajumaladinni@gmail.com");
	            email.setSubject("Test Report - " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
	            email.setContent(htmlContent, "text/html");
	            email.addTo("rajumaladinni007@gmail.com");

	            email.attach(reportFile);
	            email.send();

	            System.out.println("Email sent successfully");

	        } catch (org.apache.commons.mail.EmailException e) {
	            System.out.println("Email sending failed: " + e.getMessage());
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("Failed to read HTML report: " + e.getMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("Unexpected error during email sending: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	
	    

}
