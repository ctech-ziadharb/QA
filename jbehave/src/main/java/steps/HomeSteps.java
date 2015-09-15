package steps;

import org.jbehave.core.annotations.Then;

import pages.Pages;


public class HomeSteps extends Steps{
	
	public HomeSteps(Pages pages) {
    	super(pages);   	
	}
	
	@Then("I should see the dashboard home page")
	public void verifyHomePage()
	{	
		pages.homePage().verifyHomePage();			
	}	

}
