package com.dparnold.java;

/**
 * Created by dominik on 7/6/17.
 */

public class Main {
    public static void main(String[] args){
        Vocabulary englisch = new Vocabulary();
        englisch.addNew("hallo", "hello");
        englisch.addNew("Boden", "bottom");
        englisch.print();
    }
}
