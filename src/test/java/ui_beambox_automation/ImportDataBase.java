package ui_beambox_automation;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImportDataBase {
    private WebDriver driver;

    public ImportDataBase(WebDriver driver) {
        super();
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
                WebElement guestsTag = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul[1]/li[1]/div[2]/div[1]/div[1]/a"));
                System.out.println(guestsTag.getAttribute("href"));

                // render to Guests Page
                driver.get(guestsTag.getAttribute("href"));
                WebElement importGuestsLink = driver.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/ul/li[1]/a"));
                System.out.print(importGuestsLink.getText());

                WebElement addNewGuest = driver.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/button"));
                System.out.println(addNewGuest.getText());
                addNewGuest.click();

                WebElement importGuest = driver.findElement(By.xpath("//*[@id=\"data-source\"]/div[1]/div[1]/div/div/div/ul/li[1]/a"));
                importGuest.click();

                String currentURL = driver.getCurrentUrl();
                System.out.println(currentURL);

                File uploadFile = new File("src/main/resources/guestDataBase/1500-Guests-DataBase .csv");
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("import[file]")));
                fileInput.sendKeys(uploadFile.getAbsolutePath());
                driver.findElement(By.xpath("//*[@id=\"datatable_wrapper\"]/div[4]/div/button")).click();

                try {
                    WebElement modalDialog = driver.findElement(By.xpath("//*[@id=\"change-guest-popup\"]/div"));
                    WebElement modalProceedButton = modalDialog.findElement(By.xpath("//*[@id=\"guest-proceed-btn\"]"));
                    modalProceedButton.click();
                    System.out.println("Modal dialog found. Procceding the Modal action.");
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    System.out.println("Modal dialog not found. Skipping modal action.");
                }

                success = true;
                break;
            } catch (NoSuchSessionException e) {
                // Log the exception or handle it as required
                System.out.println("Session expired. Retrying...");
                retries++;

                // Check session and reinitialize if necessary
                if (!(((ChromeDriver) driver).getSessionId() != null)) {
                    // Reinitialize the WebDriver session
                    driver.quit();
                    driver = new ChromeDriver();
                    // Set any desired capabilities or configurations for the new driver instance
                }
            }
        }

        if (!success) {
            // Handle the failure appropriately
            System.out.println("Failed after " + maxRetries + " retries.");
        }

    }
}
