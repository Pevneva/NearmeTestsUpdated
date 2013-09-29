package ru.st.selenium;

import org.testng.annotations.*;
import ru.st.selenium.model.User;


public class SignUpClient extends ru.st.selenium.pages.TestBase {
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  String respBody;

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isLoggedIn()) {
			app.getUserHelper().logout();
		}
	}  

  @Test
  public void testSignUpClient() throws Exception { 

		System.out.println("========================================");  
		System.out.println("");    
		System.out.println("============ SIGN UP CLIENT ============"); 
		System.out.println(""); 
		
		User user = new User()
					.setEmail("lyudmila_test_03@mail.ru");
		app.getUserHelper().loginAsAdmin();					
		app.getUserHelper().removeUser(user);
		app.getUserHelper().logout();		
		
		//Executing API Sign Up process
		System.out.println("Executing API Sign Up process...");
		String signupUri=app.getBaseUrl()+"api/v1/signup?hash=3/3sVlGXuPb/IYUqbflui5DwjgIEJHAtCcYoJGrefVE=&email=lyudmila_test_03@mail.ru";
		respBody=app.getApiHelper().postRequest(signupUri);
		System.out.println("Response:");
		System.out.println(respBody);	

		//Checking that Response Body is correct
		System.out.println("Checking that Response Body is correct...");
		app.getApiHelper().checkResponse(respBody,"\"code\": 200");
		app.getApiHelper().checkResponse(respBody,"\"username\": \"TestUserName02\"");
		app.getApiHelper().checkResponse(respBody,"\"firstName\": \"__first_name__\"");
		app.getApiHelper().checkResponse(respBody,"\"lastName\": \"__last_name__\"");
		app.getApiHelper().checkResponse(respBody,"\"email\": \"lyudmila_test_03@mail.ru\"");
		app.getApiHelper().checkResponse(respBody,"\"accountType\": \"consumer\"");
		app.getApiHelper().checkResponse(respBody,"\"accountStatus\": \"active\"");
		app.getApiHelper().checkResponse(respBody,"\"birthday\": null");
		app.getApiHelper().checkResponse(respBody,"\"gender\": null");
		app.getApiHelper().checkResponse(respBody,"\"hometown\": null");
		app.getApiHelper().checkResponse(respBody,"\"photoUrl\": null");
		System.out.println("OK");
		
		//Taking token for logoutB
		System.out.println("Taking token...");
		int num=app.getApiHelper().indexOfToken(respBody,"\"token\"");
		String token=respBody.substring(num+10, num+20);
		int num2=token.indexOf("\"");
		if (num2>-1) {			
			token=token.substring(0,num2);
			}
		System.out.println("token="+token);
		
		//Executing API Log Out process with taken token
		System.out.println("Executing API Log Out with taken token...");
		String logoutUri=app.getBaseUrl()+"api/v1/logout?hash=3/3sVlGXuPb/IYUqbflui5DwjgIEJHAtCcYoJGrefVE=";
		respBody=app.getApiHelper().postRequestWithToken(logoutUri,token);
		System.out.println("Response:");
		System.out.println(respBody);

		System.out.println("Checking that Response Body is correct...");
		app.getApiHelper().checkResponse(respBody,"\"message\": \"User was successfully logged out\"");		
		System.out.println("OK");
		
		//Executing API Log In process
		System.out.println("Executing API Log In process...");
		String loginUri=app.getBaseUrl()+"api/v1/login?hash=3/3sVlGXuPb/IYUqbflui5DwjgIEJHAtCcYoJGrefVE=";
		respBody=app.getApiHelper().postRequest(loginUri);
		System.out.println("Response:");
		System.out.println(respBody);
		app.getApiHelper().checkResponse(respBody,"\"email\": \"lyudmila_test_03@mail.ru\"");

		//Executing API Forgot process
		System.out.println("Executing API Forgot process...");
		String forgotUri=app.getBaseUrl()+"api/v1/forgot?user=lyudmila_test_03@mail.ru";
		respBody=app.getApiHelper().postRequest(forgotUri);		
		System.out.println("Response:");
		System.out.println(respBody);
		app.getApiHelper().checkResponse(respBody,"\"message\": \"Password successfully changed and sent to User's email\"");		
		
		System.out.println(""); 		
		System.out.println("======= SIGN UP CLIENT TEST WAS COMPLETED! ======="); 
		System.out.println(""); 
		System.out.println("=================================================="); 
	}

}
