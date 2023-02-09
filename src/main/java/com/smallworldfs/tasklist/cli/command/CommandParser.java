package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.HelpCommand;
import com.smallworldfs.tasklist.cli.UnknownCommand;
import com.smallworldfs.tasklist.cli.io.Arguments;
import com.smallworldfs.tasklist.task.completion.CheckCommand;
import com.smallworldfs.tasklist.task.completion.UncheckCommand;
import com.smallworldfs.tasklist.task.crud.AddCommand;
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
            new DeadlineCommand(),
            new TodayCommand(),
            new CheckCommand(),
            new UncheckCommand(),
            new AddCommand(),
            new ShowCommand(),
            new HelpCommand(),
            new RenameCommand());

    public ParsedCommand parse(String line) {
        String[] commandLine = line.trim().split(" ", 2);
        String commandName = commandLine[0];
        Arguments arguments = new Arguments(getArgumentString(commandLine), line);

        return parse(commandName, arguments);
    }

    private ParsedCommand parse(String commandName, Arguments arguments) {
        return new ParsedCommand(findCommand(commandName).orElse(UNKNOWN_COMMAND), arguments);
    }

    private Optional<Command> findCommand(String commandName) {
        return commands.stream().filter(command -> command.matches(commandName)).findFirst();
    }

    private String getArgumentString(String[] commandLine) {
        if (commandLine.length == 1) {
            return EMPTY;
        }
        return commandLine[1];
    }
}
