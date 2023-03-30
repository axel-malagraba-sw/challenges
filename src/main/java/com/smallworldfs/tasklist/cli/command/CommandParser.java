package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.exception.CommandNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommandParser {

    private final Commands commands;

    public <T> ParsedCommand<T> parse(String line) {
        CommandLine commandLine = new CommandLine(line.trim());
        Command<T> command = findCommand(commandLine);
        T arguments = command.getArgumentParser().parse(commandLine);

        return new ParsedCommand<>(command, arguments);
    }

    @SuppressWarnings("unchecked")
    private <T> Command<T> findCommand(CommandLine commandLine) {
        return (Command<T>) commands.stream()
                .filter(command -> command.getMatcher().matches(commandLine))
                .findFirst()
                .orElseThrow(() -> new CommandNotFoundException(commandLine.raw()));
    }
}
