package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.HelpCommand;
import com.smallworldfs.tasklist.cli.UnknownCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
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
import java.util.Optional;

public class CommandParser {

    private static final String EMPTY = "";
    private static final Command UNKNOWN_COMMAND = new UnknownCommand();

    private final List<Command> commands = List.of(
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

    public ParsedCommand parse(String line) {
        String[] commandLine = line.trim().split(" ", 2);
        Arguments arguments = new Arguments(getArgumentString(commandLine), line);

        return parse(arguments);
    }

    private ParsedCommand parse(Arguments arguments) {
        return new ParsedCommand(findCommand(arguments.getCommandLine()).orElse(UNKNOWN_COMMAND), arguments);
    }

    private Optional<Command> findCommand(String commandLine) {
        return commands.stream().filter(command -> command.getMatcher().matches(commandLine)).findFirst();
    }

    private String getArgumentString(String[] commandLine) {
        if (commandLine.length == 1) {
            return EMPTY;
        }
        return commandLine[1];
    }
}
