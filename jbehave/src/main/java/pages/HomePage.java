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
		System.out.println("11111111111111111");
		waitForElement(By.id("apptsTabBa"));
		System.out.println("22222222222222222");
		String actualResult = findElement(By.id("apptsTabBa")).getText();
		System.out.println("33333333333333333");
		Assert.assertEquals("Appointment", actualResult);
		System.out.println("44444444444444444");
		System.out.println("55555555555555555");
			
	}	
}
