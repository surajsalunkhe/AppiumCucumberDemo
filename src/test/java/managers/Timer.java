package managers;

import java.util.Date;
public class Timer {

    public long startStamp;

    public void start(){
        startStamp = getTimeStamp();
    }

    public static long getTimeStamp(){
        return new Date().getTime();
    }

    public boolean expired(int seconds){
        int difference = (int) ((getTimeStamp() - startStamp) / 1000);
        return difference > seconds;
    }

    public static int getDifference(long start, long end){
        return (int) ((end - start)/1000);
    }
}
