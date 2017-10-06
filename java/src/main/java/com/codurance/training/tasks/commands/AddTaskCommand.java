package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.*;

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
    public boolean canHandle() {
        return commandLine.matches("^task .*");
    }

    @Override
    public void handle() {
        String[] projectTask = commandLine.split(" ", 3);
        String generatedId = idGenerator.nextId() + "";
        final TaskId taskId = new TaskId(generatedId);
        String taskDescription = projectTask[2];
        String project = projectTask[1];
        projects.addTaskWithId(project, new Task(taskId, taskDescription, false));
    }
}
