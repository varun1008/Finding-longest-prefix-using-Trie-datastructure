package com.varun;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {
    Operator operator;
    Map<Long,Double> prices;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prices = new TreeMap<>(){{
            put(1L,0.92);
            put(44L,0.5);
            put(46L,0.2);
            put(48L,1.2);
            put(467L,1.0);
        }};
        operator = new Operator("B",prices);
    }

    @org.junit.jupiter.api.Test
    void getOperatorPrices() {
        assertEquals(prices,operator.getOperatorPrices());
    }

    @org.junit.jupiter.api.Test
    void getOperatorName() {
        assertEquals("B",operator.getOperatorName());
    }

    @org.junit.jupiter.api.Test
    void trie() {
        assertEquals(467,operator.trie.longestPrefix(46789999L));
    }
}