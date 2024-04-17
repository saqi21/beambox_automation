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
	private String GROW_LINK_XPATH = "//*[@id=\"navigation\"]/ul[1]/li[2]/a";
	private String BLAST_LINK_XPATH = "//*[@id=\"navigation\"]/ul[1]/li[2]/div[2]/div/div[1]/a";
	private String SEND_NEW_BLAST_BUTTON_XPATH = "//*[@id=\"data-source\"]/div[1]/div/div[1]/button";
	private String EMAIL_BLAST_XPATH = "//a[contains(@href, '/blasts?blast%5Bis_sms%5D=false')]";
	private String BLAST_NAME_INPUT_XPATH = "//input[contains(@placeholder,\"Name your Blast\")]";
	private String BLAST_SUBJECT_INPUT_XPATH = "//input[contains(@placeholder,\"Enter a subject line\")]";
	private String BLAST_FROM_INPUT_XPATH = "//input[contains(@name,\"blast[from_name]\")]";
	private String BLAST_FROM_EMAIL_INPUT_XPATH = "//input[contains(@name,\"blast[from_email]\")]";
	private String NEXT_STEP_BUTTON_XPATH = "//button[contains(text(),\"Next Step\")]";

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
			WebElement grow_link = waitForElement(GROW_LINK_XPATH);
			grow_link.click();
			WebElement blast_link = waitForElement(BLAST_LINK_XPATH);
			String blast_link_url = blast_link.getAttribute("href");
			System.out.println(blast_link_url);
			driver.get(blast_link_url);

			createNewBlast();
			blastForm();
			blastRecepients();
			blastDesign();
		} catch (Exception e) {
			System.out.println("Web ELement Not Found Check the Xpath");
		}

	}

	private void createNewBlast() {
		WebElement createNewBlast = waitForElement(SEND_NEW_BLAST_BUTTON_XPATH);
		createNewBlast.click();

		WebElement newEmailBlast = waitForElement(EMAIL_BLAST_XPATH);
		newEmailBlast.click();
	}

	private void blastForm() {
		fillForm(BLAST_NAME_INPUT_XPATH, "Automate Blast 01");
		fillForm(BLAST_SUBJECT_INPUT_XPATH, "Automate Blast Please Ignore The Blast");
		fillForm(BLAST_FROM_INPUT_XPATH, "Devsloop Automation");
		fillForm(BLAST_FROM_EMAIL_INPUT_XPATH, "saqib@adly.com");
		WebElement nextButton = waitForElement(NEXT_STEP_BUTTON_XPATH);
		nextButton.click();

	}

	private void blastRecepients() {
		WebElement nextButton = waitForElement(NEXT_STEP_BUTTON_XPATH);
		nextButton.click();
	}

	private void blastDesign() throws Exception {
		System.out.println("I am In Designing the page");
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMinutes(2));
		WebElement nextButton = wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Validate')]")));
		nextButton.click();
		System.out.println("I am Out");

	}

	private WebElement findWebElementby(String value, String type) {
		WebElement element = null;
		if (type.equalsIgnoreCase("xpath")) {
			System.out.println("Finding By Xpath: " + value);
			element = driver.findElement(By.xpath(value));
		} else if (type.equalsIgnoreCase("id")) {
			System.out.println("Finding By id");
			element = driver.findElement(By.id(value));
		}

		return element;
	}

	private void fillForm(String xpath, String inputText) {
		WebElement blastInput = driver.findElement(By.xpath(xpath));
		blastInput.clear();
		blastInput.sendKeys(inputText);
	}

	private WebElement waitForElement(String xpath) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

}

// public void navigateToBlast() {
// try {
// WebElement getGrowElement = new WebDriverWait(driver, Duration.ofSeconds(5))
// .until(ExpectedConditions
// .presenceOfElementLocated(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[2]/a")));
// getGrowElement.click();

// WebElement getBlastsWebElement = new WebDriverWait(driver,
// Duration.ofSeconds(5))
// .until(ExpectedConditions.presenceOfElementLocated(
// By.xpath("//*[@id=\"navigation\"]/ul[1]/li[2]/div[2]/div/div[1]/a")));
// getBlastsWebElement.click();

// WebElement sendNewBlastButton = new WebDriverWait(driver,
// Duration.ofSeconds(5))
// .until(ExpectedConditions
// .presenceOfElementLocated(By.xpath("//*[@id=\"data-source\"]/div[1]/div/div[1]/button")));
// sendNewBlastButton.click();

