package com.codurance.training.tasks;

import com.codurance.training.tasks.output.Display;

import java.util.*;

import static java.lang.System.out;

public class Project {
    private Map<TaskId, Task> tasks = new HashMap<>();
    private ProjectId id;

    public Project(ProjectId projectId) {
        this.id = projectId;

    }

    ProjectId getId() {
        return id;
    }

    void add(Task task) {
        tasks.put(task.getId(), task);
    }

    void show(Display display) {
        display.projectId(id);
        for (Task task : tasks.values()) {
            display.display(task);
        }
        display.endSection();
    }

    public void deleteIfExists(TaskId taskId) {
        tasks.remove(taskId);
    }

    public void showToday(MyDate today, Display display) {
        tasks.values().stream()
                .filter(task -> task.isDue(today))
                .forEach(task -> display.display(task));
    }

    public void setDoneIfExists(TaskId taskId, boolean done) {
        Task task = tasks.get(taskId);
        if (task == null) {
            out.printf("Could not find a task with an ID of %s.", taskId);
            out.println();
        } else {
            task.setDone(done);
        }

    }

    public void setDeadLineIfExists(TaskDeadline taskDeadline) {
        Task task = tasks.get(taskDeadline.id);
        if (task !=null ) {
            task.defineDeadline(taskDeadline);
        }
    }
}
