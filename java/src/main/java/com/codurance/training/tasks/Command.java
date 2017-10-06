package com.codurance.training.tasks;

public interface Command  {
    boolean canHandle();

    void handle();
}
