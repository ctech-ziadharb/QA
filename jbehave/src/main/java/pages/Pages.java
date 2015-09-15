package pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class Pages {
	
	private final WebDriverProvider webDriverProvider;
	private LoginPage loginPage;
	private HomePage homePage;


	public Pages(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
	}
	
    public LoginPage loginPage(){
        if ( loginPage == null ){
        	loginPage = new LoginPage(webDriverProvider);
        }
        return loginPage;
    }
    
    public HomePage homePage(){
        if ( homePage == null ){
        	homePage = new HomePage(webDriverProvider);
        }
        return homePage;
    }
    
	public void open(){
		webDriverProvider.initialize();
		
	}
	
	public void close(){
		webDriverProvider.end();
		
	}
    
}
