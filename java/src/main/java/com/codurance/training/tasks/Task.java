package com.codurance.training.tasks;

import com.codurance.training.tasks.output.Display;

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

    public Task(TaskId id, String description) {
        this(id, description, false);
    }

    TaskId getId() {
        return id;
    }

    public static void markDone(Task task) {
        task.done = true;
    }
    public static void markUndone(Task task) {
        task.done = false;
    }

    boolean isDue(MyDate onDate) {
        return onDate != null && onDate.equals(deadline);
    }

    public void printTask(Display display) {
        display.printTask(done, getId().toString(), description);
    }

    void defineDeadline(TaskDeadline taskDeadline) {
        this.deadline = taskDeadline.deadline();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (done != task.done) return false;
        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (description != null ? !description.equals(task.description) : task.description != null) return false;
        return deadline != null ? deadline.equals(task.deadline) : task.deadline == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        return result;
    }
}
