package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.HelpCommand;
import com.smallworldfs.tasklist.cli.UnknownCommand;
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

public class CommandParser {

    private static final Command<?> UNKNOWN_COMMAND = new UnknownCommand();

    private final List<Command<?>> commands = List.of(
            new AddProjectCommand(),
            new DeadlineCommand(),
            new TodayCommand(),
            new CheckCommand(),
            new UncheckCommand(),
            new AddTaskCommand(),
            new ShowCommand(),
            new HelpCommand(),
            new RenameCommand(),
            new DeleteCommand());

    public <T> ParsedCommand<T> parse(String line) {
        Command<T> command = findCommand(line);
        T arguments = command.getArgumentParser().parse(line);

        return new ParsedCommand<>(command, arguments);
    }

    @SuppressWarnings("unchecked")
    private <T> Command<T> findCommand(String commandLine) {
        return (Command<T>) commands.stream()
                .filter(command -> command.getMatcher().matches(commandLine))
                .findFirst()
                .orElse(UNKNOWN_COMMAND);
    }
}
