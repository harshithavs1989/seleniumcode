import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestScenario1 {

	public static void main(String[] args) throws InterruptedException {

		String path = System.getProperty("user.dir");
		System.setProperty("webDriver.chrome.driver", path + "/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");

		WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
		searchField.sendKeys("ftw4002 fossil");
		// searchField.sendKeys(Keys.ENTER);
		driver.findElement(By.id("issDiv0")).click();

		WebElement watch = driver.findElement(
				By.xpath("//a[contains(@title,\"Fossil Explorist Analog-Digital Black Dial Men's Watch - FTW4002\")]"));

		System.out.println(watch);
		// / before " is java wont accept ' and "" at at time
		if (watch.isDisplayed()) {
			System.out.println("element is displayed");
			watch.click();

		}

		// WebElement cartIcon=
		// driver.findElement(By.xpath("//span[@id='submit.add-to-cart']"));
		// cartIcon.click();
		//
		// WebElement addToCart = driver.findElement(By.id("warrantyModalBtnAtc"));
		// addToCart.click();

		// Add to cart

		// driver.findElement(By.xpath("//span[@id='nav-cart-count']")).click();
		// driver.findElement(By.name("proceedToCheckout")).click();

	}

}
