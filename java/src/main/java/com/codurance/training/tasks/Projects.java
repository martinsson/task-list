package com.codurance.training.tasks;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.codurance.training.tasks.TaskState.TODO;

public class Projects {
    private final Map<ProjectName, Project> projects = new LinkedHashMap<ProjectName, Project>();
    private long lastId = 0;
    private ProjectNotFoundSerializer projectNameSerializer;
    private TaskNotFoundSerializer taskIdSerializer;
    private ProjectSerializer projectSerializer;

    public Projects(ProjectSerializer projectSerializer, ProjectNotFoundSerializer projectNameSerializer, TaskNotFoundSerializer taskIdSerializer) {
        this.projectNameSerializer = projectNameSerializer;
        this.taskIdSerializer = taskIdSerializer;
        this.projectSerializer = projectSerializer;
    }

    public void addProject(ProjectName projectName) {
        projects.put(projectName, new Project(projectName));
    }

    public void addTask(ProjectName projectName, TaskDescription description) {
        Project project = projects.get(projectName);
        if (project == null) {
            projectName.serialize(projectNameSerializer);
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

            id.serialize(taskIdSerializer);
        }).run();
    }

    public void serialize(ProjectSerializer projectSerializer) {
        projects.values().forEach(project -> project.serialize(projectSerializer));
    }

    void show() {
        serialize(this.projectSerializer);
    }
}
