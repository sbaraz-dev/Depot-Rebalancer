package com.geldbeutel.rebalancer;

import java.io.*;

public class Main {

    public static final String BACKUP = "/home/sam/IdeaProjects/Rebalancer2.0/src/com/geldbeutel/rebalancer/Backup.csv";
    public static final String storedData = "/home/sam/IdeaProjects/Rebalancer2.0/src/com/geldbeutel/rebalancer/storedData.csv";

    public static void main(String[] args) throws IOException {


        Portfolio p = createPortfolio("Kommer Faktor Portfolio", loadData());
        p.portfolioRebalance(3350);
        System.out.println(p.toString());
        saveData(p);


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

    public static String loadData() throws IOException {
        String inputData = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(storedData));
        for (String s = bufferedReader.readLine(); s != null ; s = bufferedReader.readLine()) {
            inputData += s + "\n";
        }
        return inputData;
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

        FileWriter fileWriter = new FileWriter(storedData, false); //vorübergehen gespeichert in storedData
        fileWriter.write("Name, Preis, Depotstand, Gewichtung\n");
        fileWriter.write(output);
        fileWriter.close();
    }

}
