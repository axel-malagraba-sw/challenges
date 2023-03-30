package com.smallworldfs.tasklist.cli.command.arguments;

import com.smallworldfs.tasklist.cli.command.CommandLine;

public class NullArgumentsParser implements ArgumentParser<Void> {

    @Override
    public Void parse(CommandLine commandLine) {
        return null;
    }
}
