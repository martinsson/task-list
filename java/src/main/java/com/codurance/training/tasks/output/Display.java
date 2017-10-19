package com.codurance.training.tasks.output;

import com.codurance.training.tasks.ExecutableCommand;
import com.codurance.training.tasks.domain.ProjectId;
import com.codurance.training.tasks.domain.Task;
import com.codurance.training.tasks.domain.TaskId;

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
        out.printf("Could not find a task with an ID of %s.", taskId);
        out.println();
    }

    public void printTask(boolean done, String taskId, String description) {
        out.printf("    [%c] %s: %s%n", (done ? 'x' : ' '), taskId, description);
    }

    public void help(ExecutableCommand taskList) {
        out.println();
        out.println();
        out.println();
        out.println();
        out.println();
        out.println();
        out.println();
    }

    public void commandNotFound(String mainCommand) {
        out.printf("I don't know what the command \"%s\" is.", mainCommand);
        out.println();

    }

    public void promptForInput() {
        out.print("> ");
        out.flush();
    }
}
