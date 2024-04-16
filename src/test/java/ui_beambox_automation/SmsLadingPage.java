package ui_beambox_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SmsLadingPage {
    private final WebDriver driver;
    private final String STAGING_BASE_URL = "https://staging.beambox.com";
    private int counter = 1;

    private final String[][] elementsToCompare = {
            { "//h1[contains(text(),'SMS Marketing Built for Your Business')]",
                    "SMS Marketing Built for Your Business" },
            { "//div[contains(text(),'There is no better way to communicate')]",
                    "There is no better way to communicate with your customers than with an all-in-one SMS marketing platform." },
            { "/html/body/div[5]/div/div[1]/h2", "Gain an Advantage With Text Message Marketing" },
            { "/html/body/div[5]/div/div[1]/p",
                    "Use a text message marketing platform to trigger automated SMS messages based on how guests interact with your venue. You can ask questions, provide resources or offer coupons and discounts through SMS (short message service). Analytics shows you can expect 5x higher open rates with SMS marketing messages than with e-commerce email marketing. You can also expect even higher levels of engagement and conversion rates." },
            { "/html/body/div[6]/div/div[1]/p[1]",
                    "Get more repeat visits with personalized SMS text marketing campaigns designed to connect you to your customers directly. A strong SMS marketing strategy makes it possible to have direct contact with each of your customers, both new & returning. A strong strategy helps to improve the quality of their experience with your business." },
            { "//p[contains(text(),'Harness the power of SMS services and send multiple messages and SMS marketing offers.')]",
                    "Harness the power of SMS services and send multiple messages and SMS marketing offers. With Beambox, you can accordingly segment every guest with a phone number. You'll have access to multiple use cases and advanced segmentation options based on your users and their data. Once you have that content, it’s yours and yours alone. No third parties own your information. Plus, initial sign-up is free, with no set-up costs for subscribers." },
            { "/html/body/div[7]/div/div[1]/h2", "Powerful Small Business SMS Marketing Services" },
            { "/html/body/div[7]/div/div[1]/p",
                    "Beambox designed its customizable text message marketing software for countless small businesses. This includes hotels, restaurants, bars, retail stores, etc. Beambox's business SMS marketing service works seamlessly with your established mobile service provider. It's one of the ways you can provide quality service, promotional messages, and SMS advertising to your customers." },
            { "/html/body/div[8]/div/div/div/div[1]/h2", "Why Is Bulk SMS Marketing Important for Small Businesses?" },
            { "/html/body/div[8]/div/div/div/div[2]/p[1]",
                    "Bulk SMS marketing messages are an easy and powerful way to get your business in front of your target audience. Especially with many consumers attached to their mobile phones and devices. New customers are less likely to be familiar with your brand, so it’s essential to take action to build trust." },
            { "/html/body/div[8]/div/div/div/div[2]/p[2]",
                    "Beambox provides SMS marketing templates for both of the main types of SMS marketing for a small business. Is your goal to set up traditional SMS marketing campaigns (mass communications to provide updates or promotions)? Or maybe you prefer transactional SMS campaigns (personalized responses to specific customer behavior). Regardless of your goal, we have you covered!" },
            { "/html/body/div[8]/div/div/div/div[2]/p[3]",
                    "Plus, get more out of your business marketing strategy and retain customers by combining SMS and email marketing strategies. Combining strategies keeps customers up-to-date with channels that best accommodate them, counteract customer doubt, and provide higher levels of reassurance. Combining personalized email marketing campaigns with regular SMS communications and updates makes you twice as likely to make your potential customers feel welcomed and secure when interacting with your business." },
            { "/html/body/div[9]/div/div[1]/h2", "E-commerce SMS Marketing: Your Location, Your Segmentation" },
            { "/html/body/div[9]/div/div[1]/p",
                    "E-commerce SMS marketing is essential to your small business. Utilize your user data and analytics to design targeted messaging specific to your opt-in customers. We provide a more customizable and data-driven SMS marketing experience than other providers. Need examples of what you can do with our SMS marketing tools? You can send:" },
            { "/html/body/div[10]/div/div[1]/p[1]",
                    "Use Beambox Blasts to design and send beautifully branded and successful text messaging marketing campaigns to your guest database. Our SMS Marketing software allows you to schedule and fine-tune your marketing texts to a segment of consumers. You can choose one of your business phone number lists or use mass automatic marketing to reach them all." },
            { "/html/body/div[10]/div/div[1]/p[2]",
                    "You can also create highly interactive, personalized messages that nurture long-term customer loyalty. Easily ask for online reviews and share the best ones with the world in no time, automatically. It’s an all-around innovative marketing tool." }
    };

    public SmsLadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void smsLandingPageMain() {
        driver.get(STAGING_BASE_URL
                + "/benefits/sms-marketing?utm_source=google&utm_medium=search&utm_campaign=GS_ENG-Speaking_NB_Email_TCPA&utm_term=sms");

        testResponsiveness(driver, 375, 667);
        testResponsiveness(driver, 768, 1024);
        testResponsiveness(driver, 1280, 800);

        for (String[] element : elementsToCompare) {
            compareText(findElementAndReturnText(element[0]), element[1]);
        }
    }

    private String findElementAndReturnText(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText();
    }

    private void compareText(String websiteContent, String expectedContent) {
        if (expectedContent.equalsIgnoreCase(websiteContent)) {
            System.out.println("***************");
            System.out.println(counter + ": Figma Text Content is Matched with WebSite Content Successfully");
            System.out.println("***************");
            counter++;
        } else {
            System.out.println("Figma Text Content is Not Matched with WebSite Content. Please check the content");
            System.out.println(websiteContent);
            System.out.println("Not Matched With ");
            System.out.println(expectedContent);
        }
    }

    private void testResponsiveness(WebDriver driver, int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
        threadSleep(5000);
        for (int i = 0; i < 3; i++) {
            scrollDown(driver);
        }

    }

    private void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    private void threadSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
