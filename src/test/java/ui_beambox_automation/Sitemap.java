package ui_beambox_automation;

import org.openqa.selenium.WebDriver;

public class Sitemap {
   WebDriver driver;

public Sitemap(WebDriver driver) {
    this.driver = driver;
}

public WebDriver getDriver() {
    return driver;
}

public void setDriver(WebDriver driver) {
    this.driver = driver;
}

public void sitemap(){
    driver.get("http://lvh.me:3000/");
}

}
