package com.codurance.training.tasks;

import java.io.PrintWriter;

public class PrintTaskIdSerializer implements TaskIdSerializer {
    private PrintWriter out;

    public PrintTaskIdSerializer(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void serialize(long id) {
        out.printf("%d", id);
    }
}
