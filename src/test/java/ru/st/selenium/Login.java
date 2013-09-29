package ru.st.selenium;

import org.testng.annotations.*;
import static org.junit.Assert.*;

import ru.st.selenium.model.User;

public class Login extends ru.st.selenium.pages.TestBase {

  @BeforeMethod
  public void mayBeLogout() {
    if (app.getUserHelper().isLoggedIn()) {
      app.getUserHelper().logout();
    }
  }
  
  @Test
  public void testLoginOK() throws Exception {
    app.getUserHelper().loginAsAdmin();
    assertTrue(app.getUserHelper().isLoggedInAs(app.getUserHelper().getAdminUser()));
  }

  @Test
  public void testLoginFailed() throws Exception {
    User user = new User().setUsername(app.getUserHelper().getAdminUser().getUsername()).setPassword("wrong");
    app.getUserHelper().loginAs(user);
    assertTrue(app.getUserHelper().isNotLoggedIn());
  }

}
