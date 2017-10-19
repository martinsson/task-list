package com.codurance.training.tasks.domain;

import com.codurance.training.tasks.output.Display;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Projects {
    private final Map<ProjectId, Project> projects = new LinkedHashMap<>();
    private Display display;

    public Projects(Display display) {
        this.display = display;
    }

    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    public void deadline(TaskDeadline taskDeadline) {
        projects.values().forEach(
                project -> project.setDeadLineIfExists(taskDeadline));
    }

    public void show() {
        for (Project project : projects.values()) {
            project.show(display);
        }
    }

    public void today() {
        MyDate today = new MyDate("2017-09-21");
        projects.values()
                .forEach(project -> project.showToday(today, display));

    }

    public void addTaskWithId(ProjectId projectId, Task task) {
        Project project = projects.get(projectId);
        if (project == null) {
            display.projectNotFound(projectId);
            return;
        }
        project.add(task);
    }

    public void check(TaskId taskId) {
        markTask(taskId, Task::markDone);
    }

    public void uncheck(TaskId taskId) {
        markTask(taskId, Task::markUndone);
    }

    public void delete(TaskId taskId) {
        projects.values()
                .forEach(project -> project.deleteIfExists(taskId));
    }

    private void markTask(TaskId taskId, Consumer<Task> taskMarker) {
        Function<Project, Runnable> setTaskDone = project -> () -> project.setDone(taskId, taskMarker, display);

        findProject(taskId)
                .map(setTaskDone)
                .orElse(() -> display.taskNotFound(taskId))
                .run();
    }

    private Optional<Project> findProject(TaskId taskId) {
        return projects.values().stream()
                .filter(project -> project.hasTask(taskId))
                .findFirst();
    }


    public void viewByDate() {

    }
}