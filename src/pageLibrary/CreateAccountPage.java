package pageLibrary;

import org.openqa.selenium.By;

import utils.DataProvider;
import utils.FormActions;
import utils.PageObject;
import utils.Report;
import utils.Status;

public class CreateAccountPage extends PageObject{
	
	public CreateAccountPage(){
		// Contact Information
		element("txtFirstName", By.name("firstName"));
		element("txtLastName", By.name("lastName"));
		element("txtPhone", By.name("phone"));
		element("txtEmail", By.id("userName"));
		element("btnSubmit", By.name("register"));
		
		//Mailing Information
	}
	
	public void enterCreateAccountDetails(DataProvider dp, FormActions action){
		Report.log(Status.HEADER, "Entering data to create account");
		
		element("txtFirstName").sendKeys(dp.getData("txtFirstName"));
		element("txtLastName").sendKeys(dp.getData("txtLastName"));
		element("txtPhone").sendKeys(dp.getData("txtPhone"));
		element("txtEmail").sendKeys(dp.getData("txtEmail"));
		
		switch(action){
		case SUBMIT:
			element("btnSubmit").click();
			break;
		case NONE:
			break;
		}
	}
	

}
