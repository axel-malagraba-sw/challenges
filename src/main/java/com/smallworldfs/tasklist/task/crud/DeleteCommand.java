package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.TaskNotFoundException;
import lombok.Getter;

public class DeleteCommand implements Command<String> {

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("delete");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(String taskId, Output output) {
        if (!deleteTask(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
    }

    private boolean deleteTask(String id) {
        return registry.getAll().stream().anyMatch(project -> project.removeTask(id));
    }

    @Override
    public ArgumentParser<String> getArgumentParser() {
        return commandLine -> commandLine.getTrailingStringAtIndexForCommand(1, this);
    }

    @Override
    public String help() {
        return "delete <task ID>";
    }
}
