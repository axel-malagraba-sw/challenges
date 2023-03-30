package com.smallworldfs.tasklist.cli.command.arguments;

import com.smallworldfs.tasklist.cli.io.Arguments;

public class DefaultArgumentsParser implements ArgumentParser<Arguments> {

    private static final String EMPTY = "";

    @Override
    public Arguments parse(String commandLine) {
        String[] splitLine = commandLine.trim().split(" ", 2);

        return new Arguments(getArgumentString(splitLine), commandLine);
    }

    private String getArgumentString(String[] splitLine) {
        if (splitLine.length == 1) {
            return EMPTY;
        }
        return splitLine[1];
    }
}
