package com.smallworldfs.tasklist.task.completion;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.DefaultArgumentsParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskTargetingCommand;
import lombok.Getter;

public class UncheckCommand extends TaskTargetingCommand<Arguments> {

    @Getter
    private final ArgumentParser<Arguments> argumentParser = new DefaultArgumentsParser();
    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("uncheck");

    @Override
    public void run(Arguments arguments, Output output) {
        runOnTask(Task::uncheck, arguments.getArgumentString());
    }

    @Override
    public String help() {
        return "uncheck <task ID>";
    }
}
