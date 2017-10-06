package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Stream;

public class Projects {
    final Map<String, List<Task>> projects = new LinkedHashMap<String, List<Task>>();
    private PrintWriter out;

    Projects(PrintWriter out) {
        this.out = out;
    }

    void addProject(String name) {
        projects.put(name, new ArrayList<Task>());
    }

    void deadline(TaskDeadline taskDeadline) {
        getFirst(taskDeadline.id)
                .ifPresent(task -> task.defineDeadline(taskDeadline));
    }

    void show() {
        for (Map.Entry<String, List<Task>> project : projects.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                task.printTask(out);
            }
            out.println();
        }
    }

    void today() {
        MyDate today = new MyDate("21/09/2017");
        getAllTasks()
                .filter(task -> task.isDue(today))
                .forEach(task -> task.printTask(out));
    }

    void addTaskWithId(String project, Task task) {
        List<Task> projectTasks = projects.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(task);
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
        return projects.entrySet().stream().flatMap((project -> project.getValue().stream()));
    }



}