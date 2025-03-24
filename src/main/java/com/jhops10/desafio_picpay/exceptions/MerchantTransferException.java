package com.jhops10.desafio_picpay.exceptions;

public class MerchantTransferException extends RuntimeException {
    public MerchantTransferException(String message) {
        super(message);
    }
}
