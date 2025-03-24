package com.jhops10.desafio_picpay.services;

import com.jhops10.desafio_picpay.dtos.TransferDTO;
import com.jhops10.desafio_picpay.entities.User;
import com.jhops10.desafio_picpay.entities.UserType;
import com.jhops10.desafio_picpay.exceptions.InsufficientBalanceException;
import com.jhops10.desafio_picpay.exceptions.MerchantTransferException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final UserService userService;

    public TransferService(UserService userService) {
        this.userService = userService;
    }

    public void transferAmount(TransferDTO transferDTO) {
        User payer = userService.findUserById(transferDTO.payer());
        User payee = userService.findUserById(transferDTO.payee());

        checkIfPayerIsMerchant(payer);
        checkPayerSufficientBalance(payer, transferDTO.value());

    }


    public void checkIfPayerIsMerchant(User user) {
        if (user.getUserType().equals(UserType.MERCHANT)) {
            throw new MerchantTransferException("Erro na Transferência! Lojistas não podem transferir.");
        }
    }

    public void checkPayerSufficientBalance(User user, BigDecimal value) {
        if (user.getWallet().getBalance().compareTo(value) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transferência.");
        }
    }
}
