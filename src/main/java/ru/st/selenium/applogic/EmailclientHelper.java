package ru.st.selenium.applogic;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import ru.st.selenium.applogic.EmailclientHelper;
import ru.st.selenium.model.Emailclient;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailclientHelper extends DriverBasedHelper {

  private ApplicationManager manager;
  private boolean acceptNextAlert = true;

  public EmailclientHelper(ApplicationManager manager) {
    super(manager.getWebDriver());
    this.manager = manager;
  }
   
 public void loginToMailru(Emailclient email)  {
 	System.out.println("Loging to mail.ru...");	
	//opening mail.ru site
    driver.get("http://www.mail.ru");
	//waiting some time
    try {Thread.sleep(2000);} catch (Exception e) {};
	//going to test email
    wait.until(presenceOfElementLocated(By.id("mailbox__login")));
    driver.findElement(By.id("mailbox__login")).clear();
    driver.findElement(By.id("mailbox__login")).sendKeys(email.getEmail());	
    driver.findElement(By.id("mailbox__password")).clear();
    driver.findElement(By.id("mailbox__password")).sendKeys(email.getPassword());
    driver.findElement(By.id("mailbox__auth__button")).click();
	wait.until(presenceOfElementLocated(By.xpath("//div[@class='toolbar__action']")));
	System.out.println("OK!");	
 }
 
 public void openLastMessageInMailru() {
 	//opening last messages
	System.out.println("Opening last message...");		
	driver.findElement(By.cssSelector("span.messageline__body__name")).click();
	wait.until(presenceOfElementLocated(By.id("action_buttons")));
	System.out.println("OK!");	
 }
 
 public void clickOnActivationLink() {
 	System.out.println("Clicking on the activation link...");	
	//waiting some time
    try {Thread.sleep(4000);} catch (Exception e) {};
	//clicking on link for authorization
    driver.findElement(By.xpath("//a[contains(text(),'http')]")).click();
    try {Thread.sleep(3000);} catch (Exception e) {};
	System.out.println("OK!");
 
 }
   
 public void logoutFromMailru(){
 	System.out.println("Logging out from email...");	
	//opening mail.ru site
    driver.get("http://www.mail.ru");	
	//log out from mail.ru
    driver.findElement(By.xpath("//a[@id=\"PH_logoutLink\"]")).click();
	System.out.println("OK!");
 } 
 
 public String getSubjectOfLastMessageInMailru() {
 	return driver.findElement(By.xpath("//div[@id='ML0']/div[1]//span[@class='messageline__body__subject']")).getText(); 
 }
 
 public void checkFirstOnlineRegistrationEmail(Emailclient email){
	loginToMailru(email);
	System.out.println("Checking that 'Regisrtation in NearMe' email was sent...");	
	//checking that subject of top messages contains 'Registration in NearMe' text
	new WebDriverWait(driver,30).until(presenceOfElementLocated(By.xpath("//div[@id=\"ML0\"]/div[1]//span[contains(text(),'Registration in NearMe')]")));
	/*
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//div[@id=\"ML0\"]/div[1]//span[contains(text(),'Registration in NearMe')]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
*/
	System.out.println("OK!");
	//opening first messages
	System.out.println("Opening this messages...");		
	driver.findElement(By.xpath("//div[@id=\"ML0\"]/div[1]//span[contains(text(),'Registration in NearMe')]")).click();
	System.out.println("OK!");	
	//log out from 'lyudmila_test_mm@mail.ru' email
	System.out.println("Logging out from " + email.getEmail() + " email");	
    driver.findElement(By.xpath("//a[@id=\"PH_logoutLink\"]")).click();	
	System.out.println("OK!");
}
 
public void checkNextOnlineRegistrationEmails(Emailclient email) throws Exception{

	System.out.println("Checking that emails notifications were sent to " + email.getEmail() + "...");
	
	loginToMailru(email);

	//waiting until subject of 3rd notification will not be 'Registration in NearMe' text (2 emails will not be sent)
	System.out.println("Taking subject of 3rd message of lyudmila_test_mm@mail.ru email and waiting until it will not be 'Registration in NearMe'... ");
	String  S1="";
	for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { 
			S1  = driver.findElement(By.xpath("//div[@id=\"ML0\"]/div[3]//span[contains(text(),'Registration in')]")).getText();
			System.out.println("S1 = "+S1);
			if (S1.equals("Registration in NearMe")) 
			break; 
			} catch (Exception e) {}
    	Thread.sleep(1000);
    }	
	//waiting until subject of 2rd notification will NOT be 'Registration in NearMe' text 
	System.out.println("Taking subject of 2rd message of lyudmila_test_mm@mail.ru email and waiting until it will NOT be 'Registration in NearMe'... ");
	for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { 
			S1  = driver.findElement(By.xpath("//div[@id=\"ML0\"]/div[2]//span[contains(text(),'')]")).getText();
			System.out.println("S1 = "+S1);
			if (!S1.contains("Registration in NearMe")) 
			break; 
			} catch (Exception e) {}
    	Thread.sleep(1000);
    }		
	//opening first messages
	System.out.println("Opening first message of " + email.getEmail() + " email...");
	driver.findElement(By.cssSelector("span.messageline__body__name")).click();
	System.out.println("OK!");	
	//taking subject of email
	System.out.println("Taking subject of first message of " + email.getEmail() + " email...");	
	S1= driver.findElement(By.xpath("//div[@id ='msgFieldSubject']//span[@class='val']")).getText();
	System.out.println("OK!	   Subject = "+S1);	
	if (S1.contains("Payment Confirmation")){
		//clicking on the 'Письма' link
		driver.findElement(By.xpath("//a[@id=\"HeaderBtnCheckNewMsg\"]")).click();
		//opening second message		
		System.out.println("Opening second message...");
		driver.findElement(By.xpath("//div[@id=\"ML0\"]/div[2]//span[contains(text(),'Welcome to NearMe')]")).click();
		System.out.println("OK!");		
		}
} 

public void clickClickHereLinkAndFindCompleteBRwindow()throws Exception {
	//clicking on the 'click here' link
	System.out.println("Clicking on the 'click here' link...");	
    driver.findElement(By.linkText("click here")).click();
    //finding window with 'Complete Business Registration' title and go to it
	Thread.sleep(5000);	
    for (String handle : driver.getWindowHandles())
       {
       driver.switchTo().window(handle);
	   Thread.sleep(3000);
       if (driver.getTitle().equals("Complete Business Registration")){break;};
       }
	Thread.sleep(3000);	   
	System.out.println("OK!");	
} 
 
 
 
 
    private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
/*
 private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }  
 */ 
  
}
