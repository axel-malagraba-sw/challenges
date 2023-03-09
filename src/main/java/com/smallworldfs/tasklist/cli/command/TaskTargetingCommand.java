package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.task.Project;
import com.smallworldfs.tasklist.task.ProjectRegistry;
import com.smallworldfs.tasklist.task.Task;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class TaskTargetingCommand implements Command {

    protected ProjectRegistry registry = ProjectRegistry.getInstance();


    protected void runOnTask(Output output, Consumer<Task> consumer, String id) {
        findTaskById(id).ifPresentOrElse(consumer, taskNotFoundHandler(output, id));
    }

    protected Optional<Task> findTaskById(String id) {
        return registry.getAll()
                .stream()
                .map(Project::getTasks)
                .flatMap(Collection::stream)
                .filter(task -> id.equals(task.getId()))
                .findFirst();
    }

    protected Runnable taskNotFoundHandler(Output output, String id) {
        return () -> output.writeFormatted("Could not find a task with an ID of %s.", id);
    }
}
