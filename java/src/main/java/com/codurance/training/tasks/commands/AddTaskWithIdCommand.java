package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.*;
import com.codurance.training.tasks.input.CommandLine;

import static com.codurance.training.tasks.input.MainCommand.add;
import static com.codurance.training.tasks.input.SubCommand.taskWithId;

public class AddTaskWithIdCommand implements Command {
    private Projects projects;

    public AddTaskWithIdCommand(Projects projects) {
        this.projects = projects;
    }

    @Override
    public boolean canHandle(CommandLine cmdLine) {
        return cmdLine.is(add, taskWithId);
    }

    @Override
    public void handle(CommandLine cmdLine) {
        projects.addTaskWithId(cmdLine.getProjectId(), cmdLine.getTask());
    }
}
