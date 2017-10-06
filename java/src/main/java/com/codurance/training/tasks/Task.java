package com.codurance.training.tasks;

import java.io.PrintWriter;

public final class Task {
    private final TaskId id;
    private final String description;
    private boolean done;
    private MyDate deadline;

    public Task(TaskId id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    TaskId getId() {
        return id;
    }

    void setDone(boolean done) {
        this.done = done;
    }

    boolean isDue(MyDate onDate) {
        return onDate != null && onDate.equals(deadline);
    }

    void printTask(PrintWriter out) {
        out.printf("    [%c] %s: %s%n", (done ? 'x' : ' '), getId().toString(), description);
    }

    void defineDeadline(TaskDeadline taskDeadline) {
        this.deadline = taskDeadline.deadline();
    }
}
