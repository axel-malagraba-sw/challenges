package com.smallworldfs.tasklist.task.crud;

import static com.smallworldfs.tasklist.task.crud.AddTaskCommand.NewTask;

import com.smallworldfs.tasklist.cli.command.Command;
import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.command.match.StartsWithCommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;
import com.smallworldfs.tasklist.project.ProjectRegistry;
import com.smallworldfs.tasklist.task.Task;
import lombok.Getter;

public class AddTaskCommand implements Command<NewTask> {

    private long lastId = 0;

    @Getter
    private final CommandMatcher matcher = new StartsWithCommandMatcher("add task");
    private final ProjectRegistry registry = ProjectRegistry.getInstance();

    @Override
    public void run(NewTask task, Output output) {
        registry.find(task.project()).addTask(new Task(nextId(), task.description()));
    }

    @Override
    public ArgumentParser<NewTask> getArgumentParser() {
        return commandLine -> {
            String[] chunks = commandLine.splitIntoChunksForCommand(4, this);

            return new NewTask(chunks[2], chunks[3]);
        };
    }

    private long nextId() {
        return ++lastId;
    }

    @Override
    public String help() {
        return "add task <project name> <task description>";
    }

    record NewTask(String project, String description) {
    }
}
