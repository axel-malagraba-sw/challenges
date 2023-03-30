package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.HelpCommand;
import com.smallworldfs.tasklist.project.crud.AddProjectCommand;
import com.smallworldfs.tasklist.task.completion.CheckCommand;
import com.smallworldfs.tasklist.task.completion.UncheckCommand;
import com.smallworldfs.tasklist.task.crud.AddTaskCommand;
import com.smallworldfs.tasklist.task.crud.DeleteCommand;
import com.smallworldfs.tasklist.task.crud.RenameCommand;
import com.smallworldfs.tasklist.task.crud.ShowCommand;
import com.smallworldfs.tasklist.task.timeline.DeadlineCommand;
import com.smallworldfs.tasklist.task.timeline.TodayCommand;
import java.util.List;
import java.util.stream.Stream;

public class Commands {

    private final List<Command<?>> commands = List.of(
            new ShowCommand(),
            new AddProjectCommand(),
            new AddTaskCommand(),
            new CheckCommand(),
            new UncheckCommand(),
            new DeadlineCommand(),
            new TodayCommand(),
            new HelpCommand(this::stream),
            new RenameCommand(),
            new DeleteCommand());

    public Stream<Command<?>> stream() {
        return commands.stream();
    }
}
