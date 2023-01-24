package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.Task;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;

public class CheckCommand extends TaskTargetingCommand {

    @Override
    public void run(Arguments arguments, Output output) {
        runOnTask(output, Task::check, arguments.getArgumentString());
    }

    @Override
    public String name() {
        return "check";
    }
}
