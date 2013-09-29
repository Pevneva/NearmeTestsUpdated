package ru.st.selenium.applogic;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import ru.st.selenium.applogic.UserHelper;
import ru.st.selenium.applogic.EmailclientHelper;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Emailclient;
import org.openqa.selenium.interactions.Actions;

public class UserHelper extends DriverBasedHelper {

  private ApplicationManager manager;
  private boolean acceptNextAlert = true;

  public UserHelper(ApplicationManager manager) {
    super(manager.getWebDriver());
    this.manager = manager;
  }

  public void loginAs(User user) {
	manager.getNavigationHelper().gotoLoginPage();
    driver.findElement(By.id("username"))
      .sendKeys(user.getUsername());
    driver.findElement(By.id("password"))
      .sendKeys(user.getPassword());
    driver.findElement(By.name("_action_save"))
      .click();
	wait.until(presenceOfElementLocated(By.xpath("//li[@class='profile_menu_top']")));
  }
  
  public void loginAsAdmin() {
	manager.getNavigationHelper().gotoLoginPage();
    driver.findElement(By.id("username"))
      .sendKeys(manager.getAdminLogin());
    driver.findElement(By.id("password"))
      .sendKeys(manager.getAdminPassword());
    driver.findElement(By.name("_action_save"))
      .click();
	wait.until(presenceOfElementLocated(By.xpath("//li[@class='profile_menu_top']")));
  }  
  
  public void loginWithFacebook(){
	manager.getNavigationHelper().gotoLoginPage();

	//clicking on the "Log In With Facebook" button
	System.out.println("Clicking on the \"Log In With Facebook\" button...");
	driver.findElement(By.id("joinWithFacebook")).click();
	System.out.println("OK!");	
	checkAccountPage();
  }

  public void logout() {
	driver.findElement(By.cssSelector("a.profile_menu_dropdown_link")).click();
	driver.findElement(By.linkText("Log Out")).click();	
  }

  public boolean isLoggedIn() {
    return driver.findElements(By.xpath("//li[@class='profile_menu_top']")).size() > 0;
  }

  public boolean isLoggedInAs(User user) {
    return isLoggedIn()
        && getLoggedUser().getUsername().equals(user.getUsername());
  }

  public boolean isNotLoggedIn() {
    return driver.findElements(By.id("loginForm")).size() > 0;
  }
  
  private User getLoggedUser() {
    manager.getNavigationHelper().gotoUserProfilePage();
    return new User()
      .setUsername(driver.findElement(By.name("username")).getAttribute("value"))
      .setEmail(driver.findElement(By.name("email")).getAttribute("value"));
  }
  
    public User getAdminUser() {
    return manager.getAdminUser();
  } 
  
  public boolean isMerchantManager(User user) {
  		manager.getNavigationHelper().gotoUsersPage();
  
		By byKeywordsFieldOnUsersPage = By.id("keywords");
		By byRoleFieldOnUsersPage = By.id("role");
		By byResultTable = By.xpath("//table[@id='searchResultList']/tbody");
		By bySearchButton = By.id("action_button");
		By byRowIiResultTable = By.xpath("//tr[@class='row clickable']");
		
		WebElement keywordsFieldOnUsersPage = driver.findElement(byKeywordsFieldOnUsersPage);
		WebElement resultTable = driver.findElement(byResultTable);
		WebElement searchButton = driver.findElement(bySearchButton);
		keywordsFieldOnUsersPage.clear();
		keywordsFieldOnUsersPage.sendKeys(user.getEmail());
	
		searchButton.click();
		wait.until(ExpectedConditions.stalenessOf(resultTable));
		wait.until(presenceOfElementLocated(byResultTable));

		WebElement roleFieldOnUsersPage = driver.findElement(byRoleFieldOnUsersPage);
		new Select(roleFieldOnUsersPage).selectByVisibleText("Merchant Manager");
		wait.until(ExpectedConditions.stalenessOf(resultTable));
		wait.until(presenceOfElementLocated(byResultTable));		

		return driver.findElements(byRowIiResultTable).size() > 0;		
   }

