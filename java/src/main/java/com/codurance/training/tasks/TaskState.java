package com.codurance.training.tasks;

public enum TaskState {
    TODO, DONE;

    public char serialize() {
        return (this == DONE ? 'x' : ' ');
    }
}
