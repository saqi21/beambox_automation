package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ImportDataBase {
	private WebDriver driver;

	public ImportDataBase(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void importGuestDatabase() {
		int maxRetries = 3;
		int retries = 0;
		boolean success = false;

		while (retries < maxRetries) {
			try {
				WebElement guestsTag = driver
						.findElement(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[1]/div[2]/div[1]/div[1]/a"));
				String guestsTagHref = guestsTag.getAttribute("href");
				System.out.println(guestsTagHref);

				driver.get(guestsTagHref);

				WebElement addNewGuest = driver
						.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/button"));
				System.out.println(addNewGuest.getText());
				addNewGuest.click();

				WebElement importGuest = driver
						.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/ul/li[1]/a"));
				importGuest.click();

				String currentURL = driver.getCurrentUrl();
				System.out.println(currentURL);

				File uploadFile = new File("src/main/resources/guestDataBase/1500-Guests-DataBase .csv");
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("import[file]")));
				fileInput.sendKeys(uploadFile.getAbsolutePath());
				driver.findElement(By.xpath("//*[@id=\"datatable_wrapper\"]/div[4]/div/button")).click();

				try {
					WebElement modalDialog = driver.findElement(By.xpath("//*[@id=\"change-guest-popup\"]/div"));
					WebElement modalProceedButton = modalDialog.findElement(By.xpath("//*[@id=\"guest-proceed-btn\"]"));
					modalProceedButton.click();
					System.out.println("Modal dialog found. Proceeding with the Modal action.");
				} catch (org.openqa.selenium.NoSuchElementException e) {
					System.out.println("Modal dialog not found. Skipping modal action.");
				}

				success = true;
				break;
			} catch (NoSuchSessionException e) {
				System.out.println("Session expired. Retrying...");
				retries++;

				// Reinitialize the WebDriver session
				driver.quit();
				driver = new ChromeDriver();
				// Set any desired capabilities or configurations for the new driver instance
			}
		}

		if (!success) {
			System.out.println("Failed after " + maxRetries + " retries.");
		}
	}
}
