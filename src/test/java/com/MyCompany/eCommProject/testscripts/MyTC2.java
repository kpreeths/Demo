package com.MyCompany.eCommProject.testscripts;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.MyCompany.eCommProject.Pages.HomePage;
import com.MyCompany.eCommProject.Pages.ProductListPage;
import com.MyCompany.eCommProject.testbase.TestBase;

public class MyTC2 extends TestBase {
	
	public static final Logger log = Logger.getLogger(MyTC1.class.getName());
	
	HomePage hp;
	ProductListPage prd;
	
	@BeforeClass
	public void setUp(){
		init();
	}
	
	@Test(description = "Verify cost of Product")
	public void costVerify(){
		hp = new HomePage(driver);
		hp.mobileClick();
		prd = new ProductListPage(driver);
		log.info("=========== Product List page invoked =========");
		String listPrice = prd.getPrice();
		//System.out.println("list price "+ listPrice);
		prd.clickXperia();
		String detPrice = prd.detailPrice();
		//System.out.println("Detail price "+ detPrice);
		
		try {
			Assert.assertEquals(listPrice, detPrice);
		} catch (Exception e) {
			log.error("*****************Printing error*********************");
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

}
