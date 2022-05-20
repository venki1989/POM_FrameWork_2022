package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private Map<String, String> productInfoMap;

	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By ProductImages = By.xpath("//div[@id='content']//img");
	private By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By productPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.xpath("//button[@id='button-cart']");
	private By successMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private By cartBtn = By.xpath("//div[@id='cart']/button");
	private By viewCart = By.xpath("//div//ul//p/a");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return eleutil.doElementGetText(productHeader).trim();
	}

	public int getProductImagesCount() {
		return eleutil.waitForElementsToBeVisible(ProductImages, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("productName", getProductHeaderText());
		productInfoMap.put("productImagesCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleutil.getElements(productMetaData);

		for (WebElement e : metaDataList) {
			String text = e.getText().trim();
			String[] metaArray = text.split(":");
			String metaKey = metaArray[0].trim();
			String metaValue = metaArray[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}

	private Map<String, String> getProductPriceData() {
		List<WebElement> metaPriceList = eleutil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText().trim();
		String exclTax = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("ex-Tax", exclTax);
		return productInfoMap;
	}

	public String addToCartClick() {
		eleutil.doSendKeys(qty, String.valueOf(Constants.PRODUCT_QUANTITY));
		//eleutil.waitForElementPresent(addToCartBtn, Constants.DEFAULT_TIME_OUT, 3000).click();
		eleutil.doClick(addToCartBtn);
		return eleutil.waitForElementPresent(successMessage, Constants.DEFAULT_TIME_OUT).getText();
	}

	public String doCartBtnClick() {
		eleutil.doClick(cartBtn);
		return eleutil.waitForElementPresent(successMessage, Constants.DEFAULT_TIME_OUT).getText();
	}
	
}
