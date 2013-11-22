package com.globant.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUSPage {

	public WebDriver driver;

	@FindBy(xpath = "//li[2]/a")
	public WebElement contacaccess;

	public void getintocontactUS(WebDriver driver) {
		driver.get("http://10.28.148.127/wordpress/");
		PageFactory.initElements(driver, this);
		this.driver = driver;
		contacaccess.click();
	}

	public void waitForTitle() {
		WebDriverWait waitForOne = new WebDriverWait(driver, 10);
		waitForOne.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("//h1[@id='site-title']/span/a")));

		if (contacaccess.isDisplayed()) {
			System.out.println();

			contacaccess.click();
		}

	}

	@FindBy(id = "cntctfrm_contact_name")
	public WebElement name;

	@FindBy(id = "cntctfrm_contact_email")
	public WebElement email;

	@FindBy(id = "cntctfrm_contact_subject")
	public WebElement subject;

	@FindBy(id = "cntctfrm_contact_message")
	public WebElement message;

	@FindBy(id = "cntctfrm_contact_form")
	public WebElement submitButton2;

	public void fillForm(String nombre, String mail, String asunto,
			String mensaje) {
		name.sendKeys(nombre);
		email.sendKeys(mail);
		subject.sendKeys(asunto);
		message.sendKeys(mensaje);
		WebElement click = driver.findElement(By
				.xpath("//input[@value='Submit']"));
		click.click();

	}
}
