package testCases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageLibrary.LoginPage;
import pageLibrary.SearchFlightsPage;
import pageLibrary.SearchResultsPage;
import utils.Assert;
import utils.BaseTest;
import utils.DataProvider;
import utils.Report;
import utils.Status;
import utils.ValidationType;


public class TestCase1 extends BaseTest{
	
	
	@Test(dataProvider="getTestData")
	public void main(DataProvider dp) throws FileNotFoundException, IOException {
		
		Report.startTest(dp.getData("TCName"), "Desc", dp.getData("TestCategory"));
		//Login
		LoginPage lgn = new LoginPage();
		lgn.openApplication(driver, URL);
		lgn.loginApplication(driver, dp);
		
		//Search flights
		SearchFlightsPage srch = new SearchFlightsPage();
		srch.enterSearchDetails(dp);
		srch.clickContinue();
		
		SearchResultsPage results = new SearchResultsPage();
		
		Assert.assertTrue(ValidationType.VERIFY, "Results are displayed successfully", results.element("rdoSearchRsultsFirstRecord").isDisplayed());
		
		Report.log(Status.INFO, "After Validation");
		
	}
	
	@org.testng.annotations.DataProvider
	public Object[][] getTestData() throws FileNotFoundException, IOException{
		
		Object[][] obj = new Object[1][1];
		
		DataProvider dp1 = new DataProvider();
		dp1.loadTestData("TestCase1", "LdnMar");
		obj[0][0] = dp1;
		
		/*DataProvider dp2 = new DataProvider();
		dp2.loadTestData("TestCase1", "NyMar");
		obj[1][0] = dp2;
		
		DataProvider dp3 = new DataProvider();
		dp3.loadTestData("TestCase1", "LdnJun");
		obj[2][0] = dp3;*/
		
		return obj;
	}
}
