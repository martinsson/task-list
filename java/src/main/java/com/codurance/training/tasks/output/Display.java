package com.codurance.training.tasks.output;

import com.codurance.training.tasks.ProjectId;
import com.codurance.training.tasks.Task;

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
        // TODO roundrip again to ProjectId and back
        out.println(id);
    }

    public void display(Task task) {
        task.printTask(out);
    }

    public void endSection() {
        out.println();
    }
}
