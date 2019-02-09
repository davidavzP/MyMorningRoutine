package com.example.mymorningroutine.handleobjects;

import java.util.ArrayList;

public class TheWeek {
    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;


    public TheWeek parseWeek(ArrayList<String> week){

        monday = setDay(week.get(0));
        tuesday = setDay(week.get(1));
        wednesday = setDay(week.get(2));
        thursday = setDay(week.get(3));
        friday = setDay(week.get(4));
        saturday = setDay(week.get(5));
        sunday = setDay(week.get(6));
        return this;
    }

    @Override
    public String toString(){
        return "Monday: "+isMonday()+
                "\nTuesday: "+ isTuesday()+
                "\nWednesday: "+ isWednesday() +
                "\nThursday: "+ isThursday() +
                "\nFriday: "+ isFriday() +
                "\nSaturday: "+ isSaturday() +
                "\nSunday: "+ isSunday();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof TheWeek) {
            TheWeek that = (TheWeek) other;
            return this.monday == that.monday && this.tuesday == that.tuesday &&
                    this.wednesday == that.wednesday && this.thursday == that.thursday &&
                    this.friday == that.friday && this.saturday == that.saturday && this.sunday == that.sunday;
        } else {
            return false;
        }
    }



    private boolean setDay(String day) {
        if(day.endsWith("false")){
            return false;
        }else if(day.endsWith("true")){
            return true;
        }

        return false;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}

