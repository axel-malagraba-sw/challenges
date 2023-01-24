package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.ProjectRegistry;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;
import com.smallworldfs.tasklist.io.TaskWriter;

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
