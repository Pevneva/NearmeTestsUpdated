package ru.st.selenium.applogic;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
/*
import ru.st.selenium.applogic.UserHelper;
import ru.st.selenium.applogic.EmailclientHelper;
*/
import ru.st.selenium.applogic.ApiHelper;
import ru.st.selenium.model.User;
import ru.st.selenium.model.Emailclient;

import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApiHelper extends DriverBasedHelper {

  private ApplicationManager manager;
  private boolean acceptNextAlert = true;

  public ApiHelper(ApplicationManager manager) {
    super(manager.getWebDriver());
    this.manager = manager;
  }
  
public static String postRequest(String request_uri){ 
    DefaultHttpClient httpclient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(request_uri);		
        try {
		System.out.println("executing request:" );
		System.out.println(httpPost.getURI());
        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody="";
        try { responseBody = httpclient.execute(httpPost, responseHandler);} catch (Exception e) {}
		return responseBody;
	    } 
		finally { httpPost.releaseConnection();}	
}	

public static String postRequestWithToken(String request_uri, String token) { 
    DefaultHttpClient httpclient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(request_uri);		
	httpPost.setHeader("session_token",token);	
    try {
		System.out.println(httpPost.getURI());
        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody="";
        try { responseBody = httpclient.execute(httpPost, responseHandler);} catch (Exception e) {}
		return responseBody;
	    } 
	finally { httpPost.releaseConnection();}	
}

  public static boolean	checkResponse(String str, String subStr) {
		boolean b;
		int otv =str.indexOf(subStr);
			if (otv>=0) {b=true;} 
			else {
			System.out.println("Response doesn't contain "+ subStr); 
			b=false;
			assertEquals(b,true);} 
		return b;
  }

	public static int indexOfToken(String str, String subStr)  {
		int otv =str.indexOf(subStr);
		return otv;
  }	  
/*
  public void loginAs(User user) {
	manager.getNavigationHelper().gotoLoginPage();
    driver.findElement(By.id("username"))
      .sendKeys(user.getUsername());
    driver.findElement(By.id("password"))
      .sendKeys(user.getPassword());
    driver.findElement(By.name("_action_save"))
      .click();
	wait.until(presenceOfElementLocated(By.xpath("//li[@class='profile_menu_top']")));
  }
 */ 
  
}
