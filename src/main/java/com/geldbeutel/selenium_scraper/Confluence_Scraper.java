package com.geldbeutel.selenium_scraper;

import de.slackspace.openkeepass.KeePassDatabase;
import de.slackspace.openkeepass.domain.Entry;
import de.slackspace.openkeepass.domain.KeePassFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Confluence_Scraper {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {


        LinkedList<String> liste = new LinkedList<String>();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\IdeaProjects\\geckodriver.exe");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://confluence.adesso.de/login.action?os_destination=%2Findex.action&permissionViolation=true");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@id='os_username']")).sendKeys(getEntryFromKeepass("Confluence").getUsername());
        webDriver.findElement(By.xpath("//input[@id='os_password']")).sendKeys(getEntryFromKeepass("Confluence").getPassword());
        Thread.sleep(2000);
        webDriver.quit();



    }

    public static Entry getEntryFromKeepass(String title) {
        // Open Database
        KeePassFile database = KeePassDatabase.getInstance("C:\\Users\\User\\IdeaProjects\\Rebalancer2.0(clone)\\src\\main\\java\\com\\geldbeutel\\selenium_scraper\\Infos.kdbx")
                .openDatabase("1234");
        List<Entry> entries = database.getEntries();
        // Check Entries
        for (Entry entry : entries) {
            if (entry.getTitle().equals("Confluence")) {
                return entry;
            }
        }
        return null;
        // List<Group> groups = database.getTopGroups();
    }

}
