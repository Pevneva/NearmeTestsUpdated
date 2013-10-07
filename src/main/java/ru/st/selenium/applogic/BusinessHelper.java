package ru.st.selenium.applogic;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import ru.st.selenium.applogic.BusinessHelper;
import ru.st.selenium.applogic.UserHelper;
import ru.st.selenium.applogic.EmailclientHelper;
import ru.st.selenium.model.Business;
import ru.st.selenium.model.Venue;
import ru.st.selenium.model.Offer;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Emailclient;

public class BusinessHelper extends DriverBasedHelper {

  private ApplicationManager manager;
  private boolean acceptNextAlert = true;

  public BusinessHelper(ApplicationManager manager) {
    super(manager.getWebDriver());
    this.manager = manager;
  }
   
   public void removeBusinessByKeywords(String keywords) {
   
		By byResultTable = By.xpath("//table[@id='searchResultList']/tbody");	
		By bySearchButton = By.id("action_button");
		By bySelectAllCheckbox = By.id("selectAll");
		By byDeleteButton = By.name("_action_buldDeleteMerchants");
		
		System.out.println("Removing Business with " + keywords + " keywords...");	
		manager.getNavigationHelper().gotoBusinessesPage();

		//entering keywords to 'Keywords' field
		driver.findElement(By.id("keywords")).clear();
		driver.findElement(By.id("keywords")).sendKeys(keywords);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(byResultTable));
		WebElement resultTable = driver.findElement(byResultTable);
		wait.until(ExpectedConditions.presenceOfElementLocated(bySearchButton));
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

