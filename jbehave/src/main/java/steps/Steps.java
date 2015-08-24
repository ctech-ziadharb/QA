package steps;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps {
	
	public static ChromeDriver driver;
	Path projectPath = Paths.get(System.getProperty("user.dir")).getFileName();
	
	public Steps() {
		System.out.println(System.getProperty("user.dir") +"//jbehave//chromedriver.exe");
		if(driver == null ){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"//chromedriver.exe");			
			driver = new ChromeDriver();	
			}	
	}
	
	public static void wait(By by)
    {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
	
	 public static void close()
     {
         driver.quit();
         driver = null;
     }

}
