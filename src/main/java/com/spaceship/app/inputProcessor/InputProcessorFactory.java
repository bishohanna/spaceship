package com.spaceship.app.inputProcessor;

public final class InputProcessorFactory {

    private static final InputProcessor PROCESSOR = new InputProcessorContext();

    private InputProcessorFactory() {
        //no construction
    }


    public static InputProcessor get() {
        return PROCESSOR;
    }
}
