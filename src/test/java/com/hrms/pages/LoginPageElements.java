package com.hrms.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;
import com.hrms.utils.CommonMethods;

public class LoginPageElements  extends CommonMethods{
	
	@FindBy(id="txtUsername")
	public WebElement username;
	
	@FindBy(name="txtPassword")
	public WebElement password;
	
	@FindBy(css="input#btnLogin")
	public WebElement loginBtn;
	
	@FindBy(xpath="//div[@id='divLogo']/img")
	public WebElement logo;
	
	public LoginPageElements(){
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	@FindBy(id="spanMessage")
	public WebElement errorMsg;
	
	public void sendUsername(String uid) {
		sendText(username, uid);
	}
	
	public WebElement getUsername() {
		return username;
	}

	public void login(String uid, String pwd) {
		
		sendText(username, uid);
		sendText(password, pwd);
		click(loginBtn);
	}
}
