package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskId;
import com.codurance.training.tasks.TaskList;

public class AddTaskWithIdCommand implements Command {
    private final String commandLine;
    private Projects projects;

    public AddTaskWithIdCommand(Projects projects, String commandLine) {
        this.projects = projects;
        this.commandLine = commandLine;
    }

    @Override
    public boolean canHandle() {
        return commandLine.matches("^task-with-id .*");
    }

    @Override
    public void handle() {
        String[] projectTask = commandLine.split(" ", 4);
        final TaskId taskId = new TaskId(projectTask[2]);
        String taskDescription = projectTask[3];
        String project = projectTask[1];
        projects.addTaskWithId(project, new Task(taskId, taskDescription, false));
    }
}
