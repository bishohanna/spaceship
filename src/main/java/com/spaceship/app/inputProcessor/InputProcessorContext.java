package com.spaceship.app.inputProcessor;

import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.app.inputProcessor.nodes.DefinitionsProcessorNode;
import com.spaceship.app.inputProcessor.nodes.MissingValuesProcessorNode;
import com.spaceship.app.inputProcessor.nodes.QuestionsEvaluatorNode;
import com.spaceship.tools.baseChain.AbstractChainContext;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.Arrays;
import java.util.List;

final class InputProcessorContext extends AbstractChainContext<ProcessorResultModel, InputParserModel> implements InputProcessor {


    public InputProcessorContext() {
        super(ProcessorResultModel.class);
    }

    @Override
    public ProcessorResultModel process(InputParserModel inputParserModel) {
        return handle(inputParserModel);
    }

    @Override
    protected List<ChainNode<ProcessorResultModel, InputParserModel>> getChainNodes() {
        return Arrays.asList(new ChainNode[]{
                new DefinitionsProcessorNode(),
                new MissingValuesProcessorNode(),
                new QuestionsEvaluatorNode()
        });
    }
}
