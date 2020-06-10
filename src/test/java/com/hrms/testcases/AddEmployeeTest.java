package com.hrms.testcases;

import org.apache.commons.math3.analysis.function.Constant;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelUtility;

public class AddEmployeeTest extends CommonMethods {

	@Test(dataProvider = "userData", groups = { "homework", "addEmp", "regression" })
	public void addEmployee(String firstName, String lastName, String username, String password) {
		// System.out.println(firstName + " " + lastName + " " + username + " " +
		// password);

		// login into HRMS
		login.login(ConfigsReader.getProperty("userName"), ConfigsReader.getProperty("password"));

		// navigate to Add Employee page
		dashboard.navigateToAddEmployee();
		wait(1);

		// add employee information
		sendText(addEmp.empName, firstName);
		sendText(addEmp.lastName, lastName);
		// get EmployeeID
		String expectedEmpId = addEmp.empId.getAttribute("value");

		// click on Create Login Details
		click(addEmp.createLoginBtn);
		wait(1);
		sendText(addEmp.username, username);
		sendText(addEmp.userPwd, password);
		sendText(addEmp.rePwd, password);
		wait(1);
		jsClick(addEmp.saveBtn);
		wait(1);

		// validation
		//waitForVisibility(pdetails.lblPersonalDetails);
		String actualEmpId = pdetails.empId.getAttribute("value");
		Assert.assertEquals(actualEmpId, expectedEmpId, "Employee ID did not match!");

		// take screenshot
		TakesScreenshot(firstName + "_" + lastName);
	}

	@DataProvider(name = "userData")
	public Object[][] getData() {
		Object[][] data = { 
				{ "Rajma", "Capoora", "raj123435345", "AmirKhan_@123" },
				{ "John", "Smith", "john123", "AmirKhan_@123" }, 
				{ "Mary", "Ann", "mary123", "AmirKhan_@123" },
				{ "Rohani", "Sakhi", "rohani123", "AmirKhan_@123" }, 
				{ "Ali", "Tarlaci", "ali123", "AmirKhan_@123" }, 
				};
		return data;
	}

	@DataProvider(name = "userDataFromExcel")
	public Object[][] getData2() {
		return ExcelUtility.excelToArray(Constants.TESTDATA_FIlEPATH, "Employee");
	}

}
