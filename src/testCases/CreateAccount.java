package testCases;

import org.testng.annotations.Test;

import pageLibrary.CreateAccountPage;
import pageLibrary.LoginPage;
import utils.BaseTest;
import utils.FormActions;
import utils.Report;

public class CreateAccount extends BaseTest{
	
	@Test(groups={"sanity"})
	public void createAccSubmit(){
		
		//Load test data
		loadTestData("CreateAccount", "CreateAcc");
		
		//Start report 
		Report.startTest(dp.getData("TCName"), "Desc", "Regression");
		
		//Open Application
		LoginPage lgn = new LoginPage();
		lgn.openApplication(driver, URL);
		
		//Click Register Link
		lgn.clickRegosterLink();
		
		//Enter data to create account
		CreateAccountPage crtAcc = new CreateAccountPage();
		crtAcc.enterCreateAccountDetails(dp, FormActions.SUBMIT);
	}

}
