package com.spaceship.app.inputProcessor.romanCalculator.nodes;

import com.spaceship.app.inputProcessor.romanCalculator.RomanResultModel;
import com.spaceship.tools.baseChain.ChainNode;

public class RomanValidatorNode implements ChainNode<RomanResultModel, String> {


    @Override
    public void process(RomanResultModel model, String romanString) {

        //check if all chars are roman
        if (ValidationUtils.checkAllCharsRoman(romanString)) {
            model.setValid(false);
            model.setErrorMessage("Not all characters are roman !");
            return;
        }

        //check for non repeated chars validity
        if (ValidationUtils.validateNotRepeatedChars(romanString)) {
            model.setValid(false);
            model.setErrorMessage("Characters like {D,L,V} can never be repeated !");
            return;
        }

        //validate repeated chars pattern
        if (!ValidationUtils.validateMaxThreeRepeatedChars(romanString)) {
            model.setValid(false);
            model.setErrorMessage(
                    "Characters like {I,X,C,M} can never appear more than three times, they can however appear four times if the separator between third and fourth is of lower value");
            return;
        }


        model.setValid(true);

    }


}
