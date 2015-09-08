package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps {
	
	static PhantomJSDriver driver = null;
    
	public Steps() {
				
		if(driver == null ){
			
			DesiredCapabilities caps = DesiredCapabilities.phantomjs();
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "c:\\phantomjs.exe");
	        caps.setCapability("browserConnectionEnabled", true);
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
