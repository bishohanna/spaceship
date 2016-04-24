package com.spaceship.app.inputParser;

public final class InputParserFactory {

    private final static InputParser PARSER = new InputParserContext();

    private InputParserFactory() {
        //no construction
    }


    public static InputParser get() {
        return PARSER;
    }
}