	public void removeUser(User user) {
		By byResultTable = By.xpath("//table[@id='searchResultList']/tbody");	
		By byKeywordsFieldOnUsersPage = By.id("keywords");
		By bySearchButton = By.id("action_button");
		By bySelectAllCheckbox = By.id("selectAll");
		By byDeleteButton = By.name("_action_bulkDelete");
	
	//	loginAsAdmin();
 		System.out.println("Removing user with " + user.getEmail() + " email...");
/*
		if (isMerchantManager(user)) {
			//manager.getBusinessHelper().removeBusinessByKeywords(user.getEmail());
/*
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath("//td[@class='search_checkbox']"))).build().perform();

			System.out.println("clicking on '...' ...");
			driver.findElement(By.xpath("//a[@class='list_tools']")).click();
			System.out.println("Ok!");
			actions.moveToElement(driver.findElement(By.xpath("//div[@class='tools_item'][3]"))).build().perform();
			wait.until(presenceOfElementLocated(By.xpath("//div[@class='tools_item'][3]")));
			System.out.println("clicking on Business Name...");
			driver.findElement(By.xpath("//div[@class='tools_item'][3]")).click();
			System.out.println("Ok!");
			wait.until(presenceOfElementLocated(By.xpath("//div[@class='spacer_t_10']/a/span")));
			System.out.println("clicking on 'Delete Account' button...");
			driver.findElement(By.xpath("//div[@class='spacer_t_10']/a/span")).click();
			System.out.println("Ok!");
			driver.findElement(By.id("deleteLink"));
			
			}
*/		
		manager.getNavigationHelper().gotoUsersPage();
		WebElement keywords = driver.findElement(byKeywordsFieldOnUsersPage);
		keywords.clear();
		keywords.sendKeys(user.getEmail());

		WebElement resultTable = driver.findElement(byResultTable);
		WebElement searchButton = driver.findElement(bySearchButton);
		searchButton.click();
		wait.until(ExpectedConditions.stalenessOf(resultTable));
		wait.until(presenceOfElementLocated(byResultTable));

		WebElement deleteButton = driver.findElement(byDeleteButton);
		WebElement selectAllCheckbox = driver.findElement(bySelectAllCheckbox);
		selectAllCheckbox.click();
		deleteButton.click();		
        Alert alert = driver.switchTo().alert();
        alert.accept();	
		wait.until(presenceOfElementLocated(byResultTable));
//		logout();
		System.out.println("OK!");	   
  }  
  
  public void signUp(User user, Emailclient email) {

	manager.getNavigationHelper().gotoSignupPage();
	
	System.out.println("Filling all fields and continue...");	
 	//filling all fields
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys(user.getFirstname());
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys(user.getLastname());
    new Select(driver.findElement(By.id("gender"))).selectByVisibleText(user.getGender());
    driver.findElement(By.id("birthday")).clear();
    driver.findElement(By.id("birthday")).sendKeys(user.getBirthday());
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(user.getEmail());
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(user.getUsername());
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(user.getPassword());
	System.out.println("Country..." + user.getCountry());
    new Select(driver.findElement(By.id("countryCode"))).selectByVisibleText(user.getCountry());
	By byCity = By.xpath("//*[@id=\"homeCity\"]/*[@value=\""+user.getCity()+"\"]");
	wait.until(presenceOfElementLocated(byCity));
	System.out.println("ok");
    new Select(driver.findElement(By.id("homeCity"))).selectByVisibleText(driver.findElement(byCity).getText());
	//clicking on the "Join" button
    driver.findElement(By.name("_action_join")).click();
	//checking that page is opened
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Confirm Your Email[\\s\\S]*$"));	
	System.out.println("OK!");	
	
	manager.getEmailclientHelper().loginToMailru(email);
	manager.getEmailclientHelper().openLastMessageInMailru();
	manager.getEmailclientHelper().clickOnActivationLink();
	manager.getNavigationHelper().findAndGotoEditUserWindow();
	checkProfilePage();
	
	manager.getNavigationHelper().findAndCloseWindowWithActivationLink();
	manager.getNavigationHelper().findAndGotoEditUserWindow();
	
	logout();
  }
  
