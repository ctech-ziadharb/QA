package pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage extends WebDriverPage  {

    private static boolean driverInitialize = false;

	public AbstractPage(WebDriverProvider driverProvider) {
        super(driverProvider);
	}
	
	public boolean isJavascriptEnabled(){
		return true;
		
	}
	
	public void initializeDriver(){
		if(driverInitialize == false){
			getDriverProvider().initialize();
			driverInitialize = true;
		}
		
	}
	
	public void quitDriver(){
		quit();
		driverInitialize = false;

		
	}

	public void waitForElement(By by){
		WebDriverWait wait = new WebDriverWait(getDriverProvider().get(), 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
	}
	
	
	
}
