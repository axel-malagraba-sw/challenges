package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.Task;
import com.smallworldfs.tasklist.TaskRegistry;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;
import java.util.List;
import java.util.Map;

public class ShowCommand implements Command {

    private final Map<String, List<Task>> tasks = TaskRegistry.getInstance().getTasks();

    @Override
    public void run(Arguments arguments, Output output) {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            output.writeln(project.getKey());
            for (Task task : project.getValue()) {
                output.writeFormatted("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(),
                        task.getDescription());
            }
            output.newLine();
        }
    }

    @Override
    public String name() {
        return "show";
    }
}
