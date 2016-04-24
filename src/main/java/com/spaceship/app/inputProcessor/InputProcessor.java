package com.spaceship.app.inputProcessor;

import com.spaceship.app.inputParser.InputParserModel;

public interface InputProcessor {

    /**
     * Processes input model and calculates missing values, and answers questions
     *
     * @param inputParserModel input model
     * @return processed result model
     */
    ProcessorResultModel process(InputParserModel inputParserModel);
}
