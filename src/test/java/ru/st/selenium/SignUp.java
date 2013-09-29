package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Emailclient;

public class SignUp extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}
  
  @Test
  public void testSignUp() throws Exception {

	System.out.println("========================================");  
	System.out.println("");  
	System.out.println("=========== SIGN UP STANDARD ===========");  
	System.out.println("");  
	
	User user = new User()
				.setEmail("lyudmila_test_accountant@mail.ru")
				.setUsername("TestUserName")
				.setPassword("12345678")
				.setFirstname("Ivan")
				.setLastname("Ivanov")
				.setGender("Male")
				.setBirthday("01/05/83")
				.setCountry("Belarus")
				.setCity("Minsk");
	Emailclient email = new Emailclient()
				.setEmail("lyudmila_test_accountant@mail.ru")
				.setPassword("test123");
	app.getUserHelper().loginAsAdmin();
	app.getUserHelper().removeUser(user);
	app.getUserHelper().logout();
	
	app.getUserHelper().signUp(user, email);
	app.getUserHelper().loginAs(user);
	app.getUserHelper().checkAccountPage();
	app.getUserHelper().logout();
	
	System.out.println("");	
	System.out.println("--- Clearing usage data ---");	
	System.out.println("");	
	
	app.getUserHelper().loginAsAdmin();
	app.getUserHelper().removeUser(user);
	app.getUserHelper().logout();
	app.getEmailclientHelper().logoutFromMailru();
	
	System.out.println("");  
	System.out.println("====== SIGN UP STANDARD TEST WAS COMPLETED! ======"); 
	System.out.println(""); 
	System.out.println("=================================================="); 
 }

}
