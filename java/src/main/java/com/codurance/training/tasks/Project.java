package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.*;

class Project {
    Map<TaskId, Task> tasksmap = new HashMap<>();
    private String id;

    Project(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    void add(Task task) {
        tasksmap.put(task.getId(), task);
    }

    void show(PrintWriter out) {
        out.println(id);
        for (Task task : tasksmap.values()) {
            task.printTask(out);
        }
        out.println();
    }

    public void deleteIfExists(TaskId taskId) {
        tasksmap.remove(taskId);
    }
}
