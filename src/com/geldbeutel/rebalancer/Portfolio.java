package com.geldbeutel.rebalancer;

import java.util.List;
import java.util.LinkedList;

public class Portfolio {

    private String name;
    private LinkedList<ETF> produktliste;

    public Portfolio(String name){
        this.name = name;
        this.produktliste = new LinkedList<ETF>();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ETF etf : produktliste) s.append(etf.toString());
        return s.toString();
    }

    public String getName(){ return this.name; }

    public void setName(String name) { this.name = name; }

    public LinkedList<ETF> getProduktliste(){ return this.produktliste; }


}
