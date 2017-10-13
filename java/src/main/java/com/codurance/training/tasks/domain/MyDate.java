package com.codurance.training.tasks.domain;

import java.time.LocalDate;

public class MyDate implements Comparable<MyDate>{
    private String date;

    public MyDate(String yyyymmdd) {

        this.date = yyyymmdd;
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
        LocalDate self = LocalDate.parse(date);
        LocalDate otherDate = LocalDate.parse(other.date);
        return self.compareTo(otherDate);
    }
}
