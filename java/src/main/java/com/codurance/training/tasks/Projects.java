package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.*;

public class Projects {
    private final Map<ProjectId, Project> projects = new LinkedHashMap<>();
    private final PrintWriter out;

    Projects(PrintWriter out) {
        this.out = out;
    }

    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    void deadline(TaskDeadline taskDeadline) {
        projects.values().stream()
                .forEach(project -> project.setDeadLineIfExists(taskDeadline));
    }

    void show() {
        for (Project project : projects.values()) {
            project.show(this.out);
        }
    }

    void today() {
        MyDate today = new MyDate("21/09/2017");
        projects.values().stream()
                .forEach(project-> project.showToday(today, out));

    }

    public void addTaskWithId(ProjectId projectId, Task task) {
        Project project = projects.get(projectId);
        if (project == null) {
            out.printf("Could not find a project with the name \"%s\".", projectId);
            out.println();
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
        projects.values().stream()
                .forEach(project->project.deleteIfExists(taskId));
    }

    private void setDone(TaskId taskId, boolean done) {
        projects.values().stream()
                .forEach(project -> project.setDoneIfExists(taskId, done));
    }

    public void viewByDate() {

    }
}