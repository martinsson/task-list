package com.codurance.training.tasks;

import java.util.Objects;

public class TaskId {
    public long id;

    public TaskId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskId taskId = (TaskId) o;
        return id == taskId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
