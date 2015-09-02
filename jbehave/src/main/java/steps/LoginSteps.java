package steps;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;


public class LoginSteps extends Steps {
	
	
	public LoginSteps() {
		
	}

	
	@Given("I am on the dashboard")
	public void goToDashboardHomePage()
	{
		System.out.println(System.getProperty("user.dir"));
		Path projectPath = Paths.get(System.getProperty("user.dir"));
		System.out.println(projectPath.getFileName());
		
        driver.get("https://qadash.chenmed.com/PMR/logon.htm");
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
