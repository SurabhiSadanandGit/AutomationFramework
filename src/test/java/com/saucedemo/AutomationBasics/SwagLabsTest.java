package com.saucedemo.AutomationBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwagLabsTest {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/");

		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void loginTest() {

		driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
				"The expected page is not loaded");

	}

	@Test(priority=2)
    public void checkoutTest() {
		
		loginTest();
    	driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
    	driver.findElement(By.cssSelector("div.shopping_cart_container a")).click();
    	
    	driver.findElement(By.xpath("//button[@data-test='checkout']")).click();
    	
    	driver.findElement(By.id("first-name")).sendKeys("NewUserFirstName");
    	driver.findElement(By.id("last-name")).sendKeys("NewUserLastName");
    	driver.findElement(By.id("postal-code")).sendKeys("L6X1X7");
    	driver.findElement(By.id("continue")).click();
  
    	driver.findElement(By.id("finish")).click();
    	
    	String orderPlacedMessage= driver.findElement(By.cssSelector("div.checkout_complete_container h2")).getText();
    	Assert.assertEquals(orderPlacedMessage,"THANK YOU FOR YOUR ORDER","Order was not placed correctly");
    }
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
