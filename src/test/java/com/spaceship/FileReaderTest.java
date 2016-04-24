package com.spaceship;

import com.spaceship.app.inputParser.fileReader.FileParser;
import com.spaceship.app.inputParser.fileReader.FileParserFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class FileReaderTest {

    private FileParser fileParser;

    @Before
    public void init() {
        fileParser = FileParserFactory.get();
    }


    @Test
    public void testReadInput() {

        URL filePath = this.getClass().getResource("/default_input.txt");

        List<String> lines = fileParser.parse(filePath.getFile());

        Assert.assertFalse("Lines shouldn't be null", lines == null);
        Assert.assertFalse("Lines shouldn't be empty", lines.isEmpty());
        Assert.assertEquals("Lines should be equal to", 12, lines.size());
    }

}
