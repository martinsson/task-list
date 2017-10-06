package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Tasks {
    final Map<String, List<Task>> tasks = new LinkedHashMap<String, List<Task>>();
    private PrintWriter out;

    public Tasks(PrintWriter out) {
        this.out = out;
    }

    public void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }

    Stream<Task> getAllTasks() {
        return tasks.entrySet().stream().flatMap((project -> project.getValue().stream()));
    }

    Task findTask(TaskId id, TaskList taskList) {
        return getAllTasks()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    void deadline(TaskDeadline taskDeadline, TaskList taskList) {
        Task task = findTask(taskDeadline.id, taskList);
        task.setDeadline(taskDeadline.deadline());
    }

    void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
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

    void addTaskWithId(String project, Task task, TaskList taskList) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        projectTasks.add(task);
    }

    void setDone(TaskId taskId, boolean done, TaskList taskList) {
        Task tsk = findTask(taskId, taskList);
        if (tsk == null) {
            out.printf("Could not find a task with an ID of %s.", taskId);
            out.println();
        } else {
            tsk.setDone(done);
        }
    }

    void check(TaskId taskId, TaskList taskList) {
        setDone(taskId, true, taskList);
    }

    void uncheck(TaskId taskId, TaskList taskList) {
        setDone(taskId, false, taskList);
    }
}