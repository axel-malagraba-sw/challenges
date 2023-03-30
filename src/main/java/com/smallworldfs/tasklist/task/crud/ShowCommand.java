package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.TaskWriter;
import lombok.Getter;

public class ShowCommand implements Command {

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("show");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        new TaskWriter(output, registry.getAll()).write();
    }
}
