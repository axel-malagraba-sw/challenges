package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.Task;
import com.smallworldfs.tasklist.TaskRegistry;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;
import java.util.List;
import java.util.Map;

public class UncheckCommand implements Command {

    private final Map<String, List<Task>> tasks = TaskRegistry.getInstance().getTasks();

    @Override
    public void run(Arguments arguments, Output output) {
        setDone(arguments.getArgumentString(), false, output);
    }

    private void setDone(String idString, boolean done, Output output) {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        output.writeFormatted("Could not find a task with an ID of %d.", id);
    }

    @Override
    public String name() {
        return "uncheck";
    }
}
