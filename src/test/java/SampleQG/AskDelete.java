package SampleQG;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AskDelete {

	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}

	// Ask A Question to "UserX", (first one)in the feed,search for the question and delete the Question.
	// Again search for the Question to check whether it was deleted successfully or not

	@Test
	public void AskDeleteVerifyTest() throws InterruptedException {

		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		
	//	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys("itsmesaketh@gmail.com");
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[@class='my_all_feed']/div/div/div[2]")).click();

		WebElement askusername = driver.findElement(By.xpath("//div[@class='f-user-name text-left pl-3']/p/a"));

		String nameText = askusername.getText();


		driver.findElement(By.xpath("//div[@id='extramenu']/button")).click();
	    Thread.sleep(3000);
		WebElement inputText = driver.findElement(By.xpath("//textarea[@id='myInput']"));
		WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary float-right mt-2']"));

		String Que = date();

		inputText.sendKeys(Que);
		submit.click();


		Thread.sleep(3000);
		if (driver.findElements(By.xpath("//div[@class='jconfirm-buttons']")).size() != 0)
		{

			driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button:nth-child(2)")).click();
		}

		Thread.sleep(3000);

		WebElement searchBar = driver.findElement(By.xpath("//input[@id='searchBar']"));
		searchBar.sendKeys(Que);

		Thread.sleep(3000);
		WebElement QueResult = driver.findElement(By.xpath("//a[@class='text-dark searchqutionlist']"));
		QueResult.click();

		Thread.sleep(3000);

		WebElement Delete = driver.findElement(By.xpath("//a[@class='cursor-pointer text-muted text-decoration-none question_delete']"));
		Delete.click();

		WebElement yes = driver.findElement(By.xpath("//button[@class='btn btn-primary confirm_question_delete']"));
		yes.click();

	    Thread.sleep(3000);

		WebElement searchBar1 = driver.findElement(By.xpath("//input[@id='searchBar']"));
		searchBar1.click();
		Thread.sleep(2000);
		searchBar1.sendKeys(Que);
		
		Thread.sleep(4000);
				
		String ResultText=driver.findElement(By.xpath("//div[@class='mobileheaderview user_list']/h5")).getText();
		

		AssertJUnit.assertEquals(ResultText, "No user found");
		
	}
	
	

	public String date() {

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

		Date date = new Date();

		String date1 = dateFormat.format(date);

		return date1;

	}
}
