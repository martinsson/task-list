package com.codurance.training.tasks;

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
