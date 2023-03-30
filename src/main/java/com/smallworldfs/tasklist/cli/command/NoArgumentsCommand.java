package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.arguments.NullArgumentsParser;
import com.smallworldfs.tasklist.cli.io.Output;
import lombok.Getter;

public abstract class NoArgumentsCommand implements Command<Void> {

    @Getter
    private final ArgumentParser<Void> argumentParser = new NullArgumentsParser();

    @Override
    public void run(Void arguments, Output output) {
        run(output);
    }

    public abstract void run(Output output);
}
