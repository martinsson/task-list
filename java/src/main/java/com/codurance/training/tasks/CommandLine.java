package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.domain.MyDate;
import com.codurance.training.tasks.domain.Projects;
import com.codurance.training.tasks.domain.TaskDeadline;
import com.codurance.training.tasks.domain.TaskId;
import com.codurance.training.tasks.input.OldCmdLine;
import com.codurance.training.tasks.output.Display;

import java.util.List;

public class CommandLine {
    private String commandLine;
    private List<Command> addCommands;
    private Projects projects;

    public CommandLine(String commandLine, List<Command> addCommands, Projects projects) {
        this.commandLine = commandLine;
        this.addCommands = addCommands;
        this.projects = projects;

    }

    public void execute(Display display) {
        OldCmdLine oldCmdLine = new OldCmdLine(commandLine);
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                projects.show();
                break;
            case "add":
                add(oldCmdLine);
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

    private void add(OldCmdLine cmdLine) {
        addCommands.stream()
                .filter(command -> command.canHandle(cmdLine))
                .findFirst()
                .ifPresent(command -> command.handle(cmdLine));
    }

}
