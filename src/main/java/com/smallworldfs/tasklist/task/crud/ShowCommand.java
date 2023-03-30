package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.NoArgumentsCommand;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.TaskWriter;
import lombok.Getter;

public class ShowCommand extends NoArgumentsCommand {

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("show");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Output output) {
        new TaskWriter(output, registry.getAll()).write();
    }
}
