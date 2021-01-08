package SampleQG;

import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FollowerFollowee {

	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}

	// Login as user1(Ex:Bookworm) and search for user2 (Ex:WinterRabit).
	// Follow User2
	// open User2's Followers list and search user1.
	// Click on "User1".
	// Open User1's Following list and search user2.
	// Unfollow User2 .

	@Test
	public void FollowerFolloweeTest() throws InterruptedException {

		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();

		String username = "Bookworm";

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys(username);
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		Thread.sleep(4000);

		String searchPern = "WinterRabit";

		WebElement searchBar = driver.findElement(By.xpath("//input[@id='searchBar']"));
		searchBar.sendKeys(searchPern);
		Thread.sleep(3000);
		WebElement searchPerson = driver.findElement(By.xpath("(//div[@class='userboxfull searchclaim'])[1]"));

		searchPerson.click();
		Thread.sleep(3000);

		WebElement follow = driver.findElement(By.xpath("//div[@class='namedetail']/a[2]"));
		follow.click();

		driver.findElement(By.xpath("(//span[@class='navname'])[5]")).click();

		Thread.sleep(3000);

		List<WebElement> follList = driver.findElements(By.cssSelector("a.usernametag"));

		int ssize = follList.size();

		for (int i = 0; i < ssize; i++)

		{

			String nameText = follList.get(i).getText();
			if (nameText.equalsIgnoreCase(username))

			{
				WebElement name = follList.get(i);

				int x = name.getLocation().getX();
				int y = name.getLocation().getY();

				scrollToElement(x - 100, y - 100);
				Thread.sleep(2000);
				name.click();

				break;
			}
		}

		Thread.sleep(3000);

		driver.findElement(By.xpath("(//span[@class='navname'])[6]")).click();

		Thread.sleep(3000);

		List<WebElement> follList1 = driver.findElements(By.cssSelector("a.usernametag"));

		int ssize1 = follList1.size();

		for (int j = 0; j < ssize1; j++)

		{

			String nameText1 = follList1.get(j).getText();
			if (nameText1.equalsIgnoreCase(searchPern))

			{
				WebElement name = follList1.get(j);

				int x = name.getLocation().getX();
				int y = name.getLocation().getY();

				scrollToElement(x - 100, y - 100);
				Thread.sleep(2000);
				name.click();

				break;

			}
			// Assert.assertTrue(nameText.equalsIgnoreCase(searchPern));

		}
		Thread.sleep(3000);
		WebElement unfollow = driver.findElement(By.xpath("//div[@class='namedetail']/a[3]"));
		unfollow.click();
	}

	public void scrollToElement(int x, int y) {

		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driver;

		javScriptExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");

	}
}