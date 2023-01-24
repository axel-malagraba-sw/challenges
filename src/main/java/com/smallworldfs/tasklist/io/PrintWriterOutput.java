package com.smallworldfs.tasklist.io;

import java.io.PrintWriter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PrintWriterOutput implements Output {

    private final PrintWriter writer;

    @Override
    public void write(String text) {
        writer.write(text);
    }

    @Override
    public void writeFormatted(String text, Object... parameters) {
        writer.printf(text, parameters);
    }
}
