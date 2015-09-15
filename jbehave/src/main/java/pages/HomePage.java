package pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.junit.Assert;
import org.openqa.selenium.By;

public class HomePage extends AbstractPage{
	
	
	public HomePage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
	}

	
	public void verifyHomePage()
	{
		String actualResult = findElement(By.id("apptsTabBa")).getText();
		Assert.assertEquals("Appointment", actualResult);	
	}	
}
