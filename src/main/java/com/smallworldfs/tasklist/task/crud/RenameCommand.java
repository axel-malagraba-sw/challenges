package com.smallworldfs.tasklist.task.crud;

import static com.smallworldfs.tasklist.task.crud.RenameCommand.TaskRenameRequest;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import com.smallworldfs.tasklist.task.TaskTargetingCommand;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import lombok.Getter;

public class RenameCommand extends TaskTargetingCommand<TaskRenameRequest> {

    private static final Pattern VALID_ID_PATTERN = compile("^\\d*[a-z][a-z\\d]+$", CASE_INSENSITIVE);
    private static final String INVALID_ID_MESSAGE =
            "New id '%s' is not valid. It must be an alphanumeric string containing at least one character.";

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("rename");

    @Override
    public void run(TaskRenameRequest request, Output output) {
        if (isInvalid(request.newId())) {
            throw new InvalidCommandArgumentsException(INVALID_ID_MESSAGE, request.newId());
        }
        runOnTask(request, request.currentId());
    }

    @Override
    public ArgumentParser<TaskRenameRequest> getArgumentParser() {
        return commandLine -> {
            String[] split = commandLine.splitIntoChunks(3, help());

            return new TaskRenameRequest(split[1], split[2]);
        };
    }

    private boolean isInvalid(String newId) {
        return !VALID_ID_PATTERN.matcher(newId).matches();
    }

    @Override
    public String help() {
        return "rename <task ID> <new task ID>";
    }

    record TaskRenameRequest(String currentId, String newId) implements Consumer<Task> {

        @Override
        public void accept(Task task) {
            task.setId(newId);
        }
    }
}
