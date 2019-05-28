package com.codurance.training.tasks;

import java.io.PrintWriter;

public class TaskSerializer {
    private PrintWriter out;

    public TaskSerializer(PrintWriter out) {
        this.out = out;
    }

    public void serialize(TaskId id, TaskState state, String description) {
        out.printf("    [%c] %d: %s%n", state.serialize(), id.id, description);
    }
}
