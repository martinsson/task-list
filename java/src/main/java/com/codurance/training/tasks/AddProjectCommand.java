package com.codurance.training.tasks;

class AddProjectCommand implements Command {


    private TaskList taskList;
    private final String commandLine;

    public AddProjectCommand(TaskList taskList, String commandLine) {
        this.taskList = taskList;
        this.commandLine = commandLine;
    }

    @Override
    public boolean canHandle() {
        return commandLine.matches("^project .*");
    }

    @Override
    public void handle() {
        String project = commandLine.split(" ", 2)[1];
        taskList.tasks.addProject(project);
    }
}
