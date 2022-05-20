package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver; // WebDriver driver; declared since page chaining used in this page
	private ElementUtil eleutil;
	private By search = By.name("search");
	private By searchbtn = By.xpath("//div[@id = 'search']//button");
	private By header = By.cssSelector("div#logo a");
	private By accSecList = By.xpath("//div[@id='content']/h2");

	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}

	public String getAccountsPageTile() {
		return eleutil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}

	public boolean isAccountsPageHeaderExist() {
		return eleutil.doIsDisplayed(header);
	}

	public boolean isSearchExist() {
		return eleutil.doIsDisplayed(search);
	}

	public SearchResultsPage doSearch(String productName) {
		if (isSearchExist()) {
			eleutil.doSendKeys(search, productName);
			eleutil.doClick(searchbtn);
			return new SearchResultsPage(driver);
		}
		return null;
	}

	public List<String> getAccountsPageSectionsList() {
		List<WebElement> secList = eleutil.getElements(accSecList);
		List<String> accSecValList = new ArrayList<String>();
		for (WebElement e : secList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}

}
