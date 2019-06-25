package com.codurance.training.tasks;

import java.io.PrintWriter;

public class TaskDescriptionSerializer {
    private PrintWriter printWriter;

    public TaskDescriptionSerializer(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void serialize(String description) {
        printWriter.printf(": %s%n", description);
    }
}
