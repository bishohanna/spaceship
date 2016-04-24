package com.spaceship.tools.logger;

import java.io.PrintWriter;

final class LoggerImpl implements Logger {

    private final PrintWriter printWriter;

    LoggerImpl() {
        printWriter = new PrintWriter(System.out);
    }

    @Override
    public void logLine(String line) {

        if (line != null && !line.isEmpty()) {
            printWriter.println(line);
            printWriter.flush();
        }

    }
}
