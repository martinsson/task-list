package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.AddProjectCommand;
import com.codurance.training.tasks.commands.AddTaskCommand;
import com.codurance.training.tasks.commands.AddTaskWithIdCommand;
import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.domain.MyDate;
import com.codurance.training.tasks.domain.Projects;
import com.codurance.training.tasks.domain.TaskDeadline;
import com.codurance.training.tasks.domain.TaskId;
import com.codurance.training.tasks.input.CommandLine;
import com.codurance.training.tasks.output.Display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Projects projects;
    private final List<Command> addCommands;
    private final Display display;
    private final UserInput userInput;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(new Display(out), new UserInput(in)).run();
    }

    public TaskList(Display display, UserInput userInput) {
        this.userInput = userInput;
        this.display = display;
        projects = new Projects(this.display);
        addCommands = Arrays.asList(
                new AddProjectCommand(projects),
                new AddTaskCommand(projects),
                new AddTaskWithIdCommand(projects)
        );
    }

    public void run() {
        while (true) {
            display.promptForInput();
            String command;
            try {
                command = userInput.getInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        CommandLine cmdLine = new CommandLine(commandLine);
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                projects.show();
                break;
            case "add":
                add(cmdLine);
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
                cmdLine.displayNotFound(display);
                break;
        }
    }

    private void view(String viewCommand) {
        projects.viewByDate();
    }

    private void add(CommandLine cmdLine) {
        addCommands.stream()
                .filter(command -> command.canHandle(cmdLine))
                .findFirst()
                .ifPresent(command -> command.handle(cmdLine));
    }

}
