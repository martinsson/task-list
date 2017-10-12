package com.codurance.training.tasks.output;

import com.codurance.training.tasks.ProjectId;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskId;

import java.io.PrintWriter;

public class Display {
    private PrintWriter out;

    public Display(PrintWriter out) {

        this.out = out;
    }

    public void projectNotFound(ProjectId projectId) {
        out.printf("Could not find a project with the name \"%s\".", projectId);
        out.println();

    }

    public void projectId(ProjectId id) {
        out.println(id);
    }

    public void display(Task task) {
        task.printTask(this);
    }

    public void endSection() {
        out.println();
    }

    public void taskNotFound(TaskId taskId) {
        System.out.printf("Could not find a task with an ID of %s.", taskId);
        System.out.println();
    }

    public void printTask(boolean done, String taskId, String description) {
        out.printf("    [%c] %s: %s%n", (done ? 'x' : ' '), taskId, description);
    }
}
