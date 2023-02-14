package com.varun;

import java.util.Set;

public class Trie {
        public Node root; //Root node of the tree

        //Constructor
        public Trie(Set<Long> keys){
            root = new Node('\0');
            for (Long key : keys){
                insert(key);
            }
        }

        //Method to insert into the trie
        private void insert(Long key){
            Node curr = root;
            int length,counter;
            length = counter = String.valueOf(key).length();
            for (int i=0; i< length ; i++){
                int value = (int) (key / Math.pow(10,counter-1)) %10;
                counter--;
                if (curr.children[value] == null){
                    curr.children[value] = new Node(value);
                }
                curr = curr.children[value];
            }
            curr.isDigit = true;
        }

        //Method to find the longest prefix for the given number in the Trie
        public long longestPrefix(long number){
            Node curr = root;
            int length,counter;
            length = counter = String.valueOf(number).length();
            for (int i=0; i< length ; i++) {
                int value = (int) (number / Math.pow(10, counter - 1)) % 10;
                if (curr.children[value] != null ){
                    curr = curr.children[value];
                }else break;
                counter--;
            }
            long prefix =(long) (number / Math.pow(10, counter));
            return prefix;
        }

        class Node{
            public int num;
            public boolean isDigit;
            public Node[] children;

            public Node(int num){
                this.num = num;
                this.isDigit = false;
                children = new Node[10];
            }
        }

}
