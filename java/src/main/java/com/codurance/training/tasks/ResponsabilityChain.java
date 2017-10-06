package com.codurance.training.tasks;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResponsabilityChain {

    private LinkedList<Command> commands;

    public ResponsabilityChain(List<Command> commands) {
        this.commands = new LinkedList<>(commands);
    }

    public void handle() {

        if (commands.isEmpty()) {
            return;
        } else {
            Command command = commands.removeFirst();
            command.handle(/*this::handle*/);
        }


    }
}
