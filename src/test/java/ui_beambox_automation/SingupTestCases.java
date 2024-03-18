package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SingupTestCases {
	private WebDriver driver;

	public SingupTestCases(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void singupPostiveTesting() {
		//driver.get("http://staging.beambox.com/trial");
		driver.get("http://lvh.me:3000/trial/");
		driver.manage().window().maximize();
		driver.findElement(By.id("email-field")).sendKeys("saqibzafar20@gmail.com");
		driver.findElement(By.id("login_fullname")).sendKeys("Saqib Zafat Automation Account 20");
		driver.findElement(By.id("login_user_logins_attributes_0_user_attributes_businessname")).sendKeys("Devsloop automate");
		driver.findElement(By.id("login_password")).sendKeys("Ab!0123456789");
		driver.findElement(By.xpath("//*[@id=\"new_login\"]/div/div[2]/div/button")).click();

		driver.findElement(By.id("first-name")).sendKeys("Saqib");
		driver.findElement(By.id("last-name")).sendKeys("Zafar");

		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"signup-card-element\"]/div/iframe")));
		driver.findElement(By.xpath("//*[@id=\"root\"]/form/span[2]/div/div/div[2]/span/input")).sendKeys("4000007020000003");
		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"card-expiry-element\"]/div/iframe")));
		driver.findElement(By.xpath("/html/body/div/form/span[2]/div/span/input")).sendKeys("06/25");
		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"card-cvc-element\"]/div/iframe")));
		driver.findElement(By.xpath("/html/body/div/form/span[2]/div/span/input")).sendKeys("123");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//*[@id=\"signup-card-submit\"]")).click();
		try {
            Thread.sleep(10000); // 10 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		driver.close();
	}

}
