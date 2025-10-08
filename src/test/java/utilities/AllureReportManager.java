package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import testBase.BaseClass;


public class AllureReportManager implements ITestListener {
	
	// =========================
    // Capture Screenshot for Allure
    // =========================
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // =========================
    // Custom Step Logging
    // =========================
    @Attachment(value = "{0}", type = "text/plain")
    public static String logStep(String message) {
        return message;
    }

    @Override
    public void onStart(ITestContext context) {
        logStep("Test Suite Started: " + context.getName());
        System.out.println("=== ALLURE REPORT MANAGER STARTED ===");
        cleanAllureResults();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logStep("Starting Test: " + result.getMethod().getMethodName());
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logStep("Test Passed: " + result.getMethod().getMethodName());
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logStep("Test Failed: " + result.getMethod().getMethodName());
        System.out.println("Test Failed: " + result.getName());
        
        if (result.getThrowable() != null) {
            logStep("Exception: " + result.getThrowable().getMessage());
            System.out.println("Exception: " + result.getThrowable().getMessage());
        }
        
        try {
            if (BaseClass.driver != null) {
                captureScreenshot(BaseClass.driver);
                System.out.println("Screenshot captured for failed test");
            }
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logStep("Test Skipped: " + result.getMethod().getMethodName());
        System.out.println("Test Skipped: " + result.getName());
        
        if (result.getThrowable() != null) {
            logStep("Reason: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        logStep("Test Suite Finished: " + context.getName());
        System.out.println("=== ALLURE REPORT MANAGER FINISHING ===");
        
        // Wait a moment for all tests to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Generate and open report
        generateAndOpenReport();
    }

    private void cleanAllureResults() {
        try {
            File allureResultsDir = new File("target/allure-results");
            if (allureResultsDir.exists()) {
                deleteDirectory(allureResultsDir);
                System.out.println("Cleaned previous allure results");
            }
            allureResultsDir.mkdirs();
        } catch (Exception e) {
            System.out.println("Failed to clean allure results: " + e.getMessage());
        }
    }

    private void generateAndOpenReport() {
        try {
            System.out.println("Generating Allure report...");
            
            // Check if allure results were created
            File allureResults = new File("target/allure-results");
            if (!allureResults.exists() || allureResults.listFiles() == null || allureResults.listFiles().length == 0) {
                System.out.println("No test results found in target/allure-results");
                System.out.println("Please ensure tests are configured properly for Allure");
                return;
            }
            
            System.out.println("Found " + allureResults.listFiles().length + " result files");
            
            // Generate report using command line
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "allure generate target/allure-results -o target/allure-report --clean");
            processBuilder.directory(new File(System.getProperty("user.dir")));
            Process process = processBuilder.start();
            
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Allure report generated successfully!");
                openReport();
                sendEmailReport();
            } else {
                System.out.println("Failed to generate Allure report. Exit code: " + exitCode);
            }
            
        } catch (Exception e) {
            System.out.println("Error generating Allure report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void openReport() {
        try {
            String reportPath = System.getProperty("user.dir") + "\\target\\allure-report\\index.html";
            File reportFile = new File(reportPath);
            
            if (reportFile.exists()) {
                Desktop.getDesktop().browse(reportFile.toURI());
                System.out.println("Allure report opened in browser: " + reportPath);
            } else {
                System.out.println("Allure report not found at: " + reportPath);
                
                // Try alternative locations
                String[] alternativePaths = {
                    System.getProperty("user.dir") + "\\allure-report\\index.html",
                    System.getProperty("user.dir") + "\\target\\site\\allure-maven-plugin\\index.html"
                };
                
                for (String path : alternativePaths) {
                    File altFile = new File(path);
                    if (altFile.exists()) {
                        Desktop.getDesktop().browse(altFile.toURI());
                        System.out.println("Allure report opened from alternative location: " + path);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to open Allure report: " + e.getMessage());
        }
    }

    private void sendEmailReport() {
        try {
            String reportPath = System.getProperty("user.dir") + "\\target\\allure-report\\index.html";
            File reportFile = new File(reportPath);
            
            if (!reportFile.exists()) {
                System.out.println("Report file not found for email: " + reportPath);
                return;
            }

            MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator("rajumaladinni@gmail.com", "beagioakrwbiufkn"));
            email.setStartTLSEnabled(true);
            email.setSSLOnConnect(false);
            
            email.setFrom("rajumaladinni@gmail.com", "Automation QA");
            email.setSubject("Automation Test Execution - " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            email.setMsg("Hello,\n\nPlease find attached the latest Allure automation test report.\n\nRegards,\nQA Team");
            email.addTo("rajumaladinni007@gmail.com");
            
            email.attach(reportFile);
            email.send();
            
            System.out.println("Allure email sent successfully!");

        } catch (EmailException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error in email sending: " + e.getMessage());
        }
    }

    private void deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            directory.delete();
        }
    }

}
