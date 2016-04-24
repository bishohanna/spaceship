package com.spaceship;

import com.spaceship.app.inputParser.InputParser;
import com.spaceship.app.inputParser.InputParserFactory;
import com.spaceship.app.inputParser.InputParserModel;
import com.spaceship.app.inputProcessor.InputProcessor;
import com.spaceship.app.inputProcessor.InputProcessorFactory;
import com.spaceship.app.inputProcessor.ProcessorResultModel;
import com.spaceship.tools.logger.Logger;
import com.spaceship.tools.logger.LoggerFactory;

public final class Application {

    private final static String INPUT_FILE_PATH = "/default_input.txt";

    private Application() {
        //no construction
    }

    public static void main(String[] args) {

        Logger logger = LoggerFactory.get();

        //1 - Parse input from file
        logger.logLine("Parsing input file ..");
        InputParser inputParser = InputParserFactory.get();
        InputParserModel parseModel = inputParser.parseFile(Application.class.getResource(INPUT_FILE_PATH).getFile());

        //2- process parsed model and answer questions
        logger.logLine("Processing input lines ..");
        InputProcessor inputProcessor = InputProcessorFactory.get();
        ProcessorResultModel resultModel = inputProcessor.process(parseModel);

        //3- print output
        logger.logLine("Answers===================================================");
        resultModel.getQuestionsAndAnswersMap().values().stream().forEach(v -> logger.logLine(v));
    }
}
