package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.DefaultArgumentsParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import lombok.Getter;

public class DeleteCommand extends TaskTargetingCommand<Arguments> {

    @Getter
    private final ArgumentParser<Arguments> argumentParser = new DefaultArgumentsParser();
    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("delete");
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
}
