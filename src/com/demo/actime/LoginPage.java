package com.demo.actime;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPage {
	 WebDriver driver;

	@BeforeTest
	public void setup() {
		String path = System.getProperty("user.dir");
		System.setProperty("webDriver.chrome.driver", path + "/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.actitime.com");
	}

	@Test
	public void loginToApplication() {
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		WebElement checkbox = driver.findElement(By.name("remember"));
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
		driver.findElement(By.xpath("//div[text()='Login ']")).click();
	}
	
	@Test(dependsOnMethods = { "loginToApplication" })
	public void verifyTitle() {
		String actual = "actiTIME - Enter Time-Track";
		//Assert.assertEquals(driver.getTitle(), actual);
		
		Assert.assertTrue(driver.getTitle().contains("actiTIME - Enter Time-Track"));
	}

	@Test(dependsOnMethods = { "loginToApplication" })
	public void verifyHomePage() {
		Boolean actiTimeLogo = driver.findElement(By.xpath("//img[@src='/img/default/top_nav/default-logo.png?hash=958080878']")).isDisplayed();
		Assert.assertTrue(actiTimeLogo);
		System.out.println("Logo is displayed");
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}

}
