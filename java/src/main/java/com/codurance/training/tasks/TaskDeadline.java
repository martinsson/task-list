package com.codurance.training.tasks;

public class TaskDeadline {
    public final TaskId id;
    private MyDate deadline;

    public TaskDeadline(TaskId taskId, MyDate deadline) {
        id = taskId;
        this.deadline = deadline;
    }

    public MyDate deadline() {
        return deadline;
    }
}
