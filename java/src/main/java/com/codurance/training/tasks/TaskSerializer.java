package com.codurance.training.tasks;

import java.io.PrintWriter;

public class TaskSerializer {
    private PrintWriter out;

    public TaskSerializer(PrintWriter out) {
        this.out = out;
    }

    public void serialize(TaskId id, boolean done, String description) {
        out.printf("    [%c] %d: %s%n", (done ? 'x' : ' '), id.id, description);
    }
}
