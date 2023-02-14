package com.varun;

import java.util.Map;
import java.util.TreeMap;

//Class to store a single operator
public class Operator{
    private final String name;     //Operator name
    private final Map<Long,Double> prices;//A map storing the prefixes, and it's corresponding prices.

    public Trie trie; //A variable to create a trie data structure for this operator

    //Constructor
    public Operator(String name, Map<Long,Double> prices){
        this.name = name;
        this.prices = prices;
        trie = new Trie(prices.keySet());
    }

    //Method to access the prices outside the class to maintain encapsulation
    public Map getOperatorPrices(){
        return new TreeMap(prices);
    }

    //Method to return price of a single code
    public double getPriceOf(long key){
        return this.prices.get(key);
    }

    //Method to access operator name
    public String getOperatorName(){
        return this.name;
    }
}
