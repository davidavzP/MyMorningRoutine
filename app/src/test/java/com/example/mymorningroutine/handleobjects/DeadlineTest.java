package com.example.mymorningroutine.handleobjects;

import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;

public class DeadlineTest {

    @Test
    public void parse() {
        ArrayList<String> deadline= new ArrayList<>();
        deadline.add("Deadline");
        deadline.add("6");
        deadline.add("10");
        Deadline d1 = Deadline.parse(deadline);
        Assert.assertEquals(d1.getDeadline(), deadline.get(0));
        Assert.assertEquals(d1.getHours(), deadline.get(1));
        Assert.assertEquals(d1.getMinutes(), deadline.get(2));
    }


    @Test
    public void equals() {
    }

    @Test
    public void getDeadline() {
    }

    @Test
    public void setDeadline() {
    }

    @Test
    public void getHours() {
    }

    @Test
    public void setHour() {
    }

    @Test
    public void getMinutes() {
    }

    @Test
    public void setMinutes() {
    }
}