package com.spaceship.tools.baseChain;


/**
 * Represents a single node in a processing chain
 * Does a part of processing logic
 * Based upon chain of responsibility design pattern
 */
public interface ChainNode<M extends ChainModel, INPUT> {


    /**
     * Does processing logic of this node
     *
     * @param model model
     * @param input input data to the chain
     */
    void process(M model, INPUT input);

}
