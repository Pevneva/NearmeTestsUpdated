package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Business;
import ru.st.selenium.model.Venue;
import ru.st.selenium.model.Offer;

public class CreatingOfferForChainBusiness extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}  
  
  @Test
  public void testCreatingOfferForChainBusiness() throws Exception { 
  
	String tradeName="Business & venue & offer - test";
	String contractNo="CoNo-001";
	String urlName="business_url";
	String managerEmail="email_01@mail.ru";
	
  	System.out.println("=================================================");  
	System.out.println("");		
	System.out.println("===== ADMIN CREATING OFFER FOR CHAIN BUSINESS =====");	
	System.out.println("");		

	Business business = new Business()
						.setTradename(tradeName)
						.setBusinessType("Chain")
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
						.setIsUnique(true)
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

	System.out.println(""); 
	System.out.println("===== ADMIN CREATING OFFER FOR CHAIN BUSINESS WAS COMPLETED! ====="); 
	System.out.println(""); 
	System.out.println("================================================================");
	
	}
 

}