package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Projects {
    final Map<String, List<Task>> projects = new LinkedHashMap<String, List<Task>>();
    private PrintWriter out;

    public Projects(PrintWriter out) {
        this.out = out;
    }

    public void addProject(String name) {
        projects.put(name, new ArrayList<Task>());
    }

    Stream<Task> getAllTasks() {
        return projects.entrySet().stream().flatMap((project -> project.getValue().stream()));
    }

    Task findTask(TaskId id) {
        return getAllTasks()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    void deadline(TaskDeadline taskDeadline) {
        Task task = findTask(taskDeadline.id);
        task.setDeadline(taskDeadline.deadline());
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
        getAllTasks().filter(task -> task.isDue(today))
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

    void setDone(TaskId taskId, boolean done) {
        Task tsk = findTask(taskId);
        if (tsk == null) {
            out.printf("Could not find a task with an ID of %s.", taskId);
            out.println();
        } else {
            tsk.setDone(done);
        }
    }

    void check(TaskId taskId) {
        setDone(taskId, true);
    }

    void uncheck(TaskId taskId) {
        setDone(taskId, false);
    }
}