		System.out.println("OK!");		
   }

 public void addBusiness(Business business) {
    System.out.println("Adding business...");
    System.out.println("TRADING NAME:     "+business.getTradename());
    System.out.println("BUSINESS TYPE:     "+business.getBusinessType());
    System.out.println("NUMBER OF VENUES:     "+business.getNumberOfVenues());
	//going to Add Business page
	driver.findElement(By.cssSelector("span.nav_btn_arrow")).click();
    driver.findElement(By.linkText("New Business")).click();
	
	wait.until(presenceOfElementLocated(By.id("contracts[0].salesRep.id")));
	
	//selecting Agent
    new Select(driver.findElement(By.id("contracts[0].salesRep.id"))).selectByVisibleText("PLA - Lyudmila Pevneva");
	//entering Contract Number
    driver.findElement(By.id("contracts[0].contractId")).clear();
    driver.findElement(By.id("contracts[0].contractId")).sendKeys(business.getContractNumber());
	//selecting Contract Type
    new Select(driver.findElement(By.id("contracts[0].type.id"))).selectByVisibleText("IT_0002 : Registration NearMe + 1 Photo of the activity (24 months)");
	//selecting Payment Method
    new Select(driver.findElement(By.id("contracts[0].paymentMethod.id"))).selectByVisibleText("Paypal");
	//selecting Business Type
    new Select(driver.findElement(By.id("type.id"))).selectByVisibleText(business.getBusinessType());
	//entering number of venues
    driver.findElement(By.id("contracts[0].numberOfVenues")).clear();
    driver.findElement(By.id("contracts[0].numberOfVenues")).sendKeys(business.getNumberOfVenues()+"");
	//entering Start of Data
    driver.findElement(By.xpath("//input[@id='contracts[0].startDate']")).sendKeys("01/05/13");
	//entering First Name for Merchant Manager
    driver.findElement(By.id("managers[0].firstName")).clear();
    driver.findElement(By.id("managers[0].firstName")).sendKeys("First01");
	//entering Last Name for Merchant Manager
    driver.findElement(By.id("managers[0].lastName")).clear();
    driver.findElement(By.id("managers[0].lastName")).sendKeys("Last01");
	//entering email for Merchant Manager
    driver.findElement(By.id("managers[0].email")).clear();
    driver.findElement(By.id("managers[0].email")).sendKeys(business.getManagerEmail());
	//entering Job Title for Merchant Manager	
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.jobTitle")).clear();
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.jobTitle")).sendKeys("Job Title");
	//entering National Insurance No for Merchant Manager	
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.nationalInsuranceNo")).clear();
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.nationalInsuranceNo")).sendKeys("National Ins");
	//entering Telephone for Merchant Manager	
    driver.findElement(By.id("managers[0].contact.telephone")).clear();
    driver.findElement(By.id("managers[0].contact.telephone")).sendKeys("2220010");
	//entering Mobile for Merchant Manager	
    driver.findElement(By.id("managers[0].contact.mobile")).clear();
    driver.findElement(By.id("managers[0].contact.mobile")).sendKeys("5550020");
	//pressing on the "Continue" button
    driver.findElement(By.name("_action_saveCommercialDetails")).click();
	wait.until(presenceOfElementLocated(By.id("displayName")));
	//entering URL
    driver.findElement(By.id("displayName")).clear();
    driver.findElement(By.id("displayName")).sendKeys(business.getUrlName());
	//entering Company No
    driver.findElement(By.id("registeredCompanyNo")).clear();
    driver.findElement(By.id("registeredCompanyNo")).sendKeys("companyNo");
	//entering Company Vat No	
    driver.findElement(By.id("registeredVatNo")).clear();
    driver.findElement(By.id("registeredVatNo")).sendKeys("ComVatNo");
	//entering Company Name
    driver.findElement(By.id("companyName")).clear();
    driver.findElement(By.id("companyName")).sendKeys("Company Name");
	//entering Trading Name
    driver.findElement(By.id("tradingName")).clear();
    driver.findElement(By.id("tradingName")).sendKeys(business.getTradename());
	//entering Address1
    driver.findElement(By.id("registeredAddress.address1")).clear();
    driver.findElement(By.id("registeredAddress.address1")).sendKeys("Pikadili");
	//entering City
    driver.findElement(By.id("registeredAddress.city")).clear();
    driver.findElement(By.id("registeredAddress.city")).sendKeys("London");
	//entering Postal Code
    driver.findElement(By.id("registeredAddress.postalCode")).clear();
    driver.findElement(By.id("registeredAddress.postalCode")).sendKeys("44004");
	//selecting country
    new Select(driver.findElement(By.id("registeredAddress.countryCode"))).selectByVisibleText("United Kingdom");
	//filling fields on the Contact Details section
    driver.findElement(By.id("companyContactDetails.telephone")).clear();
    driver.findElement(By.id("companyContactDetails.telephone")).sendKeys("100001");
    driver.findElement(By.id("companyContactDetails.fax")).clear();
    driver.findElement(By.id("companyContactDetails.fax")).sendKeys("100002");
    driver.findElement(By.id("companyContactDetails.email")).clear();
    driver.findElement(By.id("companyContactDetails.email")).sendKeys("Email@optional.net");
    driver.findElement(By.id("companyContactDetails.websiteUrl")).clear();
    driver.findElement(By.id("companyContactDetails.websiteUrl")).sendKeys("http://www.website001.com");
    driver.findElement(By.id("companyContactDetails.facebook")).clear();
    driver.findElement(By.id("companyContactDetails.facebook")).sendKeys("http://www.facebook001.com");
    driver.findElement(By.id("companyContactDetails.twitter")).clear();
    driver.findElement(By.id("companyContactDetails.twitter")).sendKeys("http://www.twitter001.com");
    driver.findElement(By.id("companyContactDetails.foursquare")).clear();
    driver.findElement(By.id("companyContactDetails.foursquare")).sendKeys("http://www.foursquare001.com");
    driver.findElement(By.id("companyContactDetails.instagram")).clear();
    driver.findElement(By.id("companyContactDetails.instagram")).sendKeys("http://www.instagram.com");
    driver.findElement(By.id("companyContactDetails.pinterest")).clear();
    driver.findElement(By.id("companyContactDetails.pinterest")).sendKeys("http://www.pinterest.com");
	//pressing on the "Continue" button
    driver.findElement(By.name("_action_saveMerchantDetails")).click();

	wait.until(presenceOfElementLocated(By.id("keywords.ti1")));
	
	//selecting Category
    driver.findElement(By.xpath("//div[@class='pickList']/div/ul/li[contains(text(),'Attraction')]")).click();
    driver.findElement(By.cssSelector("button.plus_btn")).click();
	//entering Keyword
    driver.findElement(By.id("keywords.ti1")).clear();
    driver.findElement(By.id("keywords.ti1")).sendKeys("Key Word");
	//press on the "Continue" button
    driver.findElement(By.name("_action_saveAndReview")).click();

	wait.until(presenceOfElementLocated(By.xpath("//h1[contains(text(),'Business Added')]")));
	
	System.out.println("OK!" );	 
 }
 
 public void addVenue(Venue venue){
	System.out.println("--------------------");
	System.out.println("Adding venue...");
	System.out.println("--------------------");
	//filling Venue Details section
	System.out.println("Filling Venue Details section...");
	wait.until(presenceOfElementLocated(By.id("displayName")));
    driver.findElement(By.id("displayName")).clear();
    driver.findElement(By.id("displayName")).sendKeys(venue.getUrlName());
    driver.findElement(By.id("advKey")).clear();
    driver.findElement(By.id("advKey")).sendKeys("Adv Computer");
    driver.findElement(By.id("tradingName")).clear();
    driver.findElement(By.id("tradingName")).sendKeys(venue.getTradename());
	System.out.println("OK!");	
	//filling Venue Address section
	System.out.println("Filling Venue Address section...");	
    new Select(driver.findElement(By.id("location.countryCode"))).selectByVisibleText("Belarus");
    driver.findElement(By.id("location.city")).clear();
    driver.findElement(By.id("location.city")).sendKeys("Minsk");
    driver.findElement(By.id("location.address1")).clear();
    driver.findElement(By.id("location.address1")).sendKeys("Lobanka 88");
	System.out.println("OK!");
	//filling Contact Details section
	System.out.println("Filling Contact Details section...");		
    driver.findElement(By.id("contact.telephone")).clear();
    driver.findElement(By.id("contact.telephone")).sendKeys("+375 29 545 66 91");
    driver.findElement(By.id("contact.fax")).clear();
    driver.findElement(By.id("contact.fax")).sendKeys("545 66 91");
    driver.findElement(By.id("contact.email")).clear();
    driver.findElement(By.id("contact.email")).sendKeys("email001@com.com");
    driver.findElement(By.id("contact.websiteUrl")).clear();
    driver.findElement(By.id("contact.websiteUrl")).sendKeys("http://www.website001.com");
	System.out.println("(Social Links)");
    driver.findElement(By.id("contact.facebook")).clear();
    driver.findElement(By.id("contact.facebook")).sendKeys("http://www.facebook001.com");
    driver.findElement(By.id("contact.twitter")).clear();
    driver.findElement(By.id("contact.twitter")).sendKeys("http://www.twitter001.com");
    driver.findElement(By.id("contact.foursquare")).clear();
    driver.findElement(By.id("contact.foursquare")).sendKeys("http://www.foursquare001.com");
    driver.findElement(By.id("contact.instagram")).clear();
    driver.findElement(By.id("contact.instagram")).sendKeys("http://www.instagram.com");
    driver.findElement(By.id("contact.pinterest")).clear();
    driver.findElement(By.id("contact.pinterest")).sendKeys("http://www.pinterest.com");
	System.out.println("OK!");
    driver.findElement(By.name("_action_saveDetails")).click();
	System.out.println("Category...");
    driver.findElement(By.xpath("//fieldset[1]//ul/li[contains(text(),\"Computer Shop\")]")).click();
    driver.findElement(By.cssSelector("button.plus_btn")).click();
	System.out.println("OK!");
	/* Filling Opening Hours forms */	
	System.out.println("Filling Opening Hours forms...");
	
	//noting by tick 'I'd like to enter two sets of hours for a single day.' select box	
    driver.findElement(By.id("splitHours")).click();
	//filling 'From' input field for Timetable1
    driver.findElement(By.id("0.periodFrom")).sendKeys("02/06/13");
	//filling 'To' input field for Timetable2
    driver.findElement(By.id("0.periodTo")).sendKeys("08/06/13");
	//selecting '8:00' in 'Open' select box for Monday in first case
    new Select(driver.findElement(By.id("openingHours.main.0.Monday.from_hour"))).selectByVisibleText("08:00");
	//selecting '17:30' in 'Close' select box for Monday for first case
    new Select(driver.findElement(By.id("openingHours.main.0.Monday.to_hour"))).selectByVisibleText("17:30");
	//checking 'Closed' check box for Sunday
    driver.findElement(By.id("closedSunday0Period")).click();
	//checking '24 Hours' check box for Saterday
    driver.findElement(By.id("fullDaySaturday0Period")).click();
	//noting by tick 'Note: Uncheck to edit hours individually for each day' checkbox
    driver.findElement(By.id("selectHoursSync")).click();
	//adding Timetable2
    driver.findElement(By.id("add_period_item")).click();
	//filling 'From' input field for Timetable1
    driver.findElement(By.id("0.periodFrom")).sendKeys("09/06/13");
	//filling 'To' input field for Timetable2
    driver.findElement(By.id("0.periodTo")).sendKeys("15/06/13");
	//selecting '10:00' in 'Open' select box for Monday in first case
    new Select(driver.findElement(By.id("openingHours.main.1.Monday.from_hour"))).selectByVisibleText("10:00");
	//selecting '14:00' in 'Close' select box for Monday for first case
    new Select(driver.findElement(By.id("openingHours.main.1.Monday.to_hour"))).selectByVisibleText("17:30");
	//selecting '15:00' in 'Open' select box for Monday in second case
    new Select(driver.findElement(By.id("openingHours.additional.1.Monday.from_hour"))).selectByVisibleText("15:00");
	//selecting '19:00' in 'Close' select box for Monday for second case
    new Select(driver.findElement(By.id("openingHours.additional.1.Monday.to_hour"))).selectByVisibleText("19:30");	
	System.out.println("OK!");	

	/* Filling Holidays forms */
	System.out.println("Filling Holidays forms...");	

	//filling 'From' input field for Holiday1
    driver.findElement(By.id("holiday.0.periodFrom")).sendKeys("12/06/13");
	//filling 'To' input field for Holiday1
    driver.findElement(By.id("holiday.0.periodTo")).sendKeys("13/06/13");	
	//entering Holiday name
    driver.findElement(By.id("holiday.0.name")).clear();	
    driver.findElement(By.id("holiday.0.name")).sendKeys("Holiday1");
	System.out.println("OK!");
	//clicking on the 'Add another holiday period' link
	System.out.println("Clicking on the 'Add another holiday period' link...");	
    driver.findElement(By.id("add_holiday_item")).click();
	System.out.println("OK!");
	System.out.println("Filling Second Holidays forms...");	
	//filling 'From' input field for Holiday1
    driver.findElement(By.id("holiday.1.periodFrom")).sendKeys("20/06/13");
	//filling 'To' input field for Holiday1
    driver.findElement(By.id("holiday.1.periodTo")).sendKeys("30/06/13");	
	//entering Holiday name
    driver.findElement(By.id("holiday.1.name")).clear();	
    driver.findElement(By.id("holiday.1.name")).sendKeys("Holiday2");
	System.out.println("OK!");	

	//clicking on the "Finish" button
	System.out.println("Clickingon the \"Finish\" button...");	
    driver.findElement(By.name("_action_save")).click();
	System.out.println("OK!");	
	//checking that venue page will be opened
	System.out.println("Checking that venue page will be opened...");
	wait.until(presenceOfElementLocated(By.xpath("//div[@class='share']")));

	System.out.println("OK!");
	System.out.println("VENUE WAS CREATED!");
	System.out.println("--------------------");
}

 public void updateOfferPlan(String TradeName) throws Exception {

	//opening Business List page
	System.out.println("Opening Business List page...");
    driver.findElement(By.cssSelector("#registrationsTab > span.nav_btn_text")).click();
	System.out.println("OK!");
	//entering <Trade Name> text to 'Keywords' field
	driver.findElement(By.id("keywords")).clear();
    driver.findElement(By.id("keywords")).sendKeys(TradeName);
	//clicking on Search icon
    driver.findElement(By.id("action_button")).click();
	//clicking on 'select_all' checkbox
    driver.findElement(By.name("selectAll")).click();
 
	//selecting 'Unlimited Offers' value in 'Offer Plan' select box
	System.out.println("selecting 'Unlimited Offers' value in 'Offer Plan' select box...");
    new Select(driver.findElement(By.id("offerPlan"))).selectByVisibleText("Unlimited Offers");
	System.out.println("OK!");
	//clicking on the "Update" button
	System.out.println("clicking on the 'Update' button...");	
    driver.findElement(By.name("_action_updateContract")).click();
	System.out.println("OK!");
	//checking that Business List page is opened	
	System.out.println("checking that Business List is opened...");
	wait.until(presenceOfElementLocated(By.xpath("//div[@class=\"bottom_row\"]")));
		
	System.out.println("OK!");
	}

