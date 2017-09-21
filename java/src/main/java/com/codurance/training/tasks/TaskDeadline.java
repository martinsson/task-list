package com.codurance.training.tasks;

public class TaskDeadline {
    public final int id;
    private MyDate deadline;

    public TaskDeadline(int taskId, MyDate deadline) {
        id = taskId;
        this.deadline = deadline;
    }

    public MyDate deadline() {
        return deadline;
    }
}
