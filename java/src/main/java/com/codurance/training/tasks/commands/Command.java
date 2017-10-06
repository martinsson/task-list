package com.codurance.training.tasks.commands;

public interface Command  {
    boolean canHandle();

    void handle();

}
