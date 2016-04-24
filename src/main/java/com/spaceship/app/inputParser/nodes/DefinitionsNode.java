package com.spaceship.app.inputParser.nodes;

import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.List;

public class DefinitionsNode implements ChainNode<InputParserModel, List<String>> {

    private final static String SPACE = " ";

    @Override
    public void process(InputParserModel model, List<String> strings) {

        strings.stream()
                .filter(s -> evaluateExpression(s))
                .forEach(s -> model.addDefinition(s));
    }

    private boolean evaluateExpression(String expression) {

        String[] split = expression.toLowerCase().trim().split(SPACE);

        return "is".equals(split[1]) && split.length == 3;
    }
}
