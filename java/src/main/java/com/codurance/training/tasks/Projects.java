package com.codurance.training.tasks;

import com.codurance.training.tasks.output.Display;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static com.codurance.training.tasks.java.lang.IfPresentDoOrElse.ifPresent;

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

    void check(TaskId taskId) {

        setDone(taskId, true, Task::markDone);
    }

    void uncheck(TaskId taskId) {
        setDone(taskId, false, Task::markUndone);
    }

    void delete(TaskId taskId) {
        projects.values()
                .forEach(project -> project.deleteIfExists(taskId));
    }

    private void setDone(TaskId taskId, boolean done, Consumer<Task> taskMarker) {
        Optional<Project> foundProject = projects.values().stream()
                .filter(project -> project.hasTask(taskId))
                .findFirst();
        ifPresent(foundProject)
            .then(project -> project.setDone(taskId, taskMarker, display))
            .otherwise(() -> display.taskNotFound(taskId));

    }


    public void viewByDate() {

    }
}