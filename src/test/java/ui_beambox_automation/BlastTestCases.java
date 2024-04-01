package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlastTestCases {
	private WebDriver driver;
	private int counter = 1;

	public BlastTestCases(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToBlast() {
		try {
			WebElement getGrowElement = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[2]/a")));
			getGrowElement.click();

			WebElement getBlastsWebElement = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.presenceOfElementLocated(
							By.xpath("//*[@id=\"navigation\"]/ul[1]/li[2]/div[2]/div/div[1]/a")));
			getBlastsWebElement.click();

			WebElement sendNewBlastButton = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//*[@id=\"data-source\"]/div[1]/div/div[1]/button")));
			sendNewBlastButton.click();

			WebElement emailTypeBlast = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions.elementToBeClickable(
							By.xpath("//*[@id=\"blast-modal\"]/div/div/div/div[2]/div/div/div[1]/div/div[1]/a")));
			emailTypeBlast.click();

			createEmailBlast();
		} catch (NoSuchSessionException e) {
			System.out.println("Session expired.");
			e.printStackTrace();
		}
	}

	public void createEmailBlast() {
		setUpBlast();
		selectRecipients();
		designBlast();
		// validateBlast();
		// sendBlast();
	}

	public void setUpBlast() {
		WebElement blastName = driver.findElement(By.xpath("//*[@id=\"blast_name\"]"));
		blastName.clear();
		blastName.sendKeys("Automate Blast");

		WebElement blastSubject = driver.findElement(By.xpath("//*[@id=\"blast_subject\"]"));
		blastSubject.clear();
		blastSubject.sendKeys("Test Automate Blast" + counter);
		counter++;

		WebElement blastCompanyName = driver.findElement(By.xpath("//*[@id=\"blast_from_name\"]"));
		blastCompanyName.clear();
		blastCompanyName.sendKeys("DevsLoop");

		WebElement blastEmail = driver.findElement(By.xpath("//*[@id=\"blast_from_email\"]"));
		blastEmail.clear();
		blastEmail.sendKeys("saqib@adly.com");

		WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button"));
		nextButton.click();

	}

	public void selectRecipients() {
		WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button"));
		nextButton.click();
	}

	public void designBlast() {
		try {
			Wait<WebDriver> waitForLoader = new WebDriverWait(driver, Duration.ofSeconds(5));
			waitForLoader.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
					.executeScript("return document.readyState").equals("complete"));

			WebElement nextButton = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
					ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button")));
			nextButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateBlast() {
		try {
			WebElement blastStatus = new WebDriverWait(driver, Duration.ofSeconds(5))
					.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//span[contains(@class, 'statusTag')]")));

			WebElement blastReason;
			String blastReasonText = "";
			try {
				blastReason = new WebDriverWait(driver, Duration.ofSeconds(5))
						.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath("//span[contains(@data-toggle, 'tooltip")));

				blastReasonText = blastReason.getAttribute("data-original-title");

			} catch (Exception error) {
				System.out.println("There is no Reason Exist");
			}

			if (blastStatus.getText().equals("Approved")) {
				System.out.println("In Approved Condition");
				WebElement moveNextStep = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button")));
				moveNextStep.click();
				System.out.println("Out Approved Condition");
				try {
					WebElement designToast = driver
							.findElement(By.linkText("You must design your Blast before you send"));
					Wait<WebDriver> waitForTost = new WebDriverWait(driver, Duration.ofSeconds(5));
					waitForTost.until(d -> designToast.isDisplayed());
					System.out.println(designToast.getText());
					moveNextStep.click();
				} catch (Exception e) {
					System.out.println("Blast Designed Successfully");
				}
			} else if (blastStatus.getText().equals("Pending")) {
				System.out.println("Your Blast is in Pending State Please check the reason and Approve your Blast.");
				System.out.println("Reason : " + blastReasonText);
				driver.quit();
			} else {
				System.out.println("Your Blast is Rejected");
				System.out.println("Reason : " + blastReasonText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendBlast() {
		WebElement sendBlastButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[4]/div[2]/div[1]/div/div[2]/div/ul/li[1]/a/button")));
		sendBlastButton.click();
	}
}
