package ui_beambox_automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BeamboxMain {

	public static void main(String[] args) {

        String browserName = "chrome";
        WebDriver driver;
        LoginTestCases login;

        if (browserName.equals("chrome")) { // corrected spelling of "chrome"
            WebDriverManager.chromedriver().setup(); // setting up ChromeDriver
            driver = new ChromeDriver(); // initializing ChromeDriver
            login = new LoginTestCases(driver);
            login.loginPostiveTesting();
        } 
        
//        else if (browserName.equals("firefox")) {
//            WebDriverManager.firefoxdriver().setup(); // setting up FirefoxDriver
//            driver = new FirefoxDriver(); // initializing FirefoxDriver
//            login = new LoginTestCases(driver);
//            login.loginPostiveTesting();
//        } 
        
        else {
            System.out.print("No Browser Or WebDriver Selected. Please Select."); // corrected spelling and added period at the end
        }

        // Code to proceed with WebDriver execution
    }

}
