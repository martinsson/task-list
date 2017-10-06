package com.codurance.training.tasks;

class AddTaskWithIdCommand implements Command {
    private TaskList taskList;
    private final String commandLine;

    public AddTaskWithIdCommand(TaskList taskList, String commandLine) {
        this.taskList = taskList;
        this.commandLine = commandLine;
    }

    @Override
    public boolean canHandle() {
        return commandLine.matches("^task-with-id .*");
    }

    @Override
    public void handle() {
        String[] projectTask = commandLine.split(" ", 4);
        final TaskId taskId = new TaskId(projectTask[2]);
        String taskDescription = projectTask[3];
        String project = projectTask[1];
        taskList.tasks.addTaskWithId(project, new Task(taskId, taskDescription, false));
    }
}
