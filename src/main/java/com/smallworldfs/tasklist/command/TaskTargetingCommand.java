package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.ProjectRegistry;
import com.smallworldfs.tasklist.Task;
import com.smallworldfs.tasklist.io.Output;
import java.util.function.Consumer;

public abstract class TaskTargetingCommand implements Command {

    protected ProjectRegistry registry = ProjectRegistry.getInstance();


    protected void runOnTask(Output output, Consumer<Task> consumer, String id) {
        registry.findTaskById(id).ifPresentOrElse(consumer, taskNotFoundHandler(output, id));
    }

    private Runnable taskNotFoundHandler(Output output, String id) {
        return () -> output.writeFormatted("Could not find a task with an ID of %s.", id);
    }
}
