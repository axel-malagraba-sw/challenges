package com.smallworldfs.tasklist.io;

public interface Output {

    void write(String text);

    default void writeFormatted(String text, Object... parameters) {
        write(String.format(text, parameters));
        newLine();
    }

    default void writeln(String text) {
        write(text);
        newLine();
    }

    default void newLine() {
        write(System.lineSeparator());
    }
}
