package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;

public class DeleteCommand extends TaskTargetingCommand {

    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        String id = arguments.getArgumentString();

        if (!deleteTask(id)) {
            taskNotFoundHandler(output, id).run();
        }
    }

    private boolean deleteTask(String id) {
        return registry.getAll().stream().anyMatch(project -> project.removeTask(id));
    }

    @Override
    public String name() {
        return "delete";
    }
}