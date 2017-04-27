package utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class BaseTest {
	public static String URL = null;
	public static String browserName = null;
	public static WebDriver driver;
	public boolean grid = false;
	protected DataProvider dp = null;

	@BeforeSuite
	public void beforeSuiteMethod(){
		EnvConfig.loadEnvironmentDetails();
		URL = EnvConfig.getEnvDetails("URL");
		Report.startReport();
	}

	@AfterSuite
	public void afterSuiteMethod(){
		Report.endReport();
	}

	@BeforeTest
	@Parameters("browser")
	public void beforeTestMethod(String browser){
		browserName = browser;
	}

	@BeforeMethod
	public void bforeSuite(){
		initiateDriver();
	}

	@AfterMethod
	public void AfterMethod(){
		driver.quit();
		Report.endTest();
	}

	public void initiateDriver(){
		switch(browserName){
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "E:\\JavaWorkSpace\\Selenium1\\libs\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "E:\\JavaWorkSpace\\Selenium1\\libs\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "grid":
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.setVersion("30");
			cap.setCapability("TesterName", "shekar");
			driver = new RemoteWebDriver(cap);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//life time is until driver closes
	}

	public static WebDriver getWebDriver(){
		return driver;
	}

	public void loadTestData(String testCaseName, String iteration){
		//Loading test data
		dp = new DataProvider();
		try {
			dp.loadTestData(testCaseName, iteration);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
