package com.smallworldfs.tasklist.cli.command.arguments;

public class NullArgumentsParser implements ArgumentParser<Void> {

    @Override
    public Void parse(String commandLine) {
        return null;
    }
}
