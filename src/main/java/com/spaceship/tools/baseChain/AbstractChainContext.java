package com.spaceship.tools.baseChain;

import com.spaceship.tools.exception.SpaceShipException;

import java.util.List;

public abstract class AbstractChainContext<M extends ChainModel, INPUT> {


    private final Class<M> modelType;
    private final List<ChainNode<M, INPUT>> chainNodes;

    public AbstractChainContext(Class<M> modelType) {
        this.modelType = modelType;
        this.chainNodes = getChainNodes();
    }

    /**
     * Handles an input and processes it sequentially through the defined chain nodes
     *
     * @return model for processing result
     */
    public final M handle(INPUT input) {

        try {

            //check for null input
            if (input == null) {
                throw new SpaceShipException(String.format("Input data can't be empty in context [%s]", this.getClass().getSimpleName()));
            }

            //2- create Model to process on
            M model = modelType.newInstance();

            //3- fire processing chain
            if (chainNodes != null && !chainNodes.isEmpty()) {

                chainNodes.stream().forEachOrdered(n -> n.process(model, input));

            } else {
                throw new SpaceShipException(String.format("Context [%s] has no Logic to be executed !", this.getClass().getSimpleName()));
            }

            //4- return result
            return model;

        } catch (Exception ex) {
            throw new SpaceShipException(String.format("Unknown error happened in context [%s] due to [%s]"
                    , this.getClass().getSimpleName()
                    , ex.getMessage()));
        }
    }


    /**
     * Get List of processing sequence for this context
     *
     * @return list of nodes to process sequentially
     */
    protected abstract List<ChainNode<M, INPUT>> getChainNodes();

}
