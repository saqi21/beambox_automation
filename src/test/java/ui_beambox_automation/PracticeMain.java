package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeMain {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();

        suggestionDropDown(driver);
        
        
        

	}
	
	public static void suggestionDropDown(WebDriver driver) throws InterruptedException {
		try {
			Thread.sleep(2000);
	        WebElement fromElement = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[2]/div[1]/div[1]/label/span"));
	        fromElement.click();
	        
	        Thread.sleep(2000);
	        WebElement fromInputTag = driver.findElement(By.xpath("//*[@id=\"top-banner\"]/div[2]/div/div/div/div[2]/div[1]/div[1]/div[1]/div/div/div/input"));
	        Thread.sleep(2000);
	        fromInputTag.sendKeys("Islamabad");
	        Thread.sleep(2000);
	        fromInputTag.sendKeys(Keys.ARROW_DOWN);
	        Thread.sleep(2000);
	        fromInputTag.sendKeys(Keys.ENTER);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		driver.quit();
	}

}
