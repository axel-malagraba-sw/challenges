package com.smallworldfs.tasklist.cli.io;

import lombok.Data;

@Data
public class Arguments {

    private final String argumentString;
    private final String commandLine;

    public String[] splitIn(int arguments) {
        return argumentString.split(" ", arguments);
    }
}
