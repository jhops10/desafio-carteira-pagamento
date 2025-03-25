package com.jhops10.desafio_picpay.clients;

import com.jhops10.desafio_picpay.dtos.AuthorizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://util.devi.tools/api/v2/authorize", name = "authorization")
public interface TransferAuthorizationClient {

    @GetMapping
    AuthorizationDTO isTransferAuthorized();
}
