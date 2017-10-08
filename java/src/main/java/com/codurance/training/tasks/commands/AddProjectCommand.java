package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.CommandLine;
import com.codurance.training.tasks.Project;
import com.codurance.training.tasks.Projects;

import static com.codurance.training.tasks.MainCommand.add;
import static com.codurance.training.tasks.SubCommand.project;

public class AddProjectCommand implements Command {


    private Projects projects;

    public AddProjectCommand(Projects projects) {
        this.projects = projects;
    }

    @Override
    public boolean canHandle(CommandLine cmdLine) {
        return cmdLine.is(add, project);
    }

    @Override
    public void handle(CommandLine cmdLine) {
        projects.addProject(new Project(cmdLine.getProjectId()));
    }
}
