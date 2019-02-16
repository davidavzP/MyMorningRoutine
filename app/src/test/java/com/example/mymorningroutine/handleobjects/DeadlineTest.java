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
    public void getDeadline() {
        Deadline d = new Deadline("Test", "4", "5");
        String deadline = d.getDeadline();
        Assert.assertEquals("Test", deadline);
    }

    @Test
    public void setDeadline() {
        Deadline d = new Deadline("Test", "4", "5");
        Assert.assertEquals("Test", d.getDeadline());
        d.setDeadline("NEW NAME");
        Assert.assertEquals("NEW NAME", d.getDeadline());

    }

    @Test
    public void getHours() {
        Deadline d = new Deadline("Test", "4", "5");
        String deadline = d.getHours();
        Assert.assertEquals("4" , deadline);
    }

    @Test
    public void setHour() {
        Deadline d = new Deadline("Test", "4", "5");
        Assert.assertEquals("4", d.getHours());
        d.setHour("5");
        Assert.assertEquals("5", d.getHours());
    }

    @Test
    public void getMinutes() {

        Deadline d = new Deadline("Test", "4", "5");
        Assert.assertEquals("5", d.getMinutes());

    }

    @Test
    public void setMinutes() {
        Deadline d = new Deadline("Test", "4", "5");
        Assert.assertEquals("5", d.getMinutes());
        d.setMinutes("6");
        Assert.assertEquals("6", d.getMinutes());
    }
}