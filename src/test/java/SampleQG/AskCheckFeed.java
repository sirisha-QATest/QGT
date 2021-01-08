package SampleQG;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AskCheckFeed {

	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}

	// Ask A Question to "UserX", (first one)in the feed, Logout and Login in through "UserX" 	and Answer the Question... and Verify that the question is present
	// at top of the feed.

	@Test
	public void AskCheckFeedTest() throws InterruptedException {

		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys("itsmesaketh@gmail.com");
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='my_all_feed']/div/div/div[2]")).click();

		WebElement askusername = driver.findElement(By.xpath("//div[@class='f-user-name text-left pl-3']/p/a"));
		String nameText = askusername.getText();

		driver.findElement(By.xpath("//div[@id='extramenu']/button")).click();
		
		Thread.sleep(2000);
		WebElement inputText = driver.findElement(By.xpath("//textarea[@id='myInput']"));
		WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary float-right mt-2']"));
		String Que = date();
		inputText.sendKeys(Que);
		submit.click();


		Thread.sleep(2000);
		if (driver.findElements(By.xpath("//div[@class='jconfirm-buttons']")).size() != 0)
		{
			driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button:nth-child(2)")).click();

		}

		Thread.sleep(3000);
		WebElement profilepic = driver.findElement(By.xpath("(//a[@id='navbarDropdown'])[2]"));
		profilepic.click();
		Thread.sleep(2000);
		WebElement settings = driver.findElement(By.xpath("(//a[@class='dropdown-item border-bottom'])[5]"));
		settings.click();

		WebElement logout = driver.findElement(By.xpath("(//a[@class='nav-link '])[5]"));
		int x = logout.getLocation().getX();
		int y = logout.getLocation().getY();

		scrollToElement(x - 100, y - 100);

		logout.click();

		WebElement yes = driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]"));
		yes.click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();
		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys(nameText);
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();

		Thread.sleep(3000);
		String Qtext = driver.findElement(By.xpath("//b[@class='read_more_feed']")).getText().trim();

		Thread.sleep(2000);
		AssertJUnit.assertEquals(Que,Qtext);
	
	}

	public void scrollToElement(int x, int y) {

		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driver;

		javScriptExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");

	}

	public String date() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		Date date = new Date();

		String date1 = dateFormat.format(date);

		return date1;

	}
}
