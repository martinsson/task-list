package com.codurance.training.tasks;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeadlineTest {

    @Test
    public void should_accept_a_string() throws Exception {
        String christmasDay = new Deadline("2017-12-25").toString();
        Assert.assertEquals("outdated at 2017-12-25", christmasDay);
    }
}