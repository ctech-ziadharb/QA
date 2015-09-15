package pages;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class LoginPage extends AbstractPage{
	

	public LoginPage(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);

	}
		
	public void goToDashboardHomePage()
	{
		get("https://qadash.chenmed.com/PMR/logon.htm");
	}
	
	public void enterUsername(String userName)
	{
		findElement(By.id("username")).sendKeys(userName);
		
	}
	
	public void enterPassword(String password)
	{
		findElement(By.id("password")).sendKeys(password);
		
	}
	
	public void clickLoginButton()
	{
		findElement(By.xpath("/html/body/div[1]/form/div/input[3]")).click();
	
	}

}
