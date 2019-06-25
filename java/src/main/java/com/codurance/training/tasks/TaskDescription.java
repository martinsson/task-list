package com.codurance.training.tasks;

public class TaskDescription  {
    private String description;

    public TaskDescription(String description) {
        this.description = description;
    }

    public void serialize(TaskDescriptionSerializer serializer) {
        serializer.serialize(description);
    }
}
