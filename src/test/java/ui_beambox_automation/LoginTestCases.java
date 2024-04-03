package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public void loginPositiveTesting() {
		String email = "callum@beambox.com";
		String password = "123456789";

		// String email = "testcf@gmail.com";
		// String password = "Ab!0123456789";
		String STAGING_BASE_URL = "https://staging.beambox.com/";
		String LOCAL_BASE_URL = "http://lvh.me:3000/";

		driver.get(LOCAL_BASE_URL + "login");
		driver.manage().window().maximize();

		fillFieldByXPath("//input[contains(@id, 'focus1')]", email);
		fillFieldByXPath("//input[contains(@id, 'focus2')]", password);
		clickElementByXPath("//*[@id=\"new_login\"]/div/div[2]/div/button");
	}

	private void fillFieldByXPath(String xpath, String value) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(value);
	}

	private void clickElementByXPath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
}
