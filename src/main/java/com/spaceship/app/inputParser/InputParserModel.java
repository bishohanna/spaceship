package com.spaceship.app.inputParser;

import com.spaceship.tools.baseChain.ChainModel;

import java.util.ArrayList;
import java.util.List;

public class InputParserModel implements ChainModel {

    //carries questions to be answered
    private List<String> questions = new ArrayList<>();

    //carries word to roman definitions
    private List<String> definitions = new ArrayList<>();

    //carries sentences of missing values to calculate
    private List<String> missingValues = new ArrayList<>();


    public List<String> getDefinitions() {
        return this.definitions;
    }

    public List<String> getMissingValues() {
        return this.missingValues;
    }

    public List<String> getQuestions() {
        return this.questions;
    }

    public void addDefinition(String definition) {
        definitions.add(definition);
    }

    public void addQuestion(String question) {
        questions.add(question);
    }

    public void addMissingValue(String missingValue) {
        missingValues.add(missingValue);
    }
}
