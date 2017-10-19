package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.input.OldCmdLine;

public interface Command  {
    boolean canHandle(OldCmdLine cmdLine);

    void handle(OldCmdLine cmdLine);

}
