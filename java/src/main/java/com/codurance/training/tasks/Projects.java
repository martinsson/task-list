package com.codurance.training.tasks;

import com.codurance.training.tasks.output.Display;

import java.io.PrintWriter;
import java.util.*;

public class Projects {
    private final Map<ProjectId, Project> projects = new LinkedHashMap<>();
    private Display display;

    Projects(Display display) {
        this.display = display;
    }

    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    void deadline(TaskDeadline taskDeadline) {
        projects.values().forEach(
                project -> project.setDeadLineIfExists(taskDeadline)
        );
    }

    void show() {
        for (Project project : projects.values()) {
            project.show(display);
        }
    }

    void today() {
        MyDate today = new MyDate("21/09/2017");
        projects.values()
                .forEach(project-> project.showToday(today, display));

    }

    public void addTaskWithId(ProjectId projectId, Task task) {
        Project project = projects.get(projectId);
        if (project == null) {
            display.projectNotFound(projectId);
            return;
        }
        project.add(task);
    }

    void check(TaskId taskId) {
        setDone(taskId, true);
    }

    void uncheck(TaskId taskId) {
        setDone(taskId, false);
    }

    void delete(TaskId taskId) {
        projects.values()
                .forEach(project->project.deleteIfExists(taskId));
    }

    private void setDone(TaskId taskId, boolean done) {
        projects.values()
                .forEach(project -> project.setDoneIfExists(taskId, done, display));
    }

    public void viewByDate() {

    }
}