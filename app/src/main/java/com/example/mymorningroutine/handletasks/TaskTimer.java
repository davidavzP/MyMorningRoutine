package com.example.mymorningroutine.handletasks;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;



public class TaskTimer{
    private static String TAG = "TASKTIMER";
    private long START_TIME_IN_MILLIS;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long TimeLeftInMillis;
    private TextView TextViewCountDown;
    private long mEndTime;
    private Task task;
    private int starthours;
    private int startminutes;
    private int startseconds;

    public TaskTimer(Task task, TextView TextViewCountDown){
        this.task = task;
        this.TextViewCountDown = TextViewCountDown;
        getTime();
        START_TIME_IN_MILLIS = convertTimeToMilliseconds();
        TimeLeftInMillis = START_TIME_IN_MILLIS;


    }


    private void getTime(){
        getHours();
        getMinutes();
        getSeconds();
    }


    //TODO: too many repeated things here
    private void getHours(){
        if(isEmpty(task.getTaskHours())){
            this.starthours = 0;
        }else {
            this.starthours = Integer.parseInt(task.getTaskHours());
        }
    }

    private void getMinutes(){
        if(isEmpty(task.getTaskMinutes())){
            this.startminutes = 0;
        }else{
            this.startminutes = Integer.parseInt(task.getTaskMinutes());
        }
    }

    private void getSeconds(){
        if(isEmpty(task.getTaskSeconds())){
            this.startseconds = 0;
        }else {
            this.startseconds = Integer.parseInt(task.getTaskSeconds());
        }
    }

    private boolean isEmpty(String string){
        if(string.equals("")){
            return true;
        }else{
            return false;
        }
    }

    private long convertTimeToMilliseconds(){
        double total_minutes = (starthours *60) + (startseconds *0.0166666667) + (startminutes);
        long millis = Math.round(total_minutes*60000);
        return millis;
    }


    public void startTimer() {

        mEndTime = System.currentTimeMillis() + TimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                TextViewCountDown.setText("DONE");
                TaskHandler.get().timeforNextTask();
            }
        }.start();


        mTimerRunning = true;

    }

    public void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    public void resetTimer() {
        pauseTimer();
        TimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    public void updateCountDownText() {
        int hours = (int) (TimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((TimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;
        TextViewCountDown.setText(formatTime(hours, minutes, seconds));

    }

    public void showStartTime(){
        TextViewCountDown.setText(formatTime(starthours, startminutes, startseconds));
    }

    private String formatTime(int hours, int minutes, int seconds){
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        return timeLeftFormatted;

    }

}
