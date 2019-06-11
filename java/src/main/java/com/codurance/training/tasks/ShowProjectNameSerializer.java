package com.codurance.training.tasks;

import java.io.PrintWriter;

public class ShowProjectNameSerializer implements ProjectNameSerializer {
    private PrintWriter out;

    public ShowProjectNameSerializer(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void serialize(String name) {
        out.println(name);
    }
}
