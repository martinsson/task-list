package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.*;

import static com.codurance.training.tasks.CommandLine.MainCommand.add;
import static com.codurance.training.tasks.CommandLine.SubCommand.taskWithId;

public class AddTaskWithIdCommand implements Command {
    private final String commandLine;
    private Projects projects;

    public AddTaskWithIdCommand(Projects projects, String commandLine) {
        this.projects = projects;
        this.commandLine = commandLine;
    }

    @Override
    public boolean canHandle(CommandLine cmdLine) {
        return cmdLine.is(add, taskWithId);
    }

    @Override
    public void handle(CommandLine cmdLine) {
        String[] projectTask = commandLine.split(" ", 4);
        final TaskId taskId = new TaskId(projectTask[2]);
        String taskDescription = projectTask[3];
        String project = projectTask[1];
        ProjectId projectId = new ProjectId(project);
        projects.addTaskWithId(projectId, new Task(taskId, taskDescription, false));
    }
}
