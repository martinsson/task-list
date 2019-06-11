package com.codurance.training.tasks;

import java.io.PrintWriter;

public class TaskNotFoundSerializer implements TaskIdSerializer {
    private PrintWriter out;

    public TaskNotFoundSerializer(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void serialize(long id) {
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }
}
