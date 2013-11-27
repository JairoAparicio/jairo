package com.globant.training.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.pages.HomePage;

public class Tests {
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

	// Exercise 1 Surf the Site and validate the main title of page
	@Test(timeOut = 20000)
	public void ValidateSiteTitle() {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		Assert.assertEquals(
				"Automation Training | Aprender a automatizar en un solo sitio",
				driver.getTitle());

	}

	// Exercise 2 Search and validate results

	@Test(timeOut = 20000)
	public void SearchingResultPosts() {
		// Validating Search No Results
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		homePage.search("not found");
		Assert.assertTrue(
				driver.findElement(
						By.xpath("//article[@id='post-0']/header/h1"))
						.isDisplayed(), "Nothing Found");
		Assert.assertEquals("not found | Search Results | Automation Training",
				driver.getTitle());

		// Validating post results with the word "test"
		homePage.go(driver);
		homePage.waitForTitle();
		homePage.search("test");

		WebDriverWait waitForOne = new WebDriverWait(driver, 10);
		waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//h1[@id='site-title']/span/a")));
		waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//article[@id='post-39']/header/h1/a")));
		waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
				.linkText("What software testing is")));

		driver.findElement(By.className("page-title")).isDisplayed();

		Assert.assertTrue(driver.findElement(By.xpath("//header/h1/span"))
				.isDisplayed(), "SEARCH RESULTS FOR: TEST");

		waitForOne.until(ExpectedConditions.titleContains("test"));

		assert (driver.getTitle()
				.contains(("test | Search Results | Automation Training")));
	}

	// Exercise 3 Validate post and author//
	
	@Test(timeOut = 20000)
	public void GetPostAutor() {
		// homePage ge into the post();
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.go(driver);
		homePage.search("test");
		
		
		// FindBy Posts

		 driver.findElement(By.id("post-39"));
		WebElement myLink = driver.findElement(By.xpath("//a[contains(text(),'What software testing is')]"));
		  driver.findElement(By.xpath("//article[@id='post-39']/header/h1/a")).getAttribute("href");
		  String urlPost = myLink.getAttribute("href");
		  System.out.println(urlPost);

		if (myLink.isDisplayed()) {
			myLink.click();
		}

		// Validate WebElement to the post autor;
		homePage.waitForTitle();
		WebElement autor = driver.findElement(By
				.xpath("//a[2]"));
		driver.findElement(By.xpath("//a[2]")).getAttribute("href");
		 
		//Get Attribute from who posted
		String urlpostAutor = autor.getAttribute("href");
		System.out.println(urlpostAutor);
		//Validating Autor
		Assert.assertEquals("santiago.hernandez", driver.findElement(By.xpath("//a[2]")) .getText());
		if (autor.isDisplayed()) {
			autor.click();
		}
		homePage.waitForTitle();
		String urlAutor=driver.getCurrentUrl();
		System.out.println(urlAutor);
		//Assertion to Url autor
		Assert.assertTrue(urlpostAutor.equals(urlAutor));
		
	}

 
	
}