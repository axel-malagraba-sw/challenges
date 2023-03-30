package com.smallworldfs.tasklist.project.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;

public class AddProjectCommand implements Command {

    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        addProject(arguments.getCommandLine().split(" ", 3)[2]);
    }

    private void addProject(String name) {
        registry.createProject(name);
    }

    @Override
    public String name() {
        return "add project";
    }
}
