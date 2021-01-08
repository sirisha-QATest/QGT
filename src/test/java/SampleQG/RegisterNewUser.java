package SampleQG;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterNewUser {
	 WebDriver driver;
		

		@BeforeTest
		public void first() throws InterruptedException {

			System.setProperty("webdriver.gecko.driver", "//Users//Saketh//Desktop//work//geckodriver");
			driver = new FirefoxDriver();
		}
		
		//Register new user with valid details.		
		
		
		 @Test 
			public void RegisterNewUserTest() throws InterruptedException
			{
				driver.get("https://qa.questionguru.net");
				driver.manage().window().maximize();
				
				Thread.sleep(2000);

				driver.findElement(By.xpath("//a[contains(text(),'LOGIN')]")).click();
				
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("(//a[contains(text(),'Sign Up')])[2]")).click();
			
			
				Thread.sleep(2000);
					
				driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys("first");
				driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys("last");
			
				driver.findElement(By.xpath("(//input[@name='email'])[4]")).sendKeys("qtest.qg@gmail.com");
				driver.findElement(By.cssSelector("input[name='user_name']")).sendKeys("ssample");
				
				
				driver.findElement(By.xpath("(//input[@name='password'])[3]")).sendKeys("Testing");
				
				driver.findElement(By.cssSelector("input[name='password_confirmation']")).sendKeys("Testing");
				
				driver.findElement(By.cssSelector("input[name='submit']")).click();
				
			
				
			}
		 
}
