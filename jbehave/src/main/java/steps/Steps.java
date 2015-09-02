package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps {
	
	public static ChromeDriver driver;
//	public static WebDriver driver;
	
	public Steps() {
		if(driver == null ){
			
			System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");			
//			ChromeOptions options = new ChromeOptions();
//			options.setBinary("c://chromedriver.exe");
//			driver = webdriver.Chrome(chrome_options = opts);
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
