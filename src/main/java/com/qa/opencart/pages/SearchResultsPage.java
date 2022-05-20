package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	private By searchHeader = By.xpath(" //div[@id='content']/h1");
	private By products = By.xpath("//div[@class ='caption']//a");

	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
    
	@Step("Verify getResultsPageHeaderValue")
	public String getResultsPageHeaderValue() {
		if (eleutil.doIsDisplayed(searchHeader)) {
			return eleutil.doElementGetText(searchHeader);
		}
		return null;
	}

	@Step("Verify getProductResultsList")
	public List<String> getProductResultsList() {
		List<WebElement> productList = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		List<String> productValueList = new ArrayList<String>();
		for (WebElement e : productList) {
			String productText = e.getText();
			productValueList.add(productText);
		}
		return productValueList;
	}

	@Step("Verify getProductSearchCount")
	public int getProductSearchCount() {
		return eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT).size();
	}

	@Step("Verify user is able to select product")
	public ProductInfoPage selectproduct(String mainProductName) {
		System.out.println("main product name : " + mainProductName);
		List<WebElement> productList = eleutil.waitForElementsToBeVisible(products, Constants.DEFAULT_TIME_OUT);
		for (WebElement e : productList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
