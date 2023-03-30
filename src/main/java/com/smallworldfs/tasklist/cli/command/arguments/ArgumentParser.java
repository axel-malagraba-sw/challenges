package com.smallworldfs.tasklist.cli.command.arguments;

import com.smallworldfs.tasklist.cli.command.CommandLine;

public interface ArgumentParser<T> {

    T parse(CommandLine commandLine);
}
