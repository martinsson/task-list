package com.codurance.training.tasks;

public final class Task {
    private final TaskId id;
    private final String description;
    private boolean done;

    public Task(TaskId id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
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

    public void serialize(TaskSerializer taskSerializer) {
        taskSerializer.serialize(id, done, description);
    }

    public boolean matches(TaskId id) {
        return this.id.equals(id);
    }
}
