package com.smallworldfs.tasklist.command;

import com.smallworldfs.tasklist.Task;
import com.smallworldfs.tasklist.TaskRegistry;
import com.smallworldfs.tasklist.io.Arguments;
import com.smallworldfs.tasklist.io.Output;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddCommand implements Command {

    private long lastId = 0;

    private final Map<String, List<Task>> tasks = TaskRegistry.getInstance().getTasks();

    @Override
    public void run(Arguments arguments, Output output) {
        String[] subcommandRest = arguments.getArgumentString().split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1], output);
        }
    }

    private void addProject(String name) {
        tasks.put(name, new ArrayList<>());
    }

    private void addTask(String project, String description, Output output) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            output.writeFormatted("Could not find a project with the name \"%s\".", project);
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private long nextId() {
        return ++lastId;
    }

    @Override
    public String name() {
        return "add";
    }
}
