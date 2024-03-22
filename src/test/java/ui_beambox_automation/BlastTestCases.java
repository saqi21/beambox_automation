package ui_beambox_automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BlastTestCases {
	private WebDriver driver;
	private int counter = 1;

	public BlastTestCases(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToBlast() throws InterruptedException {
		Wait<WebDriver> getGrwoElementwait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement getGrowElement = getGrwoElementwait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[2]/a")));
		System.out.println(getGrowElement.getText());
		getGrowElement.click();

		Wait<WebDriver> getBlastsWebElementWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement getBlastsWebElement = getBlastsWebElementWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[2]/div[2]/div/div[1]/a")));
		System.out.println(getBlastsWebElement.getText());
		getBlastsWebElement.click();

		// click on Send a new Blast Button
		Wait<WebDriver> sendNewBlastButtonWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement sendNewBlastButton = sendNewBlastButtonWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id=\"data-source\"]/div[1]/div/div[1]/button")));
		// System.out.println(sendNewBlastButton.getText());
		sendNewBlastButton.click();

		// locate the Modal
		Wait<WebDriver> waitforEmailTypeButton = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement emailTypeBlast = waitforEmailTypeButton.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"blast-modal\"]/div/div/div/div[2]/div/div/div[1]/div/div[1]/a")));
		// System.out.println(emailTypeBlast.getText());
		emailTypeBlast.click();
		createEmailBlast();
		// createSMSBlast();
	}

	public void createEmailBlast() throws InterruptedException {
		setUpBlast();
		selectRecipients();
		designBlast();
		validateBlast();
		sendBlast();
	}

	public void createSMSBlast() throws InterruptedException {
		Thread.sleep(1000);
		WebElement smsBlastButton = driver
				.findElement(By.xpath("//*[@id=\"blast-modal\"]/div/div/div/div[2]/div/div/div[1]/div/div[2]/div"));
		smsBlastButton.click();
		selectRecipients();
	}

	public void setUpBlast() {
		WebElement blastName = driver.findElement(By.xpath("//*[@id=\"blast_name\"]"));
		blastName.clear();
		blastName.sendKeys("Automate Blast");

		WebElement blastSubject = driver.findElement(By.xpath("//*[@id=\"blast_subject\"]"));
		blastSubject.clear();
		blastSubject.sendKeys("Test Automate Blast" + counter);
		counter = counter + 1;

		WebElement blastCompanyName = driver.findElement(By.xpath("//*[@id=\"blast_from_name\"]"));
		blastCompanyName.clear();
		blastCompanyName.sendKeys("DevsLoop");
		WebElement blastEmail = driver.findElement(By.xpath("//*[@id=\"blast_from_email\"]"));
		blastEmail.clear();
		blastEmail.sendKeys("saqib@adly.com");
		driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button")).click();
	}

	public void selectRecipients() {
		WebDriverWait pageLoadCompleteWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		pageLoadCompleteWait.until((ExpectedCondition<Boolean>) wd -> ((org.openqa.selenium.JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
		driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button")).click();
	}

	public void designBlast() throws InterruptedException {
		Thread.sleep(5000);
		try {
			WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button"));
			System.out.println(nextButton.getText());
			Thread.sleep(1000);
			nextButton.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Button not Click Bro");
			driver.quit();
		}
	}

	public void validateBlast() throws InterruptedException {
		Thread.sleep(2000);
		WebElement blastStatus = driver
				.findElement(By.xpath("/html/body/div[4]/section[2]/div/div/div/div/div[1]/div/div[2]/span"));
		System.out.println("Status : " + blastStatus.getText());

		WebElement blastReason;
		String blastReasonText = "";
		try {
			blastReason = driver
					.findElement(By.xpath("/html/body/div[4]/section[2]/div/div/div/div/div[1]/div/div[2]/i/span"));
			blastReasonText = blastReason.getAttribute("data-original-title");
		} catch (Exception error) {
			System.out.println("There is no Reason Exist");
		}

		if (blastStatus.getText().equals("Approved")) {
			System.out.println("In Approved Condition");
			Thread.sleep(2000);
			WebElement moveNextStep = driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button"));
			System.out.println("*******************************");
			System.out.println(moveNextStep.getText());
			System.out.println("*******************************");
			Thread.sleep(1000);
			moveNextStep.click();
			Thread.sleep(2000);
			System.out.println("Out Approved Condition");
			try {
				Thread.sleep(2000);
				WebElement designToast = driver.findElement(By.linkText("You must design your Blast before you send"));
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
	}

	public void sendBlast() {
		WebElement sendBlastButton = driver
				.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div/div[2]/div/ul/li[1]/a/button"));
		sendBlastButton.click();
	}
}
