package com.spaceship.app.inputProcessor.nodes;

import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.app.inputProcessor.ProcessorResultModel;
import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculator;
import com.spaceship.app.inputProcessor.romanCalculator.RomanCalculatorFactory;
import com.spaceship.app.inputProcessor.romanCalculator.RomanResultModel;
import com.spaceship.tools.baseChain.ChainNode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionsEvaluatorNode implements ChainNode<ProcessorResultModel, InputParserModel> {

    private final static String EMPTY_STRING = "";
    private final static String SPACE = " ";
    private final static String BAD_RESPONSE = "I have no idea what you are talking about ";

    private final RomanCalculator romanCalculator;

    public QuestionsEvaluatorNode() {
        romanCalculator = RomanCalculatorFactory.get();
    }

    @Override
    public void process(ProcessorResultModel model, InputParserModel inputParserModel) {

        List<String> questions = inputParserModel.getQuestions();

        Map<String, String> questionsAnswersMap = new LinkedHashMap<>();

        questions.stream().forEach(q -> {

            String lowerCaseQuestion = q.toLowerCase().trim();

            if (lowerCaseQuestion.startsWith("how much")) {

                questionsAnswersMap.put(q, answerValueQuestion(lowerCaseQuestion, model));
            } else if (lowerCaseQuestion.startsWith("how many credits")) {

                questionsAnswersMap.put(q, answerCreditsQuestion(lowerCaseQuestion, model));
            }
        });

        model.setQuestionsAndAnswersMap(questionsAnswersMap);
    }

    /**
     * Answers questions that have missing values and credits calculations
     *
     * @param q     question string
     * @param model model containing definitions and missing values numeric equivalents
     * @return the answer if everything is valid, or a predefined bad response if something is not valid
     */
    private String answerCreditsQuestion(String q, ProcessorResultModel model) {

        String effectivePart = q
                .replace("how many credits", EMPTY_STRING)
                .replace(" is ", EMPTY_STRING)
                .replace("?", EMPTY_STRING).trim();

        String[] parts = effectivePart.split(SPACE);

        StringBuilder romanBuilder = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            String term = parts[i];

            if (model.getDefinitionsMap().containsKey(term)) {
                romanBuilder.append(model.getDefinitionsMap().get(term).name());
            } else {
                return BAD_RESPONSE; //no idea about this term !
            }
        }

        RomanResultModel romanPartResult = romanCalculator.calculateValue(romanBuilder.toString());

        if (!romanPartResult.isValid()) {
            return BAD_RESPONSE; //bad roman part !
        } else {
            Float romanValue = romanPartResult.getNumericResult();

            String missingValue = parts[parts.length - 1];
            if (model.getMissingValuesMap().containsKey(missingValue)) {

                return String.format("%s is %s Credit", effectivePart, (romanValue * model.getMissingValuesMap().get(missingValue)));
            } else {
                return BAD_RESPONSE; //missing value is undefined
            }
        }

    }

    /**
     * Answers questions that have only definitions mapping to roman
     *
     * @param q     question to answer
     * @param model model containing definitions mapping to roman values
     * @return the answer if all is valid, or bad response if something is not defined
     */
    private String answerValueQuestion(String q, ProcessorResultModel model) {

        String effectivePart = q
                .replace("how much", EMPTY_STRING)
                .replace(" is ", EMPTY_STRING)
                .replace("?", EMPTY_STRING).trim();

        String[] parts = effectivePart.split(SPACE);

        StringBuilder romanBuilder = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String term = parts[i];
            if (model.getDefinitionsMap().containsKey(term)) {
                romanBuilder.append(model.getDefinitionsMap().get(term).name());
            } else {
                return BAD_RESPONSE; //no idea about this term !
            }
        }

        RomanResultModel romanPartResult = romanCalculator.calculateValue(romanBuilder.toString());

        if (!romanPartResult.isValid()) {
            return BAD_RESPONSE; //bad roman part !
        } else {
            Float romanValue = romanPartResult.getNumericResult();

            return String.format("%s is %s", effectivePart, romanValue);
        }

    }
}
