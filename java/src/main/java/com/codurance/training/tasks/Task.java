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

    public TaskId getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDeadline(MyDate deadline) {
        this.deadline = deadline;
    }

    public MyDate getDeadline() {
        return deadline;
    }

    public boolean isDue(MyDate onDate) {
        return onDate != null && onDate.equals(deadline);
    }

    void printTask(PrintWriter out) {
        out.printf("    [%c] %s: %s%n", (isDone() ? 'x' : ' '), getId().toString(), getDescription());
    }
}
