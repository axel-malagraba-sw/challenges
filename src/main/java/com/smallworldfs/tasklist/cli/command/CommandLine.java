package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.exception.InvalidCommandArgumentsException;

public record CommandLine(String raw) {

    public String[] splitIntoChunks(int chunks, String example) {
        String[] result = raw.split(" ", chunks);

        if (result.length < chunks) {
            throw new InvalidCommandArgumentsException("Invalid command arguments." + getHelpMessage(example));
        }
        return result;
    }

    public String getTrailingStringAtIndexForCommand(int index, String example) {
        return splitIntoChunks(index + 1, example)[index];
    }

    private String getHelpMessage(String example) {
        if (example != null) {
            return "Correct command syntax is \"" + example + "\"";
        }
        return "";
    }
}
