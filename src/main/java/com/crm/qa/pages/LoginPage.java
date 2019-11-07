package com.crm.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//PageFactory : Object Repository OR
	
	@FindBy(name = "username")
	WebElement username; 
	
	@FindBy(name = "password")
	WebElement password; 
	
	@FindBy(xpath="//input[@type='submit' and @value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this); //"this" points to current class objects
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		//    	JavascriptExecutor js = (JavascriptExecutor)driver;
		  //  	js.executeScript("arguments[0].click();", loginBtn);
		    	
		return new HomePage();//after clicking on login button lending page is homepage which we are returned on clicking upon logi button
	}

}
