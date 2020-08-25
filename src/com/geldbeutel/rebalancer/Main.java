package com.geldbeutel.rebalancer;

import java.io.*;

public class Main {

    public static final String BACKUP = "/home/sam/IdeaProjects/Rebalancer2.0/src/com/geldbeutel/rebalancer/Backup.csv";
    public static final String path = "/home/sam/IdeaProjects/Rebalancer2.0/src/com/geldbeutel/rebalancer/Infos.csv";
    public static final String storedData = "/home/sam/IdeaProjects/Rebalancer2.0/src/com/geldbeutel/rebalancer/Infos2.csv";
    public static String s = "1234143";


    public static void main(String[] args) throws IOException {



        Portfolio p = createPortfolio("probe", loadData());
        System.out.println(p.toString());



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
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        for (String s = bufferedReader.readLine(); s != null ; s = bufferedReader.readLine()) {
            inputData += s + "\n";
        }
        return inputData;
    }

    public static void saveData() throws IOException {
        FileWriter fileWriter = new FileWriter(storedData);
        fileWriter.write("Text");
        fileWriter.close();
    }

}
