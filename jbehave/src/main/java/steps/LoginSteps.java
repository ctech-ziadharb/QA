package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;

import pages.Pages;


public class LoginSteps extends Steps {
	
	public LoginSteps(Pages pages) {
    	super(pages);
	}
				
	@Given("I am on the dashboard")
	public void goToDashboardHomePage()
	{
		pages.loginPage().goToDashboardHomePage();
	}
		
	@When("I enter the username $userName")
	public void enterUsername(String userName)
	{
		pages.loginPage().enterUsername(userName);
		
	}
	
	@When("I enter the password $password")
	public void enterPassword(String password)
	{
		pages.loginPage().enterPassword(password);
		
	}
	
	@When("I click the login button")
	public void clickLoginButton()
	{	
		pages.loginPage().clickLoginButton();
	
	}
	
}