public void openBusiness(String tradeName)	{
//	opening business
	System.out.println("opening business...");
    driver.findElement(By.linkText(tradeName)).click();
	System.out.println("OK!");
	
//	checking that venue page will be opened
	System.out.println("Checking that venue page will be opened...");
	wait.until(presenceOfElementLocated(By.xpath("//div[@class='share']")));
}

public void clickYesletsgetstartedLink() {
//	clicking on the 'Yes, lets get started' link
	System.out.println("clicking on the 'Yes, lets get started' link...");
    driver.findElement(By.linkText("Yes, lets get started")).click();
	wait.until(presenceOfElementLocated(By.id("title")));
	System.out.println("OK!");
}

public void clickCreateanotherofferlink() {
//	clicking on the 'Create another Offer' link
	System.out.println("clicking on the 'Create another Offer' link...");
    driver.findElement(By.linkText("Create another Offer")).click();
 	System.out.println("OK!");
}

public void clickFinishButton() {
//	clicking on the 'Finish' button
	System.out.println("clicking on the 'Finish' button...");
    driver.findElement(By.linkText("Finish")).click();
 	System.out.println("OK!");
}

public void clickCreateofferButton() {
//	clicking on the 'Create Offer' button
	System.out.println("clicking on the 'create offer' button...");
    driver.findElement(By.cssSelector("a.add_button > span")).click();
 	System.out.println("OK!");
}

