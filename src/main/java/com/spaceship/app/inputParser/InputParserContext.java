package com.spaceship.app.inputParser;

import com.spaceship.app.inputParser.fileReader.FileParser;
import com.spaceship.app.inputParser.fileReader.FileParserFactory;
import com.spaceship.app.inputParser.nodes.DefinitionsNode;
import com.spaceship.app.inputParser.nodes.MissingValuesNode;
import com.spaceship.app.inputParser.nodes.QuestionsParserNode;
import com.spaceship.tools.baseChain.AbstractChainContext;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.Arrays;
import java.util.List;

final class InputParserContext extends AbstractChainContext<InputParserModel, List<String>> implements InputParser {

    private final FileParser fileParser;

    public InputParserContext() {
        super(InputParserModel.class);

        this.fileParser = FileParserFactory.get();
    }

    @Override
    public InputParserModel parseFile(String filePath) {

        //1 - read file and get lines
        List<String> lines = fileParser.parse(filePath);

        //2- Handle file input data
        return handle(lines);
    }

    @Override
    protected List<ChainNode<InputParserModel, List<String>>> getChainNodes() {
        return Arrays.asList(new ChainNode[]{
                new DefinitionsNode(),
                new MissingValuesNode(),
                new QuestionsParserNode()
        });
    }
}
