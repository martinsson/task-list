package com.codurance.training.tasks;

public class MyDate {
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
}
