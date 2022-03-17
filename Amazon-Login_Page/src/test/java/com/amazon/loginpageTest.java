package com.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginpageTest {
	private WebDriver driver;
	@BeforeMethod
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		String url = "https://www.amazon.in/ap/signin?openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26signIn%3D1%26useRedirectOnSuccess%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0";
		driver.get(url);
	}
	
	@Test(priority = 1)
	public void positivelogintest() {
		
		WebElement enterno = driver.findElement(By.id("ap_email"));
		enterno.sendKeys("Enter Value");

		WebElement continuebtn = driver.findElement(By.id("continue"));
		continuebtn.click();

		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys("Enter Value");
		sleep(1000);

		WebElement signinbtn = driver.findElement(By.id("signInSubmit"));
		signinbtn.click();

		WebElement hellotxt = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		Assert.assertTrue(hellotxt.isDisplayed(), "Hello Text is not Visible");
		sleep(2000);
		
	}

	@Test(priority=2)
	public void incorretusernameTest() {
		
		WebElement enterno = driver.findElement(By.id("ap_email"));
		enterno.sendKeys("214325346");//Enter any value
		sleep(1000);

		WebElement continuebtn = driver.findElement(By.id("continue"));
		continuebtn.click();

		String expectedmsg = "Incorrect phone number";
		WebElement errormsg = driver.findElement(By.cssSelector(".a-alert-heading"));
		String actualmsg = errormsg.getText();
		System.out.println(actualmsg);
		Assert.assertEquals(actualmsg, expectedmsg, "No such message found!");
		sleep(2000);
	}

	@Test(priority=3)
	public void incorrectpasswordTest() {

		WebElement enterno = driver.findElement(By.id("ap_email"));
		enterno.sendKeys("Enter value");
		sleep(1000);

		WebElement continuebtn = driver.findElement(By.id("continue"));
		continuebtn.click();

		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys("anything");
		sleep(1000);

		WebElement signinbtn = driver.findElement(By.id("signInSubmit"));
		signinbtn.click();
		sleep(2000);

		String expectedmsg = "Your password is incorrect";
		WebElement errormsg = driver.findElement(By.cssSelector(".a-list-item"));
		String actualmsg = errormsg.getText();
		Assert.assertEquals(actualmsg, expectedmsg, "No such msg found!");

	}

	@Test(priority=4)
	public void newaccountTest() {

		WebElement createbtn = driver.findElement(By.id("createAccountSubmit"));
		createbtn.click();

		String expectedurl="https://www.amazon.in/ap/register?";
		String actualurl=driver.getCurrentUrl();
		Assert.assertTrue(actualurl.contains(expectedurl), "Urls Did not match \nactual"+actualurl+"\n expected"+expectedurl);

		WebElement createaccheading = driver.findElement(By.cssSelector(".a-spacing-small"));
		Assert.assertTrue(createaccheading.isDisplayed(), "Create Account heading Not Visible");
		sleep(2000);
	}

	@Test(priority=5)
	public void forgotpasswordTest() {
		
		WebElement enterno = driver.findElement(By.id("ap_email"));
		enterno.sendKeys("Enter value");

		WebElement continuebtn = driver.findElement(By.id("continue"));
		continuebtn.click();
		sleep(1000);

		WebElement forgotbtn = driver.findElement(By.id("auth-fpp-link-bottom"));
		forgotbtn.click();
		sleep(2000);

		String expectedurl="https://www.amazon.in/ap/forgotpassword?";
		String actualurl=driver.getCurrentUrl();
		Assert.assertTrue(actualurl.contains(expectedurl), "Urls Did not match \nactual"+actualurl+"\n expected"+expectedurl);

		WebElement assistanceheading = driver.findElement(By.cssSelector("h1"));
		Assert.assertTrue(assistanceheading.isDisplayed(), "Password Assistance heading Not Visible");
		
	}
	@AfterMethod
	public void closedriver() {
		driver.quit();
	}

	private void sleep(int no) {
		try {
			Thread.sleep(no);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
