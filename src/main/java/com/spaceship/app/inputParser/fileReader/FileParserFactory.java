package com.spaceship.app.inputParser.fileReader;

public final class FileParserFactory {

    private static final FileParser PARSER = new FileParserImpl();

    private FileParserFactory() {
        //no construction
    }


    public static FileParser get() {
        return PARSER;
    }
}
