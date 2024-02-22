package ui_beambox_automation;


import java.util.Scanner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BeamboxMain {

	public static void main(String[] args) {

        WebDriver driver;
        LoginTestCases login;
        SingupTestCases singup;
        ImportDataBase  importDataBase;

        Scanner beamBoxMenuInput = new Scanner(System.in);

        System.out.println("**************** BeamBox Menu ****************");
        System.out.println("For Which Module you want to Run Automate Test Cases");
        System.out.println("1. Login, 2. Singup, 3. Import Guests DataBase ");
        int beamboxMenu = beamBoxMenuInput.nextInt();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        if(beamboxMenu == 1) {
            login = new LoginTestCases(driver);
            login.loginPostiveTesting();
        } 

        else if(beamboxMenu == 2){
            singup = new SingupTestCases(driver);
            singup.singupPostiveTesting();
        }

        else if (beamboxMenu == 3) {
        	login = new LoginTestCases(driver);
        	login.loginPostiveTesting();
        	importDataBase = new ImportDataBase(driver);
        	importDataBase.importGuestDatabase();
        }

        driver.quit();
    }
}
