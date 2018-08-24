package com.demo.actime;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UsersPage {
	WebDriver driver;
	String lastName= "Webtable";
	String firstName = "testing";
	
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
	
	@Test
	public void userListPage()
	{
		driver.findElement(By.xpath("//div[text()='USERS']")).click();
		Boolean userListPage = driver.findElement(By.xpath("//span[text()='User List']")).isDisplayed();
		Assert.assertTrue(userListPage);
	}
	
	@Test(dependsOnMethods= {"userListPage"})
	public void addUser() throws InterruptedException
	{
		driver.findElement(By.xpath("//div[text()='Add User']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		Boolean addUserPage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userDataLightBox_titlePlaceholder"))).isDisplayed();
		Assert.assertTrue(addUserPage);
	}
	
	@Test(dependsOnMethods= {"addUser"})
	public void createUser()
	{
		String password = "test123";
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		driver.findElement(By.name("email")).sendKeys("xzjh9s@gmail.com");
		driver.findElement(By.name("username")).sendKeys("rr");
		driver.findElement(By.name("password")).sendKeys(password);
		WebElement confirmPassword=driver.findElement(By.name("passwordCopy"));
		confirmPassword.sendKeys(password);
		confirmPassword.sendKeys(Keys.TAB);
		
	    //driver.findElement(By.xpath("//table[@id='ext-comp-1106']/tbody//tr[@id='ext-gen181']")).click();
//		driver.findElement(By.xpath("//div[@title='Sydney Office']")).click();
//		WebElement datePicker = driver.findElement(By.xpath("//tr[@id='ext-gen190']//td[@class='x-btn-right']"));
//		datePicker.click();
//		datePicker.sendKeys("08232018");
		driver.findElement(By.xpath("//span[contains(text(),'Create User')]")).click();
			}
	
	@Test(dependsOnMethods = {"createUser"})
	public void verifyUserCreation() {
		List allRows= driver.findElements(By.xpath("//table[contains(@class,'userListTable canEditUsers hidePTOSettings ')]/tbody/tr"));
		System.out.println(allRows);
		for(int i=2;i<=allRows.size();i++) {
			WebElement user = driver.findElement(By.xpath("//table[contains(@class,'userListTable canEditUsers hidePTOSettings ')]/tbody/tr["+i+"]/td[0]"));
			String us= user.getText();
			System.out.println(us);
				if(user.getText().equalsIgnoreCase(firstName)) {
					System.out.println("________User is created____________");
				}
			}
		System.out.println("_____________");
		     
		}
		
		
	
	
	
	@AfterTest
	public void tearDown() {
	
		driver.quit();
	}
	
}
