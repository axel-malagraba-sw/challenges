package com.smallworldfs.tasklist.cli.command.exception;

public class InvalidCommandArgumentsException extends CommandExecutionException {

    public InvalidCommandArgumentsException(String message, Object... params) {
        super(String.format(message, params));
    }
}
