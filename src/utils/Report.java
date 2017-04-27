package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.*;

public class Report {
	protected static ExtentReports report;
	protected static ExtentTest test;
	private static String resultpath = null;
	private static String screensPath = null;
	private static int ssCount= 0;
	static WebDriver driver = null;
	public static void startReport(){
		resultpath = System.getProperty("user.dir")+"/report/"+"Run_"+getCurrentDatenTime("yyyyMMdd"+"_"+getCurrentDatenTime("HHmmss"));
		if(!new File(resultpath).isDirectory()){
			new File(resultpath).mkdirs();
		}
		screensPath = resultpath+"/screenShots/";
		if(!new File(screensPath).isDirectory()){
			new File(screensPath).mkdirs();
		}
		report = new ExtentReports(resultpath+"/Suite.html", true, NetworkMode.OFFLINE);
		report.addSystemInfo("Environment", "QA");
		report.addSystemInfo("Author", "Shekar");
	}
	
	public static void startTest(String testCaseName, String description, String category){
		test = report.startTest(testCaseName, description);
		test.assignCategory(category);
		driver = BaseTest.getWebDriver();
	}
	
	public static void endTest(){
		report.endTest(test);
		report.flush();
	}
	
	public static void endReport(){
		report.close();
	}
	
	public static void  log(Status status, String message){
		switch(status){
		case PASS:
			test.log(LogStatus.PASS, "<span style='color:green'>"+message+"</span>");
			break;
		case FAIL:
			test.log(LogStatus.FAIL, "<span style='color:red'>"+message+"</span>");
			break;
		case INFO:
			test.log(LogStatus.INFO, message);
			break;
		case WARN:
			test.log(LogStatus.WARNING, message);
			break;
		case Pass:
			takeScreenShot(driver, screensPath+"ss_"+ssCount+".png");
			test.log(LogStatus.PASS, "<span style='color:green'>"+message+"</span>"+test.addScreenCapture(screensPath+"ss_"+ssCount+".png"));
			ssCount++;
			break;
		case Fail:
			takeScreenShot(driver, screensPath+"ss_"+ssCount+".png");
			test.log(LogStatus.FAIL, "<span style='color:red'>"+message+"</span>"+test.addScreenCapture(screensPath+"ss_"+ssCount+".png"));
			ssCount++;
			break;
		case HEADER:
			test.log(LogStatus.INFO, "<span style='color:#8B4513'><span style='font-weight:bold'>"+message+"</span></span>");
			break;
		}
	}
	
	public static void takeScreenShot(WebDriver driver, String path){
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrentDatenTime(String format){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
		
		
	}
}