  public void signupWithFacebook(User user) {
 	//clicking on the "Sign up with Facebook" button
	System.out.println("Clicking on the \"Sign up with Facebook\" button");
	driver.findElement(By.id("joinWithFacebook")).click();
	System.out.println("OK!");
	System.out.println("User email - " + user.getEmail());
	//login to facebook
	System.out.println("Logging in to facebook...");
	wait.until(presenceOfElementLocated(By.id("email")));
	driver.findElement(By.id("email")).sendKeys(user.getEmail());
	driver.findElement(By.id("pass")).sendKeys(user.getPassword());
    try {Thread.sleep(1000);} catch (Exception e) {};
	driver.findElement(By.id("u_0_1")).click();
    try {Thread.sleep(3000);} catch (Exception e) {};
	//checking that Facebook Sign Up page was opened
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='facebook-signup-greeting__container']")));
	System.out.println("OK!");	
 	//filling all fields
	System.out.println("Filling all fields and continue...");	
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(user.getUsername());
    new Select(driver.findElement(By.id("countryCode"))).selectByVisibleText(user.getCountry());
	By byCity = By.xpath("//*[@id=\"homeCity\"]/*[@value=\""+user.getCity()+"\"]");
	wait.until(ExpectedConditions.presenceOfElementLocated(byCity));
	System.out.println("ok");
    new Select(driver.findElement(By.id("homeCity"))).selectByVisibleText(driver.findElement(byCity).getText());
	//clicking on the "Join" button
    driver.findElement(By.name("_action_join")).click();
	System.out.println("OK!");	
	checkProfilePage();
	//logout
	logout();
  }
  
  public void checkProfilePage(){
	//check that Profile page is opened
	By byUserProfileMenuItem = By.xpath("//div[@class=\"user_profile_menu\"]");
	wait.until(presenceOfElementLocated(byUserProfileMenuItem));
	By byBirthdayField = By.id("birthday");
	wait.until(presenceOfElementLocated(byBirthdayField));
	System.out.println("OK!");   
 }
 
  public void checkAccountPage() {
 	System.out.println("Checking that Account Details page is opened...");
	By byUserProfileMenuItem = By.xpath("//div[@class=\"user_profile_menu\"]");
	wait.until(presenceOfElementLocated(byUserProfileMenuItem));
	By byUserFormItem = By.xpath("//form[@id=\"userForm\"]");
	wait.until(presenceOfElementLocated(byUserFormItem));
	System.out.println("OK!");	 
 }
 
 public void checkDisabledUser(User user){
	/* Checking that User with <email> email was created and has 'Disabled' status */

	System.out.println("Checking that User with " + user.getEmail() + " email was created and has 'Disabled' status...");	
	//Going to Users tab
	driver.findElement(By.cssSelector("#usersTab > span.nav_btn_text")).click();
	//entering <email> to "Keywords' field
	driver.findElement(By.id("keywords")).clear();
	driver.findElement(By.id("keywords")).sendKeys(user.getEmail());
	driver.findElement(By.id("action_button")).click();	
	//checking that "searchResultList" table contains <email> text
	wait.until(presenceOfElementLocated(By.xpath("//table[@id=\"searchResultList\"]//td[contains(text(),'" + user.getEmail() + "')]")));
	/*
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//table[@id=\"searchResultList\"]//td[contains(text(),'" + user.getEmail() + "')]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
*/	
	//checking that "searchResultList" table contains 'status_rejected' class	
	wait.until(presenceOfElementLocated(By.xpath("//table[@id=\"searchResultList\"]//div[@class='status_rejected']")));
	/*
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//table[@id=\"searchResultList\"]//div[@class='status_rejected']"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
	*/
	System.out.println("OK!");
}
 
}
