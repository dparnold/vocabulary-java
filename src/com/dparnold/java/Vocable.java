package com.dparnold.java;


public class Vocable {


    public String lang0;
    private String lang1;
    private int level;
    private int timesLearned;
    private int timesWrong;
    private int streak;
    private long lastLearned;

    public  Vocable (String wholeLine){
        String[] splitted= wholeLine.split("\t");
        this.lang0 = splitted[0];
        this.lang1 = splitted[1];
        try {
            this.level = Integer.parseInt(splitted[2]);
            this.timesLearned = Integer.parseInt(splitted[3]);
            this.timesWrong = Integer.parseInt(splitted[4]);
            this.streak = Integer.parseInt(splitted[5]);
            this.lastLearned = Long.parseLong(splitted[6]);
        }
        catch (IndexOutOfBoundsException e){

            System.out.println("Standard values will be set for the vocable: "+lang0 +" - " +lang1);
            this.level = 0;
            this.timesLearned =0;
            this.timesWrong =0;
            this.streak =0;
            this.lastLearned = (System.currentTimeMillis() / 1000L);
        }

    }
    public Vocable(String lang0, String lang1){
        this.lang0 = lang0;
        this.lang1 = lang1;
        this.level = 0;
        this.timesLearned =0;
        this.timesWrong = 0;
        this.streak = 0;
        this.lastLearned = (System.currentTimeMillis() / 1000L);
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

    public String saveFormat() {
        return lang0 + "\t" + lang1 + "\t" + level + "\t" + timesLearned + "\t" + timesWrong + "\t" + streak  + "\t" + lastLearned;
    }

    public int getUrgency() {
        // This can be a complex function
        int urgency = (int) ((System.currentTimeMillis() / 1000L) - this.lastLearned);
        return urgency;
    }
    public void test(String lang1){
        // A score value us used. -2 = totaly wrong ; 2 = absolutely correct
        if(lang1.equals(this.lang1)) this.level+=2;
        else if(lang1.equalsIgnoreCase(this.lang1)) this.level+=2;
        else this.level-=2;
    }
}
