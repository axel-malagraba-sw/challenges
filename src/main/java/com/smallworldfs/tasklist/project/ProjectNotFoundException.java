package com.smallworldfs.tasklist.project;

import com.smallworldfs.tasklist.cli.command.exception.CommandExecutionException;

public class ProjectNotFoundException extends CommandExecutionException {

    public ProjectNotFoundException(String projectName) {
        super(String.format("Could not find a project with the name \"%s\".", projectName));
    }
}
