package com.varun;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList(); //Creating an instance of price list
        //Adding operators to price list
        priceList.addOperator("A","""   
                1	  0.9
                268	  5.1
                46	  0.17
                4620  0.0
                468	  0.15
                4631  0.15
                4673  0.9
                46732  1.1
                """);
        priceList.addOperator("B","""
                1	 0.92
                44	 0.5
                46	 0.2
                467	 1.0
                48	 1.2
                """);

        System.out.println(priceList);
        List<Operator> prices = priceList.getPriceList();
        //Using helper function to print the result to console
        printResultToConsole(prices,4673212345L);
        printResultToConsole(prices,4673512345L);
        printResultToConsole(prices,46588212345L);
        printResultToConsole(prices,44L);

    }


    public static void printResultToConsole(List<Operator> prices, long phoneNumber){
        //change the method for different implementation of algorithm (Brute Force/Trie data structure).

        //List<Object> result = Algorithms.findCheapestOperator(prices,phoneNumber);
        List<Object> result = Algorithms.findCheapestOperatorUsingTrie(prices,phoneNumber);
        Operator cheapestOperator =(Operator) result.get(0);
        long prefix =(Long) result.get(1);
        System.out.println("Cheapest Operator for +"+ phoneNumber + " is Operator "+ cheapestOperator.getOperatorName()+ ", prefix: " +
                prefix + " cost: $" +cheapestOperator.getPriceOf(prefix));
    }
}