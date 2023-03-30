package com.smallworldfs.tasklist.task.timeline;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.DefaultArgumentsParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskTargetingCommand;
import java.time.LocalDate;
import java.util.function.Consumer;
import lombok.Data;
import lombok.Getter;

public class DeadlineCommand extends TaskTargetingCommand<Arguments> {

    @Getter
    private final ArgumentParser<Arguments> argumentParser = new DefaultArgumentsParser();
    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("deadline");

    @Override
    public void run(Arguments arguments, Output output) {
        TaskDeadline taskDeadline = new TaskDeadline(arguments);

        runOnTask(taskDeadline, taskDeadline.getTaskId());
    }

    @Override
    public String help() {
        return "deadline <task ID> <date yyyy-mm-dd>";
    }

    @Data
    private static class TaskDeadline implements Consumer<Task> {

        private final String taskId;
        private final LocalDate deadline;

        public TaskDeadline(Arguments arguments) {
            String[] parts = arguments.splitIn(2);
            taskId = parts[0];
            deadline = LocalDate.parse(parts[1]);
        }

        @Override
        public void accept(Task task) {
            task.setDeadline(deadline);
        }
    }
}
