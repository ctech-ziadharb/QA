package steps;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps {
	
	public static ChromeDriver driver;
	
	public Steps() {
		System.out.println(System.getProperty("user.dir") +"c://chromedriver.exe");
		if(driver == null ){
			System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");			
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
