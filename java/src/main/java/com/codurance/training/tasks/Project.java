package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Project {

    private List<Task> tasks = new ArrayList<>();
    private ProjectName projectName;

    public Project(ProjectName projectName) {
        this.projectName = projectName;
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    public Optional<Task> findTask(TaskId id) {
        for (Task task : tasks) {
            if (task.matches(id)) {
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    void serialize(ProjectSerializer projectSerializer) {
        projectSerializer.serialize(projectName, tasks);
    }
}
