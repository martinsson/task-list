package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.List;

public class ProjectSerializer {
    private final PrintWriter out;
    private TaskSerializer taskSerializer;
    private ShowProjectNameSerializer showProjectNameSerializer;

    public ProjectSerializer(PrintWriter out) {
        this.out = out;
        taskSerializer = new TaskSerializer(out);
        showProjectNameSerializer = new ShowProjectNameSerializer(out);
    }


    public void serialize(ProjectName projectName, List<Task> tasks) {
        projectName.serialize(showProjectNameSerializer);
        tasks.forEach(task -> task.serialize(taskSerializer));
        out.println();
    }
}
