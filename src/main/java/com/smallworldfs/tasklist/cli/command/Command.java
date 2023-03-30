package com.smallworldfs.tasklist.cli.command;

import com.smallworldfs.tasklist.cli.command.arguments.ArgumentParser;
import com.smallworldfs.tasklist.cli.command.match.CommandMatcher;
import com.smallworldfs.tasklist.cli.io.Output;

public interface Command<T> {

    void run(T arguments, Output output);

    CommandMatcher getMatcher();

    ArgumentParser<T> getArgumentParser();
}
