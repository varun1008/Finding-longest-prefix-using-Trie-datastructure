package com.varun;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    Trie trie;
    @BeforeEach
    void setUp() {
        trie = new Trie(new HashSet<>(){{
            addAll(Arrays.asList(1L,4L,44L,46L,48L,467L));
        }});
    }

    @Test
    void longestPrefix(){
        assertEquals(4L,trie.longestPrefix(40000000L));
        assertEquals(467L,trie.longestPrefix(467999898999L));
        assertEquals(46L,trie.longestPrefix(46951000L));
        assertEquals(48L,trie.longestPrefix(48L));
    }
}