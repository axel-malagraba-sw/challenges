package com.smallworldfs.tasklist.task.crud;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.ProjectRegistry;
import com.smallworldfs.tasklist.task.TaskWriter;

public class ShowCommand implements Command {

    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(Arguments arguments, Output output) {
        new TaskWriter(output, registry.getAll()).write();
    }

    @Override
    public String name() {
        return "show";
    }
}
