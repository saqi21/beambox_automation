package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BlastTestCases {
	private WebDriver driver;
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
			blastValidate();
			blastSend();
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
		Thread.sleep(3000);
		WebElement nextButton = waitForElement(NEXT_STEP_BUTTON_XPATH);
		nextButton.click();
		Thread.sleep(3000);

	}

	private void blastValidate() throws Exception {
		Thread.sleep(3000);
		WebElement nextButton = waitForElement(NEXT_STEP_BUTTON_XPATH);
		nextButton.click();
		Thread.sleep(3000);

	}

	private void blastSend() throws Exception {
		WebElement sendButton = waitForElement("//a[@rel = \"nofollow\" ]");
		sendButton.click();
		Thread.sleep(3000);

	}

	// private WebElement findWebElementby(String value, String type) {
	// WebElement element = null;
	// if (type.equalsIgnoreCase("xpath")) {
	// System.out.println("Finding By Xpath: " + value);
	// element = driver.findElement(By.xpath(value));
	// } else if (type.equalsIgnoreCase("id")) {
	// System.out.println("Finding By id");
	// element = driver.findElement(By.id(value));
	// }

	// return element;
	// }

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