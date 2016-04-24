package com.spaceship.app.inputParser.fileReader;

import com.spaceship.tools.exception.SpaceShipException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

final class FileParserImpl implements FileParser {


    @Override
    public List<String> parse(String filePath) {

        if (filePath == null || filePath.isEmpty()) {
            throw new SpaceShipException("Can't read file from the given path, please pass a valid pass !");
        }

        try {

            BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));

            List<String> result = new ArrayList<>();

            String line = null;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }

            return result;

        } catch (Exception ex) {
            throw new SpaceShipException(String.format("Error reading file from the given path, due to [%s]", ex.getMessage()));
        }
    }
}