// WebElement emailTypeBlast = new WebDriverWait(driver, Duration.ofSeconds(5))
// .until(ExpectedConditions.elementToBeClickable(
// By.xpath("//*[@id=\"blast-modal\"]/div/div/div/div[2]/div/div/div[1]/div/div[1]/a")));
// emailTypeBlast.click();

// createEmailBlast();
// } catch (NoSuchSessionException e) {
// System.out.println("Session expired.");
// e.printStackTrace();
// }
// }

// public void createEmailBlast() {
// setUpBlast();
// selectRecipients();
// designBlast();
// // validateBlast();
// // sendBlast();
// }

// public void setUpBlast() {
// WebElement blastName =
// driver.findElement(By.xpath("//*[@id=\"blast_name\"]"));
// blastName.clear();
// blastName.sendKeys("Automate Blast");

// WebElement blastSubject =
// driver.findElement(By.xpath("//*[@id=\"blast_subject\"]"));
// blastSubject.clear();
// blastSubject.sendKeys("Test Automate Blast" + counter);
// counter++;

// WebElement blastCompanyName =
// driver.findElement(By.xpath("//*[@id=\"blast_from_name\"]"));
// blastCompanyName.clear();
// blastCompanyName.sendKeys("DevsLoop");

// WebElement blastEmail =
// driver.findElement(By.xpath("//*[@id=\"blast_from_email\"]"));
// blastEmail.clear();
// blastEmail.sendKeys("saqib@adly.com");

// WebElement nextButton =
// driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button"));
// nextButton.click();

// }

// public void selectRecipients() {
// WebElement nextButton =
// driver.findElement(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button"));
// nextButton.click();
// }

// public void designBlast() {
// try {
// Wait<WebDriver> waitForLoader = new WebDriverWait(driver,
// Duration.ofSeconds(5));
// waitForLoader.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor)
// wd)
// .executeScript("return document.readyState").equals("complete"));

// WebElement nextButton = new WebDriverWait(driver,
// Duration.ofSeconds(5)).until(
// ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button")));
// nextButton.click();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// public void validateBlast() {
// try {
// WebElement blastStatus = new WebDriverWait(driver, Duration.ofSeconds(5))
// .until(ExpectedConditions
// .presenceOfElementLocated(By.xpath("//span[contains(@class,
// 'statusTag')]")));

// WebElement blastReason;
// String blastReasonText = "";
// try {
// blastReason = new WebDriverWait(driver, Duration.ofSeconds(5))
// .until(ExpectedConditions
// .presenceOfElementLocated(By.xpath("//span[contains(@data-toggle,
// 'tooltip")));

// blastReasonText = blastReason.getAttribute("data-original-title");

// } catch (Exception error) {
// System.out.println("There is no Reason Exist");
// }

// if (blastStatus.getText().equals("Approved")) {
// System.out.println("In Approved Condition");
// WebElement moveNextStep = new WebDriverWait(driver,
// Duration.ofSeconds(10)).until(
// ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"topnav\"]/div/ul[2]/li/button")));
// moveNextStep.click();
// System.out.println("Out Approved Condition");
// try {
// WebElement designToast = driver
// .findElement(By.linkText("You must design your Blast before you send"));
// Wait<WebDriver> waitForTost = new WebDriverWait(driver,
// Duration.ofSeconds(5));
// waitForTost.until(d -> designToast.isDisplayed());
// System.out.println(designToast.getText());
// moveNextStep.click();
// } catch (Exception e) {
// System.out.println("Blast Designed Successfully");
// }
// } else if (blastStatus.getText().equals("Pending")) {
// System.out.println("Your Blast is in Pending State Please check the reason
// and Approve your Blast.");
// System.out.println("Reason : " + blastReasonText);
// driver.quit();
// } else {
// System.out.println("Your Blast is Rejected");
// System.out.println("Reason : " + blastReasonText);
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// public void sendBlast() {
// WebElement sendBlastButton = new WebDriverWait(driver,
// Duration.ofSeconds(10)).until(ExpectedConditions
// .elementToBeClickable(By.xpath("/html/body/div[4]/div[2]/div[1]/div/div[2]/div/ul/li[1]/a/button")));
// sendBlastButton.click();
// }
