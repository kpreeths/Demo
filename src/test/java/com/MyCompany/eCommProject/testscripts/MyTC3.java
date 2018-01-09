package com.MyCompany.eCommProject.testscripts;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.MyCompany.eCommProject.Pages.HomePage;
import com.MyCompany.eCommProject.Pages.ProductListPage;
import com.MyCompany.eCommProject.testbase.TestBase;

public class MyTC3 extends TestBase {
	
public static final Logger log = Logger.getLogger(MyTC1.class.getName());
	
	HomePage hp;
	ProductListPage prd;
	
	@BeforeClass
	public void setUp(){
		init();
	}

}
