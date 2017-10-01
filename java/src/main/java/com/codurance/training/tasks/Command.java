package com.codurance.training.tasks;

public interface Command extends MatchingCommand {
    boolean canHandle();
}
