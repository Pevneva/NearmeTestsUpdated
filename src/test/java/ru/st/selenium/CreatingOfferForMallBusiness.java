package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Business;
import ru.st.selenium.model.Venue;
import ru.st.selenium.model.Offer;

public class CreatingOfferForMallBusiness extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}   
  
  @Test
  public void testCreatingOfferForMallBusiness() throws Exception { 

	String tradeName="Business & venue & offer - test";
	String contractNo="CoNo-001";
	String urlName="business_url";
	String managerEmail="email_01@mail.ru";
	
  	System.out.println("=================================================");  
	System.out.println("");		
	System.out.println("===== ADMIN CREATING OFFER FOR MALL BUSINESS =====");	
	System.out.println("");		

	Business business = new Business()
						.setTradename(tradeName)
						.setBusinessType("Mall")
						.setNumberOfVenues("2")
						.setContractNumber(contractNo)
						.setManagerEmail(managerEmail)
						.setUrlName(urlName);
	Venue venue = new Venue()
						.setUrlName("test_venue")
						.setTradename("Computer House");
	Offer offer1 = new Offer()
						.setValidityFrom("01/01/13")
						.setValidityTo("31/12/14")
						.setIsUnique(false)
						.setIsRedeemable(false)
						.setOfferType("In Store");
	Offer offer2 = new Offer()
						.setValidityFrom("01/01/12")
						.setValidityTo("31/12/12")
						.setIsUnique(false)
						.setIsRedeemable(true)
						.setOfferType("Online")
						.setCodeType("Text")
						.setCodeFormat("Custom");
	Offer offer3 = new Offer()
						.setValidityFrom("01/01/15")
						.setValidityTo("31/12/15")
						.setIsUnique(false)
						.setIsRedeemable(true)
						.setOfferType("Anywhere")
						.setCodeType("QR Code")
						.setCodeFormat("Static");
	Offer offer4 = new Offer()
						.setValidityFrom("01/01/10")
						.setValidityTo("31/12/20")
						.setIsUnique(false)
						.setIsRedeemable(true)
						.setOfferType("Online")
						.setCodeType("PDF417")
						.setCodeFormat("Static");						
	User user = new User().setEmail(managerEmail);
					
	app.getUserHelper().loginAsAdmin();
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);
	app.getBusinessHelper().removeBusinessByKeywords(venue.getUrlName());
	app.getBusinessHelper().removeBusinessByKeywords(venue.getTradename());
	app.getUserHelper().removeUser(user);
	
	app.getBusinessHelper().addBusiness(business);
	app.getNavigationHelper().clickOnAddvenueButton();
	app.getBusinessHelper().addVenue(venue);

	app.getBusinessHelper().updateOfferPlan(business.getTradename());
	app.getBusinessHelper().openBusiness(business.getTradename());
	app.getNavigationHelper().gotoOffersTab();
	app.getBusinessHelper().clickYesletsgetstartedLink();
	app.getBusinessHelper().addChainOffer(offer1);
	app.getBusinessHelper().clickCreateanotherofferlink();
	app.getBusinessHelper().addChainOffer(offer2);	
	app.getBusinessHelper().clickFinishButton();	
	app.getBusinessHelper().clickCreateofferButton();	
	app.getBusinessHelper().addChainOffer(offer3);	
	app.getBusinessHelper().clickFinishButton();
	app.getBusinessHelper().clickCreateofferButton();	
	app.getBusinessHelper().addChainOffer(offer4);
	
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);
	app.getBusinessHelper().removeBusinessByKeywords(venue.getUrlName());
	app.getBusinessHelper().removeBusinessByKeywords(venue.getTradename());		
	app.getUserHelper().removeUser(user);

	app.getUserHelper().logout();	  
/*  
	String TradName="";

  	System.out.println("===================================================");  
	System.out.println("");		
	System.out.println("===== ADMIN CREATING OFFER FOR MALL BUSINESS =====");	
	System.out.println("");		
	driver.manage().window().maximize();
	loginAsAdmin();
	
	TradName="Business & venue & offer - test";

	removeBusiness(TradName);
	removeUser("email_01@mail.ru");
	addBusiness(TradName,"Mall", "100");
//	clicking on the "Add a Venue" button
	System.out.println("Clicking on the \"Add a Venue\" button...");
	driver.findElement(By.cssSelector("a.add_button > span")).click();
	System.out.println("OK!");	
	addVenue();

	updateOfferPlanForBusiness(TradName);
	
//	opening business
	System.out.println("opening business...");
    driver.findElement(By.linkText(TradName)).click();
	System.out.println("OK!");	
	
//	checking that venue page will be opened
	System.out.println("Checking that venue page will be opened...");
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//div[@class=\"photo_video\"]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }	

//	clicking on the Offers tab	
	System.out.println("Checking that venue page will be opened...");
	driver.findElement(By.xpath("//a[contains(text(),'Offers')]")).click();
	System.out.println("OK!");
	
//	clicking on the 'Yes, lets get started' link
	System.out.println("clicking on the 'Yes, lets get started' link...");
    driver.findElement(By.linkText("Yes, lets get started")).click();
	System.out.println("OK!");

	addChainOffer("01/01/13","31/12/14",false,"In Store",false,"","");
	
//	clicking on the 'Create another Offer' link
	System.out.println("clicking on the 'Create another Offer' link...");
    driver.findElement(By.linkText("Create another Offer")).click();
 	System.out.println("OK!");
	
	addChainOffer("01/01/12","31/12/12",false,"Online",true,"Text","Custom");
	
//	clicking on the 'Finish' button
	System.out.println("clicking on the 'Finish' button...");
    driver.findElement(By.linkText("Finish")).click();
 	System.out.println("OK!");	
	
//	clicking on the 'Create Offer' button
	System.out.println("clicking on the 'create offer' button...");
    driver.findElement(By.cssSelector("a.add_button > span")).click();
 	System.out.println("OK!");

	addChainOffer("01/01/15","31/12/15",false,"Anywhere",true,"QR Code","Static");	
	
//	clicking on the 'Create another Offer' link
	System.out.println("clicking on the 'Create another Offer' link...");
    driver.findElement(By.linkText("Create another Offer")).click();
 	System.out.println("OK!");
	
	addChainOffer("01/01/10","31/12/20",false,"Online",true,"PDF417","Static");	
	
	removeBusiness(TradName);
	removeUser("email_01@mail.ru");

	logout();
*/	
	
	System.out.println(""); 
	System.out.println("===== ADMIN CREATING OFFER FOR MALL BUSINESS WAS COMPLETED! ====="); 
	System.out.println(""); 
	System.out.println("================================================================");
	
	}

 }