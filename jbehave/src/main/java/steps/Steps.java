package steps;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps {
	
	static PhantomJSDriver driver = null;
    
	public Steps() {
				
//		if(driver == null ){
//			
//			DesiredCapabilities caps = DesiredCapabilities.phantomjs();
//			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "c:\\phantomjs.exe");
//	        caps.setCapability("browserConnectionEnabled", true);
//	        driver = new PhantomJSDriver(caps);
//					
//			}	
	}
	
	public static void wait(By by)
    {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
	
	@BeforeStory
	public void open()
    {
		if(driver == null ){
			
			DesiredCapabilities caps = DesiredCapabilities.phantomjs();
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "c:\\phantomjs.exe");
	        caps.setCapability("browserConnectionEnabled", true);
	        driver = new PhantomJSDriver(caps);
					
		}	
    }
	
	@AfterStory	
	public void close()
	{
		if(driver != null ){
		driver.quit();
		driver = null;
		}
	}
	
}
