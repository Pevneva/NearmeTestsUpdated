package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Business;
import ru.st.selenium.model.Venue;

public class CreatingVenue extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}  
  
  @Test
  public void testCreatingVenue() throws Exception {    
	String tradeName="Business & venue - test";
	String contractNo="CoNo-001";
	String urlName="business_url";
	String managerEmail="email_01@mail.ru";

  	System.out.println("================================================");  
	System.out.println("");	
	System.out.println("============= ADMIN CREATING VENUE =============");
	System.out.println("");	
	
	Business business = new Business()
						.setTradename(tradeName)
						.setBusinessType("SMB")
						.setNumberOfVenues("2")
						.setContractNumber(contractNo)
						.setManagerEmail(managerEmail)
						.setUrlName(urlName);
	Venue venue = new Venue()
						.setUrlName("test_venue")
						.setTradename("Computer House");
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

	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);
	app.getBusinessHelper().removeBusinessByKeywords(venue.getUrlName());
	app.getBusinessHelper().removeBusinessByKeywords(venue.getTradename());	
	app.getUserHelper().removeUser(user);	
	
	business = business.setBusinessType("Chain").setNumberOfVenues("100");

	app.getBusinessHelper().addBusiness(business);
	app.getNavigationHelper().clickOnAddvenueButton();
	app.getBusinessHelper().addVenue(venue);
	
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);
	app.getBusinessHelper().removeBusinessByKeywords(venue.getUrlName());
	app.getBusinessHelper().removeBusinessByKeywords(venue.getTradename());	
	app.getUserHelper().removeUser(user);	
	
	business = business.setBusinessType("Mall");

	app.getBusinessHelper().addBusiness(business);
	app.getNavigationHelper().clickOnAddvenueButton();
	app.getBusinessHelper().addVenue(venue);	
	
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);
	app.getBusinessHelper().removeBusinessByKeywords(venue.getUrlName());
	app.getBusinessHelper().removeBusinessByKeywords(venue.getTradename());		
	app.getUserHelper().removeUser(user);

	app.getUserHelper().logout();
  
	System.out.println(""); 
	System.out.println("======= ADMIN CREATING VENUE WAS COMPLETED! ======="); 
	System.out.println(""); 
	System.out.println("===================================================");	
	}
	

}