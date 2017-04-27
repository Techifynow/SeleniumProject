package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WrapperWebElement implements WebElement{
	
	final WebDriver driver;
	final String name;
	final By findBy;
	
	public WrapperWebElement(WebDriver driver, String name, By findBy){
		this.driver = driver;
		this.name = name;
		this.findBy = findBy;
	}
	
	@Override
	public void click() {
		WebElement element = null;
		try{
			element = driver.findElement(findBy);
		}catch(NoSuchElementException e){
			element = driver.findElement(findBy);
		}
		element.click();
		
		if(name.startsWith("lnk")){
			Report.log(Status.INFO, name.substring(3)+" link is clicked");
		}else if(name.startsWith("btn")){
			Report.log(Status.INFO, name.substring(3)+" button is clicked");
		}else if(name.startsWith("rdo")){
			Report.log(Status.INFO, name.substring(3)+" radio button is clicked");
		}
		
	}

	@Override
	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCssValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisplayed() {
		boolean flag = false;
		try{
			driver.findElement(findBy).isDisplayed();
			flag = true;
		}catch(NoSuchElementException e){
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendKeys(CharSequence... keys) {
		WebElement element = null;
		try{
			element = driver.findElement(findBy);
		}catch(NoSuchElementException e){
			element = driver.findElement(findBy);
		}
		element.sendKeys(keys);
		
		if(element.getAttribute("type").equalsIgnoreCase("password")){
			Report.log(Status.INFO, name.substring(3)+" is entered");
		}else{
			Report.log( Status.INFO, name.substring(3)+" is entered as "+keys[0].toString());
		}
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		driver.findElement(findBy).clear();
	}
	
	public void selet(String listValues){
		WebElement element = driver.findElement(findBy);
		Select slt = new Select(element);
		
		if(listValues.startsWith("index=")){
			slt.selectByIndex(Integer.parseInt(listValues.split("=")[1]));
		}else if(listValues.startsWith("value=")){
			slt.selectByValue(listValues.split("=")[1]);
		}else{
			slt.selectByVisibleText(listValues);
		}
		Report.log(Status.INFO, name.substring(3)+" is selectd as "+slt.getFirstSelectedOption().getText());
	}

}
