package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCase3 {
	
	@Test
	public static void main() {

		System.setProperty("webdriver.chrome.driver", "E:\\JavaWorkSpace\\Selenium1\\libs\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//define implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//life time is until driver closes
		driver.manage().window().maximize();

		//Open application
		driver.get("http://newtours.demoaut.com/");

		//Login
		driver.findElement(By.name("userName")).sendKeys("mercury");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("mercury");
		driver.findElement(By.xpath("//input[@name='login']")).click();

		//Search for flight
		WebDriverWait wait= new WebDriverWait(driver, 100, 5000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='tripType' and @value='oneway']"))));
		
		driver.findElement(By.xpath("//input[@name='tripType' and @value='oneway']")).click();
		WebElement element = driver.findElement(By.xpath("//select[@name='passCount']"));
		Select slt = new Select(element);
		slt.selectByIndex(2);

		new Select(driver.findElement(By.name("fromPort"))).selectByValue("London");
		
		new Select(driver.findElement(By.name("fromMonth"))).selectByVisibleText("March");
		
		//Click on search flights
		driver.findElement(By.name("findFlights")).click();

		driver.quit();

	}
}
