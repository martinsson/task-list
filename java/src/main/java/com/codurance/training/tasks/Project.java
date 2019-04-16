package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Project {
    private List<Task> tasks = new ArrayList<>();

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
