package com.smallworldfs.tasklist.task.completion;

import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;

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
