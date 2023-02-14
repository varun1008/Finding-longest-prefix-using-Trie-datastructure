package com.varun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AlgorithmsTest {
    PriceList priceList;
    List<Operator> prices;
    @BeforeEach
    void setUp() {
        priceList = new PriceList();
        priceList.addOperator("A","""
                1	 0.9
                268	 5.1
                46	 0.17
                4620	 0.0
                468	 0.15
                4631	 0.15
                4673	 0.9
                46732	 1.1
                """);
        priceList.addOperator("B","""
                1	 0.92
                44	 0.5
                46	 0.2
                467	 1.0
                48	 1.2
                """);
        priceList.addOperator("C", """
                4112 0.1
                4112 0.2
                4112 0.3
                4112 0.0
                """);
        prices = priceList.getPriceList();
    }

    @ParameterizedTest
    @MethodSource("cheapestOperator")
    void parameterizedTest(long phoneNumber, long prefix, double price) {
        List<Object> result = Algorithms.findCheapestOperator(prices,phoneNumber);
        Operator cheapestOperator =(Operator) result.get(0);
        long returnedPrefix =(Long) result.get(1);
        assertEquals(prefix,returnedPrefix);
        assertEquals(price,cheapestOperator.getPriceOf(returnedPrefix));
    }

    @ParameterizedTest
    @MethodSource("cheapestOperator")
    void parameterizedTestUsingTrie(long phoneNumber, long prefix, double price) {
        List<Object> result = Algorithms.findCheapestOperatorUsingTrie(prices,phoneNumber);
        Operator cheapestOperator =(Operator) result.get(0);
        long returnedPrefix =(Long) result.get(1);
        assertEquals(prefix,returnedPrefix);
        assertEquals(price,cheapestOperator.getPriceOf(returnedPrefix));
    }

    private static Stream<Arguments> cheapestOperator(){
        return Stream.of(
                Arguments.of(46732123456L,46732,1.1),
                Arguments.of(4620123L,4620,0.0),
                Arguments.of(466666L,46,0.17),
                Arguments.of(411254L,4112,0.0)
        );
    }

    @Test
    void noPrefix(){
        List<Object> result = Algorithms.findCheapestOperator(prices,87888878L);
        assertEquals(-1L,result.get(1));
        assertNull(result.get(0));
    }

    @Test
    void noPrefixUsingTries(){
        List<Object> result = Algorithms.findCheapestOperatorUsingTrie(prices,87888878L);
        assertEquals(-1L,result.get(1));
        assertNull(result.get(0));
    }
}