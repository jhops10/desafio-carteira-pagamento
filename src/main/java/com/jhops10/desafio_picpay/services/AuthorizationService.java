package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.clients.TransferAuthorizationClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final TransferAuthorizationClient client;

    public AuthorizationService(TransferAuthorizationClient client) {
        this.client = client;
    }

    public boolean validateTransfer() {
        return client.isTransferAuthorized().data().authorization().equals("true");

    }



}
