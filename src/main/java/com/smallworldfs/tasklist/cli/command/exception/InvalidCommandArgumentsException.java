package com.smallworldfs.tasklist.cli.command.exception;

import com.smallworldfs.tasklist.cli.command.Command;

public class InvalidCommandArgumentsException extends CommandExecutionException {

    private final Command<?> command;

    public InvalidCommandArgumentsException(Command<?> command) {
        super("Invalid command format.");
        this.command = command;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        String help = command.help();

        if (help != null) {
            message += " Correct command syntax is \"" + help + "\"";
        }
        return message;
    }
}
