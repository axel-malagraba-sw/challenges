package com.smallworldfs.tasklist.task.completion;

import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;

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