public void addOffer(Offer offer){

//	adding offer
	System.out.println("adding offer...");
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("Offer test");
    driver.findElement(By.id("details")).clear();
    driver.findElement(By.id("details")).sendKeys("sub title for offer");
	driver.findElement(By.id("launchDate")).sendKeys(offer.getValidityFrom());
	driver.findElement(By.id("expiryDate")).sendKeys(offer.getValidityTo());	
    driver.findElement(By.id("allWeekOffer")).click();
    driver.findElement(By.id("weekdaysOffer")).click();
    driver.findElement(By.id("weekendsOffer")).click();
    driver.findElement(By.name("offerCode.code")).clear();
    driver.findElement(By.name("offerCode.code")).sendKeys("COUPON");
    driver.findElement(By.name("offerCode.quantity")).clear();
    driver.findElement(By.name("offerCode.quantity")).sendKeys("9999");
    driver.findElement(By.name("_action_save")).click();
	
	wait.until(presenceOfElementLocated(By.xpath("//div[@class=\"green_block\"]")));

	System.out.println("OK!");
}

public void addChainOffer(Offer offer){

//	adding offer
	System.out.println("adding offer...");
    driver.findElement(By.id("title")).clear();
    driver.findElement(By.id("title")).sendKeys("Offer test");
    driver.findElement(By.id("details")).clear();
    driver.findElement(By.id("details")).sendKeys("sub title for offer");
	
	driver.findElement(By.id("launchDate")).sendKeys(offer.getValidityFrom());
	driver.findElement(By.id("expiryDate")).sendKeys(offer.getValidityTo());	
	
    driver.findElement(By.id("allWeekOffer")).click();
    driver.findElement(By.id("weekdaysOffer")).click();
    driver.findElement(By.id("weekendsOffer")).click();
	
	if (offer.getIsUnique()) driver.findElement(By.id("singleCoupon")).click();
	
    new Select(driver.findElement(By.id("type"))).selectByVisibleText(offer.getOfferType());	
	
	if (offer.getOfferType().equals("Online") || offer.getOfferType().equals("Anywhere")) driver.findElement(By.id("onlineUrl")).sendKeys("http://www.some_url.com");
	
	if (offer.getIsRedeemable()) {
		new Select(driver.findElement(By.id("offerCode.renderingFormat"))).selectByVisibleText(offer.getCodeType());
		wait.until(presenceOfElementLocated(By.xpath("//div[@class=\"code\"]")));
		new Select(driver.findElement(By.id("offerCode.type"))).selectByVisibleText(offer.getCodeFormat());
		if (offer.getCodeFormat().equals("Custom")){
			
			wait.until(presenceOfElementLocated(By.name("offerCode.code")));
			driver.findElement(By.name("offerCode.code")).sendKeys("CODE_TEST_01");
			
			wait.until(presenceOfElementLocated(By.name("offerCode.quantity")));			
			driver.findElement(By.name("offerCode.quantity")).sendKeys("5000");
			
			wait.until(presenceOfElementLocated(By.name("offerCode.control")));			
			driver.findElement(By.name("offerCode.control")).sendKeys("CUSTOM_CODE_01");
		}
		if (offer.getCodeFormat().equals("Static")){

			wait.until(presenceOfElementLocated(By.name("offerCode.quantity")));			
			driver.findElement(By.name("offerCode.code")).sendKeys("CODE_TEST_01");
			
			wait.until(presenceOfElementLocated(By.name("offerCode.quantity")));
			driver.findElement(By.name("offerCode.quantity")).sendKeys("5000");
		}	
	}
	else driver.findElement(By.id("useRedeemableCode")).click();

    driver.findElement(By.name("_action_save")).click();
	wait.until(presenceOfElementLocated(By.xpath("//div[@class=\"green_block\"]")));	
	System.out.println("OK!");
} 

  public void onlineRegistration(Business business, User merchantManager, Emailclient email) throws Exception{
	System.out.println("");
	System.out.println("--- Checking online registration witmerchantManagerh next data: ---");
	System.out.println("");
	System.out.println("CONTRACT TYPE:     " + business.getContractType());
	System.out.println("PROMO CODE:     " + business.getPromoCode());
	System.out.println("");	
	
/**/	firstStepOfOnlineRegistration(business, merchantManager);
	manager.getEmailclientHelper().checkFirstOnlineRegistrationEmail(email);
	checkPendingBusiness(business);
	manager.getUserHelper().checkDisabledUser(merchantManager);
	approveBusiness(business);
	manager.getEmailclientHelper().checkNextOnlineRegistrationEmails(email);
	manager.getEmailclientHelper().clickClickHereLinkAndFindCompleteBRwindow();	
	finalRegistrationStepOfOnlineRegistration();
	}	
	
