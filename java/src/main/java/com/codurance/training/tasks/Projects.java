package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.codurance.training.tasks.TaskState.TODO;

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
            projectName.serialize(new ProjectNotFoundSerializer(out));
            return;
        }
        project.add(new Task(nextId(), description, TODO));
    }

    private TaskId nextId() {
        return new TaskId(++lastId);
    }

    public void setTaskDone(TaskId id, TaskState taskState) {
        Optional<Task> foundTask = projects.values().stream().flatMap(projet -> projet.findTask(id).stream()).findFirst();

        foundTask.map(task -> (Runnable) () -> {
            task.setState(taskState);
        }).orElse(() -> {
            id.serialize(new TaskNotFoundSerializer(out));
        }).run();
    }

    public void serialize(ProjectSerializer projectSerializer) {
        projects.values().forEach(project -> project.serialize(projectSerializer));
    }
}
