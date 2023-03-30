package com.smallworldfs.tasklist.task.completion;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.SingleStringArgumentParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskTargetingCommand;
import lombok.Getter;

public class CheckCommand extends TaskTargetingCommand<String> {

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("check");
    @Getter
    private final ArgumentParser<String> argumentParser = new SingleStringArgumentParser(this::help);

    @Override
    public void run(String taskId, Output output) {
        runOnTask(Task::check, taskId);
    }

    @Override
    public String help() {
        return "check <task ID>";
    }
}
