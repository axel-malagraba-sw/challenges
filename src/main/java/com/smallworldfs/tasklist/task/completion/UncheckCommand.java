package com.smallworldfs.tasklist.task.completion;

import com.smallworldfs.tasklist.cli.command.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import lombok.Getter;

public class UncheckCommand extends TaskTargetingCommand {

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("uncheck");

    @Override
    public void run(Arguments arguments, Output output) {
        runOnTask(output, Task::uncheck, arguments.getArgumentString());
    }
}
