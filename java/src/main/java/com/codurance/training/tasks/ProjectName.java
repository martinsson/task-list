package com.codurance.training.tasks;

import java.util.Objects;

public class ProjectName {
    private final String name;

    public ProjectName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectName that = (ProjectName) o;
        return name.equals(that.name);
    }

    @Override
    public String toString() {
        // TODO refactor suppress toString()
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //    public String getName() {
//        return name;
//    }
}
