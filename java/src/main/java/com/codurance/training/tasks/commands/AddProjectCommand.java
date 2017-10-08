package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.CommandLine;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Projects;

import static com.codurance.training.tasks.MainCommand.add;
import static com.codurance.training.tasks.SubCommand.project;

public class AddProjectCommand implements Command {


    private Projects projects;
    private final String commandLine;

    public AddProjectCommand(Projects projects, String commandLine) {
        this.projects = projects;
        this.commandLine = commandLine;
    }

    @Override
    public boolean canHandle(CommandLine cmdLine) {
        return cmdLine.is(add, project);
    }

    @Override
    public void handle(CommandLine cmdLine) {
        String project = commandLine.split(" ", 2)[1];
        projects.addProject(new Project(project));
    }
}
