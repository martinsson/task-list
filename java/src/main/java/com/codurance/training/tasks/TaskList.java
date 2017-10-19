package com.codurance.training.tasks;

import com.codurance.training.tasks.domain.Projects;
import com.codurance.training.tasks.output.Display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Projects projects;
    private final Display display;
    private final UserInput userInput;
    private final IdGenerator idGenerator;


    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(new Display(out), new UserInput(in)).run();
    }

    public TaskList(Display display, UserInput userInput) {
        this.userInput = userInput;
        this.display = display;
        projects = new Projects(this.display);
        idGenerator = new IdGenerator();
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

        CommandLine cmdLine = new CommandLine(commandLine, projects, display, idGenerator);
        cmdLine.execute();



    }


}
