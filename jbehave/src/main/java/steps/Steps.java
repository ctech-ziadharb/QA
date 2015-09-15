package steps;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;

import pages.Pages;

public class Steps{
	
	public final Pages pages;

	
	public Steps(Pages pages) {
        this.pages = pages;
	}
	
	@BeforeScenario
	public void beforeStory() {
		pages.loginPage().initializeDriver();
	}
		
	@AfterStory
	public void afterStory() {
		pages.loginPage().quitDriver();
	}
	
}
