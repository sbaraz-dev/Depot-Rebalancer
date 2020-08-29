package com.geldbeutel.selenium_scraper;

import com.geldbeutel.rebalancer.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Scraper {

    public static final String[] PATHS = {"https://de.finance.yahoo.com/quote/IS3R.DE?p=IS3R.DE&.tsrc=fin-srch&guccounter=1",
            "https://de.finance.yahoo.com/quote/IS3Q.DE?p=IS3Q.DE&.tsrc=fin-srch",
            "https://de.finance.yahoo.com/quote/IS3N.DE?p=IS3N.DE&.tsrc=fin-srch",
            "https://de.finance.yahoo.com/quote/IS3S.DE?p=IS3S.DE&.tsrc=fin-srch",
            "https://de.finance.yahoo.com/quote/IUSN.DE?p=IUSN.DE&.tsrc=fin-srch"};
    public static final String[] NAMES = {"Momentum: ", "Quality: ", "EM IMI: ", "Value: ", "Small Cap: "};

    public static void main() throws InterruptedException {
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LinkedList<String> liste = new LinkedList<String>();

        //System.setProperty("webdriver.gecko.driver", "/home/sam/IdeaProjects/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\IdeaProjects\\geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        WebDriver webDriver = new FirefoxDriver(options);

        for (int i = 0; i < PATHS.length; i++) {
            webDriver.get(String.format("%s", PATHS[i]));
            Thread.sleep(2000);
            //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // AUF POPUPS PRÜFEN
            if (webDriver.findElements(By.xpath("//button[@name='agree']")).size() != 0) {
                webDriver.findElement(By.xpath("//button[@name='agree']")).click();
            }
            // AUF REDIRECTIONS PRÜFEN
            if (webDriver.findElements(By.xpath("//a[contains(text(),'here')]")).size() != 0) {
                webDriver.findElement(By.xpath("//a[contains(text(),'here')]")).click(); // Popup für Coockies schließen
            }
            WebElement kursstand = webDriver.findElement(By.xpath("//span[@class='Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib)']"));
            liste.add(NAMES[i] + kursstand.getText());
        }

        webDriver.close();

        Main.liste = liste;


    }

}
