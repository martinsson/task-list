package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Project {

    private List<Task> tasks = new ArrayList<>();
    private ProjectName projectName;

    public Project(ProjectName projectName) {
        this.projectName = projectName;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public ProjectName getName() {
        return projectName;
    }
}
