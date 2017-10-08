package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.*;

import static com.codurance.training.tasks.CommandLine.MainCommand.add;
import static com.codurance.training.tasks.CommandLine.SubCommand.task;

public class AddTaskCommand implements Command {
    private final IdGenerator idGenerator;
    private final String commandLine;
    private Projects projects;

    public AddTaskCommand(Projects projects, String commandLine, IdGenerator idGenerator) {
        this.projects = projects;
        this.commandLine = commandLine;
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean canHandle(CommandLine cmdLine) {
        return cmdLine.is(add, task);
    }

    @Override
    public void handle(CommandLine cmdLine) {
        String generatedId = idGenerator.nextId() + "";
        final TaskId taskId = new TaskId(generatedId);
        String[] projectTask = commandLine.split(" ", 3);
        String taskDescription = projectTask[2];
        String project = projectTask[1];
        ProjectId projectId = new ProjectId(project);
        projects.addTaskWithId(project, new Task(taskId, taskDescription, false));
    }
}
