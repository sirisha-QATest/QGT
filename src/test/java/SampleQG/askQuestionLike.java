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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class askQuestionLike {
	WebDriver driver;

	@BeforeTest
	public void first() throws InterruptedException {

		System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
		driver = new FirefoxDriver();

	}
// Ask A Question to specific user(Ex:FriendsForEver) , Logout and Login in through "FriendsForEver" and Answer the Question and like it. verify like was successful and count was changed.
	@Test
	public void askAnswerLikeTest() throws InterruptedException {

		
		String uname = "FriendsForEver";
		
		driver.get("https://qa.questionguru.net");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();

		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys("itsmesaketh@gmail.com");
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
		Thread.sleep(2000);

		

		List<WebElement> names = driver.findElements(By.xpath("//div[@class='f-user-name text-left pl-3']/p/a"));

		int ssize = names.size();

		for (int i = 0; i < ssize; i++)

		{

			String nameText = names.get(i).getText();
			
			if (nameText.equalsIgnoreCase(uname))

			{

				Thread.sleep(3000);

				
				WebElement nm = names.get(i);
				int x = nm.getLocation().getX();
				int y = nm.getLocation().getY();

				scrollToElement(x - 100, y - 100);
				Thread.sleep(2000);
				nm.click();

				break;
			}

		}
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[@class='profilebtneditask']")).click();
		Thread.sleep(2000);
		WebElement inputText = driver.findElement(By.xpath("//textarea[@id='myInput']"));
		WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary float-right mt-2']"));

	
		String Que= date();
		
		inputText.sendKeys(Que);
		submit.click();
		
		Thread.sleep(2000);
		if (driver.findElements(By.xpath("//div[@class='jconfirm-buttons']")).size() != 0)
		{
			driver.findElement(By.cssSelector("div[class='jconfirm-buttons'] button:nth-child(2)")).click();

		}

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//a[@id='navbarDropdown'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='dropdown-item border-bottom'])[5]")).click();

		WebElement logout = driver.findElement(By.xpath("(//a[@class='nav-link '])[5]"));
		int x = logout.getLocation().getX();
		int y = logout.getLocation().getY();

		scrollToElement(x - 100, y - 100);

		logout.click();

		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
		
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();
		driver.findElement(By.xpath("(//input[@placeholder='Email / Username'])[1]")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@class='form-control pl-5 position-relative5 Pwd']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();

		Thread.sleep(3000);
		String Qtext = driver.findElement(By.xpath("//b[@class='read_more_feed']")).getText().trim();

		driver.findElement(By.xpath(("//a[@class=' cursor-pointer answerblue text-decoration-none give_answer']")))
				.click();

		driver.findElement(By.xpath("//textarea[@class='form-control mt-4 question-answer-mention']"))
				.sendKeys("Testing");

		Thread.sleep(2000);
		AssertJUnit.assertEquals(Que,Qtext);

		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[7]")).click();
		
		Thread.sleep(4000);
		
		WebElement like= driver.findElement(By.xpath("//b[@class='likes_count']"));
		

		int likecount1 = Integer.parseInt(like.getText());
		
		driver.findElement(By.xpath("(//span[@class='iconbtttn'])[3]")).click();
	
		int likecount2 = Integer.parseInt(like.getText());
		
		AssertJUnit.assertNotSame(likecount1,likecount2);
	

	}

	public void scrollToElement(int x, int y) {

		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driver;

		javScriptExecutor.executeScript("window.scrollBy(" + x + ", " + y + ");");

	}

	
	public String date()
	{
		
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 
		 Date date = new Date();
		 
		 String date1= dateFormat.format(date);
		 
		 return date1;
		 
		 
	}
}
