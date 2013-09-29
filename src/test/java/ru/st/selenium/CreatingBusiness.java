package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Business;

public class CreatingBusiness extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}  
  
  @Test
  public void testCreatingBusiness() throws Exception {    
	String tradeName="Auto Admin Trading";
	String contractNo="CoNo-001";
	String urlName="business_url";
	String managerEmail="email_01@mail.ru";

  	System.out.println("===================================================");  
	System.out.println("");	
	System.out.println("============= ADMIN CREATING BUSINESS =============");
	System.out.println("");	
	
	Business business = new Business()
						.setTradename(tradeName)
						.setBusinessType("SMB")
						.setNumberOfVenues("2")
						.setContractNumber(contractNo)
						.setManagerEmail(managerEmail)
						.setUrlName(urlName);
	User user = new User().setEmail(managerEmail);
						
	app.getUserHelper().loginAsAdmin();
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);
	app.getUserHelper().removeUser(user);
	
	app.getBusinessHelper().addBusiness(business);
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);	
	app.getUserHelper().removeUser(user);
	
	business = business.setBusinessType("Chain").setNumberOfVenues("100");

	app.getBusinessHelper().addBusiness(business);
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);	
	app.getUserHelper().removeUser(user);
	
	business = business.setBusinessType("Mall");

	app.getBusinessHelper().addBusiness(business);
	app.getBusinessHelper().removeBusinessByKeywords(tradeName);
	app.getBusinessHelper().removeBusinessByKeywords(contractNo);
	app.getBusinessHelper().removeBusinessByKeywords(urlName);	
	app.getUserHelper().removeUser(user);

	app.getUserHelper().logout();
	
	System.out.println(""); 
	System.out.println("======= ADMIN CREATING BUSINESS WAS COMPLETED! ======="); 
	System.out.println(""); 
	System.out.println("======================================================"); 	
	}
}