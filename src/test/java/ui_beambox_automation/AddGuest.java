package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AddGuest {
	private WebDriver driver;

	public AddGuest(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void addGuest() {
		int maxRetries = 3;
		int retries = 0;
		boolean success = false;

		while (retries < maxRetries) {
			try {
				WebElement guestsNavTag = driver
						.findElement(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[1]/div[2]/div[1]/div[1]/a"));
				System.out.println(guestsNavTag.getAttribute("href"));

				// render to Guests Page
				driver.get(guestsNavTag.getAttribute("href"));

				WebElement addNewGuestButton = driver
						.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/button"));
				System.out.println(addNewGuestButton.getText());
				addNewGuestButton.click();

				WebElement addGuest = driver
						.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/ul/li[2]/a"));
				System.out.println(addGuest.getText());
				addGuest.click();

				WebElement locationDropDown = driver.findElement(By.xpath("//*[@id=\"location_id\"]"));
				Select locationDropDownOption = new Select(locationDropDown);
				locationDropDownOption.selectByIndex(1);

				WebElement guestFullName = driver.findElement(By.xpath("//*[@id=\"guest_full_name\"]"));
				guestFullName.sendKeys("Saqib Zafar");

				WebElement guestEmail = driver.findElement(By.xpath("//*[@id=\"guest_email\"]"));
				guestEmail.sendKeys("saqib@adly.com");

				WebElement guestPhone = driver.findElement(By.xpath("//*[@id=\"number\"]"));
				guestPhone.sendKeys("5005550006");

				WebElement genderDropDown = driver.findElement(By.xpath("//*[@id=\"guest_gender\"]"));
				Select genderDropDownOption = new Select(genderDropDown);
				genderDropDownOption.selectByIndex(1);

				WebElement addGuestSubmitButton = driver
						.findElement(By.xpath("//*[@id=\"new-human\"]/div/div/div[3]/div/div[2]/div/button"));
				addGuestSubmitButton.click();

				success = true;
				break;
			} catch (NoSuchSessionException e) {
				// Log the exception or handle it as required
				System.out.println("Session expired. Retrying...");
				retries++;

				// Check session and reinitialize if necessary
				if (!(((ChromeDriver) driver).getSessionId() != null)) {
					// Reinitialize the WebDriver session
					driver.quit();
					driver = new ChromeDriver();
					// Set any desired capabilities or configurations for the new driver instance
				}
			}
		}

		if (!success) {
			// Handle the failure appropriately
			System.out.println("Failed after " + maxRetries + " retries.");
		}
	}

}
