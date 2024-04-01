package ui_beambox_automation;

import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BeamboxMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;
		LoginTestCases login;
		SignupTestCases signup;
		ImportDataBase importDataBase;
		AddGuest addGuest;
		BlastTestCases blast;
		BenefitsPages benefitsPages;
		CancellationFlow cancellationFlow;
		int cancellatioFlowOption = 0;
		Sitemap sitemap;

		Scanner beamBoxMenuInput = new Scanner(System.in);

		System.out.println("**************** BeamBox Menu ****************");
		System.out.println("For Which Module you want to Run Automate Test Cases");
		System.out.println("1. Login, 2. Singup, 3. Import Guests DataBase, 4. Add Guest, 5. Blast ");
		System.out.println("6. Smart Markting Page, 7. Cancellatio Flow, 8. Sitemap ");
		int beamboxMenu = beamBoxMenuInput.nextInt();

		if (beamboxMenu == 7) {
			System.out.println("**************** Cancellation Flow Menu ****************");
			System.out.println("1. Pause Subscription, 2. DowngradePlan, 3. 30% Discount, 4. 50% Discount");
			cancellatioFlowOption = beamBoxMenuInput.nextInt();
		}

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		try {
			if (beamboxMenu == 1) {
				login = new LoginTestCases(driver);
				login.loginPositiveTesting();
			}

			else if (beamboxMenu == 2) {
				signup = new SignupTestCases(driver);
				signup.signupPositiveTesting();
			}

			else if (beamboxMenu == 3) {
				login = new LoginTestCases(driver);
				login.loginPositiveTesting();
				importDataBase = new ImportDataBase(driver);
				importDataBase.importGuestDatabase();
			}

			else if (beamboxMenu == 4) {
				login = new LoginTestCases(driver);
				login.loginPositiveTesting();
				addGuest = new AddGuest(driver);
				addGuest.addGuest();
			}

			else if (beamboxMenu == 5) {
				login = new LoginTestCases(driver);
				login.loginPositiveTesting();
				blast = new BlastTestCases(driver);
				blast.navigateToBlast();
			}

			else if (beamboxMenu == 6) {
				benefitsPages = new BenefitsPages(driver);
				benefitsPages.smartMarkitingCampainPage();
			}

			else if (beamboxMenu == 7) {
				login = new LoginTestCases(driver);
				login.loginPositiveTesting();
				cancellationFlow = new CancellationFlow(driver);
				cancellationFlow.cancellationFlowMain(cancellatioFlowOption);
			}

			else if (beamboxMenu == 8) {
				sitemap = new Sitemap(driver);
				sitemap.sitemap();
			}
		} catch (WebDriverException e) {
			System.out.println("Warning !");
			System.out.println("There is " + e.getMessage() + " please Try Aging");
			driver.quit();
		}

		driver.quit();

	}
}
