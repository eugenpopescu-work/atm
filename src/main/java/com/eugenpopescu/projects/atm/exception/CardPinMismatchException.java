package com.eugenpopescu.projects.atm.exception;

public class CardPinMismatchException  extends RuntimeException {

    public CardPinMismatchException() {
        super();
    }

    public CardPinMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CardPinMismatchException(final String message) {
        super(message);
    }

    public CardPinMismatchException(final Throwable cause) {
        super(cause);
    }
}
