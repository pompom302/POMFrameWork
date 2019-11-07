package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class ContactsPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();Thread.sleep(3000);
		testUtil = new TestUtil();
		loginPage = new LoginPage();// it is non static so to call methods from it, we need to create object
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label in missing on the page.");
	}
	
	@Test(priority=2)
	public void selectSingleContactTest() {
		contactsPage.selectSingleContactByName();
	}
	
	@Test(priority=3)
	public void selectMultContactsTest() {
		contactsPage.selectMultContactsByName();
	}
	//data driven approach to fetch data
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=4, dataProvider = "getCRMTestData")
	public void validateCreateNewContactsTest(String title, String firstname, String lastname, String company) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "Shahrukh", "Khan", "Bollywood");
		contactsPage.createNewContact(title, firstname, lastname, company);
		
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
