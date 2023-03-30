package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;

public record CommandLine(String raw) {

    public <T> String[] splitIntoChunksForCommand(int chunks, Command<T> command) {
        String[] result = raw.split(" ", chunks);

        if (result.length < chunks) {
            throw new InvalidCommandArgumentsException(command);
        }
        return result;
    }
}
