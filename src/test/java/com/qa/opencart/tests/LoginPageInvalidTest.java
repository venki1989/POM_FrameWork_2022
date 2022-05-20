package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epcic 100 - Design Login page for openCart Application")
@Story("US 101 - User logs in with valid credentials")
@Story("US 102 - Design Login page with login features")
public class LoginPageInvalidTest extends BaseTest {

	@DataProvider
	public Object[][] getNegativeData() {
		return new Object[][] { { "testvenki123@v.com", "test123" }, { "venki123456@v.com", "789865556" }, { "", "$%6" }, {" "," "}};

	}

	@Test(dataProvider="getNegativeData")
	@Description("Login test with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	public void loginInvalidTest(String username, String password) {
		Assert.assertTrue(loginpage.doInvalidLogin(username, password), Errors.LOGIN_PAGE_ERROR_MESSAGE_NOT_DISPLAYED);

	}

}
