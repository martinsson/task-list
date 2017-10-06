package com.codurance.training.tasks;

class AddTaskCommand implements Command {
    private TaskList taskList;
    private final IdGenerator idGenerator;
    private final String commandLine;

    public AddTaskCommand(TaskList taskList, String commandLine, IdGenerator idGenerator) {
        this.taskList = taskList;
        this.commandLine = commandLine;
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean canHandle() {
        return commandLine.matches("^task .*");
    }

    @Override
    public void handle() {
        String[] projectTask = commandLine.split(" ", 3);
        String generatedId = idGenerator.nextId() + "";
        final TaskId taskId = new TaskId(generatedId);
        String taskDescription = projectTask[2];
        String project = projectTask[1];
        taskList.tasks.addTaskWithId(project, new Task(taskId, taskDescription, false));
    }
}
