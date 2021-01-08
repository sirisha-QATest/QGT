package SampleQG;

import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterRandomNewUser {
	 WebDriver driver;
		

		@BeforeTest
		public void first() throws InterruptedException {

			System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
			driver = new FirefoxDriver();
			
		}
		
		//Register new user with Random details.		
		 @Test 
			public void RegisterRandomNewUserTest() throws InterruptedException
			{
				driver.get("https://qa.questionguru.net");
				driver.manage().window().maximize();
				
				String username = "user" +date();
				String email= "user1" +date() +"@gmail.com" ;
				
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();
				
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("(//a[contains(text(),'Sign Up')])[2]")).click();
			
			
				Thread.sleep(2000);
					
				driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys("first");
				driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys("last");
			
				driver.findElement(By.xpath("(//input[@name='email'])[4]")).sendKeys(email);
				driver.findElement(By.cssSelector("input[name='user_name']")).sendKeys(username);
				
				
				driver.findElement(By.xpath("(//input[@name='password'])[3]")).sendKeys("Testing");
				
				driver.findElement(By.cssSelector("input[name='password_confirmation']")).sendKeys("Testing");
				
				driver.findElement(By.cssSelector("input[name='submit']")).click();
				
			
				
			}
		
		 public String date() {

				DateFormat dateFormat = new SimpleDateFormat("HHmmss");

				Date date = new Date();

				String date1 = dateFormat.format(date);

				return date1;

			}
}
