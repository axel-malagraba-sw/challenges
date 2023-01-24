package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.Task;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;

public class UncheckCommand extends TaskTargetingCommand {

    @Override
    public void run(Arguments arguments, Output output) {
        runOnTask(output, Task::uncheck, arguments.getArgumentString());
    }

    @Override
    public String name() {
        return "uncheck";
    }
}
