package com.smallworldfs.tasklist.cli.command.exception;

public class CommandNotFoundException extends CommandExecutionException {

    public CommandNotFoundException(String commandLine) {
        super(String.format("Unknown command \"%s\". Use help to get a list of available commands.", commandLine));
    }
}
