package com.spaceship.tools.exception;

public final class SpaceShipException extends RuntimeException {


    public SpaceShipException(String message) {
        super(message);
    }

    public SpaceShipException(String message, Exception parent) {
        super(message, parent);
    }
}
