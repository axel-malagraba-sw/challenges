package com.smallworldfs.tasklist.task.timeline;

import com.smallworldfs.tasklist.cli.command.TaskTargetingCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Task;
import java.time.LocalDate;
import java.util.function.Consumer;
import lombok.Data;

public class DeadlineCommand extends TaskTargetingCommand {

    @Override
    public void run(Arguments arguments, Output output) {
        TaskDeadline taskDeadline = new TaskDeadline(arguments);

        runOnTask(output, taskDeadline, taskDeadline.getTaskId());
    }

    @Override
    public String name() {
        return "deadline";
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
