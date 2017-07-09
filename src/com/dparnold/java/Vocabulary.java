package com.dparnold.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Vocabulary {
    private List<Vocable> vocabulary;
    private String filename;
    Settings settings = new Settings();

    // Constructors
    public Vocabulary(String filename){
        this.filename = filename;
        this.vocabulary = new ArrayList<Vocable>();
        read();
    }
    public Vocabulary(){
        this.vocabulary = new ArrayList<Vocable>();
    }

    // Custom exceptions
    class NoNewVocables extends Exception{
        public NoNewVocables(String s) {
            super(s);
        }
    }

    // Methods
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

    public void save(){
        try {
            File file = new File(this.filename);
            FileWriter fWriter = new FileWriter(file);
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for (int i = 0; i < this.vocabulary.size() ; i++) {
                bWriter.write(this.vocabulary.get(i).saveFormat() +"\n");
            }
            bWriter.flush();
            bWriter.close();
        }
        catch (IOException e){
        System.out.println(e);
        }

    }

    private void read(){
        try {
            FileReader fReader = new FileReader(this.filename);
            BufferedReader bReader = new BufferedReader(fReader);
            String line;
            while ((line = bReader.readLine()) != null){
                Vocable newVocable = new Vocable(line);
                newVocable.vocabulary = this;
                vocabulary.add(newVocable);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Vocable getUrgend(){
        int maxUrgencyIndex = 0;
        int urgency = vocabulary.get(0).getUrgency();
        for (int i = 1; i <vocabulary.size() ; i++) {
            if (urgency < vocabulary.get(i).getUrgency()){
                urgency = vocabulary.get(i).getUrgency();
                maxUrgencyIndex = i;
            }

        }
        return vocabulary.get(maxUrgencyIndex);
    }

    public Vocable[] getNewVocables (int n) {
        // Returns an array of n vocables which have never been learned
        int j = 0;
        Vocable[] newVocables = new Vocable[n];
        int i = 0;
        while(j<n){
            if (this.vocabulary.get(i).getTimesLearned()==0){
                newVocables[j] = this.vocabulary.get(i);
                j++;
            }
            i++;
            // Return only as many new vocables as there are
            if (i == this.vocabulary.size()){
                Vocable[] newVocablesShort= new Vocable[j];
                for (int k = 0; k < j; k++) {
                    newVocablesShort[k] = newVocables[k];
                }
                return  newVocablesShort;
            }
        }
        return newVocables;
    }
    public Vocable[] getNewVocables (){
        return getNewVocables(settings.numberOfNewVocables);
    }

    public void printUrgency(){
        for (int i = 0; i <vocabulary.size() ; i++) {
            System.out.println(vocabulary.get(i).getUrgency());

        }
    }

}
