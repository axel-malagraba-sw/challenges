package com.smallworldfs.tasklist.cli.command.exception;

public class CommandExecutionException extends RuntimeException {

    public CommandExecutionException(String message) {
        super(message);
    }
}
