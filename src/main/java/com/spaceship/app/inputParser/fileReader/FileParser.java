package com.spaceship.app.inputParser.fileReader;

import java.util.List;

/**
 * Reads file lines to list of Strings
 */
public interface FileParser {


    /**
     * Reads file from path to Strings representing its lines
     *
     * @param filePath file path
     * @return lines of file as list of string
     */
    List<String> parse(String filePath);

}
