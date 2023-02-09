package com.smallworldfs.tasklist.task.crud;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.compile;

import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import lombok.Getter;

public class RenameCommand extends TaskTargetingCommand {

    private static final Pattern VALID_ID_PATTERN = compile("^\\d*[a-z][a-z\\d]+$", CASE_INSENSITIVE);
    private static final String INVALID_ID_MESSAGE =
            "New id '%s' is not valid. It must be an alphanumeric string containing at least one character.";

    @Override
    public void run(Arguments arguments, Output output) {
        TaskRenameRequest request = new TaskRenameRequest(arguments);

        if (isInvalid(request.getNewId())) {
            output.writeFormatted(INVALID_ID_MESSAGE, request.getNewId());
            return;
        }
        runOnTask(output, request, request.getCurrentId());
    }

    private boolean isInvalid(String newId) {
        return !VALID_ID_PATTERN.matcher(newId).matches();
    }

    @Override
    public String name() {
        return "rename";
    }

    @Getter
    private static class TaskRenameRequest implements Consumer<Task> {

        private final String currentId;
        private final String newId;

        public TaskRenameRequest(Arguments arguments) {
            String[] parts = arguments.splitIn(2);
            currentId = parts[0];
            newId = parts[1];
        }

        @Override
        public void accept(Task task) {
            task.setId(newId);
        }
    }
}
