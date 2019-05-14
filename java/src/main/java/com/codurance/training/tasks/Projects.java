package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.System.out;

public class Projects {
    private final Map<ProjectName, Project> projects = new LinkedHashMap<ProjectName, Project>();
    private long lastId = 0;
    private PrintWriter out;

    public Projects(PrintWriter out) {
        this.out = out;
    }

    public void addProject(ProjectName projectName) {
        projects.put(projectName, new Project(projectName));
    }

    public void addTask(ProjectName projectName, String description) {
        Project project = projects.get(projectName);
        if (project == null) {
            out.printf("Could not find a project with the name \"%s\".", projectName);
            out.println();
            return;
        }
        project.add(new Task(nextId(), description, false));
    }

    private TaskId nextId() {
        return new TaskId(++lastId);
    }

    public void setTaskDone(TaskId id, boolean done) {
        for (Map.Entry<ProjectName, Project> projectEntry : projects.entrySet()) {
            for (Task task : projectEntry.getValue().getTasks()) {
                if (task.matches(id)) {
                    if (done) {
                        task.done();
                    } else {
                        task.undone();
                    }
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id.id);
        out.println();
    }

    public void serialize(ProjectSerializer projectSerializer) {
        projects.values().forEach(project -> project.serialize(projectSerializer));
    }
}
