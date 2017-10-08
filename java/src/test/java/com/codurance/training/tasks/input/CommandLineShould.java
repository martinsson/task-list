package com.codurance.training.tasks.input;

import com.codurance.training.tasks.ProjectId;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskId;
import com.codurance.training.tasks.input.CommandLine;
import org.junit.Assert;
import org.junit.Test;

import static com.codurance.training.tasks.input.MainCommand.add;
import static com.codurance.training.tasks.input.SubCommand.task;
import static com.codurance.training.tasks.input.SubCommand.taskWithId;
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
        Assert.assertEquals(new ProjectId("training"), commandLine.getProjectId());
    }

    @Test
    public void extractTheTask() throws Exception {
        CommandLine commandLine = new CommandLine("add task training Four Elements of Simple Design");
        TaskId generatedId = new TaskId("generatedId");
        Assert.assertEquals(new Task(generatedId, "Four Elements of Simple Design"), commandLine.getTask(generatedId));
    }

    @Test
    public void extractTheTaskWithId() throws Exception {
        CommandLine commandLine = new CommandLine("add task-with-id secrets givenId Stop drinking coffee");
        TaskId givenId = new TaskId("givenId");
        assertEquals(new Task(givenId, "Stop drinking coffee"), commandLine.getTask());
    }

}