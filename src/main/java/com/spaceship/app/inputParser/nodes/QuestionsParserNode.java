package com.spaceship.app.inputParser.nodes;

import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.List;

public class QuestionsParserNode implements ChainNode<InputParserModel, List<String>> {

    @Override
    public void process(InputParserModel model, List<String> strings) {
        strings.stream()
                .filter(s -> s.trim().endsWith("?"))
                .forEach(s -> model.addQuestion(s));
    }
}
