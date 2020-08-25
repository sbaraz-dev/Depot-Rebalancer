package com.geldbeutel.rebalancer;

public class Main {

    public static void main(String[] args){

        ETF etf1 = new ETF("ETF1");
        ETF etf2 = new ETF("ETF2");
        System.out.println(etf1);
        System.out.println(etf2);

        Portfolio portfolio1 = new Portfolio("Portfolio1");
        portfolio1.getProduktliste().add(etf1);
        portfolio1.getProduktliste().add(etf2);

        System.out.println(portfolio1);

    }

}
