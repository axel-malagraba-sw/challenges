package com.smallworldfs.tasklist.task;

import com.smallworldfs.tasklist.cli.command.exception.CommandExecutionException;

public class TaskNotFoundException extends CommandExecutionException {

    public TaskNotFoundException(String taskId) {
        super(String.format("Could not find a task with an ID of %s.", taskId));
    }
}
