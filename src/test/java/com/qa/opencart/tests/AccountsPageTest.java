package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest {
	
    @Description("PreCondition - User should be logged into application with username {0} and password {1}")
	@BeforeClass
	public void accPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority=1 , description ="TestNG descrition - Verify accounts page title")
	@Description("Verify accounts page tile")
	@Severity(SeverityLevel.NORMAL)
	public void accountsPageTitleTest()

	{
		String actAccountPageTile = accPage.getAccountsPageTile();
		Assert.assertEquals(actAccountPageTile, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority=2)
	@Description("Verify accounts page Header")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeadertest() {
		Assert.assertTrue(accPage.isAccountsPageHeaderExist());
	}

	@Test(priority=3)
	@Description("Verify Search feild exists")
	@Severity(SeverityLevel.CRITICAL)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test(priority=4)
	@Description("Verify Account section header values")
	@Severity(SeverityLevel.NORMAL)
	public void accSectionTest() {
		List<String> actSecList= accPage.getAccountsPageSectionsList();
		System.out.println(actSecList.toString());
		Assert.assertEquals(actSecList, Constants.ACCOUNTS_SECTIONS_LIST);
	}
	
	@Test(priority=5)
	@Description("Verify Search header text")
	@Severity(SeverityLevel.NORMAL)
	public void searchHeaderTest()
	{
		searchResultPage = accPage.doSearch("Macbook");
		String actSearchHeader = searchResultPage.getResultsPageHeaderValue();
		Assert.assertTrue(actSearchHeader.contains("Macbook"));
	}
	
	
	@Test(priority=6)
	@Description("Verify Product result count")
	@Severity(SeverityLevel.NORMAL)
	public void searchProductCountTest()
	{
		searchResultPage = accPage.doSearch("Macbook");
		int actProductCount = searchResultPage.getProductSearchCount();
		Assert.assertEquals(actProductCount, Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	
	@Test(priority=7)
	@Description("Verify Product result list after search")
	@Severity(SeverityLevel.NORMAL)
	public void searchProductListTest()
	{
		searchResultPage = accPage.doSearch("Macbook");
		List<String> accProductList = searchResultPage.getProductResultsList();
		System.out.println(accProductList.toString());
		
	}
  //adding a line to check the git push
}
