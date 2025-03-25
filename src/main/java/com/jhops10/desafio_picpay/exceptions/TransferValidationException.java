package com.jhops10.desafio_picpay.exceptions;

public class TransferValidationException extends RuntimeException {
    public TransferValidationException(String message) {
        super(message);
    }
}
