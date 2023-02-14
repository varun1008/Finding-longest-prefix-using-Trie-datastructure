package com.varun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {
    PriceList priceList;
    @BeforeEach
    void setUp() {
        priceList = new PriceList();
        priceList.addOperator("B","""
                1	 0.92
                44	 0.5
                46	 0.2
                467	 1.0
                48	 1.2
                """);
    }

    @Test
    void getPriceList() {
        assertTrue(priceList.getPriceList() instanceof List<Operator>);
    }

    @Test
    void addOperator() {
        Map<Long,Double> map = new TreeMap<>(){{
                put(1L,0.92);
                put(44L,0.5);
                put(46L,0.2);
                put(48L,1.2);
                put(467L,1.0);
        }};
        assertTrue(priceList.getPriceList().get(0).getOperatorPrices().equals(map));
    }

}