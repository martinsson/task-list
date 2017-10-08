package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.CommandLine;

public interface Command  {
    boolean canHandle(CommandLine cmdLine);

    void handle(CommandLine cmdLine);

}
