package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskIdShould {

    @Test(expected = Exception.class)
    public void notAllowSpaces() throws Exception {
        new TaskId("a b");
    }
}