package com.codurance.training.tasks;

import com.codurance.training.tasks.domain.MyDate;
import com.codurance.training.tasks.domain.Projects;
import com.codurance.training.tasks.domain.TaskDeadline;
import com.codurance.training.tasks.domain.TaskId;
import com.codurance.training.tasks.input.OldCmdLine;
import com.codurance.training.tasks.output.Display;

public class CommandLine implements ExecutableCommand {
    private String commandLine;
    private Projects projects;
    private Display display;
    private AddCommand.IdGenerator idGenerator;

    public CommandLine(String commandLine, Projects projects, Display display, AddCommand.IdGenerator idGenerator) {
        this.commandLine = commandLine;
        this.projects = projects;
        this.display = display;
        this.idGenerator = idGenerator;
    }

    @Override
    public void execute() {
        OldCmdLine oldCmdLine = new OldCmdLine(commandLine);
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                projects.show();
                break;
            case "add":
                new AddCommand(commandRest[1], projects, display, idGenerator).execute();
                break;
            case "view":
                view(commandRest[1]);
            case "check":
                projects.check(new TaskId(commandRest[1]));
                break;
            case "uncheck":
                projects.uncheck(new TaskId(commandRest[1]));
                break;
            case "help":
                display.help(this);
                break;
            case "deadline":
                commandRest = commandLine.split(" ", 3);
                MyDate deadline = new MyDate(commandRest[2]);
                TaskId id = new TaskId(commandRest[1]);
                projects.deadline(new TaskDeadline(id, deadline));
                break;
            case "deleteIfExists":
                projects.delete(new TaskId(commandRest[1]));
                break;
            case "today":
                projects.today();
                break;
            default:
                oldCmdLine.displayNotFound(display);
                break;
        }

    }

    private void view(String viewCommand) {
        projects.viewByDate();
    }


}
