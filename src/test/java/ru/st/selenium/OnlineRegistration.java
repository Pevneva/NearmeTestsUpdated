package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Business;
import ru.st.selenium.model.Venue;
import ru.st.selenium.model.Offer;
import ru.st.selenium.model.Emailclient;

public class OnlineRegistration extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}  

  @Test
  public void testOnlineRegistration() throws Exception {    

	String tradeName="Auto Online Trading";
//	String contractNo="CoNo-001";
//	String urlName="business_url";
	String managerEmail="lyudmila_test_mm@mail.ru";  
  
  	System.out.println("=========================================");  
	System.out.println("");  
	System.out.println("========== ONLINE REGISTRATION ==========");
	System.out.println("");	
	
	Business business1 = new Business()
						.setTradename(tradeName)
						.setContractType("Registration only")
						.setPromoCode("");
	Business business2 = new Business()
						.setTradename(tradeName)
						.setContractType("Registration and 3 daily offers")
						.setPromoCode("PROMO-SALES-PLA");						
	Venue venue = new Venue()
						.setUrlName("test_venue")
						.setTradename("Computer House");
	User merchantManager = new User()
						.setEmail(managerEmail)
						.setUsername("Manager")
						.setPassword("12345678");
	Emailclient email = new Emailclient()
						.setEmail(managerEmail)
						.setPassword("test12345");
	
	app.getUserHelper().loginAsAdmin();
	
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getUserHelper().removeUser(merchantManager);
	
	app.getBusinessHelper().onlineRegistration(business1, merchantManager, email);
	app.getBusinessHelper().removeBusinessByKeywords(business1.getTradename());
	app.getUserHelper().removeUser(merchantManager);
	app.getUserHelper().logout();
	app.getEmailclientHelper().logoutFromMailru();	
	
	app.getBusinessHelper().onlineRegistration(business2, merchantManager, email);
	app.getBusinessHelper().removeBusinessByKeywords(business2.getTradename());
	app.getUserHelper().removeUser(merchantManager);
	app.getUserHelper().logout();
	app.getEmailclientHelper().logoutFromMailru();	
	
	System.out.println(""); 
	System.out.println("======= ONLINE REGISTRATION WAS COMPLETED! ======="); 
	System.out.println(""); 
	System.out.println("=================================================="); 	
	
	}

/*	
  public void OnlineRegistr(String ContractType, String PromoCode)throws Exception {
	System.out.println("");
	System.out.println("--- Checking online registration with next data: ---");
	System.out.println("");
	System.out.println("CONTRACT TYPE:     " + ContractType);
	System.out.println("PROMO CODE:     " + PromoCode);
	System.out.println("");	
	firstStepOfOnlineRegistration(ContractType, PromoCode);
	checkFirstEmailOfOnlineRegistration("lyudmila_test_mm@mail.ru");
	checkPendingBusiness();
	checkDisabledUser("lyudmila_test_mm@mail.ru");
	approveBusiness("Auto Online Trading");
	checkSecondEmailOfOnlineRegistration("lyudmila_test_mm@mail.ru");
	clickClickHereLinkAndFindCompleteBRwindow();	
	finalRegistrationStepOfOnlineRegistration();
	logoutFromMailru();
	
	/* Removing created data 
	System.out.println("");	
	System.out.println("--- Removing created data... ---");	
	System.out.println("");		
	loginAsAdmin();
	removeBusiness("Auto Online Trading");
	removeUser("lyudmila_test_mm@mail.ru");
	logout();
	System.out.println("OK!");		
	}	

public void firstStepOfOnlineRegistration(String ContractType, String PromoCode)throws Exception {
	//opening external registration page
	driver.get(baseUrl+"merchant/signup");
	//filling company data fields
	System.out.println("Filling all fields on external page and continue...");	
    driver.findElement(By.id("companyName")).clear();
    driver.findElement(By.id("companyName")).sendKeys("Auto Test Company");
    driver.findElement(By.id("tradingName")).clear();
    driver.findElement(By.id("tradingName")).sendKeys("Auto Online Trading");
    driver.findElement(By.id("registeredVatNo")).clear();
    driver.findElement(By.id("registeredVatNo")).sendKeys("ComVatNo");
    //filling category for business
	new Select(driver.findElement(By.id("categoryId"))).selectByVisibleText("Card & Stationery");
    //filling adress data fields
	new Select(driver.findElement(By.id("countryCode"))).selectByVisibleText("United Kingdom");
    driver.findElement(By.id("address1")).clear();
    driver.findElement(By.id("address1")).sendKeys("Pikadili");
    driver.findElement(By.id("address2")).clear();
    driver.findElement(By.id("address2")).sendKeys("Skver");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//option[@value='London']"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
    new Select(driver.findElement(By.id("city"))).selectByVisibleText("London");
    driver.findElement(By.id("postalCode")).clear();
    driver.findElement(By.id("postalCode")).sendKeys("44400");
	//filling Merchant Manager fields
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("Merchant");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Manager");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("lyudmila_test_mm@mail.ru");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("Manager");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("12345678");
    //filling contract data fields
	new Select(driver.findElement(By.id("contractTypeId"))).selectByVisibleText(ContractType);
    driver.findElement(By.id("numberOfVenues")).clear();
    driver.findElement(By.id("numberOfVenues")).sendKeys("100");
    driver.findElement(By.id("promotionalCode")).clear();
    driver.findElement(By.id("promotionalCode")).sendKeys(PromoCode);	
    //filling payment card fields
	driver.findElement(By.id("cardNumber")).clear();
    driver.findElement(By.id("cardNumber")).sendKeys("340000432128428");
    driver.findElement(By.id("cardHolderName")).clear();
    driver.findElement(By.id("cardHolderName")).sendKeys("test");
    driver.findElement(By.id("cardExpireMonth")).clear();
    driver.findElement(By.id("cardExpireMonth")).sendKeys("12");
    driver.findElement(By.id("cardExpireYear")).clear();
    driver.findElement(By.id("cardExpireYear")).sendKeys("2015");
    driver.findElement(By.id("cardSecurityCode")).clear();
    driver.findElement(By.id("cardSecurityCode")).sendKeys("3469");
    //noting 'I agree...' check box 
	driver.findElement(By.id("termsAgreement")).click();
	//clicking on the "Continue" button
    driver.findElement(By.name("_action_signupConfirm")).click();
	//checking that div with 'order_summary' class exists
    for (int second = 0;; second++) {
   	if (second >= 60) fail("timeout");
   	try { if (isElementPresent(By.xpath("//div[@class='order_summary']"))) break; } catch (Exception e) {}
   	Thread.sleep(1000);
    }
	//waiting until "Submit" button will be shown
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.name("_action_signupProcessing"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
	//clicking on the "Submit" button
    driver.findElement(By.name("_action_signupProcessing")).click();
	//checking that div with 'print_area' id exists
    for (int second = 0;; second++) {
   	if (second >= 60) fail("timeout");
   	try { if (isElementPresent(By.xpath("//div[@id='print_area']"))) break; } catch (Exception e) {}
   	Thread.sleep(1000);
    }
	//waiting until "Finish" button will be shown
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.name("_action_null"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }
	//clicking on the "Finish" button
    driver.findElement(By.name("_action_null")).click();
	//checking that start page of nearme was opened
	checkStartPage();
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
