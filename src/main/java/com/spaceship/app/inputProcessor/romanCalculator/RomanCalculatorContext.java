package com.spaceship.app.inputProcessor.romanCalculator;

import com.spaceship.app.inputProcessor.romanCalculator.nodes.RomanValidatorNode;
import com.spaceship.app.inputProcessor.romanCalculator.nodes.RomanValueCalculatorNode;
import com.spaceship.tools.baseChain.AbstractChainContext;
import com.spaceship.tools.baseChain.ChainNode;
import com.spaceship.tools.exception.SpaceShipException;

import java.util.Arrays;
import java.util.List;

final class RomanCalculatorContext extends AbstractChainContext<RomanResultModel, String> implements RomanCalculator {

    public RomanCalculatorContext() {
        super(RomanResultModel.class);
    }

    @Override
    public RomanResultModel calculateValue(String romanString) {

        if (romanString == null || romanString.isEmpty()) {
            throw new SpaceShipException("Roman String to calculate can't be empty or null");
        }

        return handle(romanString.toLowerCase());
    }

    @Override
    protected List<ChainNode<RomanResultModel, String>> getChainNodes() {
        return Arrays.asList(new ChainNode[]{
                new RomanValidatorNode(),
                new RomanValueCalculatorNode()
        });
    }
}
