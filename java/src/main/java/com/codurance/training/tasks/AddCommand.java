package com.codurance.training.tasks;

import com.codurance.training.tasks.domain.*;
import com.codurance.training.tasks.output.Display;

public class AddCommand implements ExecutableCommand {
    private final AddCommand.IdGenerator idGenerator;
    private String addCommandLine;
    private Projects projects;
    private Display display;

    public AddCommand(String addCommandLine, Projects projects, Display display, IdGenerator idGenerator) {
        this.addCommandLine = addCommandLine;
        this.projects = projects;
        this.display = display;
        this.idGenerator = idGenerator;

    }

    public void execute() {
        String command = splitInParts(2)[0];
        String[] parts;
        ProjectId projectId;
        switch (command) {
            case "project":
                parts = splitInParts(2);
                projects.addProject(new Project(getProjectId(parts[1])));
                break;
            case "task":
                parts = splitInParts(3);
                projectId = getProjectId(parts[1]);
                TaskId taskId = new TaskId(idGenerator.nextId());
                String description = splitInParts(3)[2];

                Task task = new Task(taskId, description);
                projects.addTaskWithId(projectId, task);
                break;
            case "task-with-id":
                String[] parts1 = splitInParts(4);
                projectId = getProjectId(parts1[1]);
                String description1 = parts1[3];
                TaskId taskId1 = new TaskId(parts1[2]);

                Task task1 = new Task(taskId1, description1);
                projects.addTaskWithId(projectId, task1);
                break;


        }
    }


    public static class IdGenerator {
        private long lastId = 0;

        public IdGenerator() {
        }

        public long nextId() {
            return ++lastId;
        }
    }


    private ProjectId getProjectId(String part) {
        return new ProjectId(part);
    }

    private String[] splitInParts(int nbParts) {
        return addCommandLine.split(" ", nbParts);
    }


}
