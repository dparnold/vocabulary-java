package com.dparnold.java;


import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Vocabulary englisch = new Vocabulary("src/com/dparnold/java/vocabulary.txt");

        //for (int i=0;i<13;i++) {
            //englisch.addNew("Boden", "bottom");
            //englisch.addNew("hallo", "hello");
           // englisch.getUrgend().test("hello");
            //englisch.getUrgend().test("bottom");
            //englisch.getUrgend().test("helllo");
        //}
        englisch.printUrgency();
        System.out.println("Get new Vocables:");
        System.out.println(englisch.getNewVocables().length);

        englisch.getReviewVocables(3);

        englisch.save();

    }
}
