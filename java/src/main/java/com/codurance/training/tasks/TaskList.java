package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.AddProjectCommand;
import com.codurance.training.tasks.commands.AddTaskCommand;
import com.codurance.training.tasks.commands.AddTaskWithIdCommand;
import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.domain.Projects;
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
    private final AddCommand.IdGenerator idGenerator;


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
        idGenerator = new AddCommand.IdGenerator();
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

        ExecutableCommand cmdLine = new CommandLine(commandLine, addCommands, projects, display, idGenerator);
        cmdLine.execute();



    }


}
