package com.dparnold.java;

/**
 * Created by dominik on 7/6/17.
 */

public class Vocable {


    private String lang0;
    private String lang1;
    private int level;
    private int timesLearned;
    private int timesWrong;
    private int streak;

    public Vocable(String lang0, String lang1){
        this.lang0 = lang0;
        this.lang1 = lang1;
        this.level = 0;
        this.timesLearned =0;
        this.timesWrong = 0;
        this.streak = 0;
    }
    public Vocable(String lang0, String lang1, int level, int timesLearned,int timesWrong, int streak){
        this.lang0 = lang0;
        this.lang1 = lang1;
        this.level = level;
        this.timesLearned = timesLearned;
        this.timesWrong = timesWrong;
        this.streak = streak;
    }

    public void print(){
        System.out.println(lang0 +" - " + lang1 +" at level: "+level);
    }
}
