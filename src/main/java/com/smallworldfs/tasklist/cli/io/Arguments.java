package com.smallworldfs.tasklist.cli.io;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Arguments {

    private final String argumentString;
    private final String commandLine;

    public Arguments(String argumentString) {
        this.argumentString = argumentString;
        this.commandLine = argumentString;
    }

    public String[] splitIn(int arguments) {
        return argumentString.split(" ", arguments);
    }
}
