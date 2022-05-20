package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registrationpage = loginpage.navigateToRegisterPage();
	}

	public String getRandomEmail() {
		Random random = new Random();
		String email = "SelAutomation" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

//	@DataProvider
//	public Object[][] getRegisterData() {
//		return new Object[][] { 
//			{ "Venki", "m", "898756789", "venki123", "yes" },
//			{ "strange", "m", "898756789", "Strangei123", "no" },
//			{ "stark", "m", "898756789", "Starki123", "yes" } 
//			};
//	}
	
	@DataProvider
	public Object[][] getRegisterData()
	{
		Object[][] regData= ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	

	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String telphone, String password,
			String subscribe) {
		Assert.assertTrue(registrationpage.accountRegistration(firstName, lastName, getRandomEmail(), telphone,
				password, subscribe));
	}

}