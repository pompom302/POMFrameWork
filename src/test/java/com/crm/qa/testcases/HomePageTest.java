package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class HomePageTest extends TestBase{         //inheritance concept

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super();//to go to the particalr place ctrl + the command and it becomes link to navigate
		//super key word to fetch from parent class which here in our case is testbase
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	//so that browser don't get exhausted
		
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();Thread.sleep(3000);
		testUtil = new TestUtil();
		loginPage = new LoginPage();// it is non static so to call methods from it, we need to create object
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String actualHomePageTitle = homePage.verifyHomePageTitle();
		System.out.println(actualHomePageTitle);
		Assert.assertEquals(actualHomePageTitle, "CRMPRO","Home page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		testUtil.switchToFrame();//bcz username is in the frame
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
			
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
