package com.codurance.training.tasks;

import java.time.LocalDate;

public class Deadline {

    private LocalDate date;

    public Deadline(String date) {
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString(){
        return String.format("outdated at %s", date.toString());
    }
}
