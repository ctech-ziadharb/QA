package steps;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.anthavio.phanbedder.Phanbedder;

public class Steps {
	
	static PhantomJSDriver driver = null;

    
	public Steps() {
		
		
		if(driver == null ){
			
			DesiredCapabilities caps = DesiredCapabilities.phantomjs();
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir") + "\\phantomjs.exe");
	        
	        driver = new PhantomJSDriver(caps);
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
