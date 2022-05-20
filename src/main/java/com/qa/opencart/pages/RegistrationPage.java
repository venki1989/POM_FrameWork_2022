package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpwd = By.id("input-confirm");
	private By agreeCheckBox = By.name("agree");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value=1]");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value=0]");
	private By ContinuBtn = By.xpath("//input[@value='Continue']");
	private By successHeader = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.xpath("//div/a[contains(text(), 'Register')]");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		eleutil.waitForElementToBeVisible(this.firstname, Constants.DEFAULT_TIME_OUT).sendKeys(firstName);
		eleutil.doSendKeys(this.lastname, lastName);
		eleutil.doSendKeys(this.email, email);
		eleutil.doSendKeys(this.telephone, telephone);
		eleutil.doSendKeys(this.password, password);
		eleutil.doSendKeys(this.confirmpwd, password);
		if (subscribe.equalsIgnoreCase("yes"))
			eleutil.doClick(subscribeYes);
		else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doClick(agreeCheckBox);
		eleutil.doClick(ContinuBtn);
		if (getAccountRegistrationMessg().contains(Constants.REGISTER_SUCCESS_MESSG)) {
			goToRegisterPage();
			return true;
		}
		return false;
	}

	public String getAccountRegistrationMessg() {
		return eleutil.waitForElementToBeVisible(successHeader, Constants.DEFAULT_TIME_OUT).getText();
	}

	private void goToRegisterPage() {
		eleutil.doClick(logoutLink);
		eleutil.doClick(registerLink);
	}
}
