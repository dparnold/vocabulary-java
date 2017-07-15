package com.dparnold.java;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Vocabulary spanisch = new Vocabulary("src/com/dparnold/java/vocabulary.txt");

        System.out.println("Get new Vocables:");
        review(3,spanisch);

        spanisch.save();

    }
    public static void learnNew(int n, Vocabulary vocabulary){
        Vocable[] list = vocabulary.getNewVocables(n);
        for (int i = 0; i <n ; i++) {
            System.out.println(list[i].getLang0());
            Scanner scanner = new Scanner(System.in);
            String lang1 = scanner.nextLine();
            System.out.println(list[i].test(lang1));
        }
    }
    public static void review(int n, Vocabulary vocabulary){
        Vocable[] list = vocabulary.getReviewVocables(n);
        for (int i = 0; i <n ; i++) {
            System.out.println(list[i].getLang0());
            Scanner scanner = new Scanner(System.in);
            String lang1 = scanner.nextLine();
            System.out.println(list[i].test(lang1));
        }
    }
}
