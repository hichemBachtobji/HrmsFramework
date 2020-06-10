package com.hrms.testcases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

public class LoginTest extends CommonMethods{
	
	
	@Test(groups = "smoke")
	public void validAminLogin() {
		//LoginPageElements login= new LoginPageElements();
		sendText(login.username, ConfigsReader.getProperty("userName"));
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.loginBtn);
		
		//DashBoardPageElements dashboard= new DashBoardPageElements();
		test.info("Verifying valid username shows with welcome text");
		String expectedUser="Welcome Admin";
		String actualUser= dashboard.welcome.getText();
		Assert.assertEquals(actualUser, expectedUser, "Admin is not logged in");
        Assert.assertTrue(actualUser.contains(ConfigsReader.getProperty("userName")));		
	}
	
	@Test(groups="regression")
	public void invalidPasswordLogin() {
		test.info("Adding valid username and invalid password");
		//LoginPageElements login= new LoginPageElements();
		sendText(login.username, ConfigsReader.getProperty("userName"));
		sendText(login.password, "iugytr");
		click(login.loginBtn);
		test.info("Validating Invalid credentials error message is displayed");
		String expected="Invalid credentials";
		Assert.assertEquals(login.errorMsg.getText(), expected , "Error message text is not matched");
	}
	
	@Test(groups="regression")
	public void emptyUsernameLogin() {
		//LoginPageElements login= new LoginPageElements();
		
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.loginBtn);
		
		String expected="Username cannot be empty";
		Assert.assertEquals(login.errorMsg.getText(), expected,"Error message text is not matched");
	}
	
	@Test
	public void timeStamp() {
		Date d = new Date();
		System.out.println(d.getTime());
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		System.out.println(sdf.format(d.getTime()));
	}
}
