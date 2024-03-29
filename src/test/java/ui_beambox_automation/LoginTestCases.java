package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTestCases {
	private WebDriver driver;

	public LoginTestCases(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void loginPostiveTesting() {
		driver.get("http://lvh.me:3000/login");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"focus1\"]")).sendKeys("callum@beambox.com");
		driver.findElement(By.xpath("//*[@id=\"focus2\"]")).sendKeys("123456789");
		driver.findElement(By.xpath("//*[@id=\"new_login\"]/div/div[2]/div/button")).click();
	}
}
