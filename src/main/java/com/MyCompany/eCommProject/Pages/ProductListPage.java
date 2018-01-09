package com.MyCompany.eCommProject.Pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {

	WebDriver driver;
	public static final Logger log = Logger.getLogger(ProductListPage.class);
	
	@FindBy(how=How.CSS, using="#product-price-1 > span.price")
	@CacheLookup
	WebElement XperiaPrice;
	
	@FindBy(how=How.CSS, using="#product-collection-image-1")
	@CacheLookup
	WebElement XperiaImageLink;
	
	@FindBy(how=How.CSS, using=".price")
	@CacheLookup
	WebElement detailXperiaPrice;
	
	
	public ProductListPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this );
	}
	
	public String getPrice(){
		log.info("Xperia price WebElement...." + XperiaPrice.toString());
		return XperiaPrice.getText();
	}
	
	public void clickXperia(){
		XperiaImageLink.click();
	}
	
	public String detailPrice(){
		log.info("detailXperia price WebElement...." + detailXperiaPrice.toString());
		return detailXperiaPrice.getText();
	}
}
