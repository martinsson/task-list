package com.codurance.training.tasks;

import java.io.PrintWriter;

public class ProjectNotFoundSerializer implements ProjectNameSerializer {
    private PrintWriter out;

    public ProjectNotFoundSerializer(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void serialize(String name) {
        out.printf("Could not find a project with the name \"%s\".", name);
        out.println();
    }

}
