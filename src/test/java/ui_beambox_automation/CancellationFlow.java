package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;

public class CancellationFlow {
	private WebDriver driver;
	private static final String NAV_LINK_XPATH = "//*[@id='navigation']/ul[1]/li[7]/a";
	private static final String SETTING_LINK_XPATH = "//*[@id='navigation']/ul[1]/li[7]/ul/li[1]/a";
	private static final String EXTEND_TRIAL_ACCEPT_OFFER_XPATH = "//*[@id=\"extend_trial\"]/div/div[3]/div/button";
	private static final String PAUSE_SUBSCRIPTION_BUTTON_XPATH = "//*[@id='pause_subscription']/div/div/button";
	private static final String TWO_MONTH_RADIO_ID = "twoMonth";
	private static final String THREE_MONTH_RADIO_ID = "threeMonth";
	private static final String ACCEPT_DISCOUNT_BUTTON_XPATH = "//*[@id=\"subscription_discount\"]/div[1]/button";
	private static final String UPGRADE_TO_GROWTH_BUTTON_XPATH = "//*[@id=\"data-source\"]/div[2]/div/div/div/div/div[3]/div[2]/a";
	private static final String UPGRADE_TO_PRO_BUTTON_XPATH = "//*[@id=\"data-source\"]/div[2]/div/div/div/div/div[3]/div[3]/a";
	private static final String CONFIRM_PAUSE_SUBSCRIPTION_BUTTON_XPATH = "/html/body/div[6]/div/div[10]/button[1]";
	private static final String CONFIRM_DOWNGRADE_PLAN_BUTTON_XPATH = "//*[@id='downgrade_subscription']/div[1]/div/div[2]/button";
	private static final String DOWNGRADE_PLAN_BUTTON_XPATH = "//*[@id='downgrade_subscription']/div[2]/button";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_PAUSE_PAGE_LINK_XPATH = "//*[@id='pause_subscription']/div/div/a";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_DOWNGRADE_PAGE_LINK_XPATH = "//*[@id=\"downgrade_subscription\"]/div[2]/a";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_DISCOUNT30_PAGE_LINK_XPATH = "//*[@id=\"subscription_discount\"]/div[1]/a";
	private static final String CANCEL_SUBSCRIPTION_INSTEAD_ON_EXTEND_TRIAL_PAGE_XPATH = "//*[@id=\"extend_trial\"]/div/div[3]/div/a";

	public CancellationFlow(WebDriver driver) {
		this.driver = driver;
	}

	public void cancellationFlowMain(int cancellationFlowOption) throws InterruptedException {
		Random random = new Random();
		int randomNumber = random.nextInt(3) + 1;
		System.out.println(randomNumber);

		Thread.sleep(5000);
		driver.findElement(By.xpath(NAV_LINK_XPATH)).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath(SETTING_LINK_XPATH)).click();

		Thread.sleep(2000);
		WebElement cancelAccount = driver.findElement(By.xpath("//a[contains(@href, 'cancel_account=true')]"));
		cancelAccount.click();

		WebElement cancelInsteadOnExtendTrial = driver
				.findElement(By.xpath(CANCEL_SUBSCRIPTION_INSTEAD_ON_EXTEND_TRIAL_PAGE_XPATH));
		WebElement cancelInsteadOnPausePage = driver
				.findElement(By.xpath(CANCEL_SUBSCRIPTION_INSTEAD_ON_PAUSE_PAGE_LINK_XPATH));
		WebElement cancelInsteadOnDowngradePage = driver
				.findElement(By.xpath(CANCEL_SUBSCRIPTION_INSTEAD_ON_DOWNGRADE_PAGE_LINK_XPATH));
		WebElement cancelInsteadOnDiscount30Page = driver
				.findElement(By.xpath(CANCEL_SUBSCRIPTION_INSTEAD_ON_DISCOUNT30_PAGE_LINK_XPATH));

		if (cancellationFlowOption == 0) {
			try {
				Thread.sleep(2000);
				extenTrial();
			} catch (Exception e) {
				System.out.println("Some Thing went wrong please have a look" + e.getMessage());
			}
		}

		else if (cancellationFlowOption == 1) {
			try {
				Thread.sleep(2000);
				cancelInsteadOnExtendTrial.click();
			} catch (Exception e) {
				System.out.println("You Already Avail the Exteded Offer");
			}

			pauseSubscription(randomNumber);
			Thread.sleep(2000);
			driver.close();
		}

