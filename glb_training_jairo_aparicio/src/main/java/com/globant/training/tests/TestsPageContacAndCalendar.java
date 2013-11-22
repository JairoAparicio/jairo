package com.globant.training.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.pages.ContactUSPage;

public class TestsPageContacAndCalendar {
	WebDriver driver;

	@BeforeMethod
	public void before() {
		// Setting Proxy
		String myProxy = "proxy.corp.globant.com:3128";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY,
				new Proxy().setHttpProxy(myProxy));
		driver = new FirefoxDriver(capabilities);
		// Navigator parameterized from suite.
	}

	@AfterMethod
	public void after() {
		driver.quit();

	}

	// Exercise 4 Validate Contact us successfully
	@Test(timeOut = 20000)
	public void contactUS() {

		ContactUSPage contact = PageFactory.initElements(driver,
				ContactUSPage.class);
		contact.getintocontactUS(driver);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		contact.fillForm("nameTester", "email1@tester.com", "Contact to Us",
				"send  this  test message");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		Assert.assertTrue(driver.findElement(By.xpath("//article/div/div"))
				.isDisplayed(), "Thank you for contacting us.");

	}

	// Exercise 5 Validate Contact us failed and after successfully

	@Test()
	public void contactUSFailedAndAgainPassed() {

		ContactUSPage contact = PageFactory.initElements(driver,
				ContactUSPage.class);
		contact.getintocontactUS(driver);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		// Fill with empty fields
		contact.fillForm("", "", "", "");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.MILLISECONDS);
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//form[@id='cntctfrm_contact_form']/div[3]"))
						.isDisplayed(), "Your name is required.");
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//form[@id='cntctfrm_contact_form']/div[3]"))
						.isDisplayed(), "A valid email address is required.");
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//form[@id='cntctfrm_contact_form']/div[3]"))
						.isDisplayed(), "Subject is required.");
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//form[@id='cntctfrm_contact_form']/div[3]"))
						.isDisplayed(), "Message text is required.");

		Assert.assertTrue(
				driver.findElement(
						By.xpath("//form[@id='cntctfrm_contact_form']/div[1]"))
						.isDisplayed(),
				"Please make corrections below and try again.");

		WebElement messageRequire = driver.findElement(By
				.xpath("//form[@id='cntctfrm_contact_form']/div[1]"));
		System.out.println(messageRequire);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		// Filling the contact form again
		contact.fillForm("nameTester", "email1@tester.com", "Contact to Us",
				"send  its  test message again");
		driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);

		boolean exists = driver.findElements(By.id("cntctfrm_thanks")).size() != 0;
		System.out.println(exists);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//article/div/div"))
				.isDisplayed(), "Thank you for contacting us.");
	}

	// Exercise 6 Validating post on calendar
	@Test
	public void calendarposts() {

		driver.get("http://10.28.148.127/wordpress/");
		PageFactory.initElements(driver, this);
		WebElement mounth = driver.findElement(By.id("prev"));
		mounth.click();
		driver.manage().timeouts().implicitlyWait(45000, TimeUnit.MILLISECONDS);
		new WebDriverWait(driver, 10000);
		String urlmounth = driver.getCurrentUrl();
		System.out.println(urlmounth);
		driver.findElement(By.id("wp-calendar"));
		WebElement myLink = driver.findElement(By.id("wp-calendar"));
		driver.findElement(By.id("wp-calendar")).getAttribute("href");
		String urlPost = myLink.getAttribute("href");
		System.out.println(urlPost);
		// Assert.assertEquals("true", urlPost.getAttribute("myLink"));
		if (myLink.isDisplayed()) {
			myLink.click();
			driver.manage().timeouts()
					.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}

		// a-Verify how many days of month have posts
		WebElement dateswithpost = driver.findElement(By.id("wp-calendar"));
		int xpathCountdays = driver.findElements(By.id("wp-calendar")).size();
		System.out.println(xpathCountdays);
		// b-Click on the first day that have post
		dateswithpost.click();
		new WebDriverWait(driver, 10);
		// c- how many post there is and print the titles by console
		int xpathCounttitle = driver.findElements(By.className("entry-title"))
				.size();
		assert (driver.getTitle().contains(("")));
		System.out.println(xpathCounttitle);
		WebElement titleposts = driver.findElement(By
				.xpath(".//article/header/h1/a"));
		System.out.println(titleposts);

	}
}
