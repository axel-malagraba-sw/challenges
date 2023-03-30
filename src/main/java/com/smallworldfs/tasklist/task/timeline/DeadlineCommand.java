package com.smallworldfs.tasklist.task.timeline;

import static com.smallworldfs.tasklist.task.timeline.DeadlineCommand.TaskDeadline;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskTargetingCommand;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.function.Consumer;
import lombok.Getter;

public class DeadlineCommand extends TaskTargetingCommand<TaskDeadline> {

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("deadline");

    @Override
    public void run(TaskDeadline taskDeadline, Output output) {
        runOnTask(taskDeadline, taskDeadline.taskId());
    }

    @Override
    public ArgumentParser<TaskDeadline> getArgumentParser() {
        return commandLine -> {
            String[] split = commandLine.splitIntoChunks(3, help());

            return new TaskDeadline(split[1], parseDate(split[2]));
        };
    }

    @Override
    public String help() {
        return "deadline <task ID> <date yyyy-mm-dd>";
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandArgumentsException("Invalid date format. Provided date must match yyyy-mm-dd.");
        }
    }

    record TaskDeadline(String taskId, LocalDate deadline) implements Consumer<Task> {

        @Override
        public void accept(Task task) {
            task.setDeadline(deadline);
        }
    }
}
