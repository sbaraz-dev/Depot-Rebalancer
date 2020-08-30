package com.geldbeutel.rebalancer;

public class ETF implements Comparable<ETF>{

    private static int etfcounter = 0;

    private String name;
    private double preis;
    private int anteile;
    private double depotwert;
    private int gewichtung = 20;

    public ETF(String name){
        this.name = name;
        etfcounter++;
    }


    @Override
    public String toString(){
        return String.format("Name:\t%-25s\t Preis:\t%8.2f\t Anteile:%8d\t Depotwert:\t%-10.2f\t Gewichtung:\t%-10d%n"
                ,this.name, this.preis, this.anteile, this.depotwert, this.gewichtung).replaceAll(",",".");
    }

    // --- COMPARABLE INTERFACE OVERRITTEN METHODS --- //
    @Override
    public int compareTo(ETF etf) { return this.name.compareTo(etf.name); }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ETF)){
            return false;
        } else {
            if (this.name.equals(((ETF) obj).name)
                    && this.preis == ((ETF) obj).preis
                    && this.depotwert == ((ETF) obj).depotwert
                    && this.gewichtung == ((ETF) obj).gewichtung
                    && this.anteile == ((ETF) obj).anteile
            ) return true;
            return false;
        }
    }


    // --- GETTER AND SETTER --- //
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getDepotwert() {
        return depotwert;
    }

    public void setDepotwert(double depotwert) {
        this.depotwert = depotwert;
    }

    public int getGewichtung() {
        return gewichtung;
    }

    public void setGewichtung(int gewichtung) {
        this.gewichtung = gewichtung;
    }

    public void setAnteile(int anteile) {
        this.anteile = anteile;
    }

    public int getAnteile() {
        return anteile;
    }


}


// EXAMPLE FOR OVERRIDING THE COMPARABLE METHOD
/*    @Override
    public int compare(ETF etf, ETF t1) {
        if (etf.preis == t1.preis) {
            return 0;
        } else {
            return etf.preis < t1.preis ? -1 : 1;
        }
    }*/
