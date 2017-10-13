package com.codurance.training.tasks;

import com.codurance.training.tasks.output.Display;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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
        setDone(taskId, true);
    }

    void uncheck(TaskId taskId) {
        setDone(taskId, false);
    }

    void delete(TaskId taskId) {
        projects.values()
                .forEach(project -> project.deleteIfExists(taskId));
    }

    private void setDone(TaskId taskId, boolean done) {
        Optional<Project> foundProject = projects.values().stream()
                .filter(project -> project.hasTask(taskId))
                .findFirst();
        ifPresent(foundProject)
            .then(project -> project.setDone(taskId, done, display))
            .otherwise(() -> display.taskNotFound(taskId));

    }

    public static <T> IfPresent<T> ifPresent(Optional<T> optional) {
        return ifPresent -> otherwise -> {
            if (optional.isPresent()) {
                ifPresent.accept(optional.get());
            } else {
                otherwise.run();
            }
        };
    }

    public interface Otherwise {
        void otherwise(Runnable action);
    }

    public interface IfPresent<T> {
        Otherwise then(Consumer<T> consumer);
    }
//
//    public static class OptionalOrElseDo<T> {
//        private Optional<T> optional;
//
//        public <T> OptionalOrElseDo(Optional<T> optional) {
//            this.optional = optional;
//        }
//
//        public static <T> OptionalOrElseDo either(Optional<T> optional) {
//            return new OptionalOrElseDo(optional);
//        }
//        public OptionalOrElseDo ifPresent(Consumer<T> consumer) {
//            return this;
//        }
//        public void orElseDo(Consumer<Void> consumer) {
//            consumer.
//        }
//
//    }

    public void viewByDate() {

    }
}