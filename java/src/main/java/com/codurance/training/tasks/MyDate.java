package com.codurance.training.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyDate implements Comparable<MyDate>{
    private String date;

    public MyDate(String ddmmyyyy) {

        this.date = ddmmyyyy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyDate myDate = (MyDate) o;

        return date != null ? date.equals(myDate.date) : myDate.date == null;
    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }

    @Override
    public int compareTo(MyDate other) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("ddMMuuuu");
        LocalDate self = LocalDate.parse(date, pattern);
        LocalDate otherDate = LocalDate.parse(other.date, pattern);
        return self.compareTo(otherDate);
    }
}
