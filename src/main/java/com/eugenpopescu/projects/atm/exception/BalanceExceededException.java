package com.eugenpopescu.projects.atm.exception;

public class BalanceExceededException extends RuntimeException{

    public BalanceExceededException() {
        super();
    }

    public BalanceExceededException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BalanceExceededException(final String message) {
        super(message);
    }

    public BalanceExceededException(final Throwable cause) {
        super(cause);
    }
}
