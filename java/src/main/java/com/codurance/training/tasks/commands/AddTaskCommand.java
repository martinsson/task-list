package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.*;

import static com.codurance.training.tasks.MainCommand.add;
import static com.codurance.training.tasks.SubCommand.task;

public class AddTaskCommand implements Command {
    private final IdGenerator idGenerator;
    private Projects projects;

    public AddTaskCommand(Projects projects, IdGenerator idGenerator) {
        this.projects = projects;
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean canHandle(CommandLine cmdLine) {
        return cmdLine.is(add, task);
    }

    @Override
    public void handle(CommandLine cmdLine) {
        ProjectId projectId = cmdLine.getProjectId();
        TaskId taskId = new TaskId(idGenerator.nextId());
        Task task = cmdLine.getTask(taskId);
        projects.addTaskWithId(projectId, task);
    }
}
