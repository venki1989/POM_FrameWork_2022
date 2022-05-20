package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver; // Declared when driver is required to pass for new object(page chaining)
	private ElementUtil eleutil;

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwd = By.xpath("//div[@class='form-group']//a[contains(text(), 'Forgotten Password')]");
	private By registerLink = By.xpath("//div/a[contains(text(), 'Register')]");
	private By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	public LoginPage(WebDriver driver) {
		this.driver = driver; // initialze the private driver and pass it within the class
		eleutil = new ElementUtil(driver);
	}

	@Step("This test is to verify Login Page Tile")
	public String getLoginPageTile() {
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);

	}

	@Step("This test is to verify Login Page URL")
	public String getLoginPageUrl() {
		return eleutil.waitForUrlToBe(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_URL);
	}

	@Step("This test is to verify Forgot Password exists or not")
	public boolean isForgotPwdLinkExist() {
		return eleutil.doIsDisplayed(forgotPwd);

	}

	@Step("This test is to verify Login with valid credentials username {0} and password {1}")
	public AccountsPage doLogin(String user, String pwd) {
		eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).sendKeys(user);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("This test is to verify Login with invalid credentials username {0} and password {1}")
	public boolean doInvalidLogin(String user, String pwd) {
		eleutil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIME_OUT).clear();;
		eleutil.doSendKeys(emailId, user);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		String actualErrorMessage = eleutil.doElementGetText(loginErrorMessg);
		if (actualErrorMessage.contains(Errors.LOGIN_PAGE_ERROR_MESSAGE)) {
			return true;
		}
		return false;
	}

	@Step("This test is to verify register link exists or not")
	public boolean isRegisterLinkExist() {

		return eleutil.waitForElementToBeVisible(registerLink, Constants.DEFAULT_TIME_OUT).isDisplayed();

	}

	@Step("This test is to verify user is navigated to registration Page")
	public RegistrationPage navigateToRegisterPage() {
		if (isRegisterLinkExist()) {
			eleutil.doClick(registerLink);
			return new RegistrationPage(driver);
		}
		return null;
	}
}
