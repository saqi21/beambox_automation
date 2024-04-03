package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class CancellationFlow {
	private WebDriver driver;
	private static final String LOCAL_BASE_URL = "http://lvh.me:3000/";
	private static final String STAGING_BASE_URL = "https://staging.beambox.com/";
	private static final String CANCEL_ACCOUNT_LINK = "//*[text()='Cancel Account']";
	private static final String EXTEND_TRIAL_ACCEPT_OFFER_XPATH = "//button[contains(@type, 'submit') and contains(text(), 'Accept Offer')]";
	private static final String PAUSE_SUBSCRIPTION_BUTTON_XPATH = "//*[@id='pause_subscription']/div/div/button";
	private static final String TWO_MONTH_RADIO_ID = "twoMonth";
	private static final String THREE_MONTH_RADIO_ID = "threeMonth";
	private static final String CONFIRM_PAUSE_SUBSCRIPTION_BUTTON_XPATH = "//button[@type='button' and text()='Yes, pause subscription']";
	private static final String DOWNGRADE_PLAN_TEXT_XPATH = "//h4[contains(text(), \"Downgrade Plan\")]";
	private static final String DOWNGRADE_PLAN_BUTTON_XPATH = "//*[@id='downgrade_subscription']/div[2]/button";
	private static final String CONFIRM_DOWNGRADE_PLAN_BUTTON_XPATH = "//*[@id='downgrade_subscription']/div[1]/div/div[2]/button";
	private static final String ACCEPT_DISCOUNT_BUTTON_XPATH = "//button[@type = 'submit' and contains(text(), \"Accept Offer\")]";
	private static final String UPGRADE_TO_GROWTH_BUTTON_XPATH = "//a[contains(@href, \"_discount&plan=growth\")]";
	private static final String UPGRADE_TO_PRO_BUTTON_XPATH = "//a[contains(@href, \"_discount&plan=pro\")]";
	private static final String PLUG_AND_PLAY_PAGE_TITLE_XPATH = "//h4[contains(@class,\"page-title\")]";
	private static final String CANCEL_ACCOUNT_CONFIRM_XPATH = "//button[@type='button' and text()='Yes, cancel subscription']";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_EXTEND_TRIAL_PAGE_XPATH = "//a[contains(@href, 'settings/cancel_account/pause')]";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_PAUSE_PAGE_LINK_XPATH = "//a[contains(@href, 'settings/cancel_account/downgrade') or contains(@href, 'settings/cancel_account/discount')]";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_DOWNGRADE_PAGE_LINK_XPATH = "//a[contains(@href, 'settings/cancel_account/discount')]";
	private static final String CANCEL_ACCOUNT_XPATH = "//button[contains(text(), \" Cancel Subscription \")]";

	public CancellationFlow(WebDriver driver) {
		this.driver = driver;
	}

	public void cancellationFlowMain(int cancellationFlowOption) throws InterruptedException {
		Random random = new Random();
		int randomNumber = random.nextInt(3) + 1;
		System.out.println(randomNumber);

		String currentURL = driver.getCurrentUrl();
		String[] segments = currentURL.split("/");
		String accountId = segments[segments.length - 2];
		String account_url = LOCAL_BASE_URL + "app/" + accountId;
		driver.get(account_url + "/settings/account");

		String plan = "essential";

		WebElement cance_account_link = waitForClickableElement(CANCEL_ACCOUNT_LINK);
		cance_account_link.click();
		Thread.sleep(1000);

		if (cancellationFlowOption == 0) {
			extendTrial();
		} else if (cancellationFlowOption == 1) {
			cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_EXTEND_TRIAL_PAGE_XPATH);
			pauseSubscription(randomNumber);

		} else if (cancellationFlowOption == 2) {
			cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_EXTEND_TRIAL_PAGE_XPATH);
			cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_PAUSE_PAGE_LINK_XPATH);
			try {
				String downgradePlanH4Text = getTextOfPage(DOWNGRADE_PLAN_TEXT_XPATH);
				if (downgradePlanH4Text.equalsIgnoreCase("Downgrade Plan")) {
					downgradeSubscription(plan);
				}
			} catch (Exception e) {
				System.out.println("Downgrade Plan Page Skiped");
			}

		} else if (cancellationFlowOption == 3 || cancellationFlowOption == 4) {
			cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_EXTEND_TRIAL_PAGE_XPATH);
			cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_PAUSE_PAGE_LINK_XPATH);
			try {
				String downgradePlanH4Text = getTextOfPage(DOWNGRADE_PLAN_TEXT_XPATH);
				if (downgradePlanH4Text.equalsIgnoreCase("Downgrade Plan")) {
					cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_DOWNGRADE_PAGE_LINK_XPATH);
				}
			} catch (Exception e) {
				System.out.println("Downgrade Plan Page Skiped");
			}

			if (cancellationFlowOption == 4) {
				cancelSubscriptionInstead(CANCEL_SUBSCRIPTION_INSTEAD_ON_DOWNGRADE_PAGE_LINK_XPATH);
			}

			discountOption(randomNumber);
		} else if (cancellationFlowOption == 5) {
			String cancelConfirmed = LOCAL_BASE_URL + "/app/" + accountId + "/settings/cancel_account/confirmed";
			System.out.println(cancelConfirmed);
			driver.get(cancelConfirmed);
			cancelAccount();
		}

	}

	private void extendTrial() {
		try {
			WebElement extenTrialOffer = waitForClickableElement(EXTEND_TRIAL_ACCEPT_OFFER_XPATH);
			extenTrialOffer.click();
			System.out.println("Successfully Avail The Extend Days Offer");
		} catch (Exception e) {
			System.out.println("Sorry! You have already availed the offer.");
		}
	}

	private void pauseSubscription(int randomNumber) {
		if (randomNumber == 2) {

			WebElement twoMonthRadio = driver.findElement(By.id(TWO_MONTH_RADIO_ID));
			twoMonthRadio.click();

		} else if (randomNumber == 3) {
			WebElement threeMonthRadio = driver.findElement(By.id(THREE_MONTH_RADIO_ID));
			threeMonthRadio.click();
		}

		WebElement acceptPauseSubscription = waitForClickableElement(PAUSE_SUBSCRIPTION_BUTTON_XPATH);
		acceptPauseSubscription.click();
		WebElement confirmPauseSubscription = waitForClickableElement(CONFIRM_PAUSE_SUBSCRIPTION_BUTTON_XPATH);
		confirmPauseSubscription.click();
		System.out.println("Successfully Pause The Account");

	}

	private void downgradeSubscription(String plan) throws InterruptedException {
		try {
			WebElement clickplan = driver.findElement(By.id(plan + "_plan"));
			clickplan.click();
			waitForClickableElement(DOWNGRADE_PLAN_BUTTON_XPATH).click();
			waitForClickableElement(CONFIRM_DOWNGRADE_PLAN_BUTTON_XPATH).click();
			System.out.println("Successfully Downgraded the " + plan + " Plan.");
		} catch (Exception e) {
			System.out.println("You Already On " + plan + "Plan.");

		}

	}

	private void discountOption(int randomNumber) {
		try {
			System.out.println("Random number is " + randomNumber);
			if (randomNumber == 1) {
				try {
					WebElement upgradeToGrowth = waitForClickableElement(UPGRADE_TO_GROWTH_BUTTON_XPATH);
					upgradeToGrowth.click();
					System.out.println("Upgraded To Growth");
				} catch (Exception e) {
					System.out.println(
							"You Are Already on Growth/Pro Plan so Accepting the Offer clicking on Accept Offer CTA");
					WebElement acceptOffer = waitForClickableElement(ACCEPT_DISCOUNT_BUTTON_XPATH);
					acceptOffer.click();
				}
			} else if (randomNumber == 2) {
				try {
					WebElement upgradeToPro = waitForClickableElement(UPGRADE_TO_PRO_BUTTON_XPATH);
					upgradeToPro.click();
					System.out.println("Upgraded To Pro");
				} catch (Exception e) {
					System.out
							.println("You Are Already on Pro Plan so Accepting the Offer clicking on Accept Offer CTA");
					WebElement acceptOffer = waitForClickableElement(ACCEPT_DISCOUNT_BUTTON_XPATH);
					acceptOffer.click();
				}
			} else {
				WebElement acceptOffer = waitForClickableElement(ACCEPT_DISCOUNT_BUTTON_XPATH);
				acceptOffer.click();
			}

			System.out.println("You Successfully Availed The Discount");
		} catch (Exception e) {
			System.out.println("You Already Have the Disccount Offer");
		}

	}

	private void cancelAccount() {
		WebElement cancelAccount = waitForClickableElement(CANCEL_ACCOUNT_XPATH);
		cancelAccount.click();
		WebElement confirmPauseSubscription = waitForClickableElement(CANCEL_ACCOUNT_CONFIRM_XPATH);
		confirmPauseSubscription.click();
		System.out.println("Account Closed");
	}

	private void cancelSubscriptionInstead(String xpath) throws InterruptedException {
		try {
			Thread.sleep(1000);
			WebElement canceSubscriptionInsted = waitForClickableElement(xpath);
			System.out.println(xpath);
			canceSubscriptionInsted.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(xpath + "Not Found");
		}
	}

	private WebElement waitForClickableElement(String xpath) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		return element;
	}

	private String getTextOfPage(String xpath) {
		WebElement downgradePlanH4 = driver.findElement(By.xpath(xpath));
		String downgradePlanH4Text = downgradePlanH4.getText();
		System.out.println(downgradePlanH4Text);
		return downgradePlanH4Text;

	}

}
