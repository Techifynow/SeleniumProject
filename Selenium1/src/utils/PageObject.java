package utils;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageObject {
	LinkedHashMap<String, By> elements = new LinkedHashMap<String, By>();
	protected WrapperWebElement element;
	protected WebDriver driver = BaseTest.getWebDriver();
	
	protected void element(String name, By identifier){
		elements.put(name.toLowerCase(), identifier);
	}
	
	public WrapperWebElement element(String name){
		if(!elements.containsKey(name.toLowerCase()))
			throw new NoSuchElementException("No such element with name "+name);
		element = new WrapperWebElement(driver, name, elements.get(name.toLowerCase()));
		return element;
	}
	
}
