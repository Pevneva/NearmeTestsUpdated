package ru.st.selenium.pages;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import ru.st.selenium.applogic.ApplicationManager;
import org.openqa.selenium.WebDriver;

public class TestBase {

  protected ApplicationManager app;
  
	@BeforeClass
	public void init() {
		app = new ApplicationManager();
	}
	
	@AfterSuite
	public void stop() {
	  app.stop();
	}

}