public void firstStepOfOnlineRegistration(Business business, User merchantManager){
	//opening external registration page
	driver.get(manager.getBaseUrl()+"merchant/signup");
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
	wait.until(presenceOfElementLocated(By.xpath("//option[@value='London']")));
    new Select(driver.findElement(By.id("city"))).selectByVisibleText("London");
    driver.findElement(By.id("postalCode")).clear();
    driver.findElement(By.id("postalCode")).sendKeys("44400");
	//filling Merchant Manager fields
    driver.findElement(By.id("firstName")).clear();
    driver.findElement(By.id("firstName")).sendKeys("Merchant");
    driver.findElement(By.id("lastName")).clear();
    driver.findElement(By.id("lastName")).sendKeys("Manager");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys(merchantManager.getEmail());
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(merchantManager.getUsername());
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(merchantManager.getPassword());
    //filling contract data fields
	new Select(driver.findElement(By.id("contractTypeId"))).selectByVisibleText(business.getContractType());
    driver.findElement(By.id("numberOfVenues")).clear();
    driver.findElement(By.id("numberOfVenues")).sendKeys("100");
    driver.findElement(By.id("promotionalCode")).clear();
    driver.findElement(By.id("promotionalCode")).sendKeys(business.getPromoCode());	
	
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
	wait.until(presenceOfElementLocated(By.xpath("//table[@class='registration-steps']")));
	//waiting until "Submit" button will be shown
	wait.until(presenceOfElementLocated(By.name("_action_signupProcessing")));
	//clicking on the "Submit" button
    driver.findElement(By.name("_action_signupProcessing")).click();
	//checking that div with 'print_area' id exists
	wait.until(presenceOfElementLocated(By.xpath("//div[@id='print_area']")));
	//waiting until "Finish" button will be shown
	wait.until(presenceOfElementLocated(By.name("_action_null")));
	//clicking on the "Finish" button
    driver.findElement(By.name("_action_null")).click();
	//checking that start page of nearme was opened
	manager.getNavigationHelper().checkStartPage();
	System.out.println("OK!");
}	

