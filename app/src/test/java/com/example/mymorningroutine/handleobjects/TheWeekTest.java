package com.example.mymorningroutine.handleobjects;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TheWeekTest {

    @Test
    public void parseWeek() {
        ArrayList<String> week = new ArrayList<>();
        week.add("true");
        week.add("false");
        week.add("true");
        week.add("false");
        week.add("true");
        week.add("false");
        week.add("true");
        TheWeek theWeek = new TheWeek();
        theWeek.parseWeek(week);
        Assert.assertEquals(true, theWeek.isMonday());
        Assert.assertEquals(false, theWeek.isTuesday());
        Assert.assertEquals(true, theWeek.isWednesday());
        Assert.assertEquals(false, theWeek.isThursday());
        Assert.assertEquals(true, theWeek.isFriday());
        Assert.assertEquals(false, theWeek.isSaturday());
        Assert.assertEquals(true, theWeek.isSunday());
        theWeek.setMonday(false);
        theWeek.setTuesday(true);
        theWeek.setWednesday(false);
        theWeek.setThursday(true);
        theWeek.setFriday(false);
        theWeek.setSaturday(true);
        theWeek.setSunday(false);
        Assert.assertEquals(false, theWeek.isMonday());
        Assert.assertEquals(true, theWeek.isTuesday());
        Assert.assertEquals(false, theWeek.isWednesday());
        Assert.assertEquals(true, theWeek.isThursday());
        Assert.assertEquals(false, theWeek.isFriday());
        Assert.assertEquals(true, theWeek.isSaturday());
        Assert.assertEquals(false, theWeek.isSunday());
    }

}