package com.codurance.training.tasks;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskListShould {

    @Test
    public void name() throws Exception {
        String[] strings = {"project", "taskId", "Task description"};
        assertEquals(strings, "project taskId Task description".split(" ", 3));
    }
}