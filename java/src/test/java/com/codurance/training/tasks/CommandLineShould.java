package com.codurance.training.tasks;

import org.junit.Assert;
import org.junit.Test;

import static com.codurance.training.tasks.CommandLine.MainCommand.add;
import static com.codurance.training.tasks.CommandLine.SubCommand.task;
import static com.codurance.training.tasks.CommandLine.SubCommand.taskWithId;
import static org.junit.Assert.*;

public class CommandLineShould {
    @Test
    public void matchAddTask() throws Exception {
        assertTrue(new CommandLine("add task practice coding").is(add, task));
    }
    @Test
    public void matchAddTaskWithId() throws Exception {
        assertTrue(new CommandLine("add task-with-id myId practice coding").is(add, taskWithId));
    }
}