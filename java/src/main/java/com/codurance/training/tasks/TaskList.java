package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final BufferedReader in;
    private final PrintWriter out;
    final Projects tasks;
    private IdGenerator idGenerator = new IdGenerator();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        tasks = new Projects(writer);
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
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
        String[] commandRest = commandLine.split(" ",2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                tasks.show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                tasks.check(new TaskId(commandRest[1]));
                break;
            case "uncheck":
                tasks.uncheck(new TaskId(commandRest[1]));
                break;
            case "help":
                help();
                break;
            case "deadline":
                commandRest = commandLine.split(" ",3);
                MyDate deadline = new MyDate(commandRest[2]);
                TaskId id = new TaskId(commandRest[1]);
                tasks.deadline(new TaskDeadline(id, deadline));
                break;
            case "deleteIfExists":
                tasks.delete(new TaskId(commandRest[1]));
                break;
            case "today":
                tasks.today();
                break;
            default:
                error(command);
                break;
        }
    }

    private void add(String commandLine) {
        Command addProjectCommand = new AddProjectCommand(this, commandLine);
        Command addTaskCommand = new AddTaskCommand(this, commandLine, idGenerator);
        Command AddTaskWithIdCommand = new AddTaskWithIdCommand(this, commandLine);

        List<Command> addCommands = Arrays.asList(
                addProjectCommand,
                addTaskCommand,
                AddTaskWithIdCommand
        );
        addCommands.stream()
                .filter(command -> command.canHandle())
                .findFirst()
                .ifPresent(command->command.handle());
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

}
