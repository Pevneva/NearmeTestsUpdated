package ru.st.selenium.applogic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Test;
import java.net.URL;
import java.net.MalformedURLException;

import ru.st.selenium.applogic.ApplicationManager;
import ru.st.selenium.applogic.BusinessHelper;
import ru.st.selenium.applogic.ApiHelper;
import ru.st.selenium.applogic.EmailclientHelper;
import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.applogic.NavigationHelper;
import ru.st.selenium.applogic.UserHelper;
import ru.st.selenium.util.Browser;
import ru.st.selenium.util.PropertyLoader;
import ru.st.selenium.webdriver.WebDriverFactory;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Business;
import ru.st.selenium.model.Emailclient;

import java.util.logging.*;
import java.util.logging.Level;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {

  private UserHelper userHelper;
  private FilmHelper filmHelper;
  private ApiHelper apiHelper;
  private NavigationHelper navHelper;
  private BusinessHelper businessHelper;
  private EmailclientHelper emailclientHelper;

  private WebDriver driver;
  private String baseUrl;
  private String usernameOfAdmin;
  private String passwordOfAdmin;
  private User adminUser;
  
  public ApplicationManager() {
    baseUrl = PropertyLoader.loadProperty("site.url");
    String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

    Browser browser = new Browser();
    browser.setName(PropertyLoader.loadProperty("browser.name"));
    browser.setVersion(PropertyLoader.loadProperty("browser.version"));
    browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

//    String username = PropertyLoader.loadProperty("user.username");
    usernameOfAdmin = PropertyLoader.loadProperty("user.username");
    String username = "";
 //   String password = PropertyLoader.loadProperty("user.password");
    passwordOfAdmin = PropertyLoader.loadProperty("user.password");
    String password = "";
	adminUser = new User().setUsername(usernameOfAdmin).setPassword(passwordOfAdmin);
  
  
    
	driver = WebDriverFactory.getInstance(gridHubUrl, browser, username, password);
	
/*
	try {
	WebDriver driver = new RemoteWebDriver(
			new URL("http://tigra:b2c92462-d602-4b23-9203-e186fc8e88d7@ondemand.saucelabs.com:80/wd/hub"),
			DesiredCapabilities.firefox());
	
	} catch (MalformedURLException e) {}
*/	
	
//     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	driver.setLogLevel(Level.INFO);
	driver.manage().window().maximize();
	
    userHelper = new UserHelper(this);
    filmHelper = new FilmHelper(this);
    navHelper = new NavigationHelper(this);
    businessHelper = new BusinessHelper(this);
    emailclientHelper = new EmailclientHelper(this);
    apiHelper = new ApiHelper(this);

    getNavigationHelper().openStartPage();
  }
  
  public UserHelper getUserHelper() {
    return userHelper;
  }

  public FilmHelper getFilmHelper() {
    return filmHelper;
  }

  public BusinessHelper getBusinessHelper() {
    return businessHelper;
  }
  
  public EmailclientHelper getEmailclientHelper() {
    return emailclientHelper;
  }
  
  public ApiHelper getApiHelper() {
    return apiHelper;
  }  
  
  public NavigationHelper getNavigationHelper() {
    return navHelper;
  }

  protected WebDriver getWebDriver() {
    return driver;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  protected String getAdminLogin() {
    return usernameOfAdmin;
  }  
  
  protected String getAdminPassword() {
    return passwordOfAdmin;
  }

  protected User getAdminUser() {
    return adminUser;
  }  
  
//  @Override
  public void stop() {
    if (driver != null) {
      driver.quit();
    }
  }
}
