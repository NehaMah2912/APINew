package com.api.rootlib;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	public ApiConfig conf = new ApiConfig();
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	@BeforeSuite
	public void beforeSuite()
	{
		htmlReporter=new ExtentHtmlReporter("./test-output/test-reporter2.html");
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "Windows");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report2");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	@AfterMethod
	public void getResult(ITestResult result) 
	{
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"FAILED ", ExtentColor.RED));
			logger.fail(result.getThrowable());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"PASSED ", ExtentColor.GREEN));	
		}else {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"SKIPPED ", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
		}
	}
	@AfterSuite
	public void configAfterSuite()
	{
		extent.flush();
	}
	
}
