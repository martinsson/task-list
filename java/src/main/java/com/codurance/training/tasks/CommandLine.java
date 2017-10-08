package com.codurance.training.tasks;

public class CommandLine {

    public enum MainCommand {
        add;
    }

    public enum SubCommand {
        project,
        task,
        taskWithId("task-with-id");

        private final String command;

        SubCommand(String s) {
            this.command = s;
        }

        SubCommand() {
            this.command = this.name();
        }

        @Override
        public String toString() {
            return this.command;
        }
    }

    private String commandLine;

    public CommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public boolean is(MainCommand mainCommand, SubCommand subCommand) {
        return commandLine.matches("^" + mainCommand + " " + subCommand + " .*");
    }
}
