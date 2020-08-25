package com.geldbeutel.rebalancer;

import java.util.*;

public class Portfolio {

    private String name;
    private LinkedList<ETF> produktliste;

    public Portfolio(String name){
        this.name = name;
        this.produktliste = new LinkedList<ETF>();
    }





    public void portfolioRebalance(double investment){
        // Bestimmung des Zielbetrages
        double zielbetrag;
        Collections.sort(produktliste, new Comparator<ETF>() {
            @Override
            public int compare(ETF etf, ETF t1) {
                if (etf.getDepotwert() == t1.getDepotwert()) {
                    return 0;
                } else {
                    return etf.getDepotwert() < t1.getDepotwert() ? -1 : 1;
                }
            }
        }); // sortieren nach dem Depotwert
        if ((this.depotstandAusgeben() + investment) / this.produktliste.size() <= produktliste.getLast().getDepotwert()) {
            zielbetrag = produktliste.getLast().getDepotwert();
        } else {
            zielbetrag = (depotstandAusgeben() + investment) / produktliste.size();
        }
        // Berechnungen
        System.out.println("Handlungsempfehlung: ");
        Collections.sort(produktliste, new Comparator<ETF>() {
            @Override
            public int compare(ETF etf, ETF t1) {
                if (etf.getPreis() == t1.getPreis()) {
                    return 0;
                } else {
                    return etf.getPreis() > t1.getPreis() ? -1 : 1;
                }
            }
        }); // sortieren nach dem Preis
        double restbetrag = investment;
        //System.out.printf("%7s  %10s  %12s  %20s\n", "Anteile", "Kosten", "Neuer Stand", "Name");
        System.out.printf("%25s %10s %10s %20s\n", "Name", "Anteile", "Kosten", "neuer Depotstand");
        for (int i = 0; i < produktliste.size(); i++){
            int anteileCounter = 0;
            if (produktliste.get(i).getDepotwert() < (zielbetrag - (produktliste.get(i).getPreis() / 2)) &&
                    restbetrag >= produktliste.get(i).getPreis() ){
                restbetrag--; // 1€ Gebühr pro Transaktion!
                do{
                    produktliste.get(i).setDepotwert(produktliste.get(i).getDepotwert() + produktliste.get(i).getPreis());
                    anteileCounter++;
                    restbetrag -= produktliste.get(i).getPreis();
                } while ( Math.abs(zielbetrag - produktliste.get(i).getDepotwert()) >
                        (produktliste.get(i).getPreis() / 2 ) && restbetrag >= produktliste.get(i).getPreis());
            }
            double kosten = anteileCounter * produktliste.get(i).getPreis();
            System.out.printf("%25s %10s %10.2f %20.2f\n", produktliste.get(i).getName(), anteileCounter, kosten, produktliste.get(i).getDepotwert() );
        }
        System.out.println(String.format("%n%16s %10.2f%n%16s %10.2f%n", "Der Restbetrag: ", restbetrag , "Zielbetrag: ", zielbetrag));
    }




    public double depotstandAusgeben(){
        double sum = 0;
        for (ETF etf : produktliste) {
            sum += etf.getDepotwert();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ETF etf : produktliste) s.append(etf.toString());
        s.append(String.format("Der Depostand liegt bei: %.2f%n", depotstandAusgeben()));
        return s.toString();
    }

    // Getter und Setter
    public String getName(){ return this.name; }

    public void setName(String name) { this.name = name; }

    public LinkedList<ETF> getProduktliste(){ return this.produktliste; }

}
