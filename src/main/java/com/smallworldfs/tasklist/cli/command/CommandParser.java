package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.exception.CommandNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandParser {

    private final Commands commands;

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
                .orElseThrow(() -> new CommandNotFoundException(commandLine));
    }
}
