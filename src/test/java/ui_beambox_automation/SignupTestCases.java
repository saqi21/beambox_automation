package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SignupTestCases {
	private WebDriver driver;

	public SignupTestCases(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void signupPositiveTesting() {
		String email = "saqibzafar31@gmail.com";
		String fullName = "Saqib Zafat Automation Account 31";
		String businessName = "Devsloop Automate 31";
		String password = "Ab!0123456789";
		String cardNumber = "4000007020000003";
		String expiryDate = "06/25";
		String cvc = "123";

		driver.get("http://lvh.me:3000/trial/");
		driver.manage().window().maximize();

		fillFieldById("email-field", email);
		fillFieldById("login_fullname", fullName);
		fillFieldById("login_user_logins_attributes_0_user_attributes_businessname", businessName);
		fillFieldById("login_password", password);
		clickElementByXPath("//*[@id=\"new_login\"]/div/div[2]/div/button");

		fillFieldById("first-name", "Saqib");
		fillFieldById("last-name", "Zafar");

		switchToFrameAndFillField("//*[@id=\"signup-card-element\"]/div/iframe",
				"//input[contains(@placeholder, '1234')]", cardNumber);
		switchToFrameAndFillField("//*[@id=\"card-expiry-element\"]/div/iframe",
				"//input[contains(@placeholder, 'MM / YY')]", expiryDate);
		switchToFrameAndFillField("//*[@id=\"card-cvc-element\"]/div/iframe", "//input[contains(@placeholder, '***')]",
				cvc);

		clickElementByXPath("//*[@id=\"signup-card-submit\"]");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	private void fillFieldById(String id, String value) {
		WebElement element = driver.findElement(By.id(id));
		element.clear();
		element.sendKeys(value);
	}

	private void clickElementByXPath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	private void switchToFrameAndFillField(String frameXPath, String inputXpath, String value) {
		driver.switchTo().frame(driver.findElement(By.xpath(frameXPath)));
		fillFieldByXPath(inputXpath, value);
		driver.switchTo().defaultContent();
	}

	private void fillFieldByXPath(String xpath, String value) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(value);
	}

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		SignupTestCases signupTestCases = new SignupTestCases(driver);
		signupTestCases.signupPositiveTesting();
	}
}
