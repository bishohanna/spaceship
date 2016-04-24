package com.spaceship.app.inputProcessor.romanCalculator;

import com.spaceship.tools.baseChain.ChainModel;

public class RomanResultModel implements ChainModel {

    private boolean valid;
    private String errorMessage;
    private float numericResult;


    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public float getNumericResult() {
        return numericResult;
    }

    public void setNumericResult(float numericResult) {
        this.numericResult = numericResult;
    }
}
