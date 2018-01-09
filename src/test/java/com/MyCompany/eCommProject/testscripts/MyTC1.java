package com.MyCompany.eCommProject.testscripts;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.MyCompany.eCommProject.Pages.HomePage;
import com.MyCompany.eCommProject.testbase.TestBase;

public class MyTC1 extends TestBase {

	/* Get actual class name to be printed on */
	public static final Logger log = Logger.getLogger(MyTC1.class.getName());

	HomePage hp;
	// public WebDriver driver; dont need the driver since inheriting from
	// TestBase
	// private String baseURL;
	public int scc = 0;

	@BeforeClass
	// @BeforeTest
	public void setUp() {
		init();
	}

	@Test(description = "Check Mobile page Sorted")
	public void verifyTitle() throws InterruptedException, IOException {
		log.info("========== Starting test ========");
		hp = new HomePage(driver);
		// HomePage hp = PageFactory.initElements(driver, HomePage.class);
		try {
			Assert.assertEquals(driver.getTitle(), "Home page");
			Assert.assertEquals(hp.pgHeaderTxt(), "THIS IS DEMO SITE FOR   ");
			log.info("The Home page title matches...passed");

			hp.mobileClick();

			Assert.assertEquals(driver.getTitle(), "Mobile");
			log.info("The Mobile page title matches...passed");

			LinkedList<String> prodNames = new LinkedList<String>();
			prodNames = hp.getProducts();
			// System.out.println("Product names in verifyTitle are : " +
			// prodNames);
			boolean isSorted = checkSort(prodNames);
			Assert.assertEquals(isSorted, true);
			log.info("The Mobile page is Sorted by Names");

		} catch (Exception e) {
			log.error("*****************Printing error*********************");
			// Take screenshot
			scc++;
			File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String png = (System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\MyCompany\\eCommProject\\screenshots\\Sorted" + scc + ".png");
			FileUtils.copyFile(scrShot, new File(png));
			log.info("ScreenShot taken");
		}
	}

	public static boolean checkSort(LinkedList<String> pn) {
		String prev = "";

		for (String cur : pn) {
			if (cur.compareTo(prev) < 0)
				return false;
			prev = cur;
		}
		return true;
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

}
