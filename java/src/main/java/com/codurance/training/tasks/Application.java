package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

public final class Application implements Runnable {
    private static final String QUIT = "quit";

    private final BufferedReader in;
    public final PrintWriter out;
    private final Projects projects;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Application(in, out).run();
    }

    public Application(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        this.projects = new Projects(out);
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
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() {
        projects.serialize(new ProjectSerializer(out));

    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(new ProjectName(subcommandRest[1]));
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(new ProjectName(projectTask[0]), projectTask[1]);
        }
    }

    private void addProject(ProjectName projectName) {
        projects.addProject(projectName);
    }

    private void addTask(ProjectName projectName, String description) {
        projects.addTask(projectName, description);
    }

    private void check(String idString) {
        setDone(idString, TaskState.DONE);
    }

    private void uncheck(String idString) {
        setDone(idString, TaskState.TODO);
    }

    private void setDone(String idString, TaskState taskState) {
        TaskId id = new TaskId(Long.parseLong(idString));
        projects.setTaskDone(id, taskState);

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
