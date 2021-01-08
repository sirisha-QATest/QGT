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

public class VerifyNotification {

	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}
	
	// Ask A Question to "UserX", (first one)in the feed , Logout and Login in through "UserX"  and  Verify that notification is present for the new question.

	@Test
	public void VerifyNotificationTest () throws InterruptedException {

		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();
		
		String username= "Bookworm";

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys(username);
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		Thread.sleep(4000);

		
		String uname= driver.findElement(By.xpath("//span[@class='namebox']/small")).getText();
		
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

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();
		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys(nameText);
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();

		Thread.sleep(3000);
		
	
		driver.findElement(By.xpath("(//a[@id='navbarDropdown'])[1]")).click();
		
		String usernameNotifi=driver.findElement(By.xpath("(//a[@class='dropdown-item border-bottom notification-one'])[1]/div/div[2]/p/b")).getText();
		
		AssertJUnit.assertEquals(uname,usernameNotifi);

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
