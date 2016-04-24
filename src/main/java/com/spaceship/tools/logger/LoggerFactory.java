package com.spaceship.tools.logger;

public final class LoggerFactory {

    private final static Logger LOGGER = new LoggerImpl();

    private LoggerFactory() {
        //no construction allowed
    }

    public static Logger get() {
        return LOGGER;
    }
}
