package com.geldbeutel.rebalancer;

public class ETF {

    private static int etfcounter = 0;

    private String name;
    private double preis;
    private double depotwert;
    private int gewichtung;

    public ETF(String name){
        this.name = name;
        etfcounter++;
    }

    @Override
    public String toString(){
        return String.format("Name:\t%s,\t Preis:\t%.2f,\t Depotwert:\t%.2f,\t Gewichtung:\t%d%n"
                ,this.name, this.preis, this.depotwert, this.gewichtung);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ETF)){
            return false;
        }else{
            if (this.name.equals(((ETF) obj).name)
                    && this.preis == ((ETF) obj).preis
                    && this.depotwert == ((ETF) obj).depotwert
                    && this.gewichtung == ((ETF) obj).gewichtung
            ) return true;
            return false;
        }
    }


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


}
