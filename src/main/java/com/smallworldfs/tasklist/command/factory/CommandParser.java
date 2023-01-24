package com.smallworldfs.tasklist.command.factory;

import com.smallworldfs.tasklist.command.AddCommand;
import com.smallworldfs.tasklist.command.CheckCommand;
import com.smallworldfs.tasklist.command.Command;
import com.smallworldfs.tasklist.command.HelpCommand;
import com.smallworldfs.tasklist.command.ShowCommand;
import com.smallworldfs.tasklist.command.UncheckCommand;
import com.smallworldfs.tasklist.command.UnknownCommand;
import com.smallworldfs.tasklist.io.Arguments;
import java.util.List;

public class CommandParser {

    private static final String EMPTY = "";

    private static final ParsedCommand UNKNOWN_COMMAND = new ParsedCommand(new UnknownCommand(), null);

    private final List<Command> commands = List.of(
            new CheckCommand(),
            new UncheckCommand(),
            new AddCommand(),
            new ShowCommand(),
            new HelpCommand());

    public ParsedCommand parse(String line) {
        String[] commandLine = line.trim().split(" ", 2);
        String commandName = commandLine[0];
        Arguments arguments = new Arguments(getArgumentString(commandLine), line);

        return parse(commandName, arguments);
    }

    private ParsedCommand parse(String commandName, Arguments arguments) {
        return commands.stream()
                .filter(command -> command.matches(commandName))
                .findFirst()
                .map(command -> new ParsedCommand(command, arguments))
                .orElse(UNKNOWN_COMMAND);
    }

    private String getArgumentString(String[] commandLine) {
        if (commandLine.length == 1) {
            return EMPTY;
        }
        return commandLine[1];
    }
}
