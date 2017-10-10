package com.codurance.training.tasks;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;
    private Deadline deadline;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public long getId() {
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

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public String format() {
        return String.format("    [%c] %d: %s%s%n",
                (isDone() ? 'x' : ' '),
                getId(),
                getDescription(),
                deadline == null ? "" : " " + deadline.toString());
    }
}
