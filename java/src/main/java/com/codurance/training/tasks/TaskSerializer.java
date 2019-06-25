package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.codurance.training.tasks.TaskState.DONE;

public class TaskSerializer {
    private PrintWriter out;

    public TaskSerializer(PrintWriter out) {
        this.out = out;
    }

    public void serialize(TaskId id, TaskState state, TaskDescription description) {
        // hack for test method that listen for only one printf
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.printf("    [%c] ", (state == DONE ? 'x' : ' '));
        id.serialize(new PrintTaskIdSerializer(printWriter));
        description.serialize(new TaskDescriptionSerializer(printWriter));
        out.printf(stringWriter.toString());
    }
}
