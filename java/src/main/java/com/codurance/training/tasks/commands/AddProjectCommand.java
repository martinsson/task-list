package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.input.CommandLine;
import com.codurance.training.tasks.domain.Project;
import com.codurance.training.tasks.domain.Projects;

import static com.codurance.training.tasks.input.MainCommand.add;
import static com.codurance.training.tasks.input.SubCommand.project;

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
