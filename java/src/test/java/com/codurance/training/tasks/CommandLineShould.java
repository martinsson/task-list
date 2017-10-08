package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.ProjectId;
import org.junit.Test;

import static com.codurance.training.tasks.MainCommand.add;
import static com.codurance.training.tasks.SubCommand.task;
import static com.codurance.training.tasks.SubCommand.taskWithId;
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

    @Test
    public void extractTheProjectId() throws Exception {
        CommandLine commandLine = new CommandLine("add task training Four Elements of Simple Design");
        assertEquals(new ProjectId("training"), commandLine.getProjectId());
    }

    @Test
    public void extractTheTask() throws Exception {
        CommandLine commandLine = new CommandLine("add task training Four Elements of Simple Design");
        TaskId givenId = new TaskId("givenId");
        assertEquals(new Task(givenId, "Four Elements of Simple Design"), commandLine.getTask(givenId));
    }
}