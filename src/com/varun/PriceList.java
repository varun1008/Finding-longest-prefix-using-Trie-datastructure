package com.varun;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Class to store all the operators
public class PriceList{
    private List<Operator> list; //A list to store all the operators

    //Constructor
    public PriceList(){
        list = new ArrayList<>();
    }

    //Method to access the price list in other classes, This prevents other classes manipulating the price list and maintain encapsulation.
    public List<Operator> getPriceList(){
        return new ArrayList<Operator>(list);
    }

    //Method to add an operator to the price list
    public void addOperator(String name,String operatorString){
        Map<Long,Double> operatorPrices = new TreeMap<>();
        String[] strArr = operatorString.split("[^\\d+\\.\\d+|\\d+]");
        int counter = 0;
        long key = 0L;
        double value;
        for (int i = 0; i< strArr.length ; i++){
            try {
                if (!strArr[i].isBlank()){
                    if (counter == 0){
                        key = Long.parseLong(strArr[i].trim());
                        counter++;
                    } else if (counter == 1) {
                        value = Double.parseDouble(strArr[i].trim());
                        operatorPrices.put(key,value);
                        counter--;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error with declaring an operator please check" + e);
            }

        }
        Operator operator = new Operator(name,operatorPrices);
        list.add(operator);
    }

    //Overriding the toString() method to print the price list
    @Override
    public String toString() {
        String priceList = "";
        for (Operator operator : list){
            priceList  += "\nOperator " + operator.getOperatorName() + " : " + operator.getOperatorPrices().toString();

        }
        return priceList;
    }
}
