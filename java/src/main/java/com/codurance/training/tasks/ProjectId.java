package com.codurance.training.tasks;

public class ProjectId {
    public String projectId;

    public ProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectId projectId1 = (ProjectId) o;

        return projectId != null ? projectId.equals(projectId1.projectId) : projectId1.projectId == null;
    }

    @Override
    public int hashCode() {
        return projectId != null ? projectId.hashCode() : 0;
    }
}
