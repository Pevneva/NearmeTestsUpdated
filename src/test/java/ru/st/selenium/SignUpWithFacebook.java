package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Emailclient;

public class SignUpWithFacebook extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}  
  
  @Test
  public void testSignUpWithFacebook() throws Exception {

	System.out.println("===========================================");  
	System.out.println("");    
	System.out.println("========== SIGN UP WITH FACEBOOK =========="); 
	System.out.println("");

	User user = new User()
				.setEmail("lyudmila_test_accountant@mail.ru")
				.setPassword("test12345")
				.setUsername("TestFacebookUserName")
				.setCountry("Belarus")
				.setCity("Minsk");

	app.getUserHelper().loginAsAdmin();
	app.getUserHelper().removeUser(user);
	app.getUserHelper().logout();
	app.getNavigationHelper().gotoSignupPage();
	app.getUserHelper().signupWithFacebook(user);
	app.getUserHelper().loginWithFacebook();
	app.getUserHelper().logout();
	
	/* Removing created data */
	
	System.out.println("");	
	System.out.println("--- Removing created data... ---");	
	System.out.println("");	

	app.getUserHelper().loginAsAdmin();
	app.getUserHelper().removeUser(user);
	app.getUserHelper().logout();

	System.out.println(""); 
	System.out.println("====== SIGN UP WITH FACEBOOK WAS COMPLETED! ======"); 
	System.out.println(""); 
	System.out.println("=================================================="); 	
  }

}
