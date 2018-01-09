package com.MyCompany.eCommProject.Pages;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
		
	WebDriver driver;
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
		
	@FindBy(how=How.XPATH, using="//h2")
	@CacheLookup
	WebElement pgHeader;
	
	@FindBy(how=How.CSS, using=".level0")
	@CacheLookup
	WebElement MobileLink;
	
	@FindBy(how=How.CSS, using="select")
	@CacheLookup
	WebElement dropdownWE;
	
	@FindBy(how=How.XPATH, using="//h2[@class='product-name']")
	@CacheLookup
	List<WebElement> prdNames;
	
	/*public HomePage(WebDriver ldriver){
		this.driver = ldriver;		
	}*/
	
	public HomePage(WebDriver ldriver){
		this.driver = ldriver;
		PageFactory.initElements(ldriver, this);
	}
			
	public String pgHeaderTxt(){
		String str = pgHeader.getText();
		return str;
	}
	
	public void mobileClick(){
		log.info("Clicking Mobile link..." + MobileLink.toString());
		MobileLink.click();
	}
	
	public void selectDropdown(){
		Select dropdown = new Select(dropdownWE);
		dropdown.selectByVisibleText("Name");
	}
	
	public List<WebElement> productList(){
		return prdNames;
	}
	
	public LinkedList<String> getProducts(){
		log.info("in getProducts().....");
		selectDropdown();
		List<WebElement> li = new LinkedList<WebElement>(productList());
		log.info("linked list declared...");
		LinkedList<String> productNames = new LinkedList<String>();
		for(int i=0;i<li.size();i++){
			productNames.add(li.get(i).getText().toLowerCase().trim());
			System.out.println("Product Name :  " + li.get(i).getText().toLowerCase().trim());
		}
		return productNames;
	}
	
}
