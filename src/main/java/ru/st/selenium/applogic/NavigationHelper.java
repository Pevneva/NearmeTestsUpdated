package ru.st.selenium.applogic;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import ru.st.selenium.applogic.NavigationHelper;

public class NavigationHelper extends DriverBasedHelper{

  private String baseUrl;

  public NavigationHelper(ApplicationManager manager) {
    super(manager.getWebDriver());
    this.baseUrl = manager.getBaseUrl();
  }
  
  public void openStartPage() {
    driver.get(baseUrl);
  }

    public void gotoLoginPage() {
	driver.get(baseUrl);
	//opening login page
	System.out.println("Going to login page...");		
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='home-login-container__button']")));
    driver.findElement(By.xpath("//a[@class='home-login-container__button']")).click(); 
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginForm")));
	System.out.println("OK!");
  }
  
  public void gotoSignupPage(){
  
	gotoLoginPage();
	
 	System.out.println("Opening Sig Up page...");	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/nearme-portal/signup']")));
	driver.findElement(By.xpath("//a[@href='/nearme-portal/signup']")).click();
	System.out.println("OK!");	
  }
  
  public void findAndGotoEditUserWindow() {
	System.out.println("Finding and going to Edit User page...");
    //finding window with 'Edit User' title and go to it
    for (String handle : driver.getWindowHandles()) {
        driver.switchTo().window(handle);
        if (driver.getTitle().equals("Edit User")){break;};
    }  
	System.out.println("OK!");
  }
  
  public void findAndCloseWindowWithActivationLink(){
 	System.out.println("Finding and closing window that title contains 'Registration in NearMe' text...");
    //finding window that title contains 'Registration in NearMe' text
    for (String handle : driver.getWindowHandles()) {
        driver.switchTo().window(handle);
        if (driver.getTitle().contains("Registration in NearMe")){
		driver.close();
		};
    }  
	System.out.println("OK!"); 
  }
  
    public void gotoBusinessesPage() {
	driver.findElement(By.cssSelector("#registrationsTab > span.nav_btn_text")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("keyword")));
  }
  
  public void gotoUsersPage() {
	driver.findElement(By.cssSelector("#usersTab > span.nav_btn_text")).click();
  }
  
  public void openRelativeUrl(String url) {
    driver.get(baseUrl + url);
  }
  
  public void gotoUserProfilePage() {
    driver.findElement(By.cssSelector("a.profile_menu_dropdown_link")).click();
    driver.findElement(By.linkText("Settings")).click(); 
  }

  public void gotoUserManagementPage() {
    //driver.findElement(By.cssSelector("nav a[href $= '?go=users']"))
    //  .click();
    openRelativeUrl("?go=users");
  }
  
  public void clickOnAddvenueButton(){
  //	clicking on the 'add a venue' link
	System.out.println("Clicking on the 'add a venue' link...");
    driver.findElement(By.linkText("add a venue")).click();
	System.out.println("OK!");	
  
  }
  
  public void gotoOffersTab() {
//	clicking on the Offers tab	
	System.out.println("clicking on the Offers tab...");
	driver.findElement(By.xpath("//a[contains(text(),'Offers')]")).click();
	System.out.println("OK!");  
  }
  
	public void checkStartPage() {
	
	wait.until(presenceOfElementLocated(By.xpath("//div[@class='home-login-container']")));
	/*
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.xpath("//div[@class='home-login-container']"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
			}
			*/
	}	  

}
