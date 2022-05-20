package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epcic 100 - Design Login page for openCart Application")
@Story("US 101 - User logs in with valid credentials")
@Story("US 102 - Design Login page with login features")
public class LoginPageTest extends BaseTest {

	@Test(priority=1)
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTile();
		System.out.println("page title : " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority=2)
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageURLTest() {
		String url = loginpage.getLoginPageUrl();
		System.out.println("page title : " + url);
		Assert.assertTrue(url.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test(priority=3)
	@Description("Forgot Password link test")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTestTest() {
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}

	@Test(priority=4)
	@Description("Register link exists test")
	@Severity(SeverityLevel.CRITICAL)
	public void isRegisterLinkExistTest() {
		Assert.assertTrue(loginpage.isRegisterLinkExist());
	}

	@Test(priority=5)
	@Description("Login test with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isAccountsPageHeaderExist());
		
	}

	
	
}
