package com.dparnold.java;


import com.dparnold.java.Helper.Time;

public class Vocable {
    final int daySeconds = 60*60*24;
    final int weekSeconds = daySeconds*7;
    final int monthSeconds = weekSeconds*4;

    Vocabulary vocabulary;

    public final String lang0;
    private final String lang1;
    private int level;
    private int timesLearned;
    private int timesWrong;
    private int streak;
    private long lastReviewed;
    private int lastStreak;
    private long nextReviewTime;

    public  Vocable (String wholeLine){
        String[] splitted= wholeLine.split("\t");
        this.lang0 = splitted[0];
        this.lang1 = splitted[1];
        try {
            this.level = Integer.parseInt(splitted[2]);
            this.timesLearned = Integer.parseInt(splitted[3]);
            this.timesWrong = Integer.parseInt(splitted[4]);
            this.streak = Integer.parseInt(splitted[5]);
            this.lastReviewed = Long.parseLong(splitted[6]);
            this.nextReviewTime = Long.parseLong(splitted[7]);
        }
        catch (IndexOutOfBoundsException e){

            System.out.println("Standard values will be set for the vocable: "+lang0 +" - " +lang1);
            this.level = 0;
            this.timesLearned =0;
            this.timesWrong =0;
            this.streak =0;
            this.lastReviewed = Time.unixTime();
            this.nextReviewTime = Time.unixTime();
        }

    }
    public Vocable(String lang0, String lang1){
        this.lang0 = lang0;
        this.lang1 = lang1;
        this.level = 0;
        this.timesLearned =0;
        this.timesWrong = 0;
        this.streak = 0;
        this.lastReviewed = Time.unixTime();
        this.nextReviewTime = Time.unixTime();
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
        return lang0 + "\t" + lang1 + "\t" + level + "\t" + timesLearned + "\t" + timesWrong + "\t" + streak  + "\t" + lastReviewed +"\t" +nextReviewTime;
    }

    public int getUrgency() {
        // This can be a complex function to determine how urgent this vocable is
        int urgency = (int) (Time.unixTime() - this.nextReviewTime);
        return urgency;
    }
    public void test(String lang1){
        // This method is essential because this one is used to test whether the user knows this vocable
        // A score value us used. -2 = totaly wrong ; 2 = absolutely correct
        this.timesLearned++;
        // Set the next time to review this vocable

        int points = 0;
        // Decide how well the user did
        if(lang1.equals(this.lang1)){
            points = 2;
        }
        else if(lang1.equalsIgnoreCase(this.lang1)){
            points =2;
        }
        else{
            points =-2;
        }
        // Save older streak, in case the user did a typo
        this.lastStreak = this.streak;
        // Change the streak according to the level
        if (points>=0) this.streak++;
        else{
            this.streak =0;
            timesWrong++;
        }

        this.level+=points;
        this.nextReviewTime=calculateNextReviewTime(points);
    }

    public void onlyTypo(){
        this.streak = this.lastStreak+1; // Reset the streak and add one for the actually correct answer
        this.nextReviewTime = calculateNextReviewTime(2); // Handle as though the value had been correct
        this.timesWrong--; // Reduce the timesWrong ; remember that there is no timesRight
    }

    private long calculateNextReviewTime(int points){
        long nextReviewTime = Time.unixTime();
        switch (points){
            case 2:{
                if (this.streak<5) nextReviewTime+=daySeconds;
                else nextReviewTime+=weekSeconds;
                break;
            }
            case 1:;
                break;
            case 0:;
                break;
            case -1:;
                break;
            case -2:
                nextReviewTime+=daySeconds;
                break;
        }
    return nextReviewTime;
    }

    public int getTimesLearned() {
        return timesLearned;
    }


}
