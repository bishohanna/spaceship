package com.spaceship.app.inputProcessor.nodes;

import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.app.inputProcessor.ProcessorResultModel;
import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculator;
import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculatorFactory;
import com.spaceship.app.inputProcessor.romanCalculator.nodes.RomanValue;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissingValuesProcessorNode implements ChainNode<ProcessorResultModel, InputParserModel> {

    private final static String SPACE = " ";
    private final RomanCalculator romanCalculator;

    public MissingValuesProcessorNode() {
        romanCalculator = RomanCalculatorFactory.get();
    }


    @Override
    public void process(ProcessorResultModel model, InputParserModel inputParserModel) {

        List<String> missingValues = inputParserModel.getMissingValues();

        Map<String, Float> missingValuesMap = new HashMap<>();

        missingValues.stream().forEach(v -> {

            String[] twoSides = v.split(" is ");

            String leftSide = twoSides[0];
            String[] leftSideSplit = leftSide.split(SPACE);

            String romanLeftSide = createRomanLeftString(leftSideSplit, model);
            String missingValue = leftSideSplit[leftSideSplit.length - 1];

            Float creditValue = Float.valueOf(twoSides[1].trim().split(SPACE)[0]);
            Float romanLeftValue = romanCalculator.calculateValue(romanLeftSide).getNumericResult();

            missingValuesMap.put(missingValue.toLowerCase(), (creditValue / romanLeftValue));


        });

        model.setMissingValuesMap(missingValuesMap);

    }

    private String createRomanLeftString(String[] leftSideSplit, ProcessorResultModel model) {

        StringBuilder romanBuilder = new StringBuilder();

        for (int i = 0; i < leftSideSplit.length - 1; i++) {

            RomanValue value = model.getDefinitionsMap().get(leftSideSplit[i]);
            romanBuilder.append(value.name());
        }

        return romanBuilder.toString();
    }
}
