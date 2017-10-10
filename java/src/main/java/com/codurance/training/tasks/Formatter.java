package com.codurance.training.tasks;

import java.io.PrintWriter;

public class Formatter {

    private final Task task;
    private final PrintWriter out;

    public Formatter(Task task, PrintWriter out) {
        this.task = task;
        this.out = out;
    }

    public void print() {
        out.printf(String.format("    [%c] %d: %s%s%n",
                (task.isDone() ? 'x' : ' '),
                task.getId(),
                task.getDescription(),
                task.getDeadline() == null ? "" : " " + task.getDeadline().toString()));

    }
}
