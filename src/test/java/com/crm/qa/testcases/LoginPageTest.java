package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest(){
		super();//to call estBase Class Constructor, if not used gives NPE
		//also, so that properties will be defined
	}
	
	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	//so that browser don't get exhausted
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();// it is non static so to call methods from it, we need to create object	
	}
	@Test(priority=1)//always write test at the end i.e.after before and after method
	public void LoginPageTitleTest() {
		String actualLoginPageTitle = loginPage.validateLoginPageTitle();
		System.out.println(actualLoginPageTitle);
		Assert.assertEquals(actualLoginPageTitle, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	@Test(priority=2)
	public void crmLogoImageTest() {
		boolean ActualLogo = loginPage.validateCRMImage();
		Assert.assertTrue(ActualLogo);
	}
	@Test(priority=3)
	public void LoginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
