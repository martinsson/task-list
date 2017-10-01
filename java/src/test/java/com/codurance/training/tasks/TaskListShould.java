package com.codurance.training.tasks;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TaskListShould {

    @Test
    public void name() throws Exception {
        String[] strings = {"project", "taskId", "Task description"};
        assertEquals(strings, "project taskId Task description".split(" ", 3));
    }

    @Test
    public void extractsParts() throws Exception {
        Pattern pattern = Pattern.compile("task-with-id (.*) (.*) (.*)");
        Matcher matcher = pattern.matcher("task-with-id secrets myId Stop drinking coffee");


    }
}