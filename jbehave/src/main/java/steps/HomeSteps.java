package steps;

import org.jbehave.core.annotations.Then;
import org.junit.Assert;
import org.openqa.selenium.By;


public class HomeSteps extends Steps{
	
	public HomeSteps() {
		
	}
	
	@Then("I should see the dashboard home page")
	public void verifyHomePage()
	{
		wait(By.id("apptsTabBa"));
		String actualResult = driver.findElement(By.id("apptsTabBa")).getText();
		
		Assert.assertEquals("Appointment", actualResult);
			
	}	

}
