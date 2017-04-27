package pageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.DataProvider;
import utils.PageObject;
import utils.Report;
import utils.Status;

public class SearchFlightsPage extends PageObject{
	
	public SearchFlightsPage(){
		element("rdoTriTypeOneWay", By.xpath("//input[@name='tripType' and @value='oneway']"));
		element("rdoTriTypeRoundTrip", By.xpath("//input[@name='tripType' and @value='roundtrip']"));
		element("lstPassengerCount", By.xpath("//select[@name='passCount']"));
		element("lstDepartingFrom", By.name("fromPort"));
		element("lstDepartingOn", By.name("fromMonth"));
		element("btnContinue", By.name("findFlights"));
	}
	
	
	
	/**
	 * <b> Description</b>		Enters data in search flights field set
	 * @param					DataProvider	dp
	 * 
	 */
	public void enterSearchDetails(DataProvider dp){
		Report.log(Status.HEADER, "Enter data to searching flights");
		
		if(dp.getData("rdoTriTypeOneWay").equalsIgnoreCase("Yes")){
			element("rdoTriTypeOneWay").click();
		}
		if(dp.getData("rdoTriTypeRoundTrip").equalsIgnoreCase("Yes")){
			element("rdoTriTypeRoundTrip").click();
		}
		element("lstPassengerCount").selet(dp.getData("lstPassengers"));
		element("lstDepartingFrom").selet(dp.getData("lstDepartingFrom"));
		element("lstDepartingOn").selet(dp.getData("lstDepartingOn"));
	}

	public void clickContinue(){
		element("btnContinue").click();
	}
	
	public void clickCancel(){
		
	}
}
