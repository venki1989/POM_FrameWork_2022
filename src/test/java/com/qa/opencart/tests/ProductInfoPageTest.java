package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
//	@DataProvider
//	public Object[][] getProductData()
//	{
//		return new Object[][]
//				{
//			{"MacBook", "MacBook Pro"},
//			{"MacBook", "MacBook Air"},
//			{"Apple", "Apple Cinema 30\""}
//				};
//	}
	
	
	@DataProvider
	public Object[][] getProductData()
	{
		Object[][] productData= ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
		return productData;
	}

	@Test(dataProvider = "getProductData")
	public void productInfoHeaderTest(String productName, String mainProductName) {
		searchResultPage = accPage.doSearch(productName);
		productinfopage = searchResultPage.selectproduct(mainProductName);
		Assert.assertEquals(productinfopage.getProductHeaderText(), mainProductName);
	}

	@Test(dataProvider = "getProductData")
	public void productImagesTest(String productName, String mainProductName) {
		searchResultPage = accPage.doSearch(productName);
		productinfopage = searchResultPage.selectproduct(mainProductName);
		Assert.assertEquals(productinfopage.getProductImagesCount(), Constants.MACBOOK_IMAGES_COUNT);
	}

	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("Macbook");
		productinfopage = searchResultPage.selectproduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productinfopage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + " : " + v));
		softAssert.assertEquals(actProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}
	

	@Test
	public void addToCartTest() {
		searchResultPage = accPage.doSearch("Macbook");
		productinfopage = searchResultPage.selectproduct("MacBook Pro");
		String message = productinfopage.addToCartClick();
		System.out.println(message);
		Assert.assertTrue(true, message);
	}

}