public void checkPendingBusiness(Business business) throws Exception {

	/* Checking that Business with 'Auto Online Trading' trading name was created and has 'Pending' status */
	
	System.out.println("Checking that Business with 'Auto Online Trading' trading name was created and has 'Pending' status...");	
	manager.getUserHelper().loginAsAdmin();
	manager.getNavigationHelper().gotoBusinessesPage();
 
    //entering 'Auto Online Trading' text to 'Keywords' field
	driver.findElement(By.id("keywords")).clear();
    driver.findElement(By.id("keywords")).sendKeys(business.getTradename());
	//clicking on Search icon
    driver.findElement(By.id("action_button")).click();
	//checking that "searchResultList" table contains contains 'Auto Online Trading' text
	wait.until(presenceOfElementLocated(By.xpath("//table[@id=\"searchResultList\"]//a[contains(text(),'" + business.getTradename() + "')]")));
   //checking that "searchResultList" table contains 'status_pending' class
	wait.until(presenceOfElementLocated(By.xpath("//table[@id=\"searchResultList\"]//div[@class='status_pending']")));   
	System.out.println("OK!");
}

public void approveBusiness(Business business){
	/* Approving business */

	System.out.println("Approving new business...");	
	//going to Businesses tab
	manager.getNavigationHelper().gotoBusinessesPage();
    //entering <Trading Name> text to 'Keywords' field
	driver.findElement(By.id("keywords")).clear();
    driver.findElement(By.id("keywords")).sendKeys(business.getTradename());
	//clicking on Search icon
    driver.findElement(By.id("action_button")).click();
	//clicking on 'select_all' checkbox
    driver.findElement(By.name("selectAll")).click();	
	//selecting 'Publish' value in 'Status' select box
    new Select(driver.findElement(By.id("newStatus"))).selectByVisibleText("Publish / Approve");
	//clicking on the "Update" button
    driver.findElement(By.name("_action_updateContract")).click();
	//checking that "searchResultList" table contains 'status_live' class
	wait.until(presenceOfElementLocated(By.xpath("//table[@id=\"searchResultList\"]//div[@class='status_live']")));
	System.out.println("OK!");
}

