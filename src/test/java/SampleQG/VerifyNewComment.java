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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyNewComment {
	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}

// Search for a Question. Write a comment . verify New comment is successfully placed.
	@Test
	public void VerifyNewCommenTest() throws InterruptedException {

		String uname = "FriendsForEver";

		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys("itsmesaketh@gmail.com");
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		Thread.sleep(2000);

		String searchQuestion = "12:08:25";
	
		String comment = date();

		WebElement searchBar = driver.findElement(By.xpath("//input[@id='searchBar']"));
		searchBar.sendKeys(searchQuestion);
		Thread.sleep(3000);

		String ResultQuestion = driver.findElement(By.xpath("//div[@class='searchlistbox  mt-3']/div/a")).getText();

		driver.findElement(By.xpath("//div[@class='searchlistbox  mt-3']/div/a")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("(//textarea[@id='comment'])[1]")).sendKeys(comment);

		driver.findElement(By.xpath("//button[@class='btn btn-primary pl-4 pr-4  ml-2']")).click();

		Thread.sleep(3000);

		List<WebElement> commentList = driver.findElements(By.xpath("//div[@class='user-comment']/p"));

		int ssize = commentList.size();
		Boolean val = false;
		for (int i = 0; i < ssize; i++)

		{

			String nameText = commentList.get(i).getText();

			if (nameText.equalsIgnoreCase(comment))

			{
				val = true;

				break;
			}
		}

		Assert.assertTrue(val);

	}

	public String date() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		Date date = new Date();

		String date1 = dateFormat.format(date);

		return date1;

	}
}
