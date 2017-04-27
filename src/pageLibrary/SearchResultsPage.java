package pageLibrary;

import org.openqa.selenium.By;

import utils.PageObject;

public class SearchResultsPage extends PageObject{
	
	public SearchResultsPage(){
		element("rdoSearchRsultsFirstRecord", By.xpath("//input[@name='outFlight']"));
	}
}
