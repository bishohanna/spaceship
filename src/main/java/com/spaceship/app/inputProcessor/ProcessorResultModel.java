package com.spaceship.app.inputProcessor;

import com.spaceship.app.inputProcessor.romanCalculator.nodes.RomanValue;
import com.spaceship.tools.baseChain.ChainModel;

import java.util.Map;

public class ProcessorResultModel implements ChainModel {


    private Map<String, RomanValue> definitionsMap;
    private Map<String, Float> missingValuesMap;
    private Map<String, String> questionsAndAnswersMap;


    public Map<String, RomanValue> getDefinitionsMap() {
        return this.definitionsMap;
    }

    public void setDefinitionsMap(Map<String, RomanValue> definitionsMap) {
        this.definitionsMap = definitionsMap;
    }

    public Map<String, Float> getMissingValuesMap() {
        return this.missingValuesMap;
    }

    public void setMissingValuesMap(Map<String, Float> missingValuesMap) {
        this.missingValuesMap = missingValuesMap;
    }

    public void setQuestionsAndAnswersMap(Map<String, String> questionsAndAnswersMap) {
        this.questionsAndAnswersMap = questionsAndAnswersMap;
    }

    public Map<String, String> getQuestionsAndAnswersMap() {
        return this.questionsAndAnswersMap;
    }
}
