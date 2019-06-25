package com.codurance.training.tasks;

public final class Task {
    private final TaskId id;
    private final TaskDescription description;
    private TaskState state;

    public Task(TaskId id, TaskDescription description, TaskState state) {
        this.id = id;
        this.description = description;
        this.state = state;
    }

    public void serialize(TaskSerializer taskSerializer) {
        taskSerializer.serialize(id, state, description);
    }

    public boolean matches(TaskId id) {
        return this.id.equals(id);
    }

    void setState(TaskState taskState) {
        this.state = taskState;
    }
}
