package com.geldbeutel.rebalancer;

import com.geldbeutel.selenium_scraper.Scraper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

public class Main {

    //public static final String BACKUP = "/home/sam/IdeaProjects/Rebalancer2.0/src/main/java/com/geldbeutel/rebalancer/savefiles/Backup.csv";
    //public static final String DATA_DIRECTORY = "/home/sam/IdeaProjects/Rebalancer2.0/src/main/java/com/geldbeutel/rebalancer/savefiles/";
    //public static final String LAST_SAVE_FILE = "/home/sam/IdeaProjects/Rebalancer2.0/src/main/java/com/geldbeutel/rebalancer/savefiles/LastSaveFile.csv";
    public static final String BACKUP = "C:\\Users\\User\\IdeaProjects\\Depot-Rebalancer\\src\\main\\java\\com\\geldbeutel\\rebalancer\\savefiles\\Backup.csv";
    public static final String DATA_DIRECTORY = "C:\\Users\\User\\IdeaProjects\\Depot-Rebalancer\\src\\main\\java\\com\\geldbeutel\\rebalancer\\savefiles\\";
    public static final String LAST_SAVE_FILE = "C:\\Users\\User\\IdeaProjects\\Depot-Rebalancer\\src\\main\\java\\com\\geldbeutel\\rebalancer\\savefiles\\LastSaveFile.csv";

    public static final String DATE_FORMAT_NOW = "dd-MM-yyyy (HH-mm-ss)";

    public static LinkedList<String> liste;

    public static void main(String[] args) throws IOException, InterruptedException {

        Portfolio p = createPortfolio("Kommer Faktor Portfolio", loadData(false));
        System.out.println(p.toString());
        p.preisAktualisieren();
        //p.portfolioRebalance(1000);
        //System.out.println(p.toString());
        //saveData(p);
        //Scraper.main();
        //System.out.println(liste.toString());
        System.out.println(p.toString());

    }

    // --- GETDATE METHOD FOR LATER USE --- //
    public static String getDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static Portfolio createPortfolio(String name, String data) {
        Portfolio p = new Portfolio(name);
        String[] lines = data.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] entries = lines[i].split("\\s*,\\s*");
            p.getProduktliste().add(new ETF(entries[0]));
            p.getProduktliste().get(i - 1).setPreis(Double.parseDouble(entries[1]));
            p.getProduktliste().get(i - 1).setDepotwert(Double.parseDouble(entries[2]));
            p.getProduktliste().get(i - 1).setGewichtung(Integer.parseInt(entries[3]));
        }
        return p;
    }


    // --- CREATE AND LOAD THE DATA --- //
    public static String loadData(boolean lastSave) throws IOException {
        if (lastSave) {
            String inputData = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(LAST_SAVE_FILE));
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(bufferedReader.readLine().trim()));
            for (String s = bufferedReader2.readLine(); s != null ; s = bufferedReader2.readLine()) {
                inputData += s + "\n";
            }
            return inputData;
        } else {
            String inputData = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(BACKUP));
            for (String s = bufferedReader.readLine(); s != null ; s = bufferedReader.readLine()) {
                inputData += s + "\n";
            }
            return inputData;
        }
    }

    public static void saveData(Portfolio p) throws IOException {

        String[] arrayString = p.toString().split("\n");
        String output = "";
        for (int i = 0; i < arrayString.length - 1; i++){
            String[] subarray = arrayString[i].split("\\s*Preis:\\s*");
            subarray[0] = subarray[0].replace("Name:", "").trim();
            subarray[1] = subarray[1].replaceAll("Depotwert:", " ").trim();
            subarray[1] = subarray[1].replaceAll("Gewichtung:", " ").trim();
            subarray[1] = subarray[1].replaceAll("\t", " ");
            subarray[1] = subarray[1].replaceAll(" +", ",");
            output += subarray[0] + ", " + subarray[1] + "\n";
        }

        FileWriter fileWriter = new FileWriter(String.format("%s%s%s%s", DATA_DIRECTORY, "SaveFile_", getDate(), ".csv"), false);
        FileWriter fileWriter2 = new FileWriter(LAST_SAVE_FILE, false);

        fileWriter.write("Name, Preis, Depotstand, Gewichtung\n");
        fileWriter.write(output);
        fileWriter.close();

        fileWriter2.write(String.format("%s%s%s%s", DATA_DIRECTORY, "SaveFile_", getDate(), ".csv"));
        fileWriter2.close();

    }

}
