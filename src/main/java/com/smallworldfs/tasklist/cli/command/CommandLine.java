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

    public <T> String getTrailingStringAtIndexForCommand(int index, Command<T> command) {
        return splitIntoChunksForCommand(index + 1, command)[index];
    }
}
