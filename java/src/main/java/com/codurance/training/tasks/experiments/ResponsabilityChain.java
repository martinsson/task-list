package com.codurance.training.tasks.experiments;

import com.codurance.training.tasks.commands.Command;

import java.util.LinkedList;
import java.util.List;

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
//            command.handle(/*this::handle*/cmdLine);
        }


    }
}
