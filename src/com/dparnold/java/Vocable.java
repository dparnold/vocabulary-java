package com.dparnold.java;


public class Vocable {
    Vocabulary vocabulary;

    public final String lang0;
    private final String lang1;
    private int level;
    private int timesLearned;
    private int timesWrong;
    private int streak;
    private long lastLearned;
    private int lastStreak;
    private long learnNextTime;
    private long lastlearnNextTime;

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
            this.learnNextTime = Long.parseLong(splitted[7]);
        }
        catch (IndexOutOfBoundsException e){

            System.out.println("Standard values will be set for the vocable: "+lang0 +" - " +lang1);
            this.level = 0;
            this.timesLearned =0;
            this.timesWrong =0;
            this.streak =0;
            this.lastLearned = getUnixTime();
            this.learnNextTime = getUnixTime();
        }

    }
    public Vocable(String lang0, String lang1){
        this.lang0 = lang0;
        this.lang1 = lang1;
        this.level = 0;
        this.timesLearned =0;
        this.timesWrong = 0;
        this.streak = 0;
        this.lastLearned = getUnixTime();
        this.learnNextTime = getUnixTime();
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
        return lang0 + "\t" + lang1 + "\t" + level + "\t" + timesLearned + "\t" + timesWrong + "\t" + streak  + "\t" + lastLearned +"\t" +learnNextTime;
    }

    public int getUrgency() {
        // This can be a complex function to determine how urgent this vocable is
        int urgency = (int) (getUnixTime() - this.learnNextTime);
        return urgency;
    }
    public void test(String lang1){
        // This method is essential because this one is used to test whether the user knows this vocable
        // A score value us used. -2 = totaly wrong ; 2 = absolutely correct
        this.timesLearned++;
        // Decide how well the user did
        if(lang1.equals(this.lang1)){
            this.level+=2;
            this.streak++;
            // Set the next time to review this vocable
            if (this.streak<=this.vocabulary.settings.dailyUntilStreak) {
                // Review tomorrow
                this.learnNextTime = getUnixTime() + 60*60*24;
            }
            else {
                this.learnNextTime = getUnixTime() + 60 * 60 * 24 * 30;
            }
        }
        else if(lang1.equalsIgnoreCase(this.lang1)){
            this.level+=2;
            this.streak++;
        }
        else{
            this.level-=2;
            this.timesWrong++;
            this.streak=0;
            this.learnNextTime = getUnixTime() +60*60*24;
        }
        this.lastStreak = this.streak;
        this.lastlearnNextTime = this.learnNextTime;
    }
    public void onlyTypo(){
        this.streak = this.lastStreak;
        this.learnNextTime = this.lastlearnNextTime;
        this.timesWrong--;
    }
    public int getTimesLearned() {
        return timesLearned;
    }
    private long getUnixTime (){
        return (System.currentTimeMillis() / 1000L);
    }
}
