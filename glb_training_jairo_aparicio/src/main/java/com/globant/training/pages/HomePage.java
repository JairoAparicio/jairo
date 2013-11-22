package com.globant.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class HomePage {
	public WebDriver driver;
	
	
	
	@FindBy(id = "s")
	public WebElement searchInput;

	@FindBy(id = "searchsubmit")
	public WebElement submitButton;
	
	@FindBy(id = "access")
	public WebElement menu;
	
	@FindBy (xpath = "//li[1]/a")
	public WebElement homeaccess;

	@FindBy (css = "page_item.page-item-2.current_page_item")
	public WebElement contactusaccess;
	
	public void go(WebDriver driver) {
		driver.get("http://10.28.148.127/wordpress/");
		PageFactory.initElements(driver, this);
	    this.driver = driver;
	}

	public void waitForTitle() {
		WebDriverWait waitForOne = new WebDriverWait(driver, 10);
		waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//h1[@id='site-title']/span/a")));
		
	}
	public void search(String query) {
		searchInput.sendKeys(query);
		searchInput.sendKeys(Keys.ENTER);
		WebDriverWait waitForOne = new WebDriverWait(driver, 10);
		waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//h1[@id='site-title']/span/a")));
		
	}
}	
	   

	

