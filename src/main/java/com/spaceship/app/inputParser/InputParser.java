package com.spaceship.app.inputParser;

public interface InputParser {

    /**
     * Parses file to input model
     *
     * @param filePath input to parse
     * @return parsed model
     */
    InputParserModel parseFile(String filePath);

}
