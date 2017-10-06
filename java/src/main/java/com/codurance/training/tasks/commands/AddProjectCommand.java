package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.TaskList;

public class AddProjectCommand implements Command {


    private Projects projects;
    private final String commandLine;

    public AddProjectCommand(Projects projects, String commandLine) {
        this.projects = projects;
        this.commandLine = commandLine;
    }

    @Override
    public boolean canHandle() {
        return commandLine.matches("^project .*");
    }

    @Override
    public void handle() {
        String project = commandLine.split(" ", 2)[1];
        projects.addProject(project);
    }
}
