package com.codurance.training.tasks;

public class TaskId {
    private String id;

    public TaskId(String id) {
        this.id = id;
    }

    public TaskId(long l) {
        id = l+ "";
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskId taskId = (TaskId) o;

        return id != null ? id.equals(taskId.id) : taskId.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
