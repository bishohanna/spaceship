package com.spaceship.app.inputProcessor.nodes;

import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.app.inputProcessor.ProcessorResultModel;
import com.spaceship.app.inputProcessor.romanCalculator.nodes.RomanValue;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefinitionsProcessorNode implements ChainNode<ProcessorResultModel, InputParserModel> {

    private final static String SPACE = " ";

    @Override
    public void process(ProcessorResultModel model, InputParserModel inputParserModel) {

        List<String> definitions = inputParserModel.getDefinitions();

        Map<String, RomanValue> romanValueMap = definitions.stream()
                .map(d -> d.split(SPACE))
                .collect(Collectors.toMap(d -> d[0], d -> RomanValue.valueOf(d[2].toUpperCase())));


        model.setDefinitionsMap(romanValueMap);
    }
}
