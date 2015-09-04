package steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;


public class LoginSteps extends Steps {
	
	
	public LoginSteps() {
        	
	}

	
	@Given("I am on the dashboard")
	public void goToDashboardHomePage()
	{
		driver.get("https://qadash.chenmed.com/PMR/logon.htm");
        driver.manage().window().setSize( new Dimension( 1124, 850 ) );
        wait(By.xpath("//*[@id='uszzername']"));
	}
	
	@When("I enter the username $userName")
	public void enterUsername(String userName)
	{
		driver.findElement(By.id("username")).sendKeys(userName);
		
	}
	
	@When("I enter the password $password")
	public void enterPassword(String password)
	{
		driver.findElement(By.id("password")).sendKeys(password);
		
	}
	
	@When("I click the login button")
	public void clickLoginButton()
	{
		driver.findElement(By.xpath("/html/body/div[1]/form/div/input[3]")).click();
	
	}
	
	@AfterScenario
	 public void clean() {
		 close();
	 }
	
	
	


}