public void finalRegistrationStepOfOnlineRegistration() throws Exception {

	/*  Completing final registration step */

	System.out.println("--- Completing final registration step... ---");	
	//checking that 'Complete Business Registration' page was opened
	System.out.println("Checking that 'Complete Business Registration' page was opened...");
	wait.until(presenceOfElementLocated(By.xpath("//input[@id='managers[0].firstName']")));
	System.out.println("OK!");		
	//filling of empty fields
	System.out.println("Filling of empty fields:");		
	System.out.println("Job Title...");		
	driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.jobTitle")).clear();
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.jobTitle")).sendKeys("Job Title"); 
	System.out.println("OK!");
    System.out.println("National Insurance No...");	
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.nationalInsuranceNo")).clear();
    driver.findElement(By.id("managers[0].merchantManagerRoleDefinition.nationalInsuranceNo")).sendKeys("NatInsNo");
	System.out.println("OK!");
    System.out.println("Telephone...");		
    driver.findElement(By.id("managers[0].contact.telephone")).clear();
    driver.findElement(By.id("managers[0].contact.telephone")).sendKeys("2220010");
	System.out.println("OK!");
    System.out.println("Mobile...");		
    driver.findElement(By.id("managers[0].contact.mobile")).clear();
    driver.findElement(By.id("managers[0].contact.mobile")).sendKeys("334455");
	System.out.println("OK!");
	System.out.println("Company No...");
    driver.findElement(By.id("registeredCompanyNo")).clear();
    driver.findElement(By.id("registeredCompanyNo")).sendKeys("companyNo");
	System.out.println("OK!");
	System.out.println("Region...");

	wait.until(presenceOfElementLocated(By.xpath("//*[@id='registeredAddress.region']/*[@value='London']")));
    new Select(driver.findElement(By.id("registeredAddress.region"))).selectByVisibleText("London");
	System.out.println("OK!");
	System.out.println("County...");
	wait.until(presenceOfElementLocated(By.xpath("//*[@id='registeredAddress.county']/*[@value='Dorset']")));
	Thread.sleep(3000);
    new Select(driver.findElement(By.id("registeredAddress.county"))).selectByVisibleText("Dorset");
	System.out.println("OK!");
	System.out.println("Contact Details...");
    driver.findElement(By.id("companyContactDetails.telephone")).clear();
    driver.findElement(By.id("companyContactDetails.telephone")).sendKeys("100001");
    driver.findElement(By.id("companyContactDetails.fax")).clear();
    driver.findElement(By.id("companyContactDetails.fax")).sendKeys("100002");
    driver.findElement(By.id("companyContactDetails.email")).clear();
    driver.findElement(By.id("companyContactDetails.email")).sendKeys("email001@com.com");
    driver.findElement(By.id("companyContactDetails.websiteUrl")).clear();
    driver.findElement(By.id("companyContactDetails.websiteUrl")).sendKeys("http://www.website001.com");
    driver.findElement(By.id("companyContactDetails.facebook")).clear();
    driver.findElement(By.id("companyContactDetails.facebook")).sendKeys("http://www.facebook001.com");
    driver.findElement(By.id("companyContactDetails.twitter")).clear();
    driver.findElement(By.id("companyContactDetails.twitter")).sendKeys("http://www.twitter001.com");
    driver.findElement(By.id("companyContactDetails.foursquare")).clear();
    driver.findElement(By.id("companyContactDetails.foursquare")).sendKeys("http://www.foursquare001.com");
    driver.findElement(By.id("companyContactDetails.instagram")).clear();
    driver.findElement(By.id("companyContactDetails.instagram")).sendKeys("http://www.instagram.com");
    driver.findElement(By.id("companyContactDetails.pinterest")).clear();
    driver.findElement(By.id("companyContactDetails.pinterest")).sendKeys("http://www.pinterest.com");
	System.out.println("OK!");
	System.out.println("Category...");
    driver.findElement(By.xpath("//fieldset[6]//ul/li[contains(text(),\"Candy Shop\")]")).click();
    driver.findElement(By.cssSelector("button.plus_btn")).click();
	System.out.println("OK!");
	System.out.println("Keywords...");
    driver.findElement(By.id("keywords.ti1")).clear();
    driver.findElement(By.id("keywords.ti1")).sendKeys("keyword001");
    driver.findElement(By.id("keywords.ti1")).click();
	System.out.println("OK!");	
	//clicking on the "Continue" button
	System.out.println("Clicking on the Continue button...");	
    driver.findElement(By.name("_action_save")).click();
	//waiting until new page was not opened
	wait.until(presenceOfElementLocated(By.xpath("//div[@class=\"period_item\"]")));
	System.out.println("OK!");		
	//clicking on the "Use Company Details" button
	System.out.println("Clicking on the 'Use Company Details' button...");		
    driver.findElement(By.id("copyCompanyDetailsButton")).click();
	System.out.println("OK!");	
	//clicking on the "Use Contact Details" button	
	System.out.println("Clicking on the 'Use Contact Details' button...");	
    driver.findElement(By.id("copyContactDetailsButton")).click();
	System.out.println("OK!");
	Thread.sleep(1000);
	//filling "Url Name" filed
	System.out.println("Filling \"Url Name\" filed...");		
    driver.findElement(By.id("displayName")).sendKeys("venue001");
	System.out.println("OK!");
	
	/* Filling Opening Hours forms */	
	System.out.println("Filling Opening Hours forms...");
	
	//noting by tick 'I'd like to enter two sets of hours for a single day.' select box	
    driver.findElement(By.id("splitHours")).click();
	//filling 'From' input field for Timetable1
    driver.findElement(By.id("0.periodFrom")).sendKeys("02/06/13");
	//filling 'To' input field for Timetable2
    driver.findElement(By.id("0.periodTo")).sendKeys("08/06/13");
	//selecting '8:00' in 'Open' select box for Monday in first case
    new Select(driver.findElement(By.id("openingHours.main.0.Monday.from_hour"))).selectByVisibleText("08:00");
	//selecting '17:30' in 'Close' select box for Monday for first case
    new Select(driver.findElement(By.id("openingHours.main.0.Monday.to_hour"))).selectByVisibleText("17:30");
	//checking 'Closed' check box for Sunday
    driver.findElement(By.id("closedSunday0Period")).click();
	//checking '24 Hours' check box for Saterday
    driver.findElement(By.id("fullDaySaturday0Period")).click();
	//noting by tick 'Note: Uncheck to edit hours individually for each day' checkbox
    driver.findElement(By.id("selectHoursSync")).click();
	//adding Timetable2
    driver.findElement(By.id("add_period_item")).click();
	//filling 'From' input field for Timetable1
    driver.findElement(By.id("0.periodFrom")).clear();;
    driver.findElement(By.id("0.periodFrom")).sendKeys("09/06/13");
	//filling 'To' input field for Timetable2
    driver.findElement(By.id("0.periodTo")).clear();;
    driver.findElement(By.id("0.periodTo")).sendKeys("15/06/13");
	//selecting '10:00' in 'Open' select box for Monday in first case
    new Select(driver.findElement(By.id("openingHours.main.1.Monday.from_hour"))).selectByVisibleText("10:00");
	//selecting '14:00' in 'Close' select box for Monday for first case
    new Select(driver.findElement(By.id("openingHours.main.1.Monday.to_hour"))).selectByVisibleText("17:30");
	//selecting '15:00' in 'Open' select box for Monday in second case
    new Select(driver.findElement(By.id("openingHours.additional.1.Monday.from_hour"))).selectByVisibleText("15:00");
	//selecting '19:00' in 'Close' select box for Monday for second case
    new Select(driver.findElement(By.id("openingHours.additional.1.Monday.to_hour"))).selectByVisibleText("19:30");	
	System.out.println("OK!");	

	/* Filling Holidays forms */
	System.out.println("Filling Holidays forms...");	

	//filling 'From' input field for Holiday1
    driver.findElement(By.id("holiday.0.periodFrom")).sendKeys("12/06/13");
	//filling 'To' input field for Holiday1
    driver.findElement(By.id("holiday.0.periodTo")).sendKeys("13/06/13");	
	//entering Holiday name
    driver.findElement(By.id("holiday.0.name")).sendKeys("Holiday1");		
	//clicking on the 'Add another holiday period' link
    driver.findElement(By.id("add_holiday_item")).click();
	//filling 'From' input field for Holiday2
    driver.findElement(By.id("holiday.1.periodFrom")).clear();
    driver.findElement(By.id("holiday.1.periodFrom")).sendKeys("20/06/13");
	//filling 'To' input field for Holiday2
    driver.findElement(By.id("holiday.1.periodTo")).clear();	
    driver.findElement(By.id("holiday.1.periodTo")).sendKeys("30/06/13");	
	//entering Holiday name
    driver.findElement(By.id("holiday.1.name")).clear();
    driver.findElement(By.id("holiday.1.name")).sendKeys("Holiday2");
	System.out.println("OK!");	
	//clicking on the "Finish" button
    driver.findElement(By.name("_action_save")).click();
	wait.until(presenceOfElementLocated(By.xpath("//h3[@class=\"info_details__title\"]")));
	//clicking on the 'Venue' tab
    driver.findElement(By.linkText("Venue")).click();	
	//checking that venue page will be opened
	wait.until(presenceOfElementLocated(By.xpath("//div[@class='share']")));
	manager.getUserHelper().logout();
	System.out.println("OK!");
}

