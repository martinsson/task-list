package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInput {
    final BufferedReader in;

    public UserInput(BufferedReader reader) {
        in = reader;
    }

    String getInput() throws IOException {
        return in.readLine();
    }
}