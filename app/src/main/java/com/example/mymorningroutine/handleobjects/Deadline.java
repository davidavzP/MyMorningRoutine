package com.example.mymorningroutine.handleobjects;

import java.util.ArrayList;

public class Deadline {

    private String deadline;
    private String hours;
    private String minutes;

    public Deadline(String deadline, String hours, String minutes){
        this.deadline = deadline;
        this.hours = hours;
        this.minutes = minutes;


    }

    public static Deadline parse(ArrayList<String> deadline) {
        return new Deadline(deadline.get(0), deadline.get(1), deadline.get(2));
    }

    @Override
    public String toString() {
        return deadline + "\n" + hours + "\n" + minutes;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline) {
            Deadline that = (Deadline)other;
            return this.deadline.equals(that.deadline) && this.minutes.equals(that.minutes) && this.hours.equals(that.hours);
        } else {
            return false;
        }
    }


    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getHours() {
        return hours;
    }

    public void setHour(String hours) {
        this.hours = hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}
