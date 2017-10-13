package com.codurance.training.tasks;

import com.codurance.training.tasks.output.Display;

import java.util.*;
import java.util.function.Consumer;

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
                .forEach(display::display);
    }

    public void setDone(TaskId taskId, Consumer<Task> taskMarker, Display display) {
        Task task = tasks.get(taskId);
        taskMarker.accept(task);
    }

    public boolean hasTask(TaskId taskId) {
        return tasks.get(taskId) != null;
    }

    public void setDeadLineIfExists(TaskDeadline taskDeadline) {
        Task task = tasks.get(taskDeadline.id);
        if (task !=null ) {
            task.defineDeadline(taskDeadline);
        }
    }
}
