package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;

public class Projects {
    private final Map<String, Project> projects = new LinkedHashMap<>();
    private final PrintWriter out;

    Projects(PrintWriter out) {
        this.out = out;
    }

    void addProject(String name) {
        Project project = new Project(name);
        projects.put(project.getId(), project);
    }

    void deadline(TaskDeadline taskDeadline) {
        getFirst(taskDeadline.id)
                .ifPresent(task -> task.defineDeadline(taskDeadline));
    }

    void show() {
        for (Project project : projects.values()) {
            project.show(this.out);
        }
    }

    void today() {
        MyDate today = new MyDate("21/09/2017");
        getAllTasks()
                .filter(task -> task.isDue(today))
                .forEach(task -> task.printTask(out));
    }

    void addTaskWithId(String projectId, Task task) {
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

    private Optional<Task> getFirst(TaskId id) {
        return getAllTasks()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    private void setDone(TaskId taskId, boolean done) {
        Optional<Task> optionalTask = getFirst(taskId);
        if (optionalTask.isPresent()) {
            optionalTask.get().setDone(done);
        } else {
            out.printf("Could not find a task with an ID of %s.", taskId);
            out.println();
        }
    }

    private Stream<Task> getAllTasks() {
        return projects.values().stream()
                .flatMap((project -> project.tasksmap.values().stream()));
    }


    void delete(TaskId taskId) {
        projects.values().stream()
                .forEach(project->project.deleteIfExists(taskId));
    }
}