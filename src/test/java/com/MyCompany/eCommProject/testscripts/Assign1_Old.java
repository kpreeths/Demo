package com.MyCompany.eCommProject.testscripts;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Assign1_Old {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(Assign1_Old.class);

	public static WebDriver driver;
	private String baseURL;
	private StringBuffer verificationErrors;
	public int scc = 0;

	// @Test(priority=1)
	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "E:/geckodriver.exe");
		driver = new FirefoxDriver();
		log.info("*********Firefox launched**********");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		baseURL = "http://live.guru99.com/index.php/";
	}

	// @Test(priority=2)
	@Test
	public void verifyTitle() throws InterruptedException, IOException {
		driver.get(baseURL);
		log.info("***********application url entered*********");

		try {
			Assert.assertEquals(driver.getTitle(), "Home page");
			System.out.println("The home page title matches...passed");
			// absolute xpath
			// String str =
			// driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div/h2")).getText();
			// relative xpath
			String str = driver.findElement(By.xpath("//h2")).getText();
			String str1 = "THIS IS DEMO SITE FOR   ";
			Assert.assertEquals(str, str1);
			// cssSelector for link "MOBILE"
			driver.findElement(By.cssSelector(".level0")).click();
			/*
			 * String str3 = driver.getCurrentUrl();
			 * System.out.println("Current url 2   " + str3 + "  title 2   " +
			 * driver.getTitle());
			 */
			Assert.assertEquals(driver.getTitle(), "Mobile");
			// Thread.sleep(3000);
			System.out.println("The Mobile page title matches...passed");
			Select dropdown = new Select(driver.findElement(By.cssSelector("select")));
			dropdown.selectByVisibleText("Name");
			List<WebElement> li = new LinkedList<WebElement>(
					driver.findElements(By.xpath("//h2[@class='product-name']")));
			LinkedList<String> pn = new LinkedList<String>();

			/*
			 * for (WebElement we : li){ pn.add(we.getAttribute("title"));
			 * System.out.println("Title are   " + we.getAttribute("title")); }
			 */

			for (int i = 0; i < li.size(); i++) {
				System.out.println("Title are   " + li.get(i).getText());
				pn.add(li.get(i).getText().toLowerCase().trim());

			}

			boolean result = checkSort(pn);
			Assert.assertEquals(result, true);
			scc++;
			// Take screenshot
			File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String png = (System.getProperty("user.dir")
					+ "\\src\\test\\java\\com\\MyCompany\\eCommProject\\screenshots\\Sorted" + scc + ".png");
			FileUtils.copyFile(scrShot, new File(png));

			System.out.println("The Mobile page is sorted by name...passed");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Errors in verificationErrors buffer" + verificationErrors);
			log.error("*****************Printing error*********************");
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
