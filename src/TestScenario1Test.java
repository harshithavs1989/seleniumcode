import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScenario1Test {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		String path = System.getProperty("user.dir");
		System.setProperty("webDriver.chrome.driver", path + "/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com");
	}

	@Test(enabled=false)

	public void scenario1() {

		WebElement giftCards = driver.findElement(By.xpath("WebDriver"));
		giftCards.click();

		WebElement eGiftCardCheckBox = driver.findElement(By.name("s-ref-checkbox-2740964011"));
		if (!eGiftCardCheckBox.isSelected()) {
			eGiftCardCheckBox.click();
		}
	}

	@Test

	public void scrollTillElementIsVisiable() {
		WebElement giftCards = driver.findElement(By.xpath(" //a[contains(text(),'Gift Cards')]"));
		giftCards.click();
		WebElement eGiftCardCheckBox = driver.findElement(By.name("s-ref-checkbox-2740964011"));
		if (!eGiftCardCheckBox.isSelected()) {
			eGiftCardCheckBox.click();
		}

		WebElement element = driver.findElement(By.xpath("//img[@src='https://images-na.ssl-images-amazon.com/images/I/51jcYao7ZEL._AC_US160_.jpg']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		driver.findElement(By.xpath("//span[@id='a-autoid-0']")).click();
		
		}
	
	
	

	
}