public void onlineRegistrationWithUser(Business business, User merchantManager, Emailclient email, User consumer)throws Exception {
	System.out.println("");
	System.out.println("--- Checking online registration with next data: ---");
	System.out.println("");
	System.out.println("CONTRACT TYPE:     " + business.getContractType());
	System.out.println("PROMO CODE:     " + business.getPromoCode());
	System.out.println("IS 'Use my account...' CHECKBOX NOTED?:     " + consumer.getIsUsedAsMerchantManager());
	System.out.println("");
	
	firstStepOfOnlineRegistrationWithUser(business.getContractType(), business.getPromoCode(), consumer.getIsUsedAsMerchantManager() );

	manager.getEmailclientHelper().checkFirstOnlineRegistrationEmail(email);
	 
	checkPendingBusiness(business);
	if (!consumer.getIsUsedAsMerchantManager()) {
		manager.getUserHelper().checkDisabledUser(merchantManager);
	}
	approveBusiness(business);
	manager.getUserHelper().logout();
	
	/* Checking that emails notifications were sent */
	if (!consumer.getIsUsedAsMerchantManager()) {
		manager.getEmailclientHelper().checkNextOnlineRegistrationEmails(email);
		manager.getEmailclientHelper().clickClickHereLinkAndFindCompleteBRwindow();		
	}
	else {
		manager.getEmailclientHelper().checkNextOnlineRegistrationEmails(email);
		manager.getUserHelper().loginAs(consumer);		
	}
	finalRegistrationStepOfOnlineRegistration();
	System.out.println("OK!");	
}

public void firstStepOfOnlineRegistrationWithUser(String ContractType, String PromoCode, boolean isNotedCheckbox)throws Exception {
	//opening external registration page
	driver.get(manager.getBaseUrl()+"merchant/signup");
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
	wait.until(presenceOfElementLocated(By.xpath("//option[@value='London']")));
    new Select(driver.findElement(By.id("city"))).selectByVisibleText("London");
    driver.findElement(By.id("postalCode")).clear();
    driver.findElement(By.id("postalCode")).sendKeys("44400");

	if (!isNotedCheckbox) {
		//removing a tick from 'Use my account as Merchant Manager' check box
		driver.findElement(By.id("useCurrentUserAsManager")).click();
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
	}

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
	wait.until(presenceOfElementLocated(By.xpath("//table[@class='registration-steps']")));
	//waiting until "Submit" button will be shown
	wait.until(presenceOfElementLocated(By.name("_action_signupProcessing")));
	//clicking on the "Submit" button
    driver.findElement(By.name("_action_signupProcessing")).click();
	//checking that div with 'print_area' id exists
	wait.until(presenceOfElementLocated(By.xpath("//div[@id='print_area']")));
	//waiting until "Finish" button will be shown
	wait.until(presenceOfElementLocated(By.name("_action_null")));
	//clicking on the "Finish" button
    driver.findElement(By.name("_action_null")).click();
	
	//checking that 'Account Details' page was opened 
	manager.getUserHelper().checkAccountPage();		
	System.out.println("OK!");
	
	manager.getUserHelper().logout();
}

}
 
 

