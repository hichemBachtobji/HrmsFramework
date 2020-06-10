package com.hrms.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrms.testbase.BaseClass;
import com.hrms.utils.CommonMethods;

public class DashboardPageElements extends CommonMethods {

	@FindBy(id = "welcome")
	public WebElement welcome;
	
	@FindBy(id="menu_pim_viewPimModule")
	public WebElement PIM;
	
	@FindBy(id ="menu_pim_addEmployee")
	public WebElement addEmp;
	
	public DashboardPageElements() {
		//PageFactory.initElements(BaseClass.driver, this);
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	public void navigateToAddEmployee() {
		jsClick(PIM);
		jsClick(addEmp);
	}
}