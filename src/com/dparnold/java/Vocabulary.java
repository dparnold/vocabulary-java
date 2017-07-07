package com.dparnold.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dominik on 7/7/17.
 */
public class Vocabulary {
    private List<Vocable> vocabulary;

    public Vocabulary(String file){
        this.vocabulary = new ArrayList<Vocable>();
    }
    public Vocabulary(){
        this.vocabulary = new ArrayList<Vocable>();
    }

    public void print(){
        for (int i = 0; i < this.vocabulary.size(); i++) {
            vocabulary.get(i).print();
        }
    }

    public void addNewInput(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Native language:");
        String lang0 = scan.nextLine();
        System.out.println("Learning language:");
        String lang1 = scan.nextLine();
        Vocable newVocable = new Vocable(lang0,lang1);
        this.vocabulary.add(newVocable);
    }

    public void addNew(String lang0, String lang1){
        this.vocabulary.add(new Vocable(lang0,lang1));
    }
}
