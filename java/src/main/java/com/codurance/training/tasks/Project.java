package com.codurance.training.tasks;

import java.io.PrintWriter;
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

    void show(PrintWriter out) {
        out.println(id);
        for (Task task : tasks.values()) {
            task.printTask(out);
        }
        out.println();
    }

    public void deleteIfExists(TaskId taskId) {
        tasks.remove(taskId);
    }

    public void showToday(MyDate today, PrintWriter out) {
        tasks.values().stream()
                .filter(task -> task.isDue(today))
                .forEach(task -> task.printTask(out));
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
