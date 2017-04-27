package pageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DataProvider;
import utils.EnvConfig;
import utils.PageObject;
import utils.Report;
import utils.Status;

public class LoginPage extends PageObject{

	public LoginPage(){
		element("txtUserName", By.name("userName"));
		element("txtPassword", By.xpath("//input[@name='password']"));
		element("btnSignIn", By.xpath("//input[@name='login']"));
		element("lnkRegister", By.linkText("REGISTER"));
	}
	
	public void loginApplication(WebDriver driver, DataProvider dp){
		Report.log(Status.HEADER, "Ente login details");
		
		element("txtUserName").sendKeys(EnvConfig.getEnvDetails("UserName"));
		element("txtPassword").sendKeys(EnvConfig.getEnvDetails("Password"));
		element("btnSignIn").click();
	}
	
	public void openApplication(WebDriver driver, String URL){
		driver.get(URL);
	}
	
	public void clickRegosterLink(){
		element("lnkRegister").click();
	}
}