		else if (cancellationFlowOption == 2) {
			try {
				Thread.sleep(2000);
				cancelInsteadOnExtendTrial.click();
				Thread.sleep(2000);
				cancelInsteadOnPausePage.click();
			} catch (Exception e) {
				System.out.println("You are on Pause Page ");
			}

			try {
				Thread.sleep(2000);
				downgradePlan("growth");
				driver.close();
			} catch (Exception e) {
				System.out.println("Skiped the Downgrade Option");
				driver.close();
			}

		}

		else if (cancellationFlowOption == 3 || cancellationFlowOption == 4) {
			try {
				Thread.sleep(2000);
				cancelInsteadOnExtendTrial.click();
				cancelInsteadOnPausePage.click();
				cancelInsteadOnDowngradePage.click();

				if (cancellationFlowOption == 4) {
					Thread.sleep(2000);
					cancelInsteadOnDiscount30Page.click();
				}
			} catch (Exception e) {
				System.out.println("You are on 30% Discount Page ");

			}

			try {
				discount30Or50(randomNumber);
			} catch (Exception e) {
				System.out.println("You already availed the discount");
				driver.close();
			}
		}

		else if (cancellationFlowOption == 5) {
			try {
				cancelInsteadOnExtendTrial.click();
				cancelInsteadOnPausePage.click();
				cancelInsteadOnDowngradePage.click();
				cancelInsteadOnDiscount30Page.click();
				cancelInsteadOnDiscount30Page.click();
			} catch (Exception e) {
				System.out.println("You are dirctly rederict to Return hardware");
			}

		}

	}

	private void extenTrial() {
		try {
			WebElement extendTrialAcceptOffer = driver.findElement(By.xpath(EXTEND_TRIAL_ACCEPT_OFFER_XPATH));
			extendTrialAcceptOffer.click();
			System.out.println("You Succeessfully Availed the Offer");
		} catch (Exception e) {
			System.out.println("You Already Availed the Offer");
			driver.close();
		}
	}

	public void pauseSubscription(int randomNumber) throws InterruptedException {
		Thread.sleep(2000);
		WebElement pauseSubscriptionButton = driver.findElement(By.xpath(PAUSE_SUBSCRIPTION_BUTTON_XPATH));

		if (randomNumber == 2) {
			Thread.sleep(2000);
			WebElement twoMonthRadio = driver.findElement(By.id(TWO_MONTH_RADIO_ID));
			twoMonthRadio.click();
		} else if (randomNumber == 3) {
			Thread.sleep(2000);
			WebElement threeMonthRadio = driver.findElement(By.id(THREE_MONTH_RADIO_ID));
			threeMonthRadio.click();
		}

		pauseSubscriptionButton.click();
		Thread.sleep(2000);
		WebElement confirmPauseSubscriptionButton = driver
				.findElement(By.xpath(CONFIRM_PAUSE_SUBSCRIPTION_BUTTON_XPATH));
		confirmPauseSubscriptionButton.click();
	}

	public void downgradePlan(String plan) {
		try {
			WebElement planRadio = driver.findElement(By.id(plan + "_plan"));
			planRadio.click();
			Thread.sleep(2000);
			WebElement downgradePlanButton = driver.findElement(By.xpath(DOWNGRADE_PLAN_BUTTON_XPATH));
			downgradePlanButton.click();
			Thread.sleep(5000);
			WebElement confirmDowngradePlanButton = driver.findElement(By.xpath(CONFIRM_DOWNGRADE_PLAN_BUTTON_XPATH));
			confirmDowngradePlanButton.click();
		} catch (Exception e) {
			System.out.println("You are already on " + plan + " Plan Or Skipped the Downgrade Plan Page");
			driver.close();
		}
	}

	public void discount30Or50(int randomNumber) throws InterruptedException {
		Thread.sleep(2000);
		WebElement acceptDiscount = driver.findElement(By.xpath(ACCEPT_DISCOUNT_BUTTON_XPATH));
		if (randomNumber == 1) {
			acceptDiscount.click();
		} else if (randomNumber == 2) {
			try {
				WebElement upgradeToGrowth = driver.findElement(By.xpath(UPGRADE_TO_GROWTH_BUTTON_XPATH));
				upgradeToGrowth.click();
			} catch (Exception e) {
				System.out.println(
						"You cannot upgrade to Growth as you are already on the Growth Plan. Availing the discount by clicking on 'Accept Offer' Button");
				acceptDiscount.click();
			}
		} else if (randomNumber == 3) {
			try {
				WebElement upgradeToPro = driver.findElement(By.xpath(UPGRADE_TO_PRO_BUTTON_XPATH));
				upgradeToPro.click();
			} catch (Exception e) {
				System.out.println(
						"You cannot upgrade to Pro as you are already on the Pro Plan. Availing the discount by clicking on 'Accept Offer' Button");
			}
		}
	}
}
