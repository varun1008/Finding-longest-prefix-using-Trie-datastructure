package com.varun;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Algorithms{
    //Brute force algorithm to find the cheapest operator
    public static List<Object> findCheapestOperator(List<Operator> priceList, long phoneNumber){
        //variable declarations
        Operator cheapestOperator = null; //cheapest operator
        double cheapestPrice = 100000000; //cheapest price of the longest prefix
        long prefix = -1; //longest prefix
        int prefixLength = 0; //length of the longest prefix
        String targetPhoneNumber  = String.valueOf(phoneNumber); //converting phone number to string

        for (Operator operator : priceList){ //Looping across all the operators in price list
            Map<Long,Double> prices = operator.getOperatorPrices(); //Assigning the prices of an operator to a map
            Set<Long> keys = prices.keySet(); //Extracting the prefixes of the operator

            for (Long key : keys){ //Looping across all the prefixes of the operator
                String keyString = String.valueOf(key);

                //Checking if telephone code is the prefix of the phone number
                //Checking if the length of the code is greater than the previously found prefix
                if( (targetPhoneNumber.startsWith(keyString) ) && (keyString.length() >= prefixLength) ){

                    //Checking if code price is less than previously found prefix, then assigning this prefix as the cheapest operator
                    if (keyString.length() > prefixLength | prices.get(key) < cheapestPrice){
                        prefixLength = keyString.length();
                        prefix = key;
                        cheapestOperator = operator;
                        cheapestPrice = prices.get(key);
                    }
                }
            }

        }
        //Returning the cheapest operator and the longest prefix
        return Arrays.asList(cheapestOperator,prefix);
    }


    //Algorithm using trie data structure providing better time complexity compared to brute force approach
    public static List<Object> findCheapestOperatorUsingTrie(List<Operator> priceList, long phoneNumber){
        //variable declarations
        Operator cheapestOperator = null; //cheapest operator
        double cheapestPrice = 100000000; //cheapest price of the longest prefix
        long prefix = -1; //longest prefix
        int prefixLength = 0; //length of the longest prefix

        for (Operator operator : priceList) { //Looping across all the operators in price list
            Map<Long,Double> prices = operator.getOperatorPrices(); //Assigning the prices of an operator to a map
            long tempPrefix = operator.trie.longestPrefix(phoneNumber); //calling the longestPrefix method on the operator instance
            int tempPrefixLength = String.valueOf(tempPrefix).length();

            if((tempPrefixLength >= prefixLength) && (prices.get(tempPrefix) != null)){
                //Checking if code price is less than previously found prefix, then assigning this prefix as the cheapest operator
                if (tempPrefixLength > prefixLength |  prices.get(tempPrefix) < cheapestPrice){
                    prefixLength = tempPrefixLength;
                    prefix = tempPrefix;
                    cheapestOperator = operator;
                    cheapestPrice = prices.get(tempPrefix);
                }
            }
        }
        //Returning the cheapest operator and the longest prefix
        return Arrays.asList(cheapestOperator,prefix);
    }
}